package wave.spring.Constants;


public interface SystemConstants {
	//added by Gaurav Srivastava
	String  PROJECT_NAME = "Wave Xps",
			PROJECT_NAME_ONE = "Wave",
			PROJECT_NAME_TWO = "Xps",
			ADMIN_LOGIN = "Admin Login",
			CAPTCHA = "Captcha",
			TRUE = "true",
			FALSE = "falses",
			MSG = "message",
			MSG_SUCCESS = "successMessage",
			SOMETHING_WRONG = "Some thing went wrong please try again later ..",
			ADMIN_PASSWORD_RESET_SUCCESSFULL = "Password has been reset successfully",
			
	//mail generation		
			TEMPORARY_PASSWORD_MAIL_SENT = "We have sent you a temporary password to your registered mail id..",
			ADMIN_SENDER_MAIL_ID = "gauravsri9828@gmail.com",
			SENDER_PASSWORD = "XXXXXXXXX",
			
			MAIL_TRANSPORT_PROTOCOL = "mail.transport.protocol",
			MAIL_HOST = "mail.host",
			SMPT_GMAIL_COM = "smtp.gmail.com",
			MAIL_SMTP_AUTH = "mail.smtp.auth",
			MAIL_SMTP_PORT = "mail.smtp.port",
			MAIL_DEBUG = "mail.debug",
			MAIL_SMTP_SOCKET_FACTORY_PORT = "mail.smtp.socketFactory.port",
			MAIL_SMTP_SOCKET_FACTORY_CLASS = "mail.smtp.socketFactory.class",
			JAVAX_NET_SSL = "javax.net.ssl.SSLSocketFactory",
			MAIL_SMTP_SOCKET_FACTORY_FALL_BACK = "mail.smtp.socketFactory.fallback",
			
			TEMPORARY_PASSWORD_RESET_SUBJECT = "WAVE Xps Passwrd Reset ",
			FIRST_MESSAGE = "  is your temporary password. Please reset your password withing 1 hour.",
			SECOND_MESSAGE = "Do not share your sensitive data like password/memorable word with anyone. Thank you we are glad to help you.",
			THIRD_MESSAGE = "Wave Xps password reset functionality through secret memorable word as per the user request.",
	//added by Gaurav Srivastava
	//project credentials
			DEVELOPERS = "Gaurav Srivastava, Prashansa Saxena and Ayush Pandey",
			THEAM = "Developed by ",
	//jdbc properties
			DRIVER_CLASS_NAME = "oracle.jdbc.driver.OracleDriver",
			DILECT = "org.hibernate.dialect.Oracle10gDialect",
			DATABASE_URL = "jdbc:oracle:thin:@localhost:1521:XE",
			USER_NAME = "MYDATABASE",
			PASSWORD = "GAV9828GaV",
			HBM2DDL_AUTO = "update",
			C3P0_TIMEOUT = "1000",
			
			LOGIN_ERROR = "There was a login error. Please try again after some time.",
			ACTIVE = "Y",
			INACTIVE = "N";
			
}
