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
	 * Mostrar todos los productos del inventario
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView view: "inventario.jsp", model: {ArrayList &lt;
	 *         Product &gt; "products", String "fecha"}
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
	 * Muestra formulario para crear nuevo producto
	 * 
	 * @return
	 */
	@RequestMapping(value = "/inventario/nuevo", method = RequestMethod.GET)
	public ModelAndView mostrarFormulario() {
		this.logger.trace("Mostrar formulario crear nuevo producto");

		final Map<String, Object> model = new HashMap<String, Object>();
		model.put("product", new Product());
		model.put("isNew", true);

		return new ModelAndView("product/form", model);
	}

	/**
	 * Crea producto nuevo o lo modifica.
	 * 
	 * @param producto
	 * @param bindingResult
	 * @return nos devuelve a la lista
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/inventario/save", method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Product producto, BindingResult bindingResult)
			throws ServletException, IOException {
		this.logger.trace("Salvando producto...");

		final Map<String, Object> model = new HashMap<String, Object>();
		if (bindingResult.hasErrors()) {
			this.logger.trace("Parametros no validos");
			model.put("isNew", producto.isNew());
			model.put("product", producto);

			// crear producto nuevo
		} else if (producto.isNew()) {

			String msg = "Producto [" + producto.getId() + "] no insertado";
			if (this.productManager.insertar(producto)) {
				msg = "Producto[" + producto.getId() + "] insertado";
				this.logger.info(msg);
			} else {
				this.logger.warn(msg);
			}
			model.put("msg", msg);
			model.put("product", producto);

			// modificar producto existente
		} else {

			String msg = "Producto [" + producto.getId() + "] no modificado";
			if (this.productManager.modificar(producto)) {
				msg = "Producto[" + producto.getId() + "] modificado";
				this.logger.info(msg);
			} else {
				this.logger.warn(msg);
			}
			model.put("msg", msg);

		}
		model.put("products", this.productManager.getProducts());
		model.put("fecha", new Date().toString());
		model.put("producto", producto);
		return new ModelAndView("product/inventario", model);
	}

	/**
	 * 
	 * @param id
	 * @return ModelAndView con los parametros del producto existente en la BBDD
	 *         y la vista del form.jsp
	 */
	@RequestMapping(value = "/inventario/detalle/{id}", method = RequestMethod.GET)
	public ModelAndView verDetalle(@PathVariable(value = "id") final long id) {
		this.logger.trace("Mostrando detalle de producto " + id + "...");

		final Map<String, Object> model = new HashMap<String, Object>();

		model.put("product", this.productManager.getById(id));
		model.put("isNew", false);
		return new ModelAndView("product/form", model);
	}

	/**
	 * 
	 * @param id
	 * @return elimina un producto de la BBDD
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/inventario/eliminar/{id}", method = RequestMethod.GET)
	public ModelAndView eliminar(@PathVariable(value = "id") final long id) throws ServletException, IOException {
		this.logger.trace("Eliminando producto " + id + "...");

		final Map<String, Object> model = new HashMap<String, Object>();
		String msg = "No eliminado producto[" + id + "]";
		if (this.productManager.eliminar(id)) {
			msg = "Producto[" + id + "] eliminado";
			this.logger.info(msg);
		} else {
			this.logger.warn(msg);
		}

		model.put("msg", msg);
		model.put("products", this.productManager.getProducts());
		model.put("fecha", new Date().toString());

		return new ModelAndView("product/inventario", model);
		// return new ModelAndView("product/inventario", model);
	}

}
