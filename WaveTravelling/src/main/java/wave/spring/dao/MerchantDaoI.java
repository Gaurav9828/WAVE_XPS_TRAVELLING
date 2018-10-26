package wave.spring.dao;

import java.util.List;

import wave.spring.model.MerchantDetails;

public interface MerchantDaoI {
	public MerchantDetails merchantAlreadyExist(String mobileNumber);
	public String applyForWatingMerchantship(MerchantDetails merchantDetails);
	public List<MerchantDetails> getAcceptedMerchantList();
}
