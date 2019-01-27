package wave.spring.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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
import wave.spring.Constants.SystemConstants;
import wave.spring.model.EmailDetails;
import wave.spring.model.EmployeeDetails;
import wave.spring.model.MerchantDetails;
import wave.spring.model.VehicleDetails;
import wave.spring.security.Security;
import wave.spring.security.SecurityI;
import wave.spring.services.AdminServices;
import wave.spring.services.AdminServicesI;
import wave.spring.services.MerchantServices;
import wave.spring.services.MerchantServicesI;
import wave.spring.services.MerchantRegistration;
import wave.spring.services.MerchantRegistrationI;

@Controller
public class MerchantController {
	@Autowired
	@RequestMapping(value = "/AdminAddVehicle", method = RequestMethod.POST)
	public ModelAndView AdminAddVehicle(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("MerchantAddVehicle");
		mav.addObject("MerchantAddVehicle", new VehicleDetails());
		MerchantServicesI marchantServices = new MerchantServices();
		ArrayList<ArrayList> vehicleList = marchantServices
				.getVehicleCompanyAndModelList(AdminConstantsI.ALREADY_ADDED);
		ArrayList aList = new ArrayList();
		for (ArrayList aL : vehicleList) {
			if (!aList.contains(aL.get(0))) {
				aList.add(aL.get(0));
			}
		}
		mav.addObject("vehicleCompany", aList);
		mav.addObject(SystemConstants.LIST, vehicleList);
		return mav;
	}
	
