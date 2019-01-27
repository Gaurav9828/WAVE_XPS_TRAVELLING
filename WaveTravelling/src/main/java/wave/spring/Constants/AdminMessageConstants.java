package wave.spring.Constants;

public interface AdminMessageConstants {
	String 
	//---------------login error messages
	INVALID_USER = "Either wrong employee id or password ..",
	INVALID_PASSWORD = "Invalid Password",
	INVALID_USERID_OR_SECRET_WORD = "Either wrong employee id or memorable word ..",
	LAST_LOGIN_ATTMEPT = "One more invalid attempt will block your account ..",
	ACCOUNT_BLOCKED = "Your account has been temporarily blocked due to invalid password attempts ..",
	USER_BLACK_LISTED = "Your account has been black-listed due to some policies ..",
	
	//--------------------Merchant Registration messages
    ALREADY_REGISTERED_MSG = "You have already applied for the merchant registration ..",
	ALREADY_MERCHANT_MSG = "You are already a merchant of Wave Xps Travelling",
	BLACK_LISTED_REQUEST_MSG = "Sorry ! You are black listed for registration request due to some company policies ..",
	REGISTRAION_REQUEST_SUCCESSFUL = "Your registration request has been accepted. We have sent you a email for further informations ..",
	SOME_THING_WRONG_REGISTRATION = "Sorry ! Registration failed due to some error. Please try after some time ..",
	REJECTION_SUCCESSFUL = "Merchant application successfully rejected",
	
	//-------------------Merchant addition process messages
	MERCHANT_ADDED = "Merchant added successfully.",
	
	//------------------Admin addition process messages
	ADMIN_ADDED = "Admin added successfully",
	ADMIN_ALREADY_EXISTING = "Requested admin is already an admin",
	
	//------------------------Reset messages
	SOMETHING_WRONG = "Some thing went wrong please try again later ..",
	ADMIN_PASSWORD_RESET_SUCCESSFULL = "Password has been reset successfully.",
	ADMIN_PASSWORD_AND_MEMORABLE_WORD_RESET_SUCCESSFULL = "You have successfully set your password and secret memorable word. Now you may login.",
	
	//--------------------------Add Vehicle Messages
	PLEASE_CHOOSE_A_FILE_TYPE = "Please choose a image type like .png, .jpeg or .jpg",
	VEHICLE_ADDED_SUCCESSFULLY = "Vehicle added successfully.",
	VEHICLE_ALREADY_ADDED = "Vehicle has been already added.",
	VEHICLE_WAITING_LIST = "This Vehicle is already waiting for approval.",
	WAITING_FOR_APPROVAL= "Waiting for approval",
	VEHICLE_APPROVED_SUCCESSFULLY = "Vehicle approved successfully.",
	VEHICLE_REJECTED_SUCCESSFULLY = "Vehicle rejected successfully.",
	VEHICLE_ALREADY_REJECTED = "Vehicle has been rejected already.",
	VEHICLE_BLOCKED = "Vehicle blocked",
	VEHICLE_UNBLOCKED = "Vehicle unblocked",
	ACTIVE = "Active",
	VEHICLE_ADDED_TO_WAITING_APPROVALS = "Vehicle is added to waiting approvals.",
	
	//----------------------------Admin Block / Unblock
	ADMIN_UNBLOCKED = "Admin unblocked",
	ADMIN_BLOCKED = "Admin Blocked",
	
	//--------------------------Admin Add Merchant Vehicle
	INVALID_OWNER = "Invalid owner found for the requested Vehicle",
	INSURENCE_PAPER_ERROR = "Insurence paper is not complete of this Vehicle",
	POLUTION_PAPER_ERROR = "Polution paper is not complete of this Vehicle",
	INVALID_VEHICLE_NUMBER = "Invalid Vehicle number",
	SUCCESSFULL = "Successful",
	PLEASE_TRY_AGAIN = "Please try again after some time.",
	BLOCKED_ATTEMPT = "Blocked Attempt"

    ;

}
