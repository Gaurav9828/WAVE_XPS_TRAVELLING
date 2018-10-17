package wave.spring.Constants;

public interface AdminConstantsI {
	String  EMP_ID = "Employee Id",    //login form
			PASSWORD = "Password",
			RESET_PASSWORD = "Forgot Password",
			LOGIN = "Admin Login",
			SUBMIT = "Submit",
			RESET = "Reset",
			
			//messages
			INVALID_USER = "Either wrong employee id or password ..",
			LAST_LOGIN_ATTMEPT = "One more invalid attempt will block your account ..",
			ACCOUNT_BLOCKED = "Your account has been temporarily blocked due to invalid password attempts ..",
			USER_BLACK_LISTED = "Your account has been black-listed due to some policies ..",
			ALREADY_LOGGED_IN = "You are already logged in ..";
	
		int MAX_INVALID_PASSWORD_ATTEMPTS = 4;
}
