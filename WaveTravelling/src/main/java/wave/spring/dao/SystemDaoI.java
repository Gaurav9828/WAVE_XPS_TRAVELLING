package wave.spring.dao;

import java.util.ArrayList;
import java.util.List;

import wave.spring.model.EmailDetails;
import wave.spring.model.VechileAttributes;

public interface SystemDaoI {
	public void saveEmailDetails(EmailDetails emailDetails);
	public List<VechileAttributes> getVechileList();
}
