package wave.spring.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import wave.spring.Constants.SystemConstants;
import wave.spring.model.EmployeeDetails;
import wave.spring.model.Login;
import wave.spring.services.AdminLogin;
import wave.spring.services.AdminLoginI;
@Controller
public class LoginController {
  @Autowired
  //added by Gaurav Sriavstava
  @RequestMapping(value = "/AdminLogin", method = RequestMethod.GET)
  public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = new ModelAndView("AdminLogin");
    mav.addObject("AdminLogin", new Login());
    return mav;
  }
  //Added by Gaurav Srivastava
  @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
  public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response,
  @ModelAttribute("AdminLogin") Login loginValues) {
	  ModelAndView mav = null;
    try {
    	AdminLoginI adminLogin = new AdminLogin();
		String message = adminLogin.adminLoginService(loginValues);
		if(message.equals(SystemConstants.TRUE)) {
			mav = new ModelAndView("AdminWelcome");
			mav.addObject("emploueeDetails", new EmployeeDetails());
		}else {
			mav = new ModelAndView("AdminLogin");
			mav.addObject("AdminLogin", new Login());
			mav.addObject("message",message);
		}
    }catch(Exception e) {
    	e.printStackTrace();
    }
    return mav;
  }
}