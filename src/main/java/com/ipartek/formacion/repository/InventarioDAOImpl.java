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
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.ipartek.formacion.domain.Product;
import com.ipartek.formacion.repository.mapper.ProductMapper;

@Repository("inventarioDAOImpl")
public class InventarioDAOImpl implements InventarioDAO {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(InventarioDAOImpl.class);

	private JdbcTemplate jdbctemplate;
	
	private SimpleJdbcCall simpleJdbcCall;

	@Autowired
	private DataSource datasource;
	
	@Autowired
	@Override
	public void setDataSource(DataSource dataSource) {
		this.datasource = dataSource;
		this.jdbctemplate = new JdbcTemplate(this.datasource);
	}

	@Override
	public void increasePrice(long id, double newPrice) {
		// TODO CallableStatement con procedimiento almacenado en BBDD
		final String SQL = "UPDATE products SET price = ? WHERE id = ?;";
		this.jdbctemplate.update(SQL, newPrice, id);
	}

	@Override
	public List<Product> getProducts() {
		ArrayList<Product> lista = new ArrayList<Product>();
		final String SQL = "SELECT id, description, price FROM products;";

		try {
			lista = (ArrayList<Product>) this.jdbctemplate.query(SQL, new ProductMapper());

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
		final String SQL = "SELECT id, description, price FROM products WHERE id=" + id;

		try {
			p = this.jdbctemplate.queryForObject(SQL, new ProductMapper());

		} catch (EmptyResultDataAccessException e) {
			logger.warn("No existe el producto con id " + id);
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
		final String SQL = "DELETE FROM `products` WHERE  `id`=" + id;
		
		if ( 1 == this.jdbctemplate.update(SQL)){
			resul=true;
		}
		return resul;
	}

	@Override
	public boolean insertar(final Product p) {
		boolean resul = false;
		int affectedRows = -1;
		
		final KeyHolder keyHolder = new GeneratedKeyHolder();
		final String SQL = "INSERT INTO `products` (`description`, `price`) VALUES (?,?)";
		affectedRows = this.jdbctemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				final PreparedStatement ps = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, p.getDescription());
				ps.setDouble(2, p.getPrice());
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
		final String SQL = "UPDATE `products` SET `description`=?, `price`=? WHERE  `id`=?;";
		final Object[] args = { p.getDescription(), p.getPrice(), p.getId() };
		
		if ( 1 == this.jdbctemplate.update(SQL, args)){
			resul=true;
		}
		return resul;
	}

//	@Override
//	public boolean modificar(Product p) {
//		boolean resul = false;
//		final String SQL = "UPDATE `products` SET `description`=?, `price`=? WHERE  `id`=?;";
//		final Object[] args = { p.getDescription(), p.getPrice(), p.getId() };
//		
//		if ( 1 == this.jdbctemplate.update(SQL, args)){
//			resul=true;
//		}
//		return resul;
//	}

//	@Override
//	public boolean modificar(Product p) {
//		boolean resul = false;
//		
//		final Map<String, Object> args = new HashMap<String, Object>();	
//		args.put("pDescription", p.getDescription());
//		args.put("pPrice", p.getPrice());
//		args.put("pId", p.getId());
//		
//		MapSqlParameterSource in = new MapSqlParameterSource();
//		in.addValue("pDescription", p.getDescription());
//		in.addValue("pPrice", p.getPrice());
//		in.addValue("pId", p.getId());
//		
//		if (this.simpleJdbcCall.executeFunction(boolean.class, in)){
//			resul=true;
//		}
//		return resul;
//	}

}
