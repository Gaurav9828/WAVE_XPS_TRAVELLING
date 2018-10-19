package wave.spring.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import wave.spring.Constants.AdminConstantsI;
import wave.spring.Constants.SystemConstants;
import wave.spring.dao.AdminDao;
import wave.spring.dao.AdminDaoI;
import wave.spring.model.AdminMemorableWordPasswordReset;
import wave.spring.model.Employee1MenuList;
import wave.spring.model.EmployeeDetails;
import wave.spring.model.Login;
import wave.spring.security.Security;
import wave.spring.security.SecurityI;

public class AdminLogin implements AdminLoginI {
	// added by Gaurav Srivastava
	public HashMap adminLoginService(Login loginValues) {
		HashMap map = new HashMap();
		try {
			String message = SystemConstants.LOGIN_ERROR;
			AdminDaoI dao = new AdminDao();
			EmployeeDetails employeeDetails = new EmployeeDetails();
			employeeDetails = dao.getEmployeeDetails(loginValues.getId());
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
				if ((loginValues.getPassword()).equals(employeeDetails.getPassword())) {
					message = AdminConstantsI.PASSWORD_RESET;
					map.put(SystemConstants.MSG, message);
					map.put(AdminConstantsI.EMPLOYEE_DETAILS, employeeDetails);
					return map;
				}
			}

			SecurityI security = new Security();
			String password = security.valueEncrptyer(loginValues.getPassword());
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
			for (Employee1MenuList list : menuList) {
				List<String> l = new ArrayList();
				l.add(list.getMenuName());
				l.add(list.getMenuAction());
				employeeMenu.add(l);
			}

			LocalDate date = LocalDate.now();
			employeeDetails.setInvalidPasswordAttempts(0);
			employeeDetails.setLoginStatus(SystemConstants.ACTIVE);
			employeeDetails.setLastLoginDate(date);
			dao.updateEmployeeDetails(employeeDetails); // set user Logged in
			message = SystemConstants.TRUE;
			map.put(AdminConstantsI.EMPLOYEE_DETAILS, employeeDetails);
			map.put(AdminConstantsI.EMPLOYEE_MENU_LIST, employeeMenu);
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
		SecurityI security = new Security();
		String password = security.valueEncrptyer(newPassword);
		String message = dao.setAdminPassword(employeeId, password);
		if (message.equals("true")) {
			return SystemConstants.ACTIVE;
		} else {
			return SystemConstants.INACTIVE;
		}
	}

	// added by Gaurav Srivastava
	public String resetPasswordAndSendMail(AdminMemorableWordPasswordReset resetValues) {
		String message = "";
		AdminDaoI dao = new AdminDao();
		EmployeeDetails employeeDetails = new EmployeeDetails();
		employeeDetails = dao.getEmployeeDetails(resetValues.getEmployeeId());
		if (employeeDetails == null) {
			message = SystemConstants.FALSE;
		}
		SecurityI security = new Security();
		String inputMemorableWord = security.valueEncrptyer(resetValues.getMemorableWord());
		if (employeeDetails.getMemorableWord().equals(inputMemorableWord)) {
			String msg = sendTemporaryPasswordMail(employeeDetails);
			if (msg.equals(SystemConstants.ACTIVE)) {
				message = SystemConstants.ACTIVE;
			} else {
				message = SystemConstants.INACTIVE;
			}
		} else {
			message = SystemConstants.INACTIVE;
		}
		return message;
	}

	public static String sendTemporaryPasswordMail(EmployeeDetails employeeDetails) {
		String MSG = "";
		try {
			String to = employeeDetails.getEmailId();
			String from = SystemConstants.ADMIN_SENDER_MAIL_ID;
			String subject = SystemConstants.TEMPORARY_PASSWORD_RESET_SUBJECT;

			SecurityI security = new Security();
			HashMap<String, String> map = security.generateCaptcha();
			String temporartPassword = map.get(SystemConstants.CAPTCHA);
			String msg = temporartPassword + SystemConstants.FIRST_MESSAGE+"\n"+ SystemConstants.SECOND_MESSAGE
					+"\n\n\n\n"+ SystemConstants.THIRD_MESSAGE;
			final String password = SystemConstants.SENDER_PASSWORD;

			Properties props = new Properties();
			props.setProperty(SystemConstants.MAIL_TRANSPORT_PROTOCOL, "smtp");
			props.setProperty(SystemConstants.MAIL_HOST, SystemConstants.SMPT_GMAIL_COM);    
			props.put(SystemConstants.MAIL_SMTP_AUTH, SystemConstants.TRUE);         
			props.put(SystemConstants.MAIL_SMTP_PORT, "465");			
			props.put(SystemConstants.MAIL_DEBUG, SystemConstants.TRUE);       
			props.put(SystemConstants.MAIL_SMTP_SOCKET_FACTORY_PORT, "465");    
			props.put(SystemConstants.MAIL_SMTP_SOCKET_FACTORY_CLASS, SystemConstants.JAVAX_NET_SSL);     
			props.put(SystemConstants.MAIL_SMTP_SOCKET_FACTORY_FALL_BACK, SystemConstants.FALSE);    
			Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(from, password);
				}
			});

			// session.setDebug(true);
			Transport transport = session.getTransport();
			InternetAddress addressFrom = new InternetAddress(from);

			MimeMessage message = new MimeMessage(session);
			message.setSender(addressFrom);
			message.setSubject(subject);
			message.setContent(msg, "text/plain");
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			transport.connect();
			Transport.send(message);
			transport.close();

			employeeDetails.setLastLoginDate(null);
			employeeDetails.setPassword(temporartPassword);

			AdminDaoI dao = new AdminDao();
			dao.updateEmployeeDetails(employeeDetails);
			MSG = SystemConstants.ACTIVE;
		} catch (MessagingException mex) {
			mex.printStackTrace();
			MSG = SystemConstants.INACTIVE;
		}
		return MSG;
	}
}
