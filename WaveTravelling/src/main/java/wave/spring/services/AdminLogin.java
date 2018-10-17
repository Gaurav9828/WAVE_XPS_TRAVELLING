package wave.spring.services;


import java.time.LocalDate;

import wave.spring.Constants.AdminConstantsI;
import wave.spring.Constants.SystemConstants;
import wave.spring.dao.AdminDao;
import wave.spring.dao.AdminDaoI;
import wave.spring.model.EmployeeDetails;
import wave.spring.model.Login;
import wave.spring.security.Security;
import wave.spring.security.SecurityI;

public class AdminLogin implements AdminLoginI{
	//added by Gaurav Srivastava
	public String adminLoginService(Login loginValues) {
		String message = SystemConstants.LOGIN_ERROR;
		AdminDaoI dao = new AdminDao();
		EmployeeDetails employeeDetails = new EmployeeDetails();
		employeeDetails = dao.getEmployeeDetails(loginValues.getId());
		if(employeeDetails == null) {
			message = AdminConstantsI.INVALID_USER;
			return message;
		}
		
		if(employeeDetails.getInvalidPasswordAttempts() == AdminConstantsI.MAX_INVALID_PASSWORD_ATTEMPTS - 2){
			dao.setInvalidPasswordAttempt(employeeDetails.getUserId(), employeeDetails.getInvalidPasswordAttempts()+1);
			message = AdminConstantsI.LAST_LOGIN_ATTMEPT;
			return message;
		}
		
		if(employeeDetails.getInvalidPasswordAttempts() == AdminConstantsI.MAX_INVALID_PASSWORD_ATTEMPTS) {
			message = AdminConstantsI.ACCOUNT_BLOCKED;
			return message;
		}
		
		SecurityI security = new Security();
		String password = security.valueEncrptyer(loginValues.getPassword());
		if(!password.equals(employeeDetails.getPassword())) {
			dao.setInvalidPasswordAttempt(employeeDetails.getUserId(), employeeDetails.getInvalidPasswordAttempts()+1);
			message = AdminConstantsI.INVALID_USER;
			return message;
		}
		
		if(!employeeDetails.getStatus().equals(SystemConstants.ACTIVE)) {
			message = AdminConstantsI.USER_BLACK_LISTED;
			return message;
		}
		
		if(employeeDetails.getLoginStatus().equals(SystemConstants.ACTIVE)) {
			message = AdminConstantsI.ALREADY_LOGGED_IN;
			return message;
		}
		//logging in
		LocalDate date = LocalDate.now();
		employeeDetails.setInvalidPasswordAttempts(0);
		employeeDetails.setLoginStatus(SystemConstants.ACTIVE);
		employeeDetails.setLastLoginDate(date);
		dao.setAdminUserLoggedIn(employeeDetails);
		message =  SystemConstants.TRUE;
		return message;
	}

}
