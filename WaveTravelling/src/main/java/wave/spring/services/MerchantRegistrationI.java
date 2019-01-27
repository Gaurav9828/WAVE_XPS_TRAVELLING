package wave.spring.services;

import java.util.HashMap;
import java.util.List;

import wave.spring.model.EmailDetails;
import wave.spring.model.MerchantDetails;

public interface MerchantRegistrationI {
	public String freshRegistrationRequest(MerchantDetails merchantDetails);
	public HashMap<?, ?> getMerchantApplications();
	public String deleteMerchantApplication(int merchantId, String mailId);
	public String createMerchant(MerchantDetails merchant, String creator);
	public MerchantDetails getMerchantDetails(String merchantId, List<MerchantDetails> list);
	public List<EmailDetails> getPendingEmails();
	public String generateMerchantId(MerchantDetails merchantDetails);
}
