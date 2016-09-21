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

	private final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private DataSource dataSource = null;

	private JdbcTemplate jdbctemplate = null;

	// para hacer el callablestatement desde jdbctemplates de spring
	private SimpleJdbcCall jdbcCall;

	@Autowired
	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbctemplate = new JdbcTemplate(this.dataSource);
		this.jdbcCall = new SimpleJdbcCall(this.dataSource);
	}

	/**
	 * incrementa los valores de todos los productos de la BBDD un porcentaje
	 * que le pasamos como parametro hacemos una llamada a la rutina almacenada
	 * en la BBDD 'incrementar_precio' para hacer la operacion
	 */
	@Override
	public void increasePrice(int percentage) {

		this.logger.trace("llamando Rutina almacenada 'incrementar_precio'");
		jdbcCall.withProcedureName("incrementar_precio");

		SqlParameterSource parameterIn = new MapSqlParameterSource().addValue("porcentaje", percentage);
		this.jdbcCall.execute(parameterIn);
		/*
		 * si tuvieramos parametros de salida 'out' Map<String, Object> out =
		 * jdbcCall.execute(in); out.get("nombre_parametro_salida");
		 */
		this.logger.info("Incrementado todos los productos un " + percentage + " %");

	}

	/**
	 * devuelve una lista con todos los productos de la BBDD
	 */
	@Override
	public List<Product> getProducts() {
		ArrayList<Product> lista = new ArrayList<Product>();
		final String SQL = "SELECT id,description,price FROM products;";

		try {
			lista = (ArrayList<Product>) jdbctemplate.query(SQL, new ProductMapper());
		} catch (EmptyResultDataAccessException e) {
			this.logger.warn("No existen productos todavia " + SQL);
		} catch (Exception e) {
			this.logger.error(e.getMessage());
		}

		return lista;
	}

	/**
	 * devuelve un producto de la BBDD, si existe
	 */
	@Override
	public Product getById(long id) {
		Product p = null;
		// TODO cambiar por PreparedStatement
		final String SQL = "SELECT id,description,price FROM products WHERE id=" + id;
		try {
			p = (Product) jdbctemplate.queryForObject(SQL, new ProductMapper());
		} catch (EmptyResultDataAccessException e) {
			this.logger.warn("No existen productos con ID=" + id);
			p = null;
		} catch (Exception e) {
			this.logger.error(e.getMessage());
			p = null;
		}
		return p;
	}

	/**
	 * Elimina un producto de la BBDD con PreparedStatement
	 */
	@Override
	public boolean eliminar(final long id) {
		boolean resul = false;
		final String SQL = "DELETE FROM `products` WHERE  `id`=?";

		if (1 == this.jdbctemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				final PreparedStatement ps = conn.prepareStatement(SQL);
				ps.setLong(1, id);
				return ps;
			}
		})) {
			resul = true;
		}
		return resul;
	}

	/**
	 * inserta un producto nuevo en la BBDD
	 */
	@Override
	public boolean insertar(final Product p) {
		final KeyHolder keyHolder = new GeneratedKeyHolder();
		boolean resul = false;
		// con preparedStatement o CallableStatement

		final String SQL = "INSERT products (`description`, `price`) VALUES ( ?,?)";

		if (1 == this.jdbctemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				final PreparedStatement ps = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, p.getDescription());
				ps.setDouble(2, p.getPrice());
				return ps;
			}
		}, keyHolder)) {
			resul = true;
		}
		p.setId(keyHolder.getKey().longValue());
		return resul;

	}

	/**
	 * modifica un producto existente en la BBDD
	 */
	@Override
	public boolean modificar(final Product p) {
		boolean resul = false;
		// con preparedStatement o CallableStatement

		final String SQL = "UPDATE `products` SET `description` = ?, `price` = ? WHERE `id`=?";

		if (1 == this.jdbctemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				final PreparedStatement ps = conn.prepareStatement(SQL);
				ps.setString(1, p.getDescription());
				ps.setDouble(2, p.getPrice());
				ps.setLong(3, p.getId());
				return ps;
			}
		})) {
			resul = true;
		}
		return resul;

		/*
		 * Otra forma de hacerlo pero menos ooptima final String SQL =
		 * "UPDATE `products` SET `description` = ?, `price` = ? WHERE `id`=?";
		 * Object[]arguments = { p.getDescription(), p.getPrice(), p.getID() };
		 * int affectedRows = this.jdbctemplate.update(SQL,arguments); return
		 * (1==affectedRows) ? true : false;
		 */
	}

}
