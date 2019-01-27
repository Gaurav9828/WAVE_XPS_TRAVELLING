package wave.spring.dao;


import java.util.ArrayList;
import java.util.List;
import wave.spring.model.MenuDetails;
import wave.spring.model.EmployeeDetails;
import wave.spring.model.MerchantDetails;
import wave.spring.model.VehicleAttributes;

public interface AdminDaoI {
  public EmployeeDetails getEmployeeDetails(String id);
  public void setInvalidPasswordAttempt(int userId, int attemptNumber);
  public void updateEmployeeDetails(EmployeeDetails employeeDetails);
  public List<MenuDetails> getEmployeeMenuList(String employeeLevel);
  public String setAdminPassword(int employeeId, String password);
  public String saveVechielDetails(VehicleAttributes vechielAttributes);
  public VehicleAttributes checkVehicleAlreadyExist(String company, String model);
  public void updateVehicleDetails(VehicleAttributes vehicleAttributes);
  public ArrayList<EmployeeDetails> getEmployeeDetailsList();
  public List<MerchantDetails> getAcceptedMerchantList();
  public List<EmployeeDetails> getUnderProcessBlockMerchantList(String stauts, String status2);
  public String updateEmployeeStatus(int userId, String status);
  public ArrayList<VehicleAttributes> getVehicleList(String type, String value);
  public ArrayList<MenuDetails> getMenuDetails();
  public String updateMenuDetails(MenuDetails menuDetails);
}