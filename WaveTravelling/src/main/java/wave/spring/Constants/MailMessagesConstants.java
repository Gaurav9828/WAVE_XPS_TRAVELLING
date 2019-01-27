package wave.spring.Constants;

public interface MailMessagesConstants {
	String
	//---------------------mail errors
	ERROR_MAIL_SENDING = "Error occured during mail sending. We will send your mail sooner.\n",
	MAIL_SENDING_NETWORK_ERROR = "There is network error while sending mail.\nPlease try again later.",
	
	//---------------------Merchant Registration Messages 
	MERCHANT_REGISTRATION_APPOINTEMENT = "Wave Xps Registration Appoointement",
	MERCHANT_REGISTRATION_APPOINTEMENT_MSG_ONE = "\tWe are glad to inform that your application for Wave Xps Travelling Merchant has been "
			+ "successfully accepted.\n\tYou may visit Wave Xps Office Kushinagar on any Date .",
	MERCHANT_REGISTRATION_APPOINTEMENT_MSG_TWO = "\n\tPlease carry all important details "
			+ "of you and your vehicle such as Adhar card.\n\n\n\n\tThank you we hope that "
			+ "sooner you will be a part of Wave Xps Travelling.",
							
	MERCHANT_REGISTRATION_CANCELLED = "Wave Xps Registration Cancelled",
	MERCHANT_REGISTRATION_SUCCESSFUL = "Wave Xps Registration successful",
	REGISTRATION_APPLICATION_CANCELLED_MSG = "\tSorry due to some leagal issues with your documents we are unable to accepet your Merchant application"
			+ "in Wave Xps Travelling\n\n\n\n\tWave Xps Travelling merchant application cancellition service.",
	REGISTRATION_APPLICATION_SUCCESS_MSG = "\tCongratuation, Now you are a Merchant in Wave Xps Travelling.\n\tYou can login as a Merchant admin in "
			+ "Wave Xps by clicking on the following link.\n",
	MERCHANT_LOGIN_URL = "http://localhost:9090/WaveTravelling/adminLogin",
	
	//-----------------------Reset Messages
	TEMPORARY_PASSWORD_RESET_SUBJECT = "WAVE Xps Password Reset ",
	TEMPORARY_PASSWORD_MAIL_SENT = "\tWe have sent you a temporary password to your registered mail id..",
	FIRST_MESSAGE = "  is your temporary password. Please reset your password withing 1 hour.",
	SECOND_MESSAGE = "\tDo not share your sensitive data like password or memorable word with anyone. Thank you we are glad to help you.",
	THIRD_MESSAGE = "\tWave Xps password reset functionality through secret memorable word as per the user request.",
	
	//----------------------Admin registration Messages
	ADMIN_ADDITION_SUCCESS_MSG = "\tCongratuation, Now you are a Admin in Wave Xps Travelling.\n\tYou can login as a Admin in "
			+ "Wave Xps by clicking on the following link.\n",
	ADMIN_ADDITION_SUCCESSFUL = "Wave Xps Admin Partner created"
	;	
	
}
