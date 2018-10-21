package wave.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import wave.spring.model.AdminLevel1SelfRegistration;

@Controller
public class AdminRegistrationController {
	 //added by Gaurav Sriavstava
	 @RequestMapping(value = "/AdminLevel1SelfRegistrationRequest", method = RequestMethod.GET)
	  public ModelAndView showAdmin1SelfRegistration(HttpServletRequest request, HttpServletResponse response) {
	    ModelAndView mav = new ModelAndView("AdminLevel1RegistrationRequest");
	    mav.addObject("AdminLevel1RegistrationRequest", new AdminLevel1SelfRegistration());
	    return mav;
	  }
}
