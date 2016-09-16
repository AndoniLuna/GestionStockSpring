package com.ipartek.formacion.domain;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Persona implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long id;
	
	@NotNull
	@Size(min = 3, max = 199)
	private String nombre;

	@Min(0)
	@Max(199)
	private int edad;

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getEdad() {
		return this.edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Persona [nombre=" + this.nombre + ", edad=" + this.edad + "]";
	}

}
