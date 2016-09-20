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
import org.springframework.ui.Model;
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
	 * booModificar evalua si en '@RequestMapping(value = "/insertar-producto.html"'
	 * se va a realizar una modificacion o una insercion nueva
	 */
	public boolean booModificar = false;

	/**
	 * Mostrar listado de todos los productos del inventario
	 *
	 * @param request
	 * @param response
	 * @return ModelAndView "inventario.jsp", model : { ArrayList &lt; Product
	 *         &gt;"products" ,String model }
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

		return new ModelAndView("producto/inventario", model);
	}

	/**
	 * Muestra formulario para crear nuevo producto, al acceder por el Anchor se entiende que
	 * lo que se va a hacer es un nuevo registro, por lo que se establece 'booModificar = false'
	 * 
	 * @param model
	 * @return
	 */

	@SuppressWarnings("unchecked")
	///////////////////////// MUESTRA EL FORMULARIO DE NUEVO ARTICULO DESDE EL
	///////////////////////// ANCHOR//////////////////////
	@RequestMapping(value = "/insertar-producto.html", method = RequestMethod.GET)
	public String preView(Model model) {
		this.logger.trace("Antes de cargar insert-producto.jsp");
		Product p = new Product();
		((Map<String, Object>) model).put("product", p);
		booModificar = false;
		return "producto/insert-producto";
	}

	/**
	 * Con este Mapping realizamos la insercion o modificación de un registro
	 * @param product
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping(value = "/insertar-producto.html", method = RequestMethod.POST)

	public String insert(@Valid Product product, BindingResult bindingResult) {
		this.logger.trace("Insertando nuevo articulo " + product);
		String msg = "No insertado producto " + product;

		if (bindingResult.hasErrors()) {
			this.logger.trace("Parametros de nuevo articulo NO validos");
			return "producto/insert-producto";
		} else {
			if (!booModificar) {
				if (this.productManager.insertar(product)) {

					msg = "Producto " + product + " insertado";
					this.logger.info(msg);
				} else {
					this.logger.warn(msg);
				}

				final Map<String, Object> model = new HashMap<String, Object>();
				model.put("msg", msg);

				this.logger.info("Insertado " + product);
				booModificar = true;
				return "producto/insert-producto";
			} else {
				if (this.productManager.modificar(product)) {

					msg = "Producto " + product + " modificado";
					this.logger.info(msg);
				} else {
					this.logger.warn(msg);
				}

				final Map<String, Object> model = new HashMap<String, Object>();
				model.put("msg", msg);

				this.logger.info("Modificado " + product);
				return "producto/insert-producto";

			}
		}

	}

	///////////////////////// VISUALIZADO Y MODIFICADO DE DETALLE PRODUCTO
	///////////////////////// ////////////

	@RequestMapping(value = "/detalle-producto.html/{id}", method = RequestMethod.GET)
	public ModelAndView detalleArticulo(@PathVariable(value = "id") final long id)

			throws ServletException, IOException {
		final Map<String, Object> model = new HashMap<String, Object>();
		model.put("product", this.productManager.getById(id));
		booModificar = true;
		this.logger.trace("Detalle de articulo Articulo " + id);
		return new ModelAndView("producto/insert-producto", model);
	}

	///////////////////////// ELIMINAR ARTICULO/////////////////////////////////

	@RequestMapping(value = "/eliminar-producto/{id}", method = RequestMethod.GET)
	public ModelAndView eliminarArticulo(@PathVariable(value = "id") final long id)
			throws ServletException, IOException {

		final Map<String, Object> model = new HashMap<String, Object>();
		String msg = "No eliminado producto " + id;
		if (this.productManager.eliminar(id)) {
			msg = "Producto " + id + " eliminado";
			this.logger.info(msg);
		} else {
			this.logger.warn(msg);
		}
		model.put("msg", msg);
		this.logger.trace("Articulo eliminado " + id);
		return listarInventario();
	}

}
