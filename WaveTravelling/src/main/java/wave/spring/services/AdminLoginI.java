package wave.spring.services;

import java.util.HashMap;

import wave.spring.model.EmployeeDetails;
import wave.spring.model.Login;

public interface AdminLoginI {
	//added by Gaurav Sriavastava
	public HashMap adminLoginService(Login loginValues);
	public void adminLogout(EmployeeDetails employeeDetails);
	public String resetAdminPassword(int employeeId,String newPassword);
	public String resetPasswordAndSendMail(Login resetValues);
}
