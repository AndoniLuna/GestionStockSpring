package com.ipartek.formacion.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

public class InventoryControllerTest {

	@Test
	public void test() {
		try {

			final InventoryController controller = new InventoryController();
			// controller.setProductManager(new SimpleProductManager());

			final ModelAndView mv = controller.listarInventario(null, null);

			assertEquals("Vista NO valida", "inventario", mv.getViewName());
			assertNotNull(mv.getModel().get("fecha"));

		} catch (final Exception e) {
			fail("No deberia lanzar Exception: " + e.getMessage());
		}
	}

}
