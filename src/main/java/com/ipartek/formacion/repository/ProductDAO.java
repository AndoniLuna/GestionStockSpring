package com.ipartek.formacion.repository;

import java.util.List;

import com.ipartek.formacion.domain.Product;

public interface ProductDAO {

	 public List<Product> getProductList();

	 public void saveProduct(Product prod);
}
