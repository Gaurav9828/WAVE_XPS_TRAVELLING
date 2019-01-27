package wave.spring.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import wave.spring.Constants.AdminConstantsI;
import wave.spring.Constants.AdminMessageConstants;
import wave.spring.Constants.MailMessagesConstants;
import wave.spring.Constants.SystemConstants;
import wave.spring.dao.AdminDao;
import wave.spring.dao.AdminDaoI;
import wave.spring.model.MenuDetails;
import wave.spring.model.EmployeeDetails;
import wave.spring.model.Login;
import wave.spring.security.Security;
import wave.spring.security.SecurityI;

public class AdminLogin implements AdminLoginI {
	// added by Gaurav Srivastava
	SecurityI security = new Security();

	public HashMap adminLoginService(Login loginValues) {
		HashMap map = new HashMap();
		try {
			String message = SystemConstants.LOGIN_ERROR;
			AdminDaoI dao = new AdminDao();
			EmployeeDetails employeeDetails = new EmployeeDetails();
			employeeDetails = dao.getEmployeeDetails(loginValues.getAuthValue1());
			if (employeeDetails == null) {
				message = AdminMessageConstants.INVALID_USER;
				map.put(SystemConstants.ERROR_MESSAGE, message);
				return map;
			}

			if (employeeDetails.getInvalidPasswordAttempts() == AdminConstantsI.MAX_INVALID_PASSWORD_ATTEMPTS - 2) {
				dao.setInvalidPasswordAttempt(employeeDetails.getUserId(),
						employeeDetails.getInvalidPasswordAttempts() + 1);
				message = AdminMessageConstants.LAST_LOGIN_ATTMEPT;
				map.put(SystemConstants.ERROR_MESSAGE, message);
				return map;
			}

			if (employeeDetails.getInvalidPasswordAttempts() == AdminConstantsI.MAX_INVALID_PASSWORD_ATTEMPTS) {
				message = AdminMessageConstants.ACCOUNT_BLOCKED;
				map.put(SystemConstants.ERROR_MESSAGE, message);
				return map;
			}

			if (employeeDetails.getLastLogin() == null) { // for first time login
				if ((loginValues.getAuthValue2()).equals(employeeDetails.getPassword())) {
					message = AdminConstantsI.PASSWORD_RESET;
					map.put(SystemConstants.ERROR_MESSAGE, message);
					map.put(AdminConstantsI.EMPLOYEE_DETAILS, employeeDetails);
					return map;
				}
			}

			String password = security.valueEncrptyer(loginValues.getAuthValue2());
			if (!password.equals(employeeDetails.getPassword())) {
				dao.setInvalidPasswordAttempt(employeeDetails.getUserId(),
						employeeDetails.getInvalidPasswordAttempts() + 1);
				message = AdminMessageConstants.INVALID_USER;
				map.put(SystemConstants.ERROR_MESSAGE, message);
				return map;
			}

			if (employeeDetails.getStatus().equals(SystemConstants.INACTIVE) || employeeDetails.getStatus().equals(AdminConstantsI.BLACK_LISTED_REQUEST)) {
				message = AdminMessageConstants.USER_BLACK_LISTED;
				map.put(SystemConstants.ERROR_MESSAGE, message);
				return map;
			}

			// logging in
			// generation menus as per admin level
			List<MenuDetails> menuList = dao.getEmployeeMenuList(employeeDetails.getAdminLevel());
			List<List> employeeMenu = new ArrayList();
			List<String> menuDomain = new ArrayList();
			for (MenuDetails list : menuList) {
				List<String> l = new ArrayList();
				l.add(list.getMenuName());
				l.add(list.getMenuAction());
				l.add(list.getMenuDomain());
				employeeMenu.add(l);
				if(menuDomain.isEmpty()) {
					menuDomain.add(list.getMenuDomain());
				}else {
					if(!menuDomain.contains(list.getMenuDomain())){
						menuDomain.add(list.getMenuDomain());
					}
				}
			}

			LocalDate date = LocalDate.now();
			LocalTime time = LocalTime.now();
			employeeDetails.setInvalidPasswordAttempts(0); 
			employeeDetails.setLastLogin(time.getHour()+":"+time.getMinute()+" "+date.toString());
			dao.updateEmployeeDetails(employeeDetails); // set user Logged in
			message = SystemConstants.TRUE;
			map.put(AdminConstantsI.EMPLOYEE_DETAILS, employeeDetails);
			map.put(AdminConstantsI.EMPLOYEE_MENU_LIST, employeeMenu);
			map.put(AdminConstantsI.EMPLOYEE_MENU_DOMAIN_LIST, menuDomain);
			map.put(SystemConstants.ERROR_MESSAGE, message);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	// added by Gaurav Srivastava
	public void adminLogout(EmployeeDetails employeeDetails) {
		AdminDaoI dao = new AdminDao();
		LocalDate date = LocalDate.now();
		LocalTime time = LocalTime.now();
		employeeDetails.setLastLogout(time.getHour()+":"+time.getMinute()+" / "+date.getDayOfMonth()+"-"+(date.getMonth().toString()).substring(0,3)+"-"+date.getYear());
		dao.updateEmployeeDetails(employeeDetails);
	}

	// added by Gaurav Srivastava
	public String resetAdminPassword(int employeeId, String newPassword) {
		AdminDaoI dao = new AdminDao();
		String password = security.valueEncrptyer(newPassword);
		String message = dao.setAdminPassword(employeeId, password);
		if (message.equals("true")) {
			return SystemConstants.ACTIVE;
		} else {
			return SystemConstants.INACTIVE;
		}
	}

	// added by Gaurav Srivastava
	public String resetPasswordAndSendMail(Login resetValues) {
		String message = "";
		AdminDaoI dao = new AdminDao();
		EmployeeDetails employeeDetails = new EmployeeDetails();
		employeeDetails = dao.getEmployeeDetails(resetValues.getAuthValue1());
		if (employeeDetails == null) {
			message = SystemConstants.FALSE;
		}
		String inputMemorableWord = security.valueEncrptyer(resetValues.getAuthValue2());
		if (employeeDetails.getMemorableWord().equals(inputMemorableWord)) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put(AdminConstantsI.TO, employeeDetails.getEmailId());
			map.put(AdminConstantsI.SUBJECT, MailMessagesConstants.TEMPORARY_PASSWORD_RESET_SUBJECT);

			HashMap<String, String> val = security.generateCaptcha();
			String temporartPassword = val.get(SystemConstants.CAPTCHA);
			String messageToBeSent = temporartPassword + MailMessagesConstants.FIRST_MESSAGE+"\n"+ MailMessagesConstants.SECOND_MESSAGE
					+"\n\n\n\n"+ MailMessagesConstants.THIRD_MESSAGE;
			
			map.put(AdminConstantsI.MESSAGE, messageToBeSent);
			String msg = security.sendMail(map);
			if (msg.equals(SystemConstants.ACTIVE)) {
				employeeDetails.setLastLogin(null);
				employeeDetails.setPassword(temporartPassword);
				dao.updateEmployeeDetails(employeeDetails);
				message = SystemConstants.ACTIVE;
			} else {
				message = SystemConstants.MAIL_ERROR;
			}
		} else {
			message = SystemConstants.INACTIVE;
		}
		return message;
	}

	
	public String setSecretMemorableWord(EmployeeDetails employeeDetails, String secretWord) {
		String message = "";
		employeeDetails.setMemorableWord(security.valueEncrptyer(secretWord));
		employeeDetails.setPassword(security.valueEncrptyer(employeeDetails.getPassword()));
		try {
			AdminDaoI dao = new AdminDao();
			dao.updateEmployeeDetails(employeeDetails);
			message = SystemConstants.ACTIVE;
		}catch(Exception e) {
			e.printStackTrace();
			message = SystemConstants.INACTIVE;
		}
		return message;
	}

}
