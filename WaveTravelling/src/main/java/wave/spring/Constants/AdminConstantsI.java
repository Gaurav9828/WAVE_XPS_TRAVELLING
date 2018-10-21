package wave.spring.Constants;

public interface AdminConstantsI {
	String  EMP_ID = "Employee Id",    //login form
			PASSWORD = "Password",
			RESET_PASSWORD = "Forgot Password",
			LOGIN = "Admin Login",
			SUBMIT = "Submit",
			RESET = "Reset",
			APPLY = "Apply",
			CURRENT_PASSWORD = "Current Password",
			NEW_PASSWORD = "New Password",
			CONFIRM_PASSWORD = "Confirm Password",
			PASSWORD_RESET = "Admin Password Reset",
			MEMORABLE_WORD = "Memorable Word",
			
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
			ID_NUMBER = "Identity Number",
			ADHAR_ID = "Adhar",
			PAN_ID = "Pan",
			PASSPORT_ID = "Passport",
			LAND_MARK = "Land Mark";
				//vehile details
			
		int MAX_INVALID_PASSWORD_ATTEMPTS = 4;
}