	@RequestMapping(value = "/addVehicle", method = RequestMethod.POST)
	public ModelAndView addVehicle(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("MerchantAddVehicle") VehicleDetails vehicleDetails) {
		ModelAndView mav = new ModelAndView("MerchantAddVehicle");
		MerchantServicesI marchantServices = new MerchantServices();
		HttpSession session = request.getSession();
		EmployeeDetails employeeDetails = (EmployeeDetails) session.getAttribute(AdminConstantsI.EMPLOYEE_DETAILS);
		LocalTime time = LocalTime.now();
		LocalDate date = LocalDate.now();
		vehicleDetails.setOwnerId(employeeDetails.getEmployeeId());
		vehicleDetails.setOwnerName(employeeDetails.getFirstName() + " " + employeeDetails.getLastName());
		vehicleDetails.setAddedOn(time.toString() + " " + date.toString());
		vehicleDetails.setStatus(AdminConstantsI.WAITING_LIST);
		vehicleDetails.setMessage(AdminMessageConstants.WAITING_FOR_APPROVAL);
		String message = marchantServices.setNewMarchantVehicle(vehicleDetails);
		if (message.equals(SystemConstants.TRUE)) {
			mav.addObject(SystemConstants.SUCCESS_MESSAGE, AdminMessageConstants.VEHICLE_ADDED_TO_WAITING_APPROVALS);
		} else {
			mav.addObject(SystemConstants.ERROR_MESSAGE, message);
		}
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
			mav.addObject(SystemConstants.ERROR_MESSAGE, message);
		} else {
			request.setAttribute(AdminConstantsI.PENDING_MAIL_LIST, emailDetails);
		}
		return mav;
	}

	// added by Gaurav Srivastava
	@RequestMapping(value = "/resendEmailManually", method = RequestMethod.POST)
	public ModelAndView resendEmail(HttpServletRequest request, HttpServletResponse response) {
		String to = request.getParameter(AdminConstantsI.TO);
		String subject = request.getParameter(AdminConstantsI.SUBJECT);
		String msg = request.getParameter(AdminConstantsI.MESSAGE);
		ModelAndView mav = new ModelAndView("MerchantPendingMail");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put(AdminConstantsI.TO, to);
		map.put(AdminConstantsI.SUBJECT, subject);
		map.put(AdminConstantsI.MESSAGE, msg);
		SecurityI security = new Security();
		String message = security.sendMail(map);
		if (message.equals(SystemConstants.ACTIVE)) {
			mav.addObject(SystemConstants.SUCCESS_MESSAGE, AdminConstantsI.SENT);
		} else {
			mav.addObject(SystemConstants.ERROR_MESSAGE, AdminConstantsI.FAILED);
		}
		return mav;
	}

	// added by Gaurav Sriavstava
	@RequestMapping(value = "/merchantDetails", method = RequestMethod.POST)
	public ModelAndView merchantDetails(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("AdminMerchantDetails");
		HttpSession session = request.getSession();
		// gentrate application List
		AdminServicesI adminServices = new AdminServices();
		HashMap<?, ?> map = adminServices.getMerchants();
		if (map.get(SystemConstants.ERROR_MESSAGE).equals(SystemConstants.FALSE)) {
			String message = SystemConstants.EMPTY_LIST;
			mav.addObject(SystemConstants.ERROR_MESSAGE, message);
		} else {
			session.setAttribute(SystemConstants.MERCHANT_LIST, map.get(SystemConstants.LIST));
		}
		return mav;
	}

	// added by Gaurav Sriavstava
	@RequestMapping(value = "/merchantDetailsView", method = RequestMethod.POST)
	public ModelAndView merchantDetailsView(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		MerchantRegistrationI mercahntRegistration = new MerchantRegistration();
		String merchantId = request.getParameter("merchantId");
		List<MerchantDetails> merchantList = (List<MerchantDetails>) session
				.getAttribute(SystemConstants.MERCHANT_LIST);
		ModelAndView mav = new ModelAndView("AdminMerchantDetailsView");
		// gentrate application List
		MerchantDetails merchant = mercahntRegistration.getMerchantDetails(merchantId, merchantList);
		request.setAttribute(SystemConstants.MERCHANT_DETAILS, merchant);
		return mav;
	}

	// added by Gaurav Sriavstava
	@RequestMapping(value = "/backAdminMerchantDetails", method = RequestMethod.POST)
	public ModelAndView backAdminMerchantDetails(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("AdminMerchantDetails");
		return mav;
	}

	// added by Gaurav Sriavstava
	@RequestMapping(value = "/blockUnblockMerchantProcess", method = RequestMethod.POST)
	public ModelAndView blockUnblockMerchantProcess(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("AdminMerchantDetailsView");
		String message = "";
		HttpSession session = request.getSession();
		String reqType = request.getParameter("submit");
		String merchantId = request.getParameter("merchantId");
		List<MerchantDetails> merchantList = (List<MerchantDetails>) session
				.getAttribute(SystemConstants.MERCHANT_LIST);
		AdminServicesI adminServices = new AdminServices();
		MerchantRegistrationI merchantRegistration = new MerchantRegistration();
		MerchantDetails merchant = merchantRegistration.getMerchantDetails(merchantId, merchantList);
		if (reqType.equals("Block")) {
			message = adminServices.blockUnBlockMerchantProcess(merchant, AdminConstantsI.UNDER_BLACKLIST_PROCESS);
			if (message.equals(AdminConstantsI.REQUEST_ACCEPTED)) {
				merchant.setStatus(AdminConstantsI.UNDER_BLACKLIST_PROCESS);
			}
		} else if (reqType.equals("Undo")) {
			message = adminServices.blockUnBlockMerchantProcess(merchant, AdminConstantsI.ALREADY_MERCHANT);
			if (message.equals(AdminConstantsI.REQUEST_ACCEPTED)) {
				merchant.setStatus(AdminConstantsI.ALREADY_MERCHANT);
			}
		} else if (reqType.equals("Unblock")) {
			message = adminServices.blockUnBlockMerchantProcess(merchant,
					AdminConstantsI.UNDER_CANCLE_BLACKLIST_PROCESS);
		} else if (reqType.equals("Cancle")) {
			message = adminServices.blockUnBlockMerchantProcess(merchant, AdminConstantsI.BLACK_LISTED_REQUEST);
		}
		request.setAttribute(SystemConstants.MERCHANT_DETAILS, merchant);
		if (message.equals(SystemConstants.ERROR)) {
			mav.addObject(SystemConstants.ERROR_MESSAGE, message);
		} else {
			mav.addObject(SystemConstants.SUCCESS_MESSAGE, message);
		}
		return mav;
	}

	// added by Gaurav Sriavstava
	@RequestMapping(value = "/blockRequests", method = RequestMethod.POST)
	public ModelAndView blockRequests(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("AdminBlockMerchantRequests");
		// generate block request List
		AdminServicesI adminServices = new AdminServices();
		List<EmployeeDetails> merchantDetails = adminServices.getBlockRequestMerchantList();
		if (merchantDetails.isEmpty()) {
			String message = SystemConstants.EMPTY_LIST;
			mav.addObject(SystemConstants.ERROR_MESSAGE, message);
		} else {
			mav.addObject(SystemConstants.LIST, merchantDetails);
		}
		return mav;
	}

	// added by Gaurav Sriavstava
	@RequestMapping(value = "/merchantConfirmBlock", method = RequestMethod.POST)
	public ModelAndView merchantConfirmBlock(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("AdminBlockMerchantRequests");
		int userId = Integer.parseInt(request.getParameter("userId"));
		String merchantCode = request.getParameter("empId");
		String status = request.getParameter("status");
		AdminServicesI adminServices = new AdminServices();
		String message = SystemConstants.FALSE;
		try {
			message = adminServices.updateMerchantStatusForBlackListing(userId, merchantCode, status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (message.equals(SystemConstants.TRUE)) {
			List<EmployeeDetails> merchantDetails = adminServices.getBlockRequestMerchantList();
			mav.addObject(SystemConstants.SUCCESS_MESSAGE, AdminMessageConstants.ADMIN_BLOCKED);
			if (!merchantDetails.isEmpty()) {
				mav.addObject(SystemConstants.LIST, merchantDetails);
			}
		} else {
			mav.addObject(SystemConstants.ERROR_MESSAGE, AdminMessageConstants.SOMETHING_WRONG);
		}

		return mav;
	}

	// added by Gaurav Sriavstava
	@RequestMapping(value = "/merchantRerjectBlockRequest", method = RequestMethod.POST)
	public ModelAndView merchantRerjectBlockRequest(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("AdminBlockMerchantRequests");
		int userId = Integer.parseInt(request.getParameter("userId"));
		String status = request.getParameter("status");
		AdminServicesI adminServices = new AdminServices();
		String merchantCode = request.getParameter("empId");
		String message = SystemConstants.FALSE;
		try {
			message = adminServices.updateMerchantStatusForBlackListing(userId, merchantCode, status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (message.equals(SystemConstants.TRUE)) {
			List<EmployeeDetails> merchantDetails = adminServices.getBlockRequestMerchantList();
			mav.addObject(SystemConstants.SUCCESS_MESSAGE, AdminMessageConstants.ADMIN_UNBLOCKED);
			if (!merchantDetails.isEmpty()) {
				mav.addObject(SystemConstants.LIST, merchantDetails);
			}
		} else {
			mav.addObject(SystemConstants.ERROR_MESSAGE, AdminMessageConstants.SOMETHING_WRONG);
		}

		return mav;
	}

	@RequestMapping(value = "/myVehicle", method = RequestMethod.POST)
	public ModelAndView myVehicle(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("MerchantMyVehicle");
		MerchantServicesI merchantServices = new MerchantServices();
		HttpSession session = request.getSession();
		EmployeeDetails employeeDetails = (EmployeeDetails) session.getAttribute(AdminConstantsI.EMPLOYEE_DETAILS);
		ArrayList<VehicleDetails> vehicleList = merchantServices
				.getMerchantVehicleDetails(employeeDetails.getEmployeeId());
		if (vehicleList != null) {
			mav.addObject(SystemConstants.LIST, vehicleList);
			mav.addObject("vehicleDetails", new VehicleDetails());
		} else {
			mav.addObject(SystemConstants.ERROR_MESSAGE, SystemConstants.EMPTY_LIST);
		}
		return mav;
	}

	// Added by Gaurav Srivastava
	@RequestMapping(value = "/myVehicleAction", method = RequestMethod.POST)
	public ModelAndView myVehicleAction(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("vehicleDetails") VehicleDetails vehicleDetails) {
		String message = "";
		String msg = "";
		ModelAndView mav = new ModelAndView("MerchantMyVehicle");
		MerchantServicesI merchantServices = new MerchantServices();
		if (request.getParameter("reqType").equals("Block")) {
			vehicleDetails.setStatus(AdminConstantsI.BLOCKED);
			vehicleDetails.setMessage(AdminMessageConstants.VEHICLE_BLOCKED);
			message = merchantServices.updateVehicleDetails(vehicleDetails);
			msg = AdminMessageConstants.VEHICLE_BLOCKED;
		} else if (request.getParameter("reqType").equals("Unblock")) {
			vehicleDetails.setStatus(AdminConstantsI.ALREADY_ADDED);
			vehicleDetails.setMessage(AdminMessageConstants.ACTIVE);
			message = merchantServices.updateVehicleDetails(vehicleDetails);
			msg = AdminMessageConstants.VEHICLE_UNBLOCKED;
		} else {
			message = merchantServices.deleteVehicleDetails(vehicleDetails);
			msg = AdminMessageConstants.SUCCESSFULL;
		}
		if (message.equals(SystemConstants.TRUE)) {
			HttpSession session = request.getSession();
			EmployeeDetails employeeDetails = (EmployeeDetails) session.getAttribute(AdminConstantsI.EMPLOYEE_DETAILS);
			ArrayList<VehicleDetails> vehicleList = merchantServices
					.getMerchantVehicleDetails(employeeDetails.getEmployeeId());
			if (vehicleList != null) {
				mav.addObject(SystemConstants.SUCCESS_MESSAGE, msg);
				mav.addObject(SystemConstants.LIST, vehicleList);
			} else {
				mav.addObject(SystemConstants.ERROR_MESSAGE, SystemConstants.EMPTY_LIST);
			}
		} else {
			mav.addObject(SystemConstants.ERROR_MESSAGE, message);
		}
		return mav;
	}
}
