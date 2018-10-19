package wave.spring.services;

import java.util.HashMap;

import wave.spring.model.AdminPasswordReset;
import wave.spring.model.Login;

public interface AdminLoginI {
	//added by Gaurav Sriavastava
	public HashMap adminLoginService(Login loginValues);
	public void adminLogout(int employeeId);
	public String resetAdminPassword(int employeeId,String newPassword);
}
