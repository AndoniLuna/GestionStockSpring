package com.ipartek.formacion.repository;

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
import com.mysql.jdbc.PreparedStatement;

@Repository("inventarioDAOImp")
public class InventarioDAOImp implements InventarioDAO {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(InventarioDAOImp.class);

	@Autowired
	private DataSource dataSource = null;
	private JdbcTemplate jdbctemplate = null;

	@Autowired
	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbctemplate = new JdbcTemplate(this.dataSource);

	}

	@Override
	public void increasePrice(int percentage) {
		// TODO CallableStatement con procedimiento almacenado en BBDD

	}

	@Override
	public List<Product> getProducts() {

		ArrayList<Product> lista = new ArrayList<Product>();
		final String SQL = "SELECT id, description, price FROM  products;";

		try {
			lista = (ArrayList<Product>) this.jdbctemplate.query(SQL, new ProductMapper());
		} catch (final EmptyResultDataAccessException e) {
			logger.warn("NO existen productos todavia" + SQL);
		} catch (final Exception e) {

		}
		return lista;
	}

	// ***********************************
	// * insertar nuevo producto en BBDD *
	// ***********************************
	/*
	 * @Override public boolean insert(Product p) {
	 * 
	 * if (-1 == p.getId()) {
	 * 
	 * final KeyHolder keyHolder = new GeneratedKeyHolder(); final String sql =
	 * "INSERT INTO `products` (`description`, `price`) VALUES ( ? , ? );";
	 * 
	 * affectedRows = this.jdbcTemplateObject.update(new
	 * PreparedStatementCreator() {
	 * 
	 * @Override public PreparedStatement createPreparedStatement(Connection
	 * conn) throws SQLException { final PreparedStatement ps =
	 * conn.prepareStatement(sqlInsert); ps.setString(1, p.getDescription());
	 * ps.setString(2, p.getPrice());
	 * 
	 * return ps; } }, keyHolder);
	 * 
	 * p.setId(keyHolder.getKey().longValue());
	 * 
	 * return false; } }
	 */

	@Override
	public Product getById(long id) {
		Product p = null;
		// TODO cambiar por PreparedStatement
		final String SQL = "SELECT id, description, price FROM products WHERE id=" + id;

		try {
			p = this.jdbctemplate.queryForObject(SQL, new ProductMapper());

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
		// TODO preparedStatement
		final String SQL = "DELETE FROM `products` WHERE  `id`=" + id;

		if (1 == this.jdbctemplate.update(SQL)) {
			resul = true;
		}

		return resul;
	}

	@Override
	public boolean insertar(Product p) {
		boolean result = false;
		int affectedRows = -1;

		// comprobar el ultimo id de la BBDD

		if (0 == p.getId()) {

			final KeyHolder keyHolder = new GeneratedKeyHolder();
			final String sqlInsert = "INSERT INTO `productos` ( `description`, `price`) VALUES ( ? , ? );";
			affectedRows = this.jdbcTemplateObject.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					final PreparedStatement ps = conn.prepareStatement(sqlInsert);
					ps.setString(1, p.getDescription());
					ps.setString(2, p.getPrice());

					return ps;
				}
			}, keyHolder);

			p.setId(keyHolder.getKey().longValue());
		}
		return true;
	}

	@Override
	public boolean modificar(Product p) {
		// TODO Auto-generated method stub
		return false;
	}

}
