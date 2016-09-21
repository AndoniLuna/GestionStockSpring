package com.ipartek.formacion.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ipartek.formacion.domain.Product;
import com.ipartek.formacion.service.ProductManager;

@Controller
public class InventoryController {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private ProductManager productManager;

	/**
	 *
	 * Mostrar Listado de todos los productos de Inventario
	 *
	 * @param request
	 * @param response
	 * @return ModelAndView view: "inventario.jsp", model:{ArrayList
	 *         <Product> "products", String "fecha"}
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/inventario", method = RequestMethod.GET)
	public ModelAndView listarInventario() throws ServletException, IOException {

		this.logger.info("procesando peticion");

		// atributos == modelo
		final Map<String, Object> model = new HashMap<String, Object>();
		model.put("products", this.productManager.getProducts());
		model.put("fecha", new Date().toString());

		return new ModelAndView("product/inventario", model);
	}

	/**
	 * Muestra formulario para crear nuevo Producto
	 *
	 * @return
	 */
	@RequestMapping(value = "/inventario/nuevo", method = RequestMethod.GET)
	public ModelAndView mostrarFormulario() {
		this.logger.trace("Mostrar formulario crear nuevo producto");

		final Map<String, Object> model = new HashMap<String, Object>();
		model.put("product", new Product());
		return new ModelAndView("product/form", model);
	}

	/**
	 * Alta/Modificacion del producto
	 *
	 * @param product
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping(value = "/inventario/nuevo", method = RequestMethod.POST)
	public ModelAndView crear(@Valid Product product, BindingResult bindingResult) {

		Map<String, Object> model = new HashMap<String, Object>();

		if (product.getId() != -1) {
			this.logger.trace("Modificando producto....");
			String msg = "No se ha podido modificar el producto [" + product + "]";
			if (bindingResult.hasErrors()) {
				this.logger.warn("parametros no validos");
				return new ModelAndView("product/form", model);
			} else {
				if (this.productManager.modificar(product)) {
					msg = "El producto [" + product + "] ha sido modificado";
					this.logger.info(msg);
				} else {
					this.logger.warn(msg);
				}
				model.put("products", this.productManager.getProducts());
				model.put("fecha", new Date().toString());
				return new ModelAndView("product/inventario", model);
			}

		} else {
			this.logger.trace("Creando producto....");
			String msg = "No se ha podido dar de alta al producto [" + product + "]";
			if (bindingResult.hasErrors()) {
				this.logger.warn("parametros no validos");
				return new ModelAndView("product/form", model);
			} else {
				if (this.productManager.insertar(product)) {
					msg = "producto [" + product + "] dado de alta";
					this.logger.info(msg);
				} else {
					this.logger.warn(msg);
				}
				model.put("products", this.productManager.getProducts());
				model.put("fecha", new Date().toString());
				return new ModelAndView("product/inventario", model);
			}
		}
	}

	/**
	 * Muestre el detalle del producto
	 *
	 * @return
	 */
	@RequestMapping(value = "/inventario/detalle/{id}", method = RequestMethod.GET)
	public ModelAndView mostrarDetalle(@PathVariable(value = "id") final long id) {
		this.logger.trace("Mostrar detalle del producto");

		final Map<String, Object> model = new HashMap<String, Object>();

		model.put("product", this.productManager.getById(id));

		return new ModelAndView("product/form", model);
	}

	/**
	 * Borrar el producto
	 *
	 * @return
	 */
	@RequestMapping(value = "/inventario/borrar/{id}", method = RequestMethod.GET)
	public ModelAndView borrarProducto(@PathVariable(value = "id") final long id) {
		this.logger.trace("Borrar el producto");

		final Map<String, Object> model = new HashMap<String, Object>();
		String msg = "No se ha podido eliminar el producto[" + id + "]";
		if (this.productManager.eliminar(id)) {
			msg = "producto[" + id + "] eliminado";
			this.logger.info(msg);
		} else {
			this.logger.warn(msg);
		}
		model.put("msg", "Eliminando producto [" + id + "]...");
		// Recargo los productos y la fecha
		model.put("products", this.productManager.getProducts());
		model.put("fecha", new Date().toString());

		return new ModelAndView("product/inventario", model);
	}
}
