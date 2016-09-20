package com.ipartek.formacion.repository;

import java.io.Serializable;
import java.util.List;

import javax.sql.DataSource;

import com.ipartek.formacion.domain.Product;

public interface InventarioDAO extends Serializable{
	
	
	void increasePrice(long id,double percentage);
	
	List<Product> getProducts();
	
	Product getById(long id);
	
	boolean eliminar(long id);
	
	boolean insertar(Product producto);
	
	boolean modificar(Product producto);
	
	void setDataSource(DataSource dataSource);
	

}
