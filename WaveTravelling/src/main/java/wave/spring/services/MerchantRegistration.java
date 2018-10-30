package wave.spring.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import wave.spring.Constants.AdminConstantsI;
import wave.spring.Constants.SystemConstants;
import wave.spring.dao.MerchantDao;
import wave.spring.dao.MerchantDaoI;
import wave.spring.model.EmployeeDetails;
import wave.spring.model.MerchantDetails;
import wave.spring.security.Security;
import wave.spring.security.SecurityI;

public class MerchantRegistration implements MerchantRegistrationI {
	MerchantDaoI dao = new MerchantDao();
	SecurityI security = new Security();

	public String freshRegistrationRequest(MerchantDetails merchantDetails) {
		String message = "";
		MerchantDetails details = dao.merchantAlreadyExist(merchantDetails.getMobileNumber());
		if (details != null) {
			if (details.getStatus().equals(AdminConstantsI.ALREADY_REGISTERED)) {
				message = AdminConstantsI.ALREADY_REGISTERED;
			} else if (details.getStatus().equals(AdminConstantsI.ALREADY_MERCHANT)) {
				message = AdminConstantsI.ALREADY_MERCHANT;
			} else if (details.getStatus().equals(AdminConstantsI.BLACK_LISTED_REQUEST)) {
				message = AdminConstantsI.BLACK_LISTED_REQUEST;
			}
		} else {
			merchantDetails.setStatus(AdminConstantsI.ALREADY_REGISTERED);
			LocalDate appointmentDate1 = LocalDate.now().plusDays(7);
			LocalDate appointmentDate2 = LocalDate.now().plusDays(9);
			LocalDate appointmentDate3 = LocalDate.now().plusDays(11);
			merchantDetails.setAppointementDates(
					(appointmentDate1 + ", " + appointmentDate2 + ", " + appointmentDate3).toString());
			// submit the application
			merchantDetails.setSubmissionDate(LocalDate.now());
			message = dao.applyForWatingMerchantship(merchantDetails);
			if (message.equals(AdminConstantsI.REGISTRAION_REQUEST_SUCCESSFUL)) {
				// mail generation

				String messageToBeSent = SystemConstants.MERCHANT_REGISTRATION_APPOINTEMENT_MSG_ONE + " "
						+ appointmentDate1 + ", " + appointmentDate2 + " or " + appointmentDate3 + ". "
						+ SystemConstants.MERCHANT_REGISTRATION_APPOINTEMENT_MSG_TWO;

				HashMap<String, String> map = new HashMap();
				map.put("to", merchantDetails.getEmailId());
				map.put("from", SystemConstants.ADMIN_SENDER_MAIL_ID);
				map.put("subject", SystemConstants.MERCHANT_REGISTRATION_APPOINTEMENT);
				map.put("msg", messageToBeSent);
				map.put("password", SystemConstants.SENDER_PASSWORD);
				String msg = security.sendMail(map);
				if (!msg.equals(SystemConstants.ACTIVE)) {
					message = SystemConstants.MAIL_ERROR;
				}
			}
		}
		return message;
	}

	// added by Gaurav Srivastava
	public HashMap getMerchantApplications() {
		HashMap map = new HashMap();
		List<MerchantDetails> applicantList = dao.getAcceptedMerchantList();
		if (applicantList.isEmpty()) {
			map.put(SystemConstants.MSG, SystemConstants.FALSE);
		} else {
			map.put(SystemConstants.MSG, SystemConstants.TRUE);
			map.put(SystemConstants.LIST, applicantList);
		}
		return map;
	}

	public String deleteMerchantApplication(int merchantId, String mailId) {
		String message = "";
		try {
			message = dao.rejectApplication(merchantId);
			// mail generation
			String messageToBeSent = SystemConstants.REGISTRATION_APPLICATION_CANCELLED_MSG;
			HashMap<String, String> map = new HashMap();
			map.put("to", mailId);
			map.put("from", SystemConstants.ADMIN_SENDER_MAIL_ID);
			map.put("subject", SystemConstants.MERCHANT_REGISTRATION_CANCELLED);
			map.put("msg", messageToBeSent);
			map.put("password", SystemConstants.SENDER_PASSWORD);
			String msg = security.sendMail(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}
	
	
	public String createMerchant(MerchantDetails merchant, String creator) {
		String message = "";
		String merchantId = "";
		HashMap<String,String> map = new HashMap();
		
		try {
			String[] str = merchant.getMobileNumber().split("");
			String id = str[0]+str[9]+str[8]+str[7];
			merchantId = merchant.getFirstName()+id;
			map = security.generateCaptcha();
			EmployeeDetails employeeDetails = new EmployeeDetails();
			employeeDetails.setEmployeeId(merchantId);
			employeeDetails.setPassword(map.get(SystemConstants.CAPTCHA));
			employeeDetails.setAdminLevel("1");
			employeeDetails.setEmailId(merchant.getEmailId());
			employeeDetails.setInvalidPasswordAttempts(0);
			employeeDetails.setLoginStatus(SystemConstants.INACTIVE);
			employeeDetails.setMobileNumber(merchant.getMobileNumber());
			employeeDetails.setName(merchant.getFirstName());
			employeeDetails.setStatus(SystemConstants.ACTIVE);
			employeeDetails.setAddedByEmpId(creator);
			message = dao.insertMerchantAdmin(employeeDetails);
			if(message.equals(SystemConstants.INACTIVE)) {
				message = SystemConstants.ERROR;
			}else {
				message = AdminConstantsI.MERCHANT_ADDED;
				merchant.setStatus(AdminConstantsI.ALREADY_MERCHANT);
				dao.updateMerchant(merchant);
				String messageToBeSent = SystemConstants.REGISTRATION_APPLICATION_SUCCESS_MSG+SystemConstants.MERCHANT_LOGIN_URL+
						"\n"+AdminConstantsI.EMP_ID+":"+merchantId+"\n"+AdminConstantsI.PASSWORD+":"+map.get(SystemConstants.CAPTCHA);
				HashMap mailMap = new HashMap();
				map.put("to", merchant.getEmailId());
				map.put("from", SystemConstants.ADMIN_SENDER_MAIL_ID);
				map.put("subject", SystemConstants.MERCHANT_REGISTRATION_SUCCESSFUL);
				map.put("msg", messageToBeSent);
				map.put("password", SystemConstants.SENDER_PASSWORD);
				String msg = security.sendMail(map);
				if(!msg.equals(SystemConstants.ACTIVE)) {
					message = message+SystemConstants.MAIL_ERROR;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			message = SystemConstants.ERROR;
		}
		return message;
	}

}
