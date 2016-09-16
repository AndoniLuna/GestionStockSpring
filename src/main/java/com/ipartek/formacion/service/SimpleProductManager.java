package com.ipartek.formacion.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ipartek.formacion.domain.Product;



@Component
public class SimpleProductManager implements ProductManager {

	private static final long serialVersionUID = 1L;

	private List<Product> products;
	
	
	
	@Override
	public void increasePrice(int percentage) throws IllegalArgumentException {

		if (percentage < INCREASE_MIN || percentage > INCREASE_MAX) {
			throw new IllegalArgumentException(MSG_ILLEGALARGUMENT_EXCEPTION);

		} else {
			if (this.products != null) {
				for (final Product product : this.products) {
					if (product != null) {
						final double newPrice = product.getPrice().doubleValue() * (100 + percentage) / 100;
						product.setPrice(newPrice);
					}
				}
			}
		}

	}

	@Override
	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
