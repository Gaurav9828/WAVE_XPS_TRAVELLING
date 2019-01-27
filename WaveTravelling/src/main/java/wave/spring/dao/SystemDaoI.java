package wave.spring.dao;

import java.util.List;
import wave.spring.model.EmailDetails;
import wave.spring.model.VehicleAttributes;

public interface SystemDaoI {
	public void saveEmailDetails(EmailDetails emailDetails);
	public List<VehicleAttributes> getVehicleList();
}
