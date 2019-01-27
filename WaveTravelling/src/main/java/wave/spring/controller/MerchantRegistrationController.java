package wave.spring.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import wave.spring.Constants.AdminConstantsI;
import wave.spring.Constants.AdminMessageConstants;
import wave.spring.Constants.MailMessagesConstants;
import wave.spring.Constants.SystemConstants;
import wave.spring.model.EmployeeDetails;
import wave.spring.model.MerchantDetails;
import wave.spring.services.MerchantRegistration;
import wave.spring.services.MerchantRegistrationI;

@Controller
public class MerchantRegistrationController {
	@Autowired
	// added by Gaurav Sriavstava
	@RequestMapping(value = "/Registration", method = RequestMethod.GET)
	public ModelAndView merchantRegistrationPage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("MerchantRegistration");
		mav.addObject("MerchantRegistration", new MerchantDetails());
		return mav;
	}
	
	@RequestMapping(value = "/Back_To_Registration", method = RequestMethod.POST)
	public ModelAndView Back_To_Registration(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("MerchantRegistration");
		mav.addObject("MerchantRegistration", new MerchantDetails());
		return mav;
	}

	@RequestMapping(value = "/RegistrationConfirmation", method = RequestMethod.POST)
	public ModelAndView merchantRegistrationConfirmationProcess(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("MerchantRegistration") MerchantDetails merchantDetails) {
		ModelAndView mav = new ModelAndView("MerchantRegistrationConfirmation");
		mav.addObject(SystemConstants.LIST, merchantDetails);
		return mav;
	}
	
	// Added by Gaurav Srivastava
	@RequestMapping(value = "/RegistrationProcess", method = RequestMethod.POST)
	public ModelAndView startMerchantRegistrationProcess(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("MerchantRegistration") MerchantDetails merchantDetails) {
		ModelAndView mav = null;
		mav = new ModelAndView("MerchantRegistration");
		MerchantRegistrationI merchantRegistration = new MerchantRegistration();
		String message = merchantRegistration.freshRegistrationRequest(merchantDetails);
		if (message.equals(AdminConstantsI.ALREADY_REGISTERED)) {
			mav.addObject("MerchantRegistration", new MerchantDetails());
			mav.addObject(SystemConstants.ERROR_MESSAGE, AdminMessageConstants.ALREADY_REGISTERED_MSG);
		} else if (message.equals(AdminConstantsI.ALREADY_MERCHANT)) {
			mav.addObject("MerchantRegistration", new MerchantDetails());
			mav.addObject(SystemConstants.ERROR_MESSAGE, AdminMessageConstants.ALREADY_MERCHANT_MSG);
		} else if (message.equals(AdminConstantsI.BLACK_LISTED_REQUEST)) {
			mav.addObject("MerchantRegistration", new MerchantDetails());
			mav.addObject(SystemConstants.ERROR_MESSAGE, AdminMessageConstants.BLACK_LISTED_REQUEST_MSG);
		} else if (message.equals(SystemConstants.ERROR)) {
			mav.addObject("MerchantRegistration", new MerchantDetails());
			mav.addObject(SystemConstants.ERROR_MESSAGE, AdminMessageConstants.SOME_THING_WRONG_REGISTRATION);
		} else if (message.equals(SystemConstants.MAIL_ERROR)) {
			mav.addObject("MerchantRegistration", new MerchantDetails());
			mav.addObject(SystemConstants.SUCCESS_MESSAGE, AdminMessageConstants.REGISTRAION_REQUEST_SUCCESSFUL);
			mav.addObject(SystemConstants.ERROR_MESSAGE, MailMessagesConstants.ERROR_MAIL_SENDING);
		} else {
			mav.addObject("MerchantRegistration", new MerchantDetails());
			mav.addObject(SystemConstants.SUCCESS_MESSAGE, AdminMessageConstants.REGISTRAION_REQUEST_SUCCESSFUL);
		}
		return mav;
	}

	// added by Gaurav Sriavstava
	@RequestMapping(value = "/merchantApplications", method = RequestMethod.POST)
	public ModelAndView merchantApplicationsPage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("AdminMerchantApplications");
		HttpSession session = request.getSession();
		// gentrate application List
		MerchantRegistrationI mercahntRegistration = new MerchantRegistration();
		HashMap<?, ?> map = mercahntRegistration.getMerchantApplications();
		if (map.get(SystemConstants.ERROR_MESSAGE).equals(SystemConstants.FALSE)) {
			String message = SystemConstants.EMPTY_LIST;
			session.removeAttribute(SystemConstants.LIST);
			mav.addObject(SystemConstants.ERROR_MESSAGE, message);
		} else {
			session.setAttribute(SystemConstants.LIST, map.get(SystemConstants.LIST));
		}
		return mav;
	}

	// added by Gaurav Sriavstava
	@RequestMapping(value = "/confirmRejectApplication", method = RequestMethod.POST)
	public ModelAndView confirmRejectApplication(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String merchantId = request.getParameter("merchantId");
		List<MerchantDetails> applicantList = (List<MerchantDetails>) session.getAttribute(SystemConstants.LIST);
		ModelAndView mav = new ModelAndView("AdminMerchantConfirmRejectApplication");
		// gentrate application List
		MerchantDetails merchant = null;
		for (MerchantDetails applicant : applicantList) {
			if (applicant.getMarchantId().toString().equals(merchantId)) {
				merchant = applicant;
				continue;
			}
		}
		request.setAttribute(SystemConstants.MERCHANT, merchant);
		return mav;
	}

	// added by Gaurav Sriavstava
	@RequestMapping(value = "/backMerchantApplications", method = RequestMethod.POST)
	public ModelAndView merchantApplications(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("AdminMerchantApplications");
		return mav;
	}

	// added by Gaurav Sriavstava
	@RequestMapping(value = "/rejectMerchantApplications", method = RequestMethod.POST)
	public ModelAndView rejectMerchantApplications(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("merchantId");
		String mailId = request.getParameter("mailId");
		MerchantRegistrationI mercahntRegistration = new MerchantRegistration();
		int merchantId = Integer.parseInt(id);
		ModelAndView mav = new ModelAndView("AdminMerchantApplications");
		String message = mercahntRegistration.deleteMerchantApplication(merchantId, mailId);
		mav.addObject(SystemConstants.SUCCESS_MESSAGE, message);
		merchantApplicationsPage(request, response);
		return mav;
	}

	// added by Gaurav Sriavstava
	@RequestMapping(value = "/merchantApplicationsView", method = RequestMethod.POST)
	public ModelAndView merchantApplicationsView(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String merchantId = request.getParameter("merchantId");
		List<MerchantDetails> applicantList = (List<MerchantDetails>) session.getAttribute(SystemConstants.LIST);
		ModelAndView mav = new ModelAndView("AdminMerchantApplicationView");
		// gentrate application List
		MerchantDetails merchant = null;
		for (MerchantDetails applicant : applicantList) {
			if (applicant.getMarchantId().toString().equals(merchantId)) {
				merchant = applicant;
				continue;
			}
		}
		request.setAttribute(SystemConstants.MERCHANT, merchant);
		return mav;
	}

	// added by Gaurav Sriavstava
	@RequestMapping(value = "/merchantApplicationVerified", method = RequestMethod.POST)
	public ModelAndView merchantApplicationVerified(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		EmployeeDetails empDetails = (EmployeeDetails) session.getAttribute(AdminConstantsI.EMPLOYEE_DETAILS);
		String merchantId = request.getParameter("merchantId");
		List<MerchantDetails> applicantList = (List<MerchantDetails>) session.getAttribute(SystemConstants.LIST);
		ModelAndView mav = new ModelAndView("AdminMerchantApplicationView");
		// gentrate application List
		MerchantRegistration merchantRegistration = new MerchantRegistration();
		MerchantDetails merchant = merchantRegistration.getMerchantDetails(merchantId, applicantList);
		String message = merchantRegistration.createMerchant(merchant, empDetails.getEmployeeId());
		if (message.equals(SystemConstants.ERROR)) {
			mav.addObject(SystemConstants.ERROR_MESSAGE, SystemConstants.SOMETHING_ERROR);
			request.setAttribute(SystemConstants.MERCHANT, merchant);
		} else {
			mav.addObject(SystemConstants.SUCCESS_MESSAGE, message);
			request.setAttribute(SystemConstants.MERCHANT, merchant);
		}
		return mav;
	}

	

}
