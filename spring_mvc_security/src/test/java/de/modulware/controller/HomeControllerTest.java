package de.modulware.controller;


import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

public class HomeControllerTest {

	@Test
	public void testController() {
		HomeController controller = new HomeController();
		Model model = new ExtendedModelMap();
		assertEquals("home",controller.home(model));
		
		Object message = model.asMap().get("controllerMessage");
		assertEquals("This is the message from the controller!",message);		
	}
}
