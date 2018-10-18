package wave.spring.dao;


import java.util.List;

import wave.spring.model.Employee1MenuList;
import wave.spring.model.EmployeeDetails;

public interface AdminDaoI {
  public EmployeeDetails getEmployeeDetails(String id);
  public void setInvalidPasswordAttempt(int userId, int attemptNumber);
  public void setAdminUserLoggedIn(EmployeeDetails employeeDetails);
  public List<Employee1MenuList> getEmployeeMenuList(String employeeLevel);
  public void setAdminLogout(int employeeId);
}