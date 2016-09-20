package com.ipartek.formacion.domain;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Product implements Serializable {

	/**
	 * 
	 */
	public Product() {
		super();
		this.id = (long) -1;
		this.description = "";
		this.price = (double) 0;
	}

	private static final long serialVersionUID = 1L;

	@NotNull
	private Long id;

	@NotNull
	@Size(min = 3, max = 255)
	private String description;

	@Min(0)
	private Double price;

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

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Product [description=" + this.description + ", price=" + this.price + "]";
	}

}
