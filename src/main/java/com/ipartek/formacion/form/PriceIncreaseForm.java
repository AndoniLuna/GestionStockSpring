package com.ipartek.formacion.form;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PriceIncreaseForm {

	protected final Log logger = LogFactory.getLog(getClass());

	@Min(0)
	@Max(50)
	private int porcentaje;

	@AssertTrue
	private boolean confirmar;

	public int getPorcentaje() {
		return this.porcentaje;
	}

	public void setPorcentaje(int porcentaje) {
		this.porcentaje = porcentaje;
		this.logger.info("porcentaje incrementado a " + porcentaje);
	}

	public boolean isConfirmar() {
		return this.confirmar;
	}

	public void setConfirmar(boolean confirmar) {
		this.confirmar = confirmar;
	}
}
