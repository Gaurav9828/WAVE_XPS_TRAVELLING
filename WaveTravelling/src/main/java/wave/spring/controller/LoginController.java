package wave.spring.controller;


import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import wave.spring.Constants.AdminConstantsI;
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
	  HttpSession session = request.getSession();
    try {
    	HashMap<?,?> map = new HashMap();
    	AdminLoginI adminLogin = new AdminLogin();
		map = adminLogin.adminLoginService(loginValues);
		if((map.get(SystemConstants.MSG)).equals(SystemConstants.TRUE)) {
			mav = new ModelAndView("AdminWelcome");
			mav.addObject("Welcome", "Welcome");
			session.setAttribute(AdminConstantsI.EMPLOYEE_DETAILS, map.get(AdminConstantsI.EMPLOYEE_DETAILS));
			session.setAttribute(AdminConstantsI.EMPLOYEE_MENU_LIST, map.get(AdminConstantsI.EMPLOYEE_MENU_LIST));
		}else {
			mav = new ModelAndView("AdminLogin");
			mav.addObject("AdminLogin", new Login());
			mav.addObject("message",map.get(SystemConstants.MSG));
		}
    }catch(Exception e) {
    	e.printStackTrace();
    }
    return mav;
  }
  
  //Added by Gaurav Srivastava
  @RequestMapping(value = "/logout", method = RequestMethod.POST)
  public ModelAndView logoutProcess(HttpServletRequest request, HttpServletResponse response) {
	  HttpSession session = request.getSession();
	  EmployeeDetails employeeDetails = (EmployeeDetails) session.getAttribute(AdminConstantsI.EMPLOYEE_DETAILS);
	  ModelAndView mav = null;
    try {
    	AdminLoginI adminLogin = new AdminLogin();
    	adminLogin.adminLogout(employeeDetails.getUserId());			//set Login status inactive
		mav = new ModelAndView("AdminLogin");
		mav.addObject("AdminLogin", new Login());
    }catch(Exception e) {
    	e.printStackTrace();
    }
    return mav;
  }
  
}