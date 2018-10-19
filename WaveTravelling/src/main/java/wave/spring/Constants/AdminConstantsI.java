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
			EMPLOYEE_MENU_LIST = "employeeMenuList";
	
		int MAX_INVALID_PASSWORD_ATTEMPTS = 4;
}
