package wave.spring.controller;

import java.util.HashMap;
import java.util.List;

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
import wave.spring.Constants.AdminMessageConstants;
import wave.spring.Constants.MailMessagesConstants;
import wave.spring.Constants.SystemConstants;
import wave.spring.model.EmployeeDetails;
import wave.spring.model.Login;
import wave.spring.services.AdminLogin;
import wave.spring.services.AdminLoginI;

@Controller
public class LoginController {
	@Autowired
	// added by Gaurav Sriavstava
	@RequestMapping(value = "/AdminLogin", method = RequestMethod.GET)
	public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("AdminLogin");
		mav.addObject("AdminLogin", new Login());
		return mav;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/adminWelcomeMenuDetails", method = RequestMethod.GET)
	public ModelAndView adminWelcomeMenuDetails(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("AdminWelcomeMenuDetails");
		try {
			HttpSession session = request.getSession();
			List<List<String>> menuList = (List<List<String>>)session.getAttribute(AdminConstantsI.EMPLOYEE_MENU_LIST);
			List<String> menuDomain = (List<String>)session.getAttribute(AdminConstantsI.EMPLOYEE_MENU_DOMAIN_LIST);
			session.removeAttribute(AdminConstantsI.EMPLOYEE_MENU_LIST);
			session.removeAttribute(AdminConstantsI.EMPLOYEE_MENU_DOMAIN_LIST);
			mav.addObject(AdminConstantsI.EMPLOYEE_MENU_LIST, menuList);
			mav.addObject(AdminConstantsI.EMPLOYEE_MENU_DOMAIN_LIST,menuDomain);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	@RequestMapping(value = "/adminWelcomeProfileDetails", method = RequestMethod.GET)
	public ModelAndView adminWelcomeProfileDetails(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("AdminWelcomeProfileDetails");
		try {
			HttpSession session = request.getSession();
			EmployeeDetails employeeDetails = (EmployeeDetails) session.getAttribute(AdminConstantsI.EMPLOYEE_DETAILS);
			mav.addObject(AdminConstantsI.EMPLOYEE_DETAILS, employeeDetails);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
		
	@RequestMapping(value = "/adminWelcomeWorkingPage", method = RequestMethod.GET)
	public ModelAndView adminWelcomeWorkingPage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("AdminWelcomeWorkingPage");
		return mav;
	}
	
	// Added by Gaurav Srivastava
	@RequestMapping(value = "/WAVE", method = RequestMethod.POST)
	public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("AdminLogin") Login loginValues) {
		ModelAndView mav = null;
		HttpSession session = request.getSession();
		try {
			HashMap<?, ?> map = new HashMap<Object, Object>();
			AdminLoginI adminLogin = new AdminLogin();
			map = adminLogin.adminLoginService(loginValues);
			if ((map.get(SystemConstants.ERROR_MESSAGE)).equals(SystemConstants.TRUE)) {
				mav = new ModelAndView("AdminWelcomeFrame");
				session.setAttribute(AdminConstantsI.EMPLOYEE_DETAILS, map.get(AdminConstantsI.EMPLOYEE_DETAILS));
				session.setAttribute(AdminConstantsI.EMPLOYEE_MENU_LIST, map.get(AdminConstantsI.EMPLOYEE_MENU_LIST));
				session.setAttribute(AdminConstantsI.EMPLOYEE_MENU_DOMAIN_LIST, map.get(AdminConstantsI.EMPLOYEE_MENU_DOMAIN_LIST));

			} else if ((map.get(SystemConstants.ERROR_MESSAGE)).equals(AdminConstantsI.PASSWORD_RESET)) {
				mav = new ModelAndView("AdminPasswordReset");
				mav.addObject("AdminPasswordReset", new Login());
				session.setAttribute(AdminConstantsI.EMPLOYEE_DETAILS, map.get(AdminConstantsI.EMPLOYEE_DETAILS));

			} else {
				mav = new ModelAndView("AdminLogin");
				mav.addObject("AdminLogin", new Login());
				mav.addObject(SystemConstants.ERROR_MESSAGE, map.get(SystemConstants.ERROR_MESSAGE));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}

	// Added by Gaurav Srivastava
	@RequestMapping(value = "/Logout", method = RequestMethod.POST)
	public ModelAndView logoutProcess(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		EmployeeDetails employeeDetails = (EmployeeDetails) session.getAttribute(AdminConstantsI.EMPLOYEE_DETAILS);
		ModelAndView mav = null;
		try {
			AdminLoginI adminLogin = new AdminLogin();
			adminLogin.adminLogout(employeeDetails); // set Login status inactive
			mav = new ModelAndView("AdminLogin");
			mav.addObject("AdminLogin", new Login());
		} catch (Exception e) {
			e.printStackTrace();
			mav = new ModelAndView("errorPage");
		}finally {
			session.invalidate();
		}
		//flush session
		
		return mav;
	}

	// password reset
	// added by Gaurav Sriavstava
	@SuppressWarnings("null")
	@RequestMapping(value = "/adminPasswordReset", method = RequestMethod.POST)
	public ModelAndView showPasswordReset(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("AdminPasswordReset") Login resetValues) {
		HttpSession session = request.getSession();
		EmployeeDetails employeeDetails = (EmployeeDetails) session.getAttribute(AdminConstantsI.EMPLOYEE_DETAILS);
		ModelAndView mav = null;
		if (employeeDetails.getPassword().equals(resetValues.getAuthValue1())) {
			AdminLoginI adminLogin = new AdminLogin();
				if (employeeDetails.getMemorableWord() == null) {
					employeeDetails.setPassword(resetValues.getAuthValue2());
					mav = new ModelAndView("AdminForceMemorableWordSet");
					mav.addObject("AdminForceMemorableWordSet", new Login());
				} else {
					String message = adminLogin.resetAdminPassword(employeeDetails.getUserId(), resetValues.getAuthValue2());
					if (message.equals(SystemConstants.ACTIVE)) {
						mav = new ModelAndView("AdminLogin");
						mav.addObject("AdminLogin", new Login());
						mav.addObject(SystemConstants.SUCCESS_MESSAGE, AdminMessageConstants.ADMIN_PASSWORD_RESET_SUCCESSFULL);
					}else {
						mav.addObject("AdminLogin", new Login());
						mav.addObject(SystemConstants.ERROR_MESSAGE, AdminMessageConstants.SOMETHING_WRONG);
					}		
				}
			} 
		else {
			mav = new ModelAndView("AdminPasswordReset");
			mav.addObject("AdminPasswordReset", new Login());
			mav.addObject(SystemConstants.ERROR_MESSAGE, AdminMessageConstants.INVALID_USER);
		}
		return mav;
	}

	// memorable word reset
	// added by Gaurav Sriavstava
	@RequestMapping(value = "/adminForceMemorableWordSet", method = RequestMethod.POST)
	public ModelAndView setMemorableWord(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("AdminForceMemorableWordSet") Login resetValues) {
		HttpSession session = request.getSession();
		EmployeeDetails employeeDetails = (EmployeeDetails) session.getAttribute(AdminConstantsI.EMPLOYEE_DETAILS);
		ModelAndView mav = null;
		AdminLoginI adminLogin = new AdminLogin();
		String message = adminLogin.setSecretMemorableWord(employeeDetails, resetValues.getAuthValue2());
		if (message.equals(SystemConstants.ACTIVE)) {
			mav = new ModelAndView("AdminLogin");
			mav.addObject("AdminLogin", new Login());
			mav.addObject(SystemConstants.SUCCESS_MESSAGE, AdminMessageConstants.ADMIN_PASSWORD_AND_MEMORABLE_WORD_RESET_SUCCESSFULL);
		} else {

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
			if (message.equals(SystemConstants.ACTIVE)) {
				mav = new ModelAndView("AdminLogin");
				mav.addObject("AdminLogin", new Login());
				mav.addObject(SystemConstants.SUCCESS_MESSAGE, MailMessagesConstants.TEMPORARY_PASSWORD_MAIL_SENT);
			} else if (message.equals(SystemConstants.FALSE) || message.equals(SystemConstants.INACTIVE)) {
				mav = new ModelAndView("AdminLogin");
				mav.addObject("AdminLogin", new Login());
				mav.addObject(SystemConstants.ERROR_MESSAGE, AdminMessageConstants.INVALID_USERID_OR_SECRET_WORD);
			} else if(message.equals(SystemConstants.MAIL_ERROR)){ 
				mav = new ModelAndView("AdminLogin");
				mav.addObject(SystemConstants.ERROR_MESSAGE, MailMessagesConstants.MAIL_SENDING_NETWORK_ERROR);
				mav.addObject("AdminLogin", new Login());
			}else {
				mav = new ModelAndView("AdminLogin");
				mav.addObject(SystemConstants.ERROR_MESSAGE, AdminMessageConstants.SOMETHING_WRONG);
				mav.addObject("AdminLogin", new Login());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
}