package com.ipartek.formacion.service;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.domain.Product;
import com.ipartek.formacion.repository.InventarioDAO;


@Service("productManager")
public class SimpleProductManager implements ProductManager {
	
	@Autowired
	private InventarioDAO inventarioDAOImpl;

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
						final long id = product.getId();
						final double newPrice = product.getPrice().doubleValue() * (100 + percentage) / 100;
						inventarioDAOImpl.increasePrice(id, newPrice);
						
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

	@Override
	public Product getById(long id) {
		return this.inventarioDAOImpl.getById(id);
	}

	@Override
	public boolean eliminar(long id) {
		return this.inventarioDAOImpl.eliminar(id);
	}

	@Override
	public boolean insertar(Product producto) {
		return this.inventarioDAOImpl.insertar(producto);
	}

	@Override
	public boolean modificar(Product producto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setDataSource(DataSource dataSource) {
		// TODO Auto-generated method stub
		
	}


}
