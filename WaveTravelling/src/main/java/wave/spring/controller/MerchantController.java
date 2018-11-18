package wave.spring.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import wave.spring.Constants.AdminConstantsI;
import wave.spring.Constants.SystemConstants;
import wave.spring.model.EmailDetails;
import wave.spring.model.MerchantDetails;
import wave.spring.model.VechileDetails;
import wave.spring.security.Security;
import wave.spring.security.SecurityI;
import wave.spring.services.MerchantRegistration;
import wave.spring.services.MerchantRegistrationI;

@Controller
public class MerchantController {
	@Autowired
	// added by Gaurav Sriavstava
	@RequestMapping(value = "/AdminAddVechile", method = RequestMethod.POST)
	public ModelAndView AdminAddVechile(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("MerchantAddVechile");
		mav.addObject("MerchantAddVechile", new VechileDetails());
		return mav;
	}

	// added by Gaurav Srivastava
	@RequestMapping(value = "/pendingMail", method = RequestMethod.POST)
	public ModelAndView pendingMail(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("MerchantPendingMail");
		List<EmailDetails> emailDetails = new ArrayList<EmailDetails>();
		MerchantRegistrationI mercahntRegistration = new MerchantRegistration();
		emailDetails = mercahntRegistration.getPendingEmails();
		if (emailDetails.isEmpty()) {
			String message = SystemConstants.EMPTY_LIST;
			mav.addObject(SystemConstants.MSG, message);
		} else {
			request.setAttribute(AdminConstantsI.PENDING_MAIL_LIST, emailDetails);
		}
		return mav;
	}
	
	//added by Gaurav Srivastava
	@RequestMapping(value = "/resendEmailManually", method = RequestMethod.POST)
	public ModelAndView resendEmail(HttpServletRequest request, HttpServletResponse response) {
		String to = request.getParameter(AdminConstantsI.TO);
		String subject = request.getParameter(AdminConstantsI.SUBJECT);
		String msg = request.getParameter(AdminConstantsI.MESSAGE);
		ModelAndView mav = new ModelAndView("MerchantPendingMail");
		HashMap<String, String> map = new HashMap();
		map.put(AdminConstantsI.TO, to);
		map.put(AdminConstantsI.SUBJECT, subject);
		map.put(AdminConstantsI.MESSAGE, msg);
		SecurityI security = new Security();
		String message = security.sendMail(map);
		if(message.equals(SystemConstants.ACTIVE)) {
			mav.addObject(SystemConstants.MSG_SUCCESS, AdminConstantsI.SENT);
		}else {
			mav.addObject(SystemConstants.MSG, AdminConstantsI.FAILED);
		}
		return mav;
	}
}
