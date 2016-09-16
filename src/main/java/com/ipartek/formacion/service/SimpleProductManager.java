package com.ipartek.formacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.domain.Product;
import com.ipartek.formacion.repository.InventarioDAO;

@Service("productManager")
public class SimpleProductManager implements ProductManager {

	private static final long serialVersionUID = 1L;

	@Autowired
	private InventarioDAO inventarioDAOImpl;

	private List<Product> products;

	@Override
	public void increasePrice(int percentage) throws IllegalArgumentException {

		if (percentage < INCREASE_MIN || percentage > INCREASE_MAX) {
			throw new IllegalArgumentException(MSG_ILLEGALARGUMENT_EXCEPTION);

		} else {
			if (this.products != null) {				
				for (final Product product : this.products) {
					if (product != null) {
						final long id = product.getId();
						final double newPrice = product.getPrice().doubleValue() * (100 + percentage) / 100;
						this.inventarioDAOImpl.increasePrice(id, newPrice);
					}
				}
			}
		}

	}

	@Override
	public List<Product> getProducts() {
		this.products = this.inventarioDAOImpl.getProducts();
		return this.products;
	}

}
