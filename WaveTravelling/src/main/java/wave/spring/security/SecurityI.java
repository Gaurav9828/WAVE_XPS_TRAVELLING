package wave.spring.security;

import java.util.HashMap;
import java.util.List;

import wave.spring.model.VechileAttributes;

public interface SecurityI {
	public HashMap<String,String> generateCaptcha();
	public String valueEncrptyer(String value);
	public String sendMail(HashMap<String, String> map);
	public List<VechileAttributes> getVechileList();
}
