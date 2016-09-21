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

	protected final Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private ProductManager productManager;

	/**
	 * Mostrar listado de todos lo productos del inventario
	 *
	 * @param request
	 * @param response
	 * @return ModelAndView view: "inventario.jsp", model: { ArrayList
	 *         &lt;Product&gt; "products" , String "fecha" }
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
	 * @return
	 */
	@RequestMapping(value = "/inventario/nuevo", method = RequestMethod.GET)
	public ModelAndView mostrarFormulario() {
		this.logger.trace("Mosrtrar formulario crear nuevo producto");
		
		final Map<String, Object> model = new HashMap<String, Object>();		
		model.put("product", new Product());
		model.put("isNew", true);
		
		return new ModelAndView("product/form", model);
	}
	
	@RequestMapping(value = "/inventario/save", method = RequestMethod.POST)
	public ModelAndView guardar(@Valid Product product, BindingResult bindingResult) {
		
		final Map<String, Object> model = new HashMap<String, Object>();	
		String vista = "product/inventario";
		String msg = "No se pudo guardar el Producto";
		
		this.logger.trace("Guardando producto......");
		
		if(bindingResult.hasErrors()) {
			//error
			this.logger.warn("Parametros no validos");
			model.put("msg", msg);
			model.put("product", product);
			model.put("isNew", product.isNew());
			vista = "product/form";
			
		}else{
			if(product.isNew()){
				//insert				
				if(this.productManager.insertar(product)){
					msg = "Producto creado con éxito";
					model.put("msg", msg);
					this.logger.trace(msg);		
				}
				
			}else{
				//modificar				
				if(this.productManager.modificar(product)){
					msg = "Producto modificado con éxito";
					model.put("msg", msg);
					this.logger.trace(msg);		
				}
			}
			model.put("products", this.productManager.getProducts());
		}
		
		
		return new ModelAndView(vista, model);				
		
	}
	
	@RequestMapping(value = "/inventario/detalle/{id}", method = RequestMethod.GET)
	public ModelAndView verDetalle(@PathVariable(value="id") final long id) {
		this.logger.trace("Mostrando detalle producto " + id +"......");
				
		final Map<String, Object> model = new HashMap<String, Object>();
	
		model.put("product", this.productManager.getById(id));
		model.put("isNew", false);
		
		return new ModelAndView("product/form", model);
	}
	
	@RequestMapping(value = "/inventario/eliminar/{id}", method = RequestMethod.GET)
	public ModelAndView eliminar(@PathVariable(value="id") final long id) throws ServletException, IOException {
		this.logger.trace("Eliminando producto " + id + "......");
				
		final Map<String, Object> model = new HashMap<String, Object>();	
		String msg = "No eliminado producto[" + id +"]";
		
		if (this.productManager.eliminar(id)){
			msg = "producto[" + id +"] eliminado";
			this.logger.warn(msg);			
		}else{
			this.logger.warn(msg);
		}
					
		model.put("msg", msg);		
		model.put("products", this.productManager.getProducts());
		
		return new ModelAndView("product/inventario", model);
	}

}
