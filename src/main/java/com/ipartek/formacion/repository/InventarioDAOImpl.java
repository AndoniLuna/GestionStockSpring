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

@Repository("inventarioDAOImpl")
public class InventarioDAOImpl implements InventarioDAO {

	/**
	 *
	 */
	private static final long serialVersionUID = 8748962669348646657L;
	private static final Logger logger = LoggerFactory.getLogger(InventarioDAOImpl.class);
	@Autowired
	private DataSource dataSource = null;
	private JdbcTemplate jdbctemplate;

	@Autowired
	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbctemplate = new JdbcTemplate(dataSource);

	}

	@Override
	public void increasePrice(int percentage) {
		// TODO CallableStatement con procedimiento almacenado en bd

	}

	@Override
	public List<Product> getProducts() {
		ArrayList<Product> lista = new ArrayList<Product>();
		final String SQL = "Select id,description,price FROM products;";
		try {
			lista = (ArrayList<Product>) this.jdbctemplate.query(SQL, new ProductMapper());
		} catch (EmptyResultDataAccessException e) {
			logger.warn("No existen productos todavia" + SQL);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return lista;
	}

	@Override
	public Product getById(long id) {
		Product p = null;
		final String SQL = "Select id,description,price FROM products Where id=" + id;
		try {
			p = (Product) this.jdbctemplate.queryForObject(SQL, new ProductMapper());
		} catch (EmptyResultDataAccessException e) {
			logger.warn("No existen producto con id=" + id);
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

	// @Override
	// public boolean insertar(final Product product) {
	// boolean resul = false;
	// int affectedRows = -1;
	//
	// // insert
	// if (-1 == product.getId()) {
	//
	// final KeyHolder keyHolder = new GeneratedKeyHolder();
	// final String sqlInsert = "INSERT INTO `products` ( `id`, `description`,
	// `price`) VALUES ( ? , ? , ? );";
	// affectedRows = this.jdbctemplate.update(new PreparedStatementCreator() {
	// @Override
	// public PreparedStatement createPreparedStatement(Connection conn) throws
	// SQLException {
	// final PreparedStatement ps = conn.prepareStatement(sqlInsert);
	// ps.setLong(1, product.getId());
	// ps.setString(2, product.getDescription());
	// ps.setDouble(3, product.getPrice());
	// return ps;
	// }
	//
	// }, keyHolder);
	//
	// product.setId(keyHolder.getKey().longValue());
	//
	// // modificar
	// } else {
	// final String sqlInsert = "UPDATE `products` SET `id`=?, `description`=?,
	// `price`=? WHERE `id`= ?;";
	// final Object[] args = { product.getId(), product.getDescription(),
	// product.getPrice(), product.getId() };
	// affectedRows = this.jdbctemplate.update(sqlInsert, args);
	// }
	//
	// if (affectedRows == 1) {
	// resul = true;
	// }
	// return resul;
	// }
	//
	// @Override
	// public boolean modificar(Product p) {
	// // TODO Auto-generated method stub
	// return false;
	// }
	public void saveProduct(Product product) {

		String sql = "INSERT INTO products (description, price)" + " VALUES (?, ?)";
		jdbctemplate.update(sql, product.getDescription(), product.getPrice());

	}

	@Override
	public void updateProduct(Product product) {
		if (product.getId() > 0) {
			// update
			String sql = "UPDATE products SET description=?, price=? WHERE id=?";
			jdbctemplate.update(sql, product.getDescription(), product.getPrice(), product.getId());

		}

	}
}
