package com.ipartek.formacion.service;

import java.io.Serializable;
import java.util.List;

import com.ipartek.formacion.domain.Product;

public interface ProductManager extends Serializable {

	int INCREASE_MIN = 0;
	int INCREASE_MAX = 50;

	String MSG_ILLEGALARGUMENT_EXCEPTION = "El incremento debe ser entre " + INCREASE_MIN + " y " + INCREASE_MAX;

	/**
	 * Incrementa el precio de todos los productos
	 *
	 * @param percentage
	 *            {@code int} porcentaje a incrementar maximo 50, minimo 0
	 * @return 
	 * @throws IllegalArgumentException
	 */
	public Object increasePrice(int increase) throws IllegalArgumentException;

	public List<Product> getProducts();

	Product getById(long id);

	boolean eliminar(long id);

	boolean insertar(Product p);

	boolean modificar(Product p);

}
