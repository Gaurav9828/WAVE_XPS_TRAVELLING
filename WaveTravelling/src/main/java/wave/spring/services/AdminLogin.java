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

public class AdminLogin implements AdminLoginI{
	//added by Gaurav Srivastava
	public HashMap adminLoginService(Login loginValues) {
		HashMap map = new HashMap();
		try {
			String message = SystemConstants.LOGIN_ERROR;
			AdminDaoI dao = new AdminDao();
			EmployeeDetails employeeDetails = new EmployeeDetails();
			employeeDetails = dao.getEmployeeDetails(loginValues.getId());
			if(employeeDetails == null) {
				message = AdminConstantsI.INVALID_USER;
				map.put(SystemConstants.MSG, message);
				return map;
			}
			
			if(employeeDetails.getInvalidPasswordAttempts() == AdminConstantsI.MAX_INVALID_PASSWORD_ATTEMPTS - 2){
				dao.setInvalidPasswordAttempt(employeeDetails.getUserId(), employeeDetails.getInvalidPasswordAttempts()+1);
				message = AdminConstantsI.LAST_LOGIN_ATTMEPT;
				map.put(SystemConstants.MSG, message);
				return map;
			}
			
			if(employeeDetails.getInvalidPasswordAttempts() == AdminConstantsI.MAX_INVALID_PASSWORD_ATTEMPTS) {
				message = AdminConstantsI.ACCOUNT_BLOCKED;
				map.put(SystemConstants.MSG, message);
				return map;
			}
			
			if(employeeDetails.getLastLoginDate() == null) {                             //for first time login 
				if((loginValues.getPassword()).equals(employeeDetails.getPassword())) {
					message = AdminConstantsI.PASSWORD_RESET;
					map.put(SystemConstants.MSG, message);
					map.put(AdminConstantsI.EMPLOYEE_DETAILS, employeeDetails);
					return map;
				}			
			}
			
			SecurityI security = new Security();
			String password = security.valueEncrptyer(loginValues.getPassword());
			if(!password.equals(employeeDetails.getPassword())) {
				dao.setInvalidPasswordAttempt(employeeDetails.getUserId(), employeeDetails.getInvalidPasswordAttempts()+1);
				message = AdminConstantsI.INVALID_USER;
				map.put(SystemConstants.MSG, message);
				return map;
			}
			
			if(!employeeDetails.getStatus().equals(SystemConstants.ACTIVE)) {
				message = AdminConstantsI.USER_BLACK_LISTED;
				map.put(SystemConstants.MSG, message);
				return map;
			}
			
			if(employeeDetails.getLoginStatus().equals(SystemConstants.ACTIVE)) {
				message = AdminConstantsI.ALREADY_LOGGED_IN;
				map.put(SystemConstants.MSG, message);
				return map;
			}
			
			//logging in
			//generation menus as per admin level
			List<Employee1MenuList> menuList = dao.getEmployeeMenuList(employeeDetails.getAdminLevel());
			List<List> employeeMenu = new ArrayList(); 
			for(Employee1MenuList list : menuList) {
				List<String> l = new ArrayList();
				l.add(list.getMenuName());
				l.add(list.getMenuAction());
				employeeMenu.add(l);
			}

			LocalDate date = LocalDate.now();
			employeeDetails.setInvalidPasswordAttempts(0);
			employeeDetails.setLoginStatus(SystemConstants.ACTIVE);
			employeeDetails.setLastLoginDate(date);
			dao.setAdminUserLoggedIn(employeeDetails);			//set user Logged in
			message =  SystemConstants.TRUE;
			map.put(AdminConstantsI.EMPLOYEE_DETAILS, employeeDetails);
			map.put(AdminConstantsI.EMPLOYEE_MENU_LIST, employeeMenu);
			map.put(SystemConstants.MSG, message);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	//added by Gaurav Srivastava
	public void adminLogout(int employeeId) {
		AdminDaoI dao = new AdminDao();
		dao.setAdminLogout(employeeId);
	}
	
	//added by Gaurav Srivastava
	public String resetAdminPassword(int employeeId, String newPassword) {
		AdminDaoI dao = new AdminDao();
		SecurityI security = new Security();
		String password = security.valueEncrptyer(newPassword);
		String message = dao.setAdminPassword(employeeId, password);
		if(message.equals("true")) {
			return SystemConstants.ACTIVE;
		}else {
			return SystemConstants.INACTIVE;
		}	
	}

}
