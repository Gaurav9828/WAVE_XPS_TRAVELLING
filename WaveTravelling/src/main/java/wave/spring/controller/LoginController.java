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
import wave.spring.model.AdminLevel1SelfRegistration;
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
			
		}else if((map.get(SystemConstants.MSG)).equals(AdminConstantsI.PASSWORD_RESET)){
			mav = new ModelAndView("AdminPasswordReset");
		    mav.addObject("AdminPasswordReset", new Login());
			session.setAttribute(AdminConstantsI.EMPLOYEE_DETAILS, map.get(AdminConstantsI.EMPLOYEE_DETAILS));
			
		}else {
			mav = new ModelAndView("AdminLogin");
			mav.addObject("AdminLogin", new Login());
			mav.addObject(SystemConstants.MSG,map.get(SystemConstants.MSG));
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
    	adminLogin.adminLogout(employeeDetails);			//set Login status inactive
		mav = new ModelAndView("AdminLogin");
		mav.addObject("AdminLogin", new Login());
    }catch(Exception e) {
    	e.printStackTrace();
    }
    return mav;
  }
  
  //password reset
  //added by Gaurav Sriavstava
  @RequestMapping(value = "/adminPasswordReset", method = RequestMethod.POST)
  public ModelAndView showPasswordReset(HttpServletRequest request, HttpServletResponse response,
		  @ModelAttribute("AdminPasswordReset") Login resetValues) {
	  HttpSession session = request.getSession();
  	  HashMap<?,?> map = new HashMap();
	  EmployeeDetails employeeDetails = (EmployeeDetails) session.getAttribute(AdminConstantsI.EMPLOYEE_DETAILS);
	  ModelAndView mav = null;
	  if(employeeDetails.getPassword().equals(resetValues.getAuthValue1())) {
		 AdminLoginI adminLogin = new AdminLogin();
		 String message = adminLogin.resetAdminPassword(employeeDetails.getUserId(),resetValues.getAuthValue2());
		 if(message.equals(SystemConstants.ACTIVE)) {
			 mav = new ModelAndView("AdminLogin");
			 mav.addObject("AdminLogin", new Login());
			 mav.addObject(SystemConstants.MSG_SUCCESS, SystemConstants.ADMIN_PASSWORD_RESET_SUCCESSFULL);
		 }else {
			 mav.addObject("AdminLogin", new Login());
			 mav.addObject(SystemConstants.MSG, SystemConstants.SOMETHING_WRONG);
		 }
	  }else {
			 mav = new ModelAndView("AdminPasswordReset");
			 mav.addObject("AdminPasswordReset", new Login());
			 mav.addObject(SystemConstants.MSG, AdminConstantsI.INVALID_USER);
	  }
	  return mav;
  }
  
  @RequestMapping(value = "/AdminMemorableWordPasswordReset", method = RequestMethod.GET)
  public ModelAndView showMemorableWordPassworReset(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = new ModelAndView("AdminMemorableWordPasswordReset");
    mav.addObject("AdminMemorableWordPasswordReset", new Login());
    return mav;
  }
  
  @RequestMapping(value = "/sendMailToResetPassword", method = RequestMethod.POST)
  public ModelAndView sendMailToResetPassword(HttpServletRequest request, HttpServletResponse response,
		  @ModelAttribute("AdminMemorableWordPasswordReset") Login resetValues) {
	  ModelAndView mav = null;
    try {
    	AdminLoginI adminLogin = new AdminLogin();
    	String message = adminLogin.resetPasswordAndSendMail(resetValues);
    	if(message.equals(SystemConstants.ACTIVE)) {
    		mav = new ModelAndView("AdminLogin");
    		mav.addObject("AdminLogin", new Login());
    		mav.addObject(SystemConstants.MSG_SUCCESS, SystemConstants.TEMPORARY_PASSWORD_MAIL_SENT);
    	}else if(message.equals(SystemConstants.FALSE) || message.equals(SystemConstants.INACTIVE)) {
    		mav = new ModelAndView("AdminLogin");
    		mav.addObject("AdminLogin", new Login());
    		mav.addObject(SystemConstants.MSG, AdminConstantsI.INVALID_USERID_OR_SECRET_WORD);
    	}else {
    		mav = new ModelAndView("AdminLogin");
    		mav.addObject(SystemConstants.MSG, SystemConstants.SOMETHING_WRONG);
    		mav.addObject("AdminLogin", new Login());
    	}
		
    }catch(Exception e) {
    	e.printStackTrace();
    }
    return mav;
  }
}