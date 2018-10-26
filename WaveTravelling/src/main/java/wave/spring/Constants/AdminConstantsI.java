package wave.spring.Constants;

public interface AdminConstantsI {
	String  EMP_ID = "Employee Id",    //login form
			PASSWORD = "Password",
			RESET_PASSWORD = "Forgot Password",
			ADMIN_LOGIN = "Admin Login",
			LOGIN = "Login",
			SUBMIT = "Submit",
			RESET = "Reset",
			APPLY = "Apply",
			CURRENT_PASSWORD = "Current Password",
			NEW_PASSWORD = "New Password",
			CONFIRM_PASSWORD = "Confirm Password",
			PASSWORD_RESET = "Admin Password Reset",
			SET_MEMORABLE_WORD = "Set Memorable Word",
			MEMORABLE_WORD = "Memorable Word",
			CONFIRM_MEMORABLE_WORD = "Confirm Memorable Word",
			
			//messages
			INVALID_USER = "Either wrong employee id or password ..",
			INVALID_USERID_OR_SECRET_WORD = "Either wrong employee id or memorable word ..",
			LAST_LOGIN_ATTMEPT = "One more invalid attempt will block your account ..",
			ACCOUNT_BLOCKED = "Your account has been temporarily blocked due to invalid password attempts ..",
			USER_BLACK_LISTED = "Your account has been black-listed due to some policies ..",
			ALREADY_LOGGED_IN = "You are already logged in ..",
			
			//session variables
			EMPLOYEE_DETAILS = "employeeDetails",
			EMPLOYEE_MENU_LIST = "employeeMenuList",
			EMPLOYEE_MENU_DOMAIN_LIST = "menuDomainList",
			
			//admin registration request form
				//personal details
			REGISTRATION_REQUEST = "Registration Request",
			FIRST_NAME = "First Name",
			LAST_NAME = "Last Name",
			SECRET_WORD = "Secret Memorable Word",
			MOBILE_NUMBER = "Mobile Number",
			CITY = "Choose City",
			PIN_CODE = "Pin Code",
			EMAIL_ID = "Email Id",
			CHOOSE_IDENTITY = "Choose Identity Type",
			ID_NUMBER = "Identity Secquence",
			ADHAR_ID = "Adhar",
			PAN_ID = "Pan",
			PASSPORT_ID = "Passport",
			LAND_MARK = "Land Mark",
			
			//merchant registration request
			ALREADY_REGISTERED = "AR",
		    ALREADY_REGISTERED_MSG = "You have already applied for the merchant registration ..",
		    ALREADY_MERCHANT = "AM",
		    ALREADY_MERCHANT_MSG = "You are already a merchant of Wave Xps Travelling",
		    BLACK_LISTED_REQUEST = "BL",
		    BLACK_LISTED_REQUEST_MSG = "Sorry ! You are black listed for registration request due to some company policies ..",
		    REGISTRAION_REQUEST_SUCCESSFUL = "Your registration request has been accepted. We have sent you a email for further informations ..",
		    SOME_THING_WRONG_REGISTRATION = "Sorry ! Registration failed due to some error. Please try after some time ..";
			
		int MAX_INVALID_PASSWORD_ATTEMPTS = 4;
}
