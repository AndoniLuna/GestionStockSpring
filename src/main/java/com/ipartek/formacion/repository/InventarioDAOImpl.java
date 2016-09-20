package com.ipartek.formacion.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.ipartek.formacion.domain.Product;
import com.ipartek.formacion.repository.mapper.ProductMapper;

@Repository("inventoryDAOImpl")
public class InventarioDAOImpl implements InventarioDAO {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(InventarioDAOImpl.class);

	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	@Autowired
	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void increasePrice(double newPrice, long id) {
		final String SQL = "UPDATE products SET price = ? where id = ?";
		this.jdbcTemplate.update(SQL, newPrice, id);
		// CallableStatement con procedimiento almacenado en BBDD
	}

	@Override
	public List<Product> getProducts() {

		ArrayList<Product> lista = new ArrayList<Product>();
		final String SQL = "SELECT id,description,price FROM products;";

		try {
			lista = (ArrayList<Product>) this.jdbcTemplate.query(SQL, new ProductMapper());
		} catch (EmptyResultDataAccessException e) {
			logger.warn("No existen productos todavia " + SQL);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return lista;
	}

	@Override
	public Product getById(long id) {
		Product p = null;
		// TODO cambiar por PreparedStatement
		final String SQL = "SELECT id,description,price FROM products where id=" + id;

		try {
			p = this.jdbcTemplate.queryForObject(SQL, new ProductMapper());

		} catch (EmptyResultDataAccessException e) {
			logger.warn("No existen productos con ID=" + id);
			p = null;
		} catch (Exception e) {
			logger.error(e.getMessage());
			p = null;
		}
		return p;
	}

	@Override
	public boolean eliminar(long id) {
		boolean resul = false;
		// TODO preparedStatement o CallableStatement
		final String SQL = "DELETE FROM `products` WHERE  `id`=" + id;

		if (1 == this.jdbcTemplate.update(SQL)) {
			resul = true;
		}

		return resul;
	}

	@Override
	public boolean insertar(final Product p) {
		boolean resul = false;
		int affectedRows = -1;

		final KeyHolder keyHolder = new GeneratedKeyHolder();
		final String sqlInsert = "INSERT INTO `products` ( `id`, `description`, `price`) VALUES ( ? , ? , ? );";
		affectedRows = this.jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				final PreparedStatement ps = conn.prepareStatement(sqlInsert);
				ps.setLong(1, p.getId());
				ps.setString(2, p.getDescription());
				ps.setDouble(3, p.getPrice());
				return ps;
			}
		}, keyHolder);

		p.setId(keyHolder.getKey().longValue());

		if (affectedRows == 1) {
			resul = true;
		}
		return resul;
	}

	@Override
	public boolean modificar(Product p) {
		boolean resul = false;
		int affectedRows = -1;

		final String sqlInsert = "UPDATE `products` SET `description`=?, `price`=? WHERE  `id`= ?;";
		final Object[] args = { p.getDescription(), p.getPrice(), p.getId() };
		affectedRows = this.jdbcTemplate.update(sqlInsert, args);

		if (affectedRows == 1) {
			resul = true;
		}
		return resul;
	}

}
