package wave.spring.services;

import java.util.ArrayList;

import wave.spring.model.VehicleDetails;

public interface MerchantServicesI {
	public ArrayList<ArrayList> getVehicleCompanyAndModelList(String status);
	public String setNewMarchantVehicle(VehicleDetails vehicleDetails);
	public String updateVehicleDetails(VehicleDetails vehicleDetails);
	public ArrayList<VehicleDetails> getMerchantVehicleDetails(String ownerId);
	public String deleteVehicleDetails(VehicleDetails vehicleDetails);
}
