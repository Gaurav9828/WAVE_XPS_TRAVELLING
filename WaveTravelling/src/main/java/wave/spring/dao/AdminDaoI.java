package wave.spring.dao;


import wave.spring.model.EmployeeDetails;

public interface AdminDaoI {
  public EmployeeDetails getEmployeeDetails(String id);
  public void setInvalidPasswordAttempt(int userId, int attemptNumber);
  public void setAdminUserLoggedIn(EmployeeDetails employeeDetails);
}