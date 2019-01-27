package wave.spring.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import wave.spring.model.EmployeeDetails;
import wave.spring.model.MenuDetails;
import wave.spring.model.MerchantDetails;
import wave.spring.model.VehicleAttributes;
import wave.spring.model.VehicleDetails;

public interface AdminServicesI {
	public String saveVehicleAttributes(VehicleAttributes vehicleAttributes);
	public ArrayList<VehicleAttributes> getListOfVehicles(String stauts);
	public String updateApproveVehicleAttribute(VehicleAttributes vehicleAttributes);
	public String updateRemoveVehicleAttribute(VehicleAttributes vehicleAttributes);
	public String createAdmin(EmployeeDetails employeeDetails);
	public ArrayList<EmployeeDetails> getAdminDetailsList();
	public String updateAdminAttributes(EmployeeDetails empDetails);
	public List<EmployeeDetails> getBlockRequestMerchantList();
	public HashMap<?, ?> getMerchants();
	public String updateMerchantStatusForBlackListing(int userId,String merchantCode, String status);
	public String blockUnBlockMerchantProcess(MerchantDetails merchantDetails, String status);
	public ArrayList<VehicleDetails> getListOfWaitingMarchantVecihles(String status);
	public ArrayList<ArrayList> getMenuDetails();
	public String updateMenuDetails(MenuDetails menuDetails);
	public ArrayList<VehicleAttributes> getAdminSubmittedVehicles(String employeeId);
}
