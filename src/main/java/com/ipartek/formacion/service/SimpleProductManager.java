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
			this.inventarioDAOImpl.increasePrice(percentage);
		}

	}

	@Override
	public List<Product> getProducts() {
		this.products = this.inventarioDAOImpl.getProducts();
		return this.products;
	}

	@Override
	public Product getById(long id) {
		return this.inventarioDAOImpl.getById(id);
	}

	@Override
	public boolean eliminar(long id) {
		return this.inventarioDAOImpl.eliminar(id);
	}

	@Override
	public boolean insertar(Product p) {
		return this.inventarioDAOImpl.insertar(p);
	}

	@Override
	public boolean modificar(Product p) {
		return this.inventarioDAOImpl.modificar(p);
	}

}
