package wave.spring.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import wave.spring.Constants.AdminConstantsI;
import wave.spring.Constants.SystemConstants;
import wave.spring.dao.MerchantDao;
import wave.spring.dao.MerchantDaoI;
import wave.spring.model.MerchantDetails;
import wave.spring.security.Security;
import wave.spring.security.SecurityI;

public class MerchantRegistration implements MerchantRegistrationI{
	MerchantDaoI dao = new MerchantDao();
	public String freshRegistrationRequest(MerchantDetails merchantDetails) {
		String message = "";
		SecurityI security = new Security();
		MerchantDetails details = dao.merchantAlreadyExist(merchantDetails.getMobileNumber());
		if(details != null) {
			if(details.getStatus().equals(AdminConstantsI.ALREADY_REGISTERED)) {
				message = AdminConstantsI.ALREADY_REGISTERED;
			}else if(details.getStatus().equals(AdminConstantsI.ALREADY_MERCHANT)) {
				message = AdminConstantsI.ALREADY_MERCHANT;
			}else if(details.getStatus().equals(AdminConstantsI.BLACK_LISTED_REQUEST)) {
				message = AdminConstantsI.BLACK_LISTED_REQUEST;
			}
		}else {
			merchantDetails.setStatus(AdminConstantsI.ALREADY_REGISTERED);
			LocalDate appointmentDate1 = LocalDate.now().plusDays(7);
			LocalDate appointmentDate2 = LocalDate.now().plusDays(9);
			LocalDate appointmentDate3 = LocalDate.now().plusDays(11);
			merchantDetails.setAppointementDates((appointmentDate1+", "+appointmentDate2+", "+appointmentDate3).toString());
			//submit the application
			merchantDetails.setSubmissionDate(LocalDate.now());
			message = dao.applyForWatingMerchantship(merchantDetails);
			if(message.equals(AdminConstantsI.REGISTRAION_REQUEST_SUCCESSFUL)) {
				//mail generation
				
				String messageToBeSent = SystemConstants.MERCHANT_REGISTRATION_APPOINTEMENT_MSG_ONE+" "+appointmentDate1+", "+appointmentDate2
						+" or "+appointmentDate3+". "+SystemConstants.MERCHANT_REGISTRATION_APPOINTEMENT_MSG_TWO;
				
				HashMap<String, String> map = new HashMap();
				map.put("to", merchantDetails.getEmailId());
				map.put("from", SystemConstants.ADMIN_SENDER_MAIL_ID);
				map.put("subject", SystemConstants.MERCHANT_REGISTRATION_APPOINTEMENT);
				map.put("msg", messageToBeSent);
				map.put("password", SystemConstants.SENDER_PASSWORD);
				String msg = security.sendMail(map);
				if(!msg.equals(SystemConstants.ACTIVE)) {
					message = SystemConstants.ERROR;
				}
			}			
		}
		return message;
	}
	
	
	//added by Gaurav Srivastava
	public HashMap getMerchantApplications(){
		HashMap map = new HashMap();
		List<MerchantDetails> applicantList = dao.getAcceptedMerchantList();
		if(applicantList.isEmpty()) {
			map.put(SystemConstants.MSG, SystemConstants.FALSE);
		}else {
			map.put(SystemConstants.MSG, SystemConstants.TRUE);
			map.put(SystemConstants.LIST, applicantList);
		}
		return map;	
	}

}	
