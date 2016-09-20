package com.ipartek.formacion.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		final Date date = new Date();
		final DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		final String formattedDate = dateFormat.format(date);

		// request.setAttribute('name','value');
		model.addAttribute("serverTime", formattedDate);
		model.addAttribute("title","Home");
		return "home";
	}

	@RequestMapping(value = "/info")
	public ModelAndView info(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		logger.info("Returning hello view");
		final ModelAndView mv = new ModelAndView();
		mv.setViewName("home");
		mv.addObject("requestIp", request.getRemoteHost());
		mv.addObject("requestUa", request.getHeader("User-Agent"));
		return mv;
	}

}
