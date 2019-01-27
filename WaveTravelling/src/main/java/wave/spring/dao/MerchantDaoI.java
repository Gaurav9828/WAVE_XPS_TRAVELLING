package wave.spring.dao;

import java.util.ArrayList;
import java.util.List;

import wave.spring.model.EmailDetails;
import wave.spring.model.EmployeeDetails;
import wave.spring.model.MerchantDetails;
import wave.spring.model.VehicleDetails;

public interface MerchantDaoI {
	public MerchantDetails merchantAlreadyExist(String mobileNumber);
	public String applyForWatingMerchantship(MerchantDetails merchantDetails);
	public String rejectApplication(int merchantId);
	public String insertMerchantAdmin(EmployeeDetails employeeDetails);
	public void updateMerchant(MerchantDetails merchantDetails);
	public List<EmailDetails> getPendingEmailDetails();
	public List<MerchantDetails> getApplicantMerchantList(String stauts);
	public MerchantDetails getMerchantDetails(String merchantCode);
	public String updateMarchantVehicle(VehicleDetails vehicleDetails);
	public String saveMarchantVehicle(VehicleDetails vehicleDetails);
	public VehicleDetails vehicleMarchantAlreadyExist(String vehicleNumber);
	public ArrayList<VehicleDetails> getListOfVehicleFromVehicleDetails(String status);
	public ArrayList<VehicleDetails> getMerchantVehicleDetails(String ownerId);
	public String deleteMarchantVehicle(VehicleDetails vehicleDetails);
}
