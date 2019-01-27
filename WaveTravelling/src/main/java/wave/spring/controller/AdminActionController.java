package wave.spring.controller;

import java.io.IOException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import wave.spring.Constants.AdminConstantsI;
import wave.spring.Constants.AdminMessageConstants;
import wave.spring.Constants.SystemConstants;
import wave.spring.model.EmployeeDetails;
import wave.spring.model.MenuDetails;
import wave.spring.model.VehicleAttributes;
import wave.spring.model.VehicleDetails;
import wave.spring.security.Security;
import wave.spring.security.SecurityI;
import wave.spring.services.AdminServices;
import wave.spring.services.AdminServicesI;
import wave.spring.services.MerchantServices;
import wave.spring.services.MerchantServicesI;

@Controller

public class AdminActionController {
	@Autowired
	// added by Gaurav Sriavstava

	@RequestMapping(value = "/newVehicleAdd", method = RequestMethod.POST)
	public ModelAndView addNewVehicle(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("AdminAddNewVehicle");
		return mav;
	}
	
	@RequestMapping(value = "/viewAdminProfile", method = RequestMethod.POST)
	public ModelAndView viewAdminProfile(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("CommonProfileViewPage");
		EmployeeDetails empDetails = getCurrentEmployeeDetails(request, response);
		mav.addObject(SystemConstants.LIST, empDetails);
		return mav;
	}
	
	@RequestMapping(value = "/editProfile", method = RequestMethod.POST)
	public ModelAndView editProfile(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("CommonEditProfilePage");
		EmployeeDetails empDetails = getCurrentEmployeeDetails(request, response);
		mav.addObject(SystemConstants.LIST, empDetails);
		return mav;
	}
	
	@RequestMapping(value = "/confirmEditProfile", method = RequestMethod.POST)
	public ModelAndView confirmEditProfile(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute(SystemConstants.LIST) EmployeeDetails employeeDetails) {
		EmployeeDetails empDetails = getCurrentEmployeeDetails(request, response);
		empDetails.setFirstName(employeeDetails.getFirstName());
		empDetails.setLastName(employeeDetails.getLastName());
		empDetails.setMobileNumber(employeeDetails.getMobileNumber());
		empDetails.setEmailId(employeeDetails.getEmailId());
		ModelAndView mav = new ModelAndView("CommonEditProfilePage");
		String pass2 = request.getParameter("pass2");
		SecurityI security = new Security();
		if(security.valueEncrptyer(pass2).equals(empDetails.getPassword())) {	
			if(!employeeDetails.getPassword().equals("00000000")) {
				empDetails.setPassword(security.valueEncrptyer(employeeDetails.getPassword()));
			}
			AdminServicesI adminService = new AdminServices();
			String message = adminService.updateAdminAttributes(empDetails);
			if(message.equals(SystemConstants.TRUE)) {
				mav.addObject(SystemConstants.SUCCESS_MESSAGE, AdminMessageConstants.SUCCESSFULL);
				HttpSession session = request.getSession();
				session.setAttribute(AdminConstantsI.EMPLOYEE_DETAILS, empDetails);
				mav.addObject(SystemConstants.LIST, empDetails);
			}else {
				mav.addObject(SystemConstants.ERROR_MESSAGE, AdminMessageConstants.SOMETHING_WRONG);
			}
		}else {
			mav.addObject(SystemConstants.ERROR_MESSAGE, AdminMessageConstants.INVALID_PASSWORD);
		}
		return mav;
	}

