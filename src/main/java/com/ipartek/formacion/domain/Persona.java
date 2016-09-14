package com.ipartek.formacion.domain;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class Persona implements Serializable {

	private static final long serialVersionUID = 1L;

	@Min(3)
	@Max(255)
	private String nombre;
	@Min(0)
	@Max(199)
	private int edad;
	

	

	public int getEdad() {
		return edad;
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
		return "Persona [nombre=" + this.nombre + "]";
	}

}
