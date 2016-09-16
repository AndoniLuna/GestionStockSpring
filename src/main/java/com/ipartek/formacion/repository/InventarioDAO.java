package com.ipartek.formacion.repository;

import java.io.Serializable;
import java.util.List;

import javax.sql.DataSource;

import com.ipartek.formacion.domain.Product;

public interface InventarioDAO extends Serializable {

	void increasePrice(int percentage);

	List<Product> getProducts();

	boolean insert(Product p);

	void setDataSource(DataSource dataSource);

}
