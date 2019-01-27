package wave.spring.Constants;

public interface AdminConstantsI {
	String  EMP_ID = "Employee Id",    //login form
			PASSWORD = "Password",
			RESET_PASSWORD = "Forgot Password",
			ADMIN_LOGIN = "Admin Login",
			LOGIN = "Login",
			SUBMIT = "Submit",
			COFIRM = "Confirm",
			RESET = "Reset",
			BACK = "Back",
			APPLY = "Apply",
			CURRENT_PASSWORD = "Current Password",
			NEW_PASSWORD = "New Password",
			CONFIRM_PASSWORD = "Confirm Password",
			PASSWORD_RESET = "Admin Password Reset",
			SET_MEMORABLE_WORD = "Set Memorable Word",
			MEMORABLE_WORD = "Memorable Word",
			CONFIRM_MEMORABLE_WORD = "Confirm Memorable Word",
			
			
			//session variables
			EMPLOYEE_DETAILS = "employeeDetails",
			EMPLOYEE_MENU_LIST = "employeeMenuList",
			EMPLOYEE_MENU_DOMAIN_LIST = "employeeMenuDomainList",
			
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
			MAIL_SENT = "S",
			MAIL_WAITING = "W",
			ACTION = "Action",
			CHOOSE_IDENTITY = "Choose Identity Type",
			ID_NUMBER = "Identity Secquence",
			ADHAR_ID = "Adhar",
			PAN_ID = "Pan",
			PASSPORT_ID = "Passport",
			LAND_MARK = "Land Mark",
			NAME = "Name",
			
			//merchant registration request
			ALREADY_REGISTERED = "AR",
		    ALREADY_MERCHANT = "AM",
		    BLACK_LISTED_REQUEST = "BL",
		    UNDER_BLACKLIST_PROCESS = "UBL",
		    UNDER_CANCLE_BLACKLIST_PROCESS = "UCBL",
		    
		    //merchant block-unblock
		    REQUEST_ACCEPTED = "Request accepted",
			
		    LIST_EMPTY = "Empty list",
			PENDING_MAIL_LIST = "Pending Mail List",
			TO = "to",
			SUBJECT = "subject",
			FROM = "from",
			MESSAGE = "msg",
			SENT = "Sent",
			FAILED = "Failed",
			
			//Merchant add vehicle attributes
			WAITING_LIST = "WL",
			ALREADY_ADDED = "AA",
			REJECTED = "RJ",
			BLOCKED = "BL",
			
			//Admin Creation
			NORMAL_ADMIN = "Normal Admin",
			SUPER_ADMIN = "Super Admin",
			ADMIN_LEVEL = "Admin Level",
			STATUS = "Status",
			ÇONFIRM = "Confirm",
			MERCHANT = "Merchant",
			ADMIN = "Admin",
			VISIBLE = "Visible",
			HIDE = "Hide",
			
			DEVELOPER_SIGN = "Developed By Gaurav Srivastava \n gauravsri9828@gmail.com"
			
			
			;
		int MAX_INVALID_PASSWORD_ATTEMPTS = 4;
}
