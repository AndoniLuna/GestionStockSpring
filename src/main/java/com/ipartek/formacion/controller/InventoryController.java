package com.ipartek.formacion.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
	 * MOSTRAR LISTADO DE TODOS LOS PRODUCTOS DEL INVENTARIO
	 * @param request
	 * @param response
	 * @return MOdelAndView : inventario.jsp, model: { "ArrayList <Product> "products, String fecha"}
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/inventario" , method = RequestMethod.GET)
	public ModelAndView listarInventario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.logger.info("procesando peticion");

		// atributos == modelo
		final Map<String, Object> model = new HashMap<String, Object>();
		model.put("products", this.productManager.getProducts());
		model.put("fecha", new Date().toString());

		return new ModelAndView("product/inventario", model);
	}
	
	
	/**
	 * Muestra formulario para crear nuevo producto
	 * @return
	 */
	@RequestMapping(value = "/inventario/nuevo" , method = RequestMethod.GET)
	public ModelAndView mostrarFormulario(@Valid Product product,BindingResult br){
		this.logger.trace("Mostrar formulario para crear nuevos productos");
		if(br.hasErrors()){
			this.logger.warn("Valores no validos");
		}
		final Map<String, Object> model = new HashMap<String, Object>();
		model.put("product", new Product());
		return new ModelAndView("product/form", model);
	}
	
	@RequestMapping(value = "/inventario/detalle/{id}" , method = RequestMethod.GET)
	public ModelAndView verDetalle(@PathVariable(value="id") final long id){
		this.logger.trace("Mostrar detalle de producto"+id);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("product", this.productManager.getById(id));
		return new ModelAndView("product/form",model);
	}
	
	
	@RequestMapping(value = "/inventario/guardar/{id}" , method = RequestMethod.POST)
	public ModelAndView guardar(@Valid Product product,BindingResult br){
		this.logger.trace("Guardando producto");
		if(br.hasErrors()){
			this.logger.warn("Valores no validos");
		}
		final Map<String, Object> model = new HashMap<String, Object>();
		model.put("product",this.productManager.guardar(product));
		model.put("products", this.productManager.getProducts());
		this.logger.trace("PRODUCTO GUARDADO");
		return new ModelAndView("product/inventario",model);
	}
	
	
	
	
	@RequestMapping(value = "/inventario/eliminar/{id}" , method = RequestMethod.GET)
	public ModelAndView eliminar(@PathVariable(value="id") final long id){
		this.logger.trace("Eliminando producto"+id);
		Map<String, Object> model = new HashMap<String, Object>();
		String msg = "No se ha eliminado el producto"+id;
		if(this.productManager.eliminar(id)){
			msg= "Producto  "+id+"  eliminado";
			this.logger.info(msg);
		}else{
			this.logger.warn(msg);
		}
		model.put("msg",msg);
		model.put("products", this.productManager.getProducts());
		return new ModelAndView("product/inventario",model);
	}
	
	
}
