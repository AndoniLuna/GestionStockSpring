package com.ipartek.formacion.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.ipartek.formacion.domain.Product;
import com.ipartek.formacion.repository.mapper.ProductMapper;

@Repository("inventarioDAOImpl")
public class InventarioDAOImpl implements InventarioDAO {

	private static final long serialVersionUID = 1L;
	private final Log logger = LogFactory.getLog(this.getClass());

	private JdbcTemplate jdbctemplate;	
	private SimpleJdbcCall jdbcCall;

	@Autowired
	private DataSource datasource;
	
	@Autowired
	@Override
	public void setDataSource(DataSource dataSource) {
		this.datasource = dataSource;
		this.jdbctemplate = new JdbcTemplate(this.datasource);
		this.jdbcCall = new SimpleJdbcCall(this.datasource);
	}

	@Override
	public void increasePrice(int percentage) {
		
		this.logger.trace("Llamando rutina almacenada 'incrementar_precio'");
		this.jdbcCall.withProcedureName("incrementar_precio");
		
		SqlParameterSource parameterIn =
				new MapSqlParameterSource().addValue("porcentaje", percentage);
		
		this.jdbcCall.execute(parameterIn);
				
		/*
		 * Si tuvieramos parametros de salida 'out':
		 *	Map<String, Object> out = jdbcCall.execute(parameterIn);
		 *	out.get("nombre_parametro_salida");
		 *
		 */
		
		this.logger.info("Incrementado todos los Productos un " + percentage + "%");
	}

	@Override
	public List<Product> getProducts() {
		ArrayList<Product> lista = new ArrayList<Product>();
		final String SQL = "SELECT id, description, price FROM products;";

		try {
			lista = (ArrayList<Product>) this.jdbctemplate.query(SQL, new ProductMapper());

		} catch (EmptyResultDataAccessException e) {
			this.logger.warn("No existen productos todavia " + SQL);
		} catch (Exception e) {
			this.logger.error(e.getMessage());
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
			this.logger.warn("No existe el producto con id " + id);
			p = null;
		} catch (Exception e) {
			this.logger.error(e.getMessage());
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

	@Override
	public boolean modificar(Product p) {
		boolean resul = true;
		
		this.jdbcCall.withProcedureName("modificarProducto");
		
		SqlParameterSource parameterIn =
				new MapSqlParameterSource().addValue("pDescription", p.getDescription()).addValue("pPrice", p.getPrice()).addValue("pId", p.getId());
		
		this.jdbcCall.execute(parameterIn);
		
//		if ( 1 == this.jdbctemplate.update(SQL, args)){
//			resul=true;
//		}
		return resul;
	}

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
