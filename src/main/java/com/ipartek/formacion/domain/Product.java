package com.ipartek.formacion.domain;

import java.io.Serializable;

public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id = -1;
	private String description;
	private Double price;

	public long getId() {
		return this.id;
	}

	public void setId(long l) {
		this.id = l;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [description=" + this.description + ", price=" + this.price + "]";
	}

}
