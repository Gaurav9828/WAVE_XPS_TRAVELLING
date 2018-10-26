package wave.spring.services;

import java.util.HashMap;

import wave.spring.model.MerchantDetails;

public interface MerchantRegistrationI {
	public String freshRegistrationRequest(MerchantDetails merchantDetails);
	public HashMap getMerchantApplications();
}
