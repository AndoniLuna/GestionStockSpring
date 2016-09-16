package com.ipartek.formacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ipartek.formacion.domain.Product;
import com.ipartek.formacion.repository.ProductDAO;

public class SimpleProductManager implements ProductManager {

	private static final long serialVersionUID = 1L;

	 @Autowired
	 private ProductDAO productDAO;

	@Override
	public void increasePrice(int percentage) throws IllegalArgumentException {

		 List<Product> products = productDAO.getProductList();
	        if (products != null) {
	            for (Product product : products) {
	                double newPrice = product.getPrice().doubleValue() * 
	                                    (100 + percentage)/100;
	                product.setPrice(newPrice);
	                productDAO.saveProduct(product);
	            }
	        }
	}

	@Override
	public List<Product> getProducts() {
		return this.productDAO.getProductList();
	}

	public void setProducts(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

}
