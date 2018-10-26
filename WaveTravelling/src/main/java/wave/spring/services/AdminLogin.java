package wave.spring.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import wave.spring.Constants.AdminConstantsI;
import wave.spring.Constants.SystemConstants;
import wave.spring.dao.AdminDao;
import wave.spring.dao.AdminDaoI;
import wave.spring.model.Employee1MenuList;
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
				message = AdminConstantsI.INVALID_USER;
				map.put(SystemConstants.MSG, message);
				return map;
			}

			if (employeeDetails.getInvalidPasswordAttempts() == AdminConstantsI.MAX_INVALID_PASSWORD_ATTEMPTS - 2) {
				dao.setInvalidPasswordAttempt(employeeDetails.getUserId(),
						employeeDetails.getInvalidPasswordAttempts() + 1);
				message = AdminConstantsI.LAST_LOGIN_ATTMEPT;
				map.put(SystemConstants.MSG, message);
				return map;
			}

			if (employeeDetails.getInvalidPasswordAttempts() == AdminConstantsI.MAX_INVALID_PASSWORD_ATTEMPTS) {
				message = AdminConstantsI.ACCOUNT_BLOCKED;
				map.put(SystemConstants.MSG, message);
				return map;
			}

			if (employeeDetails.getLastLoginDate() == null) { // for first time login
				if ((loginValues.getAuthValue2()).equals(employeeDetails.getPassword())) {
					message = AdminConstantsI.PASSWORD_RESET;
					map.put(SystemConstants.MSG, message);
					map.put(AdminConstantsI.EMPLOYEE_DETAILS, employeeDetails);
					return map;
				}
			}

			String password = security.valueEncrptyer(loginValues.getAuthValue2());
			if (!password.equals(employeeDetails.getPassword())) {
				dao.setInvalidPasswordAttempt(employeeDetails.getUserId(),
						employeeDetails.getInvalidPasswordAttempts() + 1);
				message = AdminConstantsI.INVALID_USER;
				map.put(SystemConstants.MSG, message);
				return map;
			}

			if (!employeeDetails.getStatus().equals(SystemConstants.ACTIVE)) {
				message = AdminConstantsI.USER_BLACK_LISTED;
				map.put(SystemConstants.MSG, message);
				return map;
			}

			if (employeeDetails.getLoginStatus().equals(SystemConstants.ACTIVE)) {
				message = AdminConstantsI.ALREADY_LOGGED_IN;
				map.put(SystemConstants.MSG, message);
				return map;
			}

			// logging in
			// generation menus as per admin level
			List<Employee1MenuList> menuList = dao.getEmployeeMenuList(employeeDetails.getAdminLevel());
			List<List> employeeMenu = new ArrayList();
			List<String> menuDomain = new ArrayList();
			for (Employee1MenuList list : menuList) {
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
			employeeDetails.setInvalidPasswordAttempts(0);
			employeeDetails.setLoginStatus(SystemConstants.ACTIVE);
			employeeDetails.setLastLoginDate(date);
			dao.updateEmployeeDetails(employeeDetails); // set user Logged in
			message = SystemConstants.TRUE;
			map.put(AdminConstantsI.EMPLOYEE_DETAILS, employeeDetails);
			map.put(AdminConstantsI.EMPLOYEE_MENU_LIST, employeeMenu);
			map.put(AdminConstantsI.EMPLOYEE_MENU_DOMAIN_LIST, menuDomain);
			map.put(SystemConstants.MSG, message);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	// added by Gaurav Srivastava
	public void adminLogout(EmployeeDetails employeeDetails) {
		AdminDaoI dao = new AdminDao();
		employeeDetails.setLoginStatus(SystemConstants.INACTIVE);
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
			HashMap<String, String> map = new HashMap();
			map.put("to", employeeDetails.getEmailId());
			map.put("from", SystemConstants.ADMIN_SENDER_MAIL_ID);
			map.put("subject", SystemConstants.TEMPORARY_PASSWORD_RESET_SUBJECT);

			HashMap<String, String> val = security.generateCaptcha();
			String temporartPassword = val.get(SystemConstants.CAPTCHA);
			String messageToBeSent = temporartPassword + SystemConstants.FIRST_MESSAGE+"\n"+ SystemConstants.SECOND_MESSAGE
					+"\n\n\n\n"+ SystemConstants.THIRD_MESSAGE;
			
			map.put("msg", messageToBeSent);
			map.put("password", SystemConstants.SENDER_PASSWORD);
			String msg = security.sendMail(map);
			if (msg.equals(SystemConstants.ACTIVE)) {
				employeeDetails.setLastLoginDate(null);
				employeeDetails.setPassword(temporartPassword);

				dao.updateEmployeeDetails(employeeDetails);
				message = SystemConstants.ACTIVE;
			} else {
				message = SystemConstants.INACTIVE;
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
