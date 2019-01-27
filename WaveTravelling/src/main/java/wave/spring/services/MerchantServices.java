package wave.spring.services;

import java.util.ArrayList;

import wave.spring.Constants.AdminMessageConstants;
import wave.spring.Constants.SystemConstants;
import wave.spring.dao.AdminDao;
import wave.spring.dao.AdminDaoI;
import wave.spring.dao.MerchantDao;
import wave.spring.dao.MerchantDaoI;
import wave.spring.model.VehicleAttributes;
import wave.spring.model.VehicleDetails;

public class MerchantServices implements MerchantServicesI {
	MerchantDaoI marchantDao = new MerchantDao();
	AdminDaoI adminDao = new AdminDao();
	public ArrayList<ArrayList> getVehicleCompanyAndModelList(String status) {
		ArrayList<VehicleAttributes> vehicleAttributes = new ArrayList<VehicleAttributes>();
		vehicleAttributes = adminDao.getVehicleList("status",status);
		ArrayList<ArrayList> list = new ArrayList<ArrayList>();
		for(VehicleAttributes va : vehicleAttributes) {
			ArrayList l = new ArrayList();
			l.add(va.getVehicleCompany());
			l.add(va.getVehicleModel());
			list.add(l);
		}
		return list;
	}
	
	public String setNewMarchantVehicle(VehicleDetails vehicleDetails) {
		String message = SystemConstants.ERROR;
		VehicleDetails vd = marchantDao.vehicleMarchantAlreadyExist(vehicleDetails.getVehicleNumber());
		if(vd!=null) {
			message = AdminMessageConstants.VEHICLE_ALREADY_ADDED;
		}else {
			message = marchantDao.saveMarchantVehicle(vehicleDetails);
		}
		return message;
	}
	
	public String updateVehicleDetails(VehicleDetails vehicleDetails) {
		String message = SystemConstants.ERROR;
		message = marchantDao.updateMarchantVehicle(vehicleDetails);
		return message;
	}
	
	public ArrayList<VehicleDetails> getMerchantVehicleDetails(String ownerId){
		ArrayList<VehicleDetails> vehicleDetails = new ArrayList<VehicleDetails>();
		vehicleDetails = marchantDao.getMerchantVehicleDetails(ownerId);
		return vehicleDetails;
	}
	
	public String deleteVehicleDetails(VehicleDetails vehicleDetails) {
		String message = SystemConstants.ERROR;
		message = marchantDao.deleteMarchantVehicle(vehicleDetails);
		return message;
	}


}
