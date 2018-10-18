package wave.spring.services;

import java.util.HashMap;

import wave.spring.model.Login;

public interface AdminLoginI {
	//added by Gaurav Sriavastava
	public HashMap adminLoginService(Login loginValues);
	public void adminLogout(int employeeId);
}
