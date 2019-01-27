package wave.spring.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import wave.spring.model.VehicleAttributes;

public interface SecurityI {
	public HashMap<String,String> generateCaptcha();
	public String valueEncrptyer(String value);
	public String sendMail(HashMap<String, String> map);
	public List<VehicleAttributes> getVehicleList();
	public ArrayList<VehicleAttributes> getImageBlob(ArrayList<VehicleAttributes> listOfVehicles) throws IOException;
}
