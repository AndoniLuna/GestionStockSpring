package com.ipartek.formacion.controller;

import java.io.IOException;
import java.util.ArrayList;
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
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ipartek.formacion.domain.Persona;
import com.ipartek.formacion.service.PersonaManager;

@Controller
public class PersonaController {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private PersonaManager personaManager;

	public void setPersonaManager(PersonaManager personaManager) {
		this.personaManager = personaManager;
	}

	@RequestMapping(value = "/persona")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		final ArrayList<Persona> lista = (ArrayList<Persona>) this.personaManager.getAll();
		final Map<String, Object> model = new HashMap<String, Object>();
		model.put("personas", lista);

		return new ModelAndView("persona", model);
	}
	
	@RequestMapping(value="/insert-persona.html", method = RequestMethod.POST)
	public String insert(@Valid Persona persona, BindingResult result) {
		// Si hay errores volver pagina priceincrease.jsp
		if (result.hasErrors()) {
			return "insert-persona";
		}else{
			logger.info("Persona insertada");
		}
		//this.logger.info("Mostrando personas: "+persona.toString());
		//this.personaManager.getAll();
		return "redirect:persona";
	}

	@RequestMapping(value="/insert-persona.html", method = RequestMethod.GET)
	public String preView(Model model){
		logger.info("Antes de cargar insert-persona");
		Persona persona = new Persona();
		persona.setEdad(18);
		model.addAttribute("persona", persona);
		return "insert-persona";
	}


}
