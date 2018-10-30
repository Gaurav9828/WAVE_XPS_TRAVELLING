package wave.spring.services;

import java.util.HashMap;

import wave.spring.model.MerchantDetails;

public interface MerchantRegistrationI {
	public String freshRegistrationRequest(MerchantDetails merchantDetails);
	public HashMap getMerchantApplications();
	public String deleteMerchantApplication(int merchantId, String mailId);
	public String createMerchant(MerchantDetails merchant, String creator);
}
