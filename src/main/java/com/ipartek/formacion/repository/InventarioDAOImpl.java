package com.ipartek.formacion.repository;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ipartek.formacion.domain.Product;
import com.ipartek.formacion.repository.mapper.ProductMapper;

@Repository("inventoryDAOImpl")
public class InventarioDAOImpl implements InventarioDAO {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(InventarioDAOImpl.class);

	@Autowired
	private DataSource dataSource;

	@Autowired
	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbctemplate = new JdbcTemplate(dataSource);
	}

	private JdbcTemplate jdbctemplate;

	@Override
	public void increasePrice(double newPrice, long id) {
		final String SQL = "UPDATE products SET price = ? where id = ?";
		this.jdbctemplate.update(SQL, newPrice, id);
	}

	@Override
	public List<Product> getProducts() {

		ArrayList<Product> lista = new ArrayList<Product>();
		final String SQL = "SELECT id,description,price FROM products;";

		try {
			lista = (ArrayList<Product>) this.jdbctemplate.query(SQL, new ProductMapper());
		} catch (EmptyResultDataAccessException e) {
			logger.warn("No existen productos todavia " + SQL);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return lista;
	}

}
