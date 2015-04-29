package tonelope.demo.currencyconverter.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	/**
	 * Serves the main app.html view.
	 * 
	 * @return the ModelAndView object for the main app.html view.
	 */
	@RequestMapping(value = "/")
	public ModelAndView getHomeView() {
		return new ModelAndView("app");
	}
}
