package wave.spring.dao;

import java.util.HashMap;
import java.util.List;

import wave.spring.model.EmailDetails;
import wave.spring.model.EmployeeDetails;
import wave.spring.model.MerchantDetails;

public interface MerchantDaoI {
	public MerchantDetails merchantAlreadyExist(String mobileNumber);
	public String applyForWatingMerchantship(MerchantDetails merchantDetails);
	public List<MerchantDetails> getAcceptedMerchantList();
	public List<MerchantDetails> getApplicantMerchantList();
	public String rejectApplication(int merchantId);
	public String insertMerchantAdmin(EmployeeDetails employeeDetails);
	public void updateMerchant(MerchantDetails merchantDetails);
	public List<EmailDetails> getPendingEmailDetails();
}
