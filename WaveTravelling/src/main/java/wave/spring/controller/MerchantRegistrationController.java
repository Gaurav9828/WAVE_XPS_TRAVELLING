package wave.spring.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import wave.spring.Constants.AdminConstantsI;
import wave.spring.Constants.SystemConstants;
import wave.spring.model.MerchantDetails;
import wave.spring.services.MerchantRegistration;
import wave.spring.services.MerchantRegistrationI;

@Controller
public class MerchantRegistrationController {
	@Autowired
	// added by Gaurav Sriavstava
	@RequestMapping(value = "/merchantRegistration", method = RequestMethod.GET)
	public ModelAndView merchantRegistrationPage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("MerchantRegistration");
		mav.addObject("MerchantRegistration", new MerchantDetails());
		return mav;
	}

	// Added by Gaurav Srivastava
	@RequestMapping(value = "/merchantRegistrationProcess", method = RequestMethod.POST)
	public ModelAndView startMerchantRegistrationProcess(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("MerchantRegistration") MerchantDetails merchantDetails,BindingResult bindingResult) {
		ModelAndView mav = null;
		mav = new ModelAndView("MerchantRegistration");
		MerchantRegistrationI merchantRegistration = new MerchantRegistration();
		String message = merchantRegistration.freshRegistrationRequest(merchantDetails);
		if (message.equals(AdminConstantsI.ALREADY_REGISTERED)) {
			mav.addObject("MerchantRegistration", new MerchantDetails());
			mav.addObject(SystemConstants.MSG, AdminConstantsI.ALREADY_REGISTERED_MSG);
		} else if (message.equals(AdminConstantsI.ALREADY_MERCHANT)) {
			mav.addObject("MerchantRegistration", new MerchantDetails());
			mav.addObject(SystemConstants.MSG, AdminConstantsI.ALREADY_MERCHANT_MSG);
		} else if (message.equals(AdminConstantsI.BLACK_LISTED_REQUEST)) {
			mav.addObject("MerchantRegistration", new MerchantDetails());
			mav.addObject(SystemConstants.MSG, AdminConstantsI.BLACK_LISTED_REQUEST_MSG);
		} else if (message.equals(SystemConstants.ERROR)) {
			mav.addObject("MerchantRegistration", new MerchantDetails());
			mav.addObject(SystemConstants.MSG, AdminConstantsI.SOME_THING_WRONG_REGISTRATION);
		} else {
			mav.addObject("MerchantRegistration", new MerchantDetails());
			mav.addObject(SystemConstants.MSG_SUCCESS, AdminConstantsI.REGISTRAION_REQUEST_SUCCESSFUL);
		}
		return mav;
	}
	
	// added by Gaurav Sriavstava
	@RequestMapping(value = "/merchantApplications", method = RequestMethod.POST)
	public ModelAndView merchantApplicationsPage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("AdminMerchantApplications");
		//gentrate application List
		MerchantRegistrationI mercahntRegistration = new MerchantRegistration();
		HashMap map = mercahntRegistration.getMerchantApplications();
		if(map.get(SystemConstants.MSG).equals(SystemConstants.INACTIVE)) {
			String message = SystemConstants.EMPTY_LIST;
			mav.addObject(SystemConstants.MSG, message);
		}else {
			request.setAttribute(SystemConstants.LIST, map.get(SystemConstants.LIST));
		}
		return mav;
	}

}
