package com.ipartek.formacion.repository;

import java.io.Serializable;
import java.util.List;

import javax.sql.DataSource;

import com.ipartek.formacion.domain.Product;

public interface InventarioDAO extends Serializable {

	void increasePrice(int percentage);

	List<Product> getProducts();

	Product getById(long id);

	boolean eliminar(long id);

	//boolean insertar(Product p);

	//boolean modificar(Product p);
	
	 public void saveProduct(Product product);
	 
	 public void updateProduct(Product product);

	void setDataSource(DataSource dataSource);
}
