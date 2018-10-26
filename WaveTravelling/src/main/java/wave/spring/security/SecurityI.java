package wave.spring.security;

import java.util.HashMap;

public interface SecurityI {
	public HashMap<String,String> generateCaptcha();
	public String valueEncrptyer(String value);
	public String sendMail(HashMap<String, String> map);
}
