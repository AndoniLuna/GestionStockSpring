package com.ipartek.formacion.repository;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.ipartek.formacion.domain.Product;

public interface InventarioDAO extends Serializable {

	void increasePrice(int percentage) throws SQLException;

	List<Product> getProducts();

	Product getById(long id);

	boolean eliminar(long id);

	boolean insertar(Product p);

	boolean modificar(Product p);

	void setDataSource(DataSource dataSource);
}
