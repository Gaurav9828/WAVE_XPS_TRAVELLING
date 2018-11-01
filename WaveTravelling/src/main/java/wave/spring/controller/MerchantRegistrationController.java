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
import wave.spring.Constants.SystemConstants;
import wave.spring.model.EmployeeDetails;
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
			@ModelAttribute("MerchantRegistration") MerchantDetails merchantDetails, BindingResult bindingResult) {
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
		} else if (message.equals(SystemConstants.MAIL_ERROR)) {
			mav.addObject("MerchantRegistration", new MerchantDetails());
			mav.addObject(SystemConstants.MSG_SUCCESS, AdminConstantsI.REGISTRAION_REQUEST_SUCCESSFUL);
			mav.addObject(SystemConstants.MSG, AdminConstantsI.ERROR_MAIL_SENDING);
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
		HttpSession session = request.getSession();
		// gentrate application List
		MerchantRegistrationI mercahntRegistration = new MerchantRegistration();
		HashMap map = mercahntRegistration.getMerchantApplications();
		if (map.get(SystemConstants.MSG).equals(SystemConstants.FALSE)) {
			String message = SystemConstants.EMPTY_LIST;
			session.removeAttribute(SystemConstants.LIST);
			mav.addObject(SystemConstants.MSG, message);
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
		HttpSession session = request.getSession();
		MerchantRegistrationI mercahntRegistration = new MerchantRegistration();
		int merchantId = Integer.parseInt(id);
		ModelAndView mav = new ModelAndView("AdminMerchantApplications");
		String message = mercahntRegistration.deleteMerchantApplication(merchantId, mailId);
		mav.addObject(SystemConstants.MSG_SUCCESS, message);
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
		MerchantDetails merchant = null;
		for (MerchantDetails applicant : applicantList) {
			if (applicant.getMarchantId().toString().equals(merchantId)) {
				merchant = applicant;
				continue;
			}
		}
		MerchantRegistrationI merchantRegistration = new MerchantRegistration();
		String message = merchantRegistration.createMerchant(merchant, empDetails.getEmployeeId());
		if(message.equals(SystemConstants.ERROR)) {
			mav.addObject(SystemConstants.MSG, SystemConstants.SOMETHING_ERROR);
			request.setAttribute(SystemConstants.MERCHANT, merchant);
		}else {
			mav.addObject(SystemConstants.MSG_SUCCESS, message);
			request.setAttribute(SystemConstants.MERCHANT, merchant);
		}
		return mav;
	}
	
	
	// added by Gaurav Sriavstava
		@RequestMapping(value = "/merchantDetails", method = RequestMethod.POST)
		public ModelAndView merchantDetails(HttpServletRequest request, HttpServletResponse response) {
			ModelAndView mav = new ModelAndView("AdminMerchantDetails");
			HttpSession session = request.getSession();
			// gentrate application List
			MerchantRegistrationI mercahntRegistration = new MerchantRegistration();
			HashMap map = mercahntRegistration.getMerchants();
			if (map.get(SystemConstants.MSG).equals(SystemConstants.FALSE)) {
				String message = SystemConstants.EMPTY_LIST;
				mav.addObject(SystemConstants.MSG, message);
			} else {
				session.setAttribute(SystemConstants.MERCHANT_LIST, map.get(SystemConstants.LIST));
			}
			return mav;
		}
		
		
		// added by Gaurav Sriavstava
		@RequestMapping(value = "/merchantDetailsView", method = RequestMethod.POST)
		public ModelAndView merchantDetailsView(HttpServletRequest request, HttpServletResponse response) {
			HttpSession session = request.getSession();
			String merchantId = request.getParameter("merchantId");
			List<MerchantDetails> merchantList = (List<MerchantDetails>) session.getAttribute(SystemConstants.MERCHANT_LIST);
			ModelAndView mav = new ModelAndView("AdminMerchantDetailsView");
			// gentrate application List
			MerchantDetails merchant1 = null;
			for (MerchantDetails merchant : merchantList) {
				if (merchant.getMarchantId().toString().equals(merchantId)) {
					merchant1 = merchant;
					continue;
				}
			}
			request.setAttribute(SystemConstants.MERCHANT_DETAILS, merchant1);
			return mav;
		}
		
		// added by Gaurav Sriavstava
		@RequestMapping(value = "/backAdminMerchantDetails", method = RequestMethod.POST)
		public ModelAndView backAdminMerchantDetails(HttpServletRequest request, HttpServletResponse response) {
			ModelAndView mav = new ModelAndView("AdminMerchantDetails");
			return mav;
		}

}
