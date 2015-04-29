package tonelope.demo.currencyconverter.controllers;

import org.springframework.web.servlet.ModelAndView;
import org.testng.Assert;
import org.testng.annotations.Test;

import tonelope.demo.currencyconverter.controllers.HomeController;

public class HomeControllerTest {

	private HomeController testee;

	@Test
	public void getHomeView() {
		this.testee = new HomeController();
		final ModelAndView modelAndView = this.testee.getHomeView();
		Assert.assertEquals(modelAndView.getViewName(), "app");
	}
}
