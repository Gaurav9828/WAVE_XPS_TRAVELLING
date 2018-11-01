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
			ADMIN_PASSWORD_AND_MEMORABLE_WORD_RESET_SUCCESSFULL = "You have successfully set your password and secret memorable word. Now you may login",
			
	//mail generation		
			TEMPORARY_PASSWORD_MAIL_SENT = "We have sent you a temporary password to your registered mail id..",
			ADMIN_SENDER_MAIL_ID = "gauravsri9828@gmail.com",
			SENDER_PASSWORD = "7309831726",
			
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
			
	//MerchantRegistration
			MERCHANT_REGISTRATION_APPOINTEMENT = "Wave Xps Registration Appoointement",
			MERCHANT_REGISTRATION_APPOINTEMENT_MSG_ONE = "\tWe are glad to inform that your application for Wave Xps Travelling Merchant has been "
					+ "successfully accepted.\n\tYou may visit Wave Xps Office Kushinagar on any Date .",
			MERCHANT_REGISTRATION_APPOINTEMENT_MSG_TWO = "\n\tPlease carry all important details "
							+ "of you and your vechile such as Adhar card.\n\n\n\n\tThank you we hope that "
							+ "sooner you will be a part of Wave Xps Travelling.",
							
			MERCHANT_REGISTRATION_CANCELLED = "Wave Xps Registration Cancelled",
			MERCHANT_REGISTRATION_SUCCESSFUL = "Wave Xps Registration successful",
		    REGISTRATION_APPLICATION_CANCELLED_MSG = "\tSorry due to some leagal issues with your documents we are unable to accepet your Merchant application"
		    		+ "in Wave Xps Travelling\n\n\n\n\tWave Xps Travelling merchant application cancellition service.",
		    REGISTRATION_APPLICATION_SUCCESS_MSG = "\tCongratuation, Now you are a Merchant in Wave Xps Travelling.\n\tYou can login as a Merchant admin in "
		    		+ "Wave Xps by clicking on the following link.\n",
		    MERCHANT_LOGIN_URL = "http://localhost:9090/WaveTravelling/adminLogin",		
		   
							
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
			C3P0_TIMEOUT = "60000",
			
			LOGIN_ERROR = "There was a login error. Please try again after some time.",
			SOMETHING_ERROR = "Something wrong occurred please try after some time",
			ACTIVE = "Y",
			INACTIVE = "N",
			ERROR = "error",
			MAIL_ERROR = "mailError",
			
			EMPTY_LIST = "List is empty",
			LIST = "list",
			MERCHANT_LIST = "merchantList",
			MERCHANT = "merchant",
			MERCHANT_DETAILS = "merchantDetails";
			
}
