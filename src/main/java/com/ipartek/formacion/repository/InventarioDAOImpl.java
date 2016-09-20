package com.ipartek.formacion.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
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


@Repository("inventarioDAOImpl")
public class InventarioDAOImpl implements InventarioDAO {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(InventarioDAO.class);

	private ArrayList<Product> lista;

	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbctemplate;

	@Autowired
	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbctemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void increasePrice(long id, double newPrice) {

	}

	@Override
	public List<Product> getProducts() {
		lista = new ArrayList<Product>();
		final String SQL = "SELECT id, description, price FROM products;";
		try {
			lista = (ArrayList<Product>) jdbctemplate.query(SQL, new ProductMapper());
		} catch (EmptyResultDataAccessException e) {
			logger.warn("No existen productos todavia" + SQL);
		} catch (Exception e) {
			logger.error(e.getMessage().toString());
		}
		return lista;
	}

	@Override
	public Product getById(long id) {
		Product product = null;
		final String SQL = "SELECT id, description, price FROM products where id=" + id;
		try {
			product = (Product) jdbctemplate.queryForObject(SQL, new ProductMapper());
		} catch (EmptyResultDataAccessException e) {
			logger.warn("No existen producto con id= " + id);
			product = null;
		} catch (Exception e) {
			logger.error(e.getMessage().toString());
			product = null;
		}
		return product;
	}

	@Override
	public boolean eliminar(long id) {
		boolean result = false;
		final String SQL = "DELETE FROM `products` WHERE  `id`=" + id;
		if (1 == this.jdbctemplate.update(SQL)) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean guardar(final Product producto) {
		boolean result = false;
		int affectedRows = -1;
		if (-1 == producto.getId()) {
			final KeyHolder keyHolder = new GeneratedKeyHolder();
			final String sqlInsert = "INSERT INTO `products` ( `description`, `price`) VALUES (?,?);";
			affectedRows = this.jdbctemplate.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					final PreparedStatement ps = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, producto.getDescription());
					ps.setDouble(2, producto.getPrice());
					return ps;
				}
			}, keyHolder);
			producto.setId(keyHolder.getKey().longValue());
			if(affectedRows == 1){
				result = true;
			}
			this.logger.trace("CREAR  -> CONTROLLER GUARDAR");

		} else {
			final String sqlInsert = "UPDATE  `products` SET  `description`=? , `price`=? WHERE id=" + producto.getId();
			affectedRows = this.jdbctemplate.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					final PreparedStatement ps = conn.prepareStatement(sqlInsert);
					ps.setString(1, producto.getDescription());
					ps.setDouble(2, producto.getPrice());
					return ps;
				}
			});
			this.logger.trace("MODIFICAR  -> CONTROLLER GUARDAR");
		}
		return result;
	}

}