	@RequestMapping(value = "/newVehicleAddProcess", method = RequestMethod.POST)
	public  ModelAndView uploadMultipleFileHandler(@RequestParam("vehicleCompany") String vehicleComapny,
			@RequestParam("vehicleModel") String vehicleModel, @RequestParam("seats") Integer seats,
			@RequestParam("image") MultipartFile image, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("AdminAddNewVehicle");

		if (!image.getContentType().equals("image/png") && !image.getContentType().equals("image/jpeg")
				&& !image.getContentType().equals("image/jpg")) {
			mav.addObject(SystemConstants.ERROR_MESSAGE, AdminMessageConstants.PLEASE_CHOOSE_A_FILE_TYPE);
		}
		EmployeeDetails empDetails = getCurrentEmployeeDetails(request, response);
		try {
			VehicleAttributes vehicleAttributes = new VehicleAttributes();
			vehicleAttributes.setSeats(seats);
			vehicleAttributes.setVehicleCompany(vehicleComapny.toUpperCase());
			vehicleAttributes.setVehicleModel(vehicleModel.toUpperCase());
			vehicleAttributes.setImage(image.getBytes());
			vehicleAttributes.setStatus(AdminConstantsI.WAITING_LIST);
			vehicleAttributes.setAddedBy(empDetails.getEmployeeId());
			AdminServicesI adminServices = new AdminServices();
			String result = adminServices.saveVehicleAttributes(vehicleAttributes);
			if (!result.equals(AdminMessageConstants.VEHICLE_ADDED_SUCCESSFULLY)) {
				mav.addObject(SystemConstants.ERROR_MESSAGE, result);
			} else {
				mav.addObject(SystemConstants.SUCCESS_MESSAGE, result);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// fileUploadDao.save(vehicleAttributes);
		return mav;
	}

	@RequestMapping(value = "/approveNewVehicle", method = RequestMethod.POST)
	public ModelAndView approveNewVehicle(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("AdminApproveNewVehicle");
		ArrayList<VehicleAttributes> listOfVehicles = new ArrayList<VehicleAttributes>();
		AdminServicesI adminServices = new AdminServices();
		listOfVehicles = adminServices.getListOfVehicles(AdminConstantsI.WAITING_LIST);
		if (listOfVehicles.isEmpty()) {
			mav.addObject(SystemConstants.ERROR_MESSAGE, SystemConstants.EMPTY_LIST);
		} else {
			SecurityI security = new Security();
			try {
				listOfVehicles = security.getImageBlob(listOfVehicles);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ArrayList<ArrayList> list = new ArrayList<ArrayList>();
			for(VehicleAttributes vehicle : listOfVehicles) {
				ArrayList l = new ArrayList();
				l.add(vehicle.getID());
				l.add(vehicle.getImage());
				list.add(l);
			}
			HttpSession session = request.getSession();
			session.setAttribute(SystemConstants.IMAGE_AND_ID, list);
			mav.addObject("vehicleAttribute", new VehicleAttributes());
			mav.addObject(SystemConstants.LIST, listOfVehicles);
		}
		return mav;
	}

	@RequestMapping(value = "/confirmApproval", method = RequestMethod.POST)
	public ModelAndView confirmApproveNewVehicle(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("vehicleAttribute") VehicleAttributes vehicleAttributes) {
		ModelAndView mav = new ModelAndView("AdminConfirmApproveNewVehicle");
		mav.addObject("vehicleAttribute", new VehicleAttributes());
		mav.addObject(SystemConstants.LIST, vehicleAttributes);
		return mav;
	}

	@RequestMapping(value = "/approveVehicle", method = RequestMethod.POST)
	public ModelAndView approveVecile(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("vehicleAttribute") VehicleAttributes vehicleAttributes){
		ModelAndView mav = new ModelAndView("AdminApproveNewVehicle");
		AdminServicesI adminServices = new AdminServices();
		HttpSession session = request.getSession();
		EmployeeDetails empDetails = getCurrentEmployeeDetails(request, response);
		ArrayList<ArrayList> list = (ArrayList<ArrayList>) session.getAttribute(SystemConstants.IMAGE_AND_ID);
		byte[] image = null ;
		for(ArrayList l : list) {
			if((int)l.get(0) == vehicleAttributes.getID()) {
				image = (byte[]) l.get(1);
				break;
			}
		}
		vehicleAttributes.setImage(image);
		vehicleAttributes.setApprovedBy(empDetails.getEmployeeId());
		vehicleAttributes.setStatus(AdminConstantsI.ALREADY_ADDED);
		vehicleAttributes.setBase64Image("");
		String message = adminServices.updateApproveVehicleAttribute(vehicleAttributes);
		ArrayList<VehicleAttributes> listOfVehicles = new ArrayList<VehicleAttributes>();
		if (!message.equals(SystemConstants.SOMETHING_ERROR)) {
			listOfVehicles = adminServices.getListOfVehicles(AdminConstantsI.WAITING_LIST);
			if (listOfVehicles.isEmpty()) {
				mav.addObject(SystemConstants.ERROR_MESSAGE, SystemConstants.EMPTY_LIST);
			} else {
				SecurityI security = new Security();
				try {
					listOfVehicles = security.getImageBlob(listOfVehicles);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mav.addObject("vehicleAttribute", new VehicleAttributes());
				mav.addObject(SystemConstants.LIST, listOfVehicles);
				mav.addObject(SystemConstants.SUCCESS_MESSAGE, message);

			}
		} else {
			mav.addObject(SystemConstants.ERROR_MESSAGE, message);
		}
		return mav;
	}

	@RequestMapping(value = "/confirmReject", method = RequestMethod.POST)
	public ModelAndView confirmRejectNewVehicle(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("vehicleAttribute") VehicleAttributes vehicleAttributes) {
		ModelAndView mav = new ModelAndView("AdminConfirmNewVehicleRemoval");
		mav.addObject("vehicleAttribute", new VehicleAttributes());
		mav.addObject(SystemConstants.LIST, vehicleAttributes);
		return mav;
	}

	@RequestMapping(value = "/rejectVehicle", method = RequestMethod.POST)
	public ModelAndView rejectVehicle(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("vehicleAttribute") VehicleAttributes vehicleAttributes){
		ModelAndView mav = new ModelAndView("AdminApproveNewVehicle");
		AdminServicesI adminServices = new AdminServices();
		HttpSession session = request.getSession();
		EmployeeDetails empDetails = getCurrentEmployeeDetails(request, response);
		ArrayList<ArrayList> list = (ArrayList<ArrayList>) session.getAttribute(SystemConstants.IMAGE_AND_ID);
		byte[] image = null ;
		for(ArrayList l : list) {
			if((int)l.get(0) == vehicleAttributes.getID()) {
				image = (byte[]) l.get(1);
				break;
			}
		}
		vehicleAttributes.setImage(image);
		vehicleAttributes.setRejectedBy(empDetails.getEmployeeId());
		vehicleAttributes.setStatus(AdminConstantsI.REJECTED);
		vehicleAttributes.setBase64Image("");
		String message = adminServices.updateRemoveVehicleAttribute(vehicleAttributes);
		ArrayList<VehicleAttributes> listOfVehicles = new ArrayList<VehicleAttributes>();
		if (!message.equals(SystemConstants.SOMETHING_ERROR)) {
			listOfVehicles = adminServices.getListOfVehicles(AdminConstantsI.WAITING_LIST);
			if (listOfVehicles.isEmpty()) {
				mav.addObject(SystemConstants.ERROR_MESSAGE, SystemConstants.EMPTY_LIST);
			} else {
				SecurityI security = new Security();
				try {
					listOfVehicles = security.getImageBlob(listOfVehicles);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mav.addObject("vehicleAttribute", new VehicleAttributes());
				mav.addObject(SystemConstants.LIST, listOfVehicles);
				mav.addObject(SystemConstants.SUCCESS_MESSAGE, message);

			}
		} else {
			mav.addObject(SystemConstants.ERROR_MESSAGE, message);
		}
		return mav;
	}
	
	public ArrayList<VehicleAttributes> vehicleListForView(ArrayList<String> statusList){
		ArrayList<VehicleAttributes> listOfVehicles = new ArrayList<VehicleAttributes>();
		AdminServicesI adminServices = new AdminServices();
		try {
			for(String s : statusList) {
				ArrayList<VehicleAttributes> vehicles = new ArrayList<VehicleAttributes>();
				vehicles = adminServices.getListOfVehicles(s);
				if(!vehicles.isEmpty()) {
					listOfVehicles.addAll(vehicles);
				}
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return listOfVehicles;
	}

	@RequestMapping(value = "/viewVehicles", method = RequestMethod.POST)
	public ModelAndView viewVehicles(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mav = new ModelAndView("AdminViewVehicles");
		ArrayList<String> statusList = new ArrayList<String>();
		statusList.add(AdminConstantsI.ALREADY_ADDED);
		statusList.add(AdminConstantsI.BLOCKED);
		ArrayList<VehicleAttributes> listOfVehicles = vehicleListForView(statusList);
		if (listOfVehicles.isEmpty()) {
			mav.addObject(SystemConstants.ERROR_MESSAGE, SystemConstants.EMPTY_LIST);
		} else {
			SecurityI security = new Security();
			listOfVehicles = security.getImageBlob(listOfVehicles);
			ArrayList<ArrayList> list = new ArrayList<ArrayList>();
			for(VehicleAttributes vehicle : listOfVehicles) {
				ArrayList l = new ArrayList();
				l.add(vehicle.getID());
				l.add(vehicle.getImage());
				list.add(l);
			}
			HttpSession session = request.getSession();
			session.setAttribute(SystemConstants.IMAGE_AND_ID, list);
			mav.addObject("vehicleAttribute", new VehicleAttributes());
			mav.addObject(SystemConstants.LIST, listOfVehicles);
		}
		return mav;
	}
	
	@RequestMapping(value = "/blockUnblockVehicle", method = RequestMethod.POST)
	public ModelAndView unblockVehicle(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("vehicleAttribute") VehicleAttributes vehicleAttributes) {
		ModelAndView mav = new ModelAndView("AdminViewVehicles");
		try {
			HttpSession session = request.getSession();
			AdminServicesI adminServices = new AdminServices();
			EmployeeDetails empDetails = getCurrentEmployeeDetails(request, response);
			vehicleAttributes.setBase64Image("");
			if (vehicleAttributes.getStatus().equals(AdminConstantsI.ALREADY_ADDED)) {
				vehicleAttributes.setUnblockedBy(empDetails.getEmployeeId());
			} else {
				vehicleAttributes.setBlockedBy(empDetails.getEmployeeId());
			}
			ArrayList<ArrayList> list = (ArrayList<ArrayList>) session.getAttribute(SystemConstants.IMAGE_AND_ID);
			byte[] image = null ;
			for(ArrayList l : list) {
				if((int)l.get(0) == vehicleAttributes.getID()) {
					image = (byte[]) l.get(1);
					break;
				}
			}
			vehicleAttributes.setImage(image);
			String message = adminServices.updateApproveVehicleAttribute(vehicleAttributes);
			if (!message.equals(SystemConstants.SOMETHING_ERROR)) {
				ArrayList<String> statusList = new ArrayList<String>();
				if (vehicleAttributes.getStatus().equals(AdminConstantsI.ALREADY_ADDED)) {
					mav.addObject(SystemConstants.SUCCESS_MESSAGE, AdminMessageConstants.VEHICLE_UNBLOCKED);
					statusList.add(AdminConstantsI.BLOCKED);
					statusList.add(AdminConstantsI.ALREADY_ADDED);
				} else if (vehicleAttributes.getStatus().equals(AdminConstantsI.WAITING_LIST)) {
					mav.addObject(SystemConstants.SUCCESS_MESSAGE, AdminMessageConstants.VEHICLE_ADDED_TO_WAITING_APPROVALS);
					statusList.add(AdminConstantsI.REJECTED);
				} else {
					mav.addObject(SystemConstants.SUCCESS_MESSAGE, AdminMessageConstants.VEHICLE_BLOCKED);
					statusList.add(AdminConstantsI.BLOCKED);
					statusList.add(AdminConstantsI.ALREADY_ADDED);
				}
				ArrayList<VehicleAttributes> listOfVehicles = vehicleListForView(statusList);
				if (listOfVehicles.isEmpty()) {
					mav.addObject(SystemConstants.ERROR_MESSAGE, SystemConstants.EMPTY_LIST);
				} else {
					SecurityI security = new Security();
					listOfVehicles = security.getImageBlob(listOfVehicles);
					mav.addObject("vehicleAttribute", new VehicleAttributes());
					mav.addObject(SystemConstants.LIST, listOfVehicles);
				}
			}else {
				mav.addObject(SystemConstants.ERROR_MESSAGE, message);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return mav;
	}

	@RequestMapping(value = "/viewRejectedVehicles", method = RequestMethod.POST)
	public ModelAndView viewRejectedVehicles(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		ModelAndView mav = new ModelAndView("AdminViewVehicles");
		try {
			ArrayList<VehicleAttributes> listOfVehicles = new ArrayList<VehicleAttributes>();
			AdminServicesI adminServices = new AdminServices();
			listOfVehicles = adminServices.getListOfVehicles(AdminConstantsI.REJECTED);
			mav.addObject("vehicleAttribute", new VehicleAttributes());
			if (listOfVehicles.isEmpty()) {
				mav.addObject(SystemConstants.ERROR_MESSAGE, SystemConstants.EMPTY_LIST);
			} else {
				SecurityI security = new Security();
				listOfVehicles = security.getImageBlob(listOfVehicles);
				ArrayList<ArrayList> list = new ArrayList<ArrayList>();
				for(VehicleAttributes vehicle : listOfVehicles) {
					ArrayList l = new ArrayList();
					l.add(vehicle.getID());
					l.add(vehicle.getImage());
					list.add(l);
				}
				HttpSession session = request.getSession();
				session.setAttribute(SystemConstants.IMAGE_AND_ID, list);
				mav.addObject(SystemConstants.LIST, listOfVehicles);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}

	@RequestMapping(value = "/createAdmin", method = RequestMethod.POST)
	public ModelAndView createAdmin(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("AdminCreateAdmin");
		mav.addObject("AdminCreateAdmin", new EmployeeDetails());
		return mav;
	}

	@RequestMapping(value = "/confirmCreateAdmin", method = RequestMethod.POST)
	public ModelAndView confirmCreateAdmin(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("AdminCreateAdmin") EmployeeDetails emplyeeDetails) {
		ModelAndView mav = new ModelAndView("AdminConfirmCreateAdmin");
		mav.addObject("AdminConfirmCreateAdmin", new EmployeeDetails());
		mav.addObject(AdminConstantsI.EMPLOYEE_DETAILS, emplyeeDetails);
		return mav;
	}

	@RequestMapping(value = "/doneCreateAdmin", method = RequestMethod.POST)
	public ModelAndView doneCreateAdmin(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("AdminConfirmCreateAdmin") EmployeeDetails employeeDetails) {
		ModelAndView mav = new ModelAndView("AdminCreateAdmin");
		AdminServicesI adminServices = new AdminServices();
		EmployeeDetails empDetails = getCurrentEmployeeDetails(request, response);
		employeeDetails.setAddedByEmpId(empDetails.getEmployeeId());
		String message = adminServices.createAdmin(employeeDetails);
		if (message.equals(SystemConstants.ERROR)) {
			mav.addObject(SystemConstants.ERROR_MESSAGE, message);
		} else if (message.equals(AdminMessageConstants.ADMIN_ALREADY_EXISTING)) {
			mav.addObject(SystemConstants.ERROR_MESSAGE, message);
		} else {
			mav.addObject(SystemConstants.SUCCESS_MESSAGE, message);
		}
		mav.addObject("AdminCreateAdmin", new EmployeeDetails());
		return mav;
	}

	@RequestMapping(value = "/adminDetails", method = RequestMethod.POST)
	public ModelAndView adminDetails(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("AdminAdminDetails");
		try {
			mav.addObject("employeeDetails", new EmployeeDetails());
			AdminServicesI adminServices = new AdminServices();
			ArrayList<EmployeeDetails> list = new ArrayList<EmployeeDetails>();
			list = adminServices.getAdminDetailsList();
			if (list == null) {
				mav.addObject(SystemConstants.ERROR_MESSAGE, SystemConstants.EMPTY_LIST);
			} else {
				mav.addObject(SystemConstants.LIST, list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}

	@RequestMapping(value = "/blockUnblockAdmin", method = RequestMethod.POST)
	public ModelAndView blockUnblockAdmin(HttpServletRequest request, HttpServletResponse response,
			 @ModelAttribute("employeeDetails") EmployeeDetails employeeDetails) {
		ModelAndView mav = new ModelAndView("AdminAdminDetails");
		AdminServicesI adminServices = new AdminServices();
		String message = adminServices.updateAdminAttributes(employeeDetails);
		if (message.equals(SystemConstants.TRUE)) {
			if (employeeDetails.getStatus().equals(SystemConstants.ACTIVE)) {
				mav.addObject(SystemConstants.SUCCESS_MESSAGE, AdminMessageConstants.ADMIN_UNBLOCKED);
			} else {
				mav.addObject(SystemConstants.SUCCESS_MESSAGE, AdminMessageConstants.ADMIN_BLOCKED);
			}
			ArrayList<EmployeeDetails> list = new ArrayList<EmployeeDetails>();
			list = adminServices.getAdminDetailsList();
			if (list == null) {
				mav.addObject(SystemConstants.ERROR_MESSAGE, SystemConstants.EMPTY_LIST);
			} else {
				mav.addObject(SystemConstants.LIST, list);
			}
		} else {
			mav.addObject(SystemConstants.ERROR_MESSAGE, message);
		}
		return mav;
	}

	public EmployeeDetails getEmployeeDetailsFromList(ArrayList<EmployeeDetails> list, int id) {
		EmployeeDetails empDetails = new EmployeeDetails();
		for (EmployeeDetails ed : list) {
			if (ed.getUserId() == id) {
				empDetails = ed;
				break;
			}
		}
		return empDetails;
	}

	@RequestMapping(value = "/vehicleApprovalRequests", method = RequestMethod.POST)
	public ModelAndView vehicleApprovalRequests(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("AdminApproveMerchantVehicle");
		mav.addObject("vehicleDetails", new VehicleDetails());
		AdminServicesI adminServices = new AdminServices();
		ArrayList<VehicleDetails> vehicleList = new ArrayList<VehicleDetails>();
		vehicleList = adminServices.getListOfWaitingMarchantVecihles(AdminConstantsI.WAITING_LIST);
		if (vehicleList != null) {
			mav.addObject(SystemConstants.LIST, vehicleList);
		} else {
			mav.addObject(SystemConstants.ERROR_MESSAGE, SystemConstants.EMPTY_LIST);
		}

		return mav;
	}

	@RequestMapping(value = "/AdminApproveRejectConfirmationOfVehicle", method = RequestMethod.POST)
	public ModelAndView AdminApproveRejectConfirmationOfVehicle(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("vehicleDetails") VehicleDetails vehicleDetails) {
		ModelAndView mav = new ModelAndView("AdminApproveRejectMerchantVehicleConfirmation");
		request.setAttribute("reqType", request.getParameter("reqType"));
		mav.addObject(SystemConstants.LIST, vehicleDetails);
		return mav;
	}

	// Added by Gaurav Srivastava
	@RequestMapping(value = "/AdminApproveConfirmationOfVehicle", method = RequestMethod.POST)
	public ModelAndView AdminApproveConfirmationOfVehicle(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute(SystemConstants.LIST) VehicleDetails vehicleDetails) {
		ModelAndView mav = new ModelAndView("AdminApproveMerchantVehicle");
		MerchantServicesI marchantServices = new MerchantServices();
		EmployeeDetails employeeDetails = getCurrentEmployeeDetails(request, response);
		LocalTime time = LocalTime.now();
		LocalDate date = LocalDate.now();
		vehicleDetails.setApprovedBy(employeeDetails.getEmployeeId());
		vehicleDetails.setAddedOn(time.toString() + " " + date.toString());
		vehicleDetails.setStatus(AdminConstantsI.ALREADY_ADDED);
		vehicleDetails.setMessage(AdminMessageConstants.ACTIVE);
		String message = marchantServices.updateVehicleDetails(vehicleDetails);
		if (message.equals(SystemConstants.TRUE)) {
			AdminServicesI adminServices = new AdminServices();
			ArrayList<VehicleDetails> vehicleList = adminServices
					.getListOfWaitingMarchantVecihles(AdminConstantsI.WAITING_LIST);
			if (vehicleList != null) {
				mav.addObject(SystemConstants.LIST, vehicleList);
			} else {
				mav.addObject(SystemConstants.ERROR_MESSAGE, SystemConstants.EMPTY_LIST);
			}
			mav.addObject(SystemConstants.SUCCESS_MESSAGE, AdminMessageConstants.VEHICLE_ADDED_SUCCESSFULLY);
		} else {
			mav.addObject(SystemConstants.ERROR_MESSAGE, message);
		}
		return mav;
	}

	// Added by Gaurav Srivastava
	@RequestMapping(value = "/AdminRejectConfirmationOfVehicle", method = RequestMethod.POST)
	public ModelAndView AdminRejectConfirmationOfVehicle(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("vehicleDetails") VehicleDetails vehicleDetails) {
		ModelAndView mav = new ModelAndView("AdminApproveMerchantVehicle");
		MerchantServicesI marchantServices = new MerchantServices();
		EmployeeDetails employeeDetails = getCurrentEmployeeDetails(request, response);
		vehicleDetails.setRejectedBy(employeeDetails.getEmployeeId());
		vehicleDetails.setStatus(AdminConstantsI.REJECTED);
		String message = marchantServices.updateVehicleDetails(vehicleDetails);
		if (message.equals(SystemConstants.TRUE)) {
			AdminServicesI adminServices = new AdminServices();
			ArrayList<VehicleDetails> vehicleList = adminServices
					.getListOfWaitingMarchantVecihles(AdminConstantsI.WAITING_LIST);
			if (vehicleList != null) {
				mav.addObject(SystemConstants.LIST, vehicleList);
			} else {
				mav.addObject(SystemConstants.ERROR_MESSAGE, SystemConstants.EMPTY_LIST);
			}
			mav.addObject(SystemConstants.SUCCESS_MESSAGE, AdminMessageConstants.VEHICLE_REJECTED_SUCCESSFULLY);
		} else {
			mav.addObject(SystemConstants.ERROR_MESSAGE, message);
		}
		return mav;
	}
	@RequestMapping(value = "/adminMenuVisibility", method = RequestMethod.POST)
	public ModelAndView adminMenuVisibility(HttpServletRequest requeset, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("AdminMenuVisibility");
		mav.addObject("menuDetails", new MenuDetails());
		AdminServicesI adminServices = new AdminServices();
		ArrayList<ArrayList> menuList = adminServices.getMenuDetails();
		ArrayList aList = new ArrayList();
		aList.add(AdminConstantsI.MERCHANT);
		aList.add(AdminConstantsI.NORMAL_ADMIN);
		aList.add(AdminConstantsI.SUPER_ADMIN);
		aList.add(AdminConstantsI.ADMIN);
		mav.addObject("menuAdmin", aList);
		mav.addObject(SystemConstants.LIST, menuList);
		return mav;
	}
	
	@RequestMapping(value = "/updateMenuDetails", method = RequestMethod.POST)
	public ModelAndView updateMenuDetails(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("menuDetails") MenuDetails menuDetails) {
		ModelAndView mav = new ModelAndView("AdminMenuVisibility");
		mav.addObject("menuDetails", new MenuDetails());
		AdminServicesI adminServices = new AdminServices();
		if(menuDetails.getMenuName().equals("Menu Visibility")) {
			mav.addObject(SystemConstants.ERROR_MESSAGE, AdminMessageConstants.BLOCKED_ATTEMPT);
		}else {
			if(menuDetails.getMenuVisibility().equals(AdminConstantsI.HIDE)) {
				menuDetails.setMenuVisibility(SystemConstants.INACTIVE);
			}else {
				menuDetails.setMenuVisibility(SystemConstants.ACTIVE);
			}
			String message = adminServices.updateMenuDetails(menuDetails);
			
			if(message.equals(SystemConstants.FALSE)) {
				mav.addObject(SystemConstants.ERROR_MESSAGE, AdminMessageConstants.PLEASE_TRY_AGAIN);
			}else {
				mav.addObject(SystemConstants.SUCCESS_MESSAGE, message);
			}
		}
		ArrayList<ArrayList> menuList = adminServices.getMenuDetails();
		ArrayList aList = new ArrayList();
		aList.add(AdminConstantsI.MERCHANT);
		aList.add(AdminConstantsI.NORMAL_ADMIN);
		aList.add(AdminConstantsI.SUPER_ADMIN);
		aList.add(AdminConstantsI.ADMIN);
		mav.addObject("menuAdmin", aList);
		mav.addObject(SystemConstants.LIST, menuList);
		return mav;
	}
	
	@RequestMapping(value = "/mySubmissions", method = RequestMethod.POST)
	public ModelAndView mySubmissions(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("AdminMyVechileSubmissions");
		EmployeeDetails empDetails = getCurrentEmployeeDetails(request, response);
		AdminServicesI adminServices = new AdminServices();
		ArrayList<VehicleAttributes> vehicleList = adminServices.getAdminSubmittedVehicles(empDetails.getEmployeeId());
		if(vehicleList.isEmpty()) {
			mav.addObject(SystemConstants.ERROR_MESSAGE, SystemConstants.EMPTY_LIST);
		}else {
			SecurityI security = new Security();
			try {
				vehicleList = security.getImageBlob(vehicleList);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mav.addObject(SystemConstants.LIST, vehicleList);
		}
		return mav;
	}

	public EmployeeDetails getCurrentEmployeeDetails(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		EmployeeDetails empDetails = (EmployeeDetails) session.getAttribute(AdminConstantsI.EMPLOYEE_DETAILS);
		return empDetails;
	}
}
