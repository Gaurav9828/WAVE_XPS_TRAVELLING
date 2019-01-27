package wave.spring.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import wave.spring.Constants.AdminConstantsI;
import wave.spring.Constants.AdminMessageConstants;
import wave.spring.Constants.MailMessagesConstants;
import wave.spring.Constants.SystemConstants;
import wave.spring.dao.AdminDao;
import wave.spring.dao.AdminDaoI;
import wave.spring.dao.MerchantDao;
import wave.spring.dao.MerchantDaoI;
import wave.spring.model.EmployeeDetails;
import wave.spring.model.MenuDetails;
import wave.spring.model.MerchantDetails;
import wave.spring.model.VehicleAttributes;
import wave.spring.model.VehicleDetails;
import wave.spring.security.Security;
import wave.spring.security.SecurityI;

public class AdminServices implements AdminServicesI {
	AdminDaoI adminDao = new AdminDao();
	MerchantDaoI merchantDao = new MerchantDao();
	public String saveVehicleAttributes(VehicleAttributes vehicleAttributes) {
		String message = SystemConstants.TRUE;
		VehicleAttributes vehicleAttributesTemp = new VehicleAttributes();
		vehicleAttributesTemp = adminDao.checkVehicleAlreadyExist(vehicleAttributes.getVehicleCompany(),
				vehicleAttributes.getVehicleModel());
		if (vehicleAttributesTemp == null) {
			String msg = adminDao.saveVechielDetails(vehicleAttributes);
			if (msg.equals(SystemConstants.TRUE)) {
				message = AdminMessageConstants.VEHICLE_ADDED_SUCCESSFULLY;
			} else {
				message = AdminMessageConstants.SOMETHING_WRONG;
			}
		} else {
			if (vehicleAttributesTemp.getStatus().equals(AdminConstantsI.WAITING_LIST)) {
				message = AdminMessageConstants.VEHICLE_WAITING_LIST;
			} else if (vehicleAttributesTemp.getStatus().equals(AdminConstantsI.ALREADY_ADDED)) {
				message = AdminMessageConstants.VEHICLE_ALREADY_ADDED;
			} else if (vehicleAttributesTemp.getStatus().equals(AdminConstantsI.REJECTED)) {
				message = AdminMessageConstants.VEHICLE_ALREADY_REJECTED;
			}
		}
		return message;
	}

	public ArrayList<VehicleAttributes> getListOfVehicles(String status) {
		ArrayList<VehicleAttributes> listOfVehicles = new ArrayList<VehicleAttributes>();
		listOfVehicles = adminDao.getVehicleList("status", status);
		return listOfVehicles;
	}

	public String updateApproveVehicleAttribute(VehicleAttributes vehicleAttributes) {
		String message = "";
		try {
			adminDao.updateVehicleDetails(vehicleAttributes);
			message = AdminMessageConstants.VEHICLE_APPROVED_SUCCESSFULLY;
		} catch (Exception e) {
			e.printStackTrace();
			message = SystemConstants.SOMETHING_ERROR;
		}
		return message;
	}

	public String updateRemoveVehicleAttribute(VehicleAttributes vehicleAttributes) {
		String message = "";
		try {
			adminDao.updateVehicleDetails(vehicleAttributes);
			message = AdminMessageConstants.VEHICLE_REJECTED_SUCCESSFULLY;
		} catch (Exception e) {
			e.printStackTrace();
			message = SystemConstants.SOMETHING_ERROR;
		}
		return message;
	}

	public String createAdmin(EmployeeDetails employeeDetails) {
		String message = "";
		String[] str = employeeDetails.getMobileNumber().split("");
		String id = str[0] + str[9] + str[8] + str[7];
		String adminId = employeeDetails.getFirstName() + id;
		SecurityI security = new Security();
		HashMap<String, String> map = new HashMap<String, String>();
		map = security.generateCaptcha();
		employeeDetails.setPassword(map.get(SystemConstants.CAPTCHA));
		employeeDetails.setEmployeeId(adminId);
		employeeDetails.setInvalidPasswordAttempts(0);
		MerchantDaoI dao = new MerchantDao();
		EmployeeDetails empDetails = adminDao.getEmployeeDetails(adminId);
		if (empDetails != null) {
			message = AdminMessageConstants.ADMIN_ALREADY_EXISTING;
			return message;
		}
		message = dao.insertMerchantAdmin(employeeDetails);
		if (message.equals(SystemConstants.INACTIVE)) {
			message = SystemConstants.ERROR;
		} else {
			message = AdminMessageConstants.ADMIN_ADDED;
			String messageToBeSent = MailMessagesConstants.ADMIN_ADDITION_SUCCESS_MSG
					+ MailMessagesConstants.MERCHANT_LOGIN_URL + "\n" + AdminConstantsI.EMP_ID + ":" + adminId + "\n"
					+ AdminConstantsI.PASSWORD + ":" + map.get(SystemConstants.CAPTCHA);
			map.put(AdminConstantsI.TO, employeeDetails.getEmailId());
			map.put(AdminConstantsI.SUBJECT, MailMessagesConstants.ADMIN_ADDITION_SUCCESSFUL);
			map.put(AdminConstantsI.MESSAGE, messageToBeSent);
			String msg = security.sendMail(map);
			if (!msg.equals(SystemConstants.ACTIVE)) {
				message = message + SystemConstants.MAIL_ERROR;
			}
		}
		return message;
	}

	public ArrayList<EmployeeDetails> getAdminDetailsList() {
		ArrayList<EmployeeDetails> list = new ArrayList<EmployeeDetails>();
		list = adminDao.getEmployeeDetailsList();
		return list;
	}

	public String updateAdminAttributes(EmployeeDetails empDetails) {
		String message = "";
		try {
			adminDao.updateEmployeeDetails(empDetails);
			message = SystemConstants.TRUE;
		} catch (Exception e) {
			e.printStackTrace();
			message = SystemConstants.SOMETHING_ERROR;
		}
		return message;
	}

	// added by Gaurav Srivastava
	public HashMap<String, Object> getMerchants() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<MerchantDetails> merchantList = adminDao.getAcceptedMerchantList();
		if (merchantList.isEmpty()) {
			map.put(SystemConstants.ERROR_MESSAGE, SystemConstants.FALSE);
		} else {
			map.put(SystemConstants.ERROR_MESSAGE, SystemConstants.TRUE);
			map.put(SystemConstants.LIST, merchantList);
		}
		return map;
	}

	public String blockUnBlockMerchantProcess(MerchantDetails merchantDetails, String status) {
		String message = SystemConstants.FALSE;
		MerchantRegistrationI merchantRegistration = new MerchantRegistration();
		try {
			String empId = merchantRegistration.generateMerchantId(merchantDetails);
			EmployeeDetails employeeDetails = adminDao.getEmployeeDetails(empId);
			merchantDetails.setStatus(status);
			merchantDao.updateMerchant(merchantDetails);
			////udpade merchant status
			message = adminDao.updateEmployeeStatus(employeeDetails.getUserId(), status);
			if(message.equals(SystemConstants.TRUE)){
				message = AdminConstantsI.REQUEST_ACCEPTED;
			}
		} catch (Exception e) {
			message = SystemConstants.ERROR;
			e.printStackTrace();
		}
		return message;
	}

	public List<EmployeeDetails> getBlockRequestMerchantList() {
		List<EmployeeDetails> merchantDetails = null;
		try {
			merchantDetails = adminDao.getUnderProcessBlockMerchantList(AdminConstantsI.UNDER_BLACKLIST_PROCESS,AdminConstantsI.UNDER_CANCLE_BLACKLIST_PROCESS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return merchantDetails;
	}

	public String updateMerchantStatusForBlackListing(int userId,String merchantCode, String status) {
		String message = SystemConstants.FALSE;
		try {
			message = adminDao.updateEmployeeStatus(userId, status);
			MerchantDetails merchantDetails = merchantDao.getMerchantDetails(merchantCode);
			merchantDetails.setStatus(status);
			merchantDao.updateMerchant(merchantDetails);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}
	
	public ArrayList<VehicleDetails> getListOfWaitingMarchantVecihles(String status){
		ArrayList<VehicleDetails> vehicleList = new ArrayList<VehicleDetails>();
		try {
			vehicleList = merchantDao.getListOfVehicleFromVehicleDetails(status);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return vehicleList;
	}
	
	public ArrayList<ArrayList> getMenuDetails(){
		ArrayList<MenuDetails> menuDetails = new ArrayList<MenuDetails>();
		menuDetails = adminDao.getMenuDetails();
		ArrayList<ArrayList> list = new ArrayList<ArrayList>();
		for(MenuDetails va : menuDetails) {
			ArrayList l = new ArrayList();
			if(va.getMenuAdminLevel().equals("1")) {
				l.add(AdminConstantsI.MERCHANT);
			}else if(va.getMenuAdminLevel().equals("2")) {
				l.add(AdminConstantsI.NORMAL_ADMIN);
			}else if(va.getMenuAdminLevel().equals("3")) {
				l.add(AdminConstantsI.SUPER_ADMIN);
			}else {
				l.add(AdminConstantsI.ADMIN);
			}
			l.add(va.getMenuName());
			l.add(va.getMenuVisibility());
			l.add(va.getMenuId());
			l.add(va.getMenuAction());
			l.add(va.getMenuDomain());
			l.add(va.getMenuAdminLevel());
			list.add(l);
		}
		return list;
	}
	
	
	public String updateMenuDetails(MenuDetails menuDetails) {
		String message = adminDao.updateMenuDetails(menuDetails);
		if(message.equals(SystemConstants.TRUE)) {
			message = AdminMessageConstants.SUCCESSFULL;
		}
		return message;
	}
	
	public ArrayList<VehicleAttributes> getAdminSubmittedVehicles(String employeeId) {
		ArrayList<VehicleAttributes> vehicleList= adminDao.getVehicleList("addedBy",employeeId);
		return vehicleList;
	}


}
