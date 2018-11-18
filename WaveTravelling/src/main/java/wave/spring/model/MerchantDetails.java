package wave.spring.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity(name = "WAVE_XPS_MERCHANT_DETAILS")
@Table(name = "WAVE_XPS_MERCHANT_DETAILS", uniqueConstraints = { @UniqueConstraint(columnNames = { "MERCHANT_ID" }) })
public class MerchantDetails {
	
	@Id
	@GeneratedValue
	@Column(name = "MERCHANT_ID")
	private Integer marchantId;
	
	@Column(name = "MOBILE_NUMBER")
	private String mobileNumber ;
	
	@Column(name = "FIRST_NAME")
	private String firstName;
	
	@Column(name = "LAST_NAME")
	private String lastName;
	
	@Column(name = "CITY")
	private String city;
	
	@Column(name = "PIN_CODE")
	private Integer pinCode;
	
	@Column(name = "LAND_MARK")
	private String landMark;
	
	@Column(name = "IDENTITY_TYPE")
	private String identityType;
	
	@Column(name = "IDENTITY_NUMBER")
	private String identityNumber;
	
	@Column(name = "EMAIL_ID")
	private String emailId;
	
	@Column(name = "STATUS")
	private String status = "";
	
	@Column(name = "SUBMISSION_DATE")
	private LocalDate submissionDate;
	
	@Column(name = "APPOINTEMENT_DATE")
	private String appointementDates = "";

	public Integer getMarchantId() {
		return marchantId;
	}

	public void setMarchantId(Integer marchantId) {
		this.marchantId = marchantId;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getPinCode() {
		return pinCode;
	}

	public void setPinCode(Integer pinCode) {
		this.pinCode = pinCode;
	}

	public String getLandMark() {
		return landMark;
	}

	public void setLandMark(String landMark) {
		this.landMark = landMark;
	}

	public String getIdentityType() {
		return identityType;
	}

	public void setIdentityType(String identityType) {
		this.identityType = identityType;
	}

	public String getIdentityNumber() {
		return identityNumber;
	}

	public void setIdentityNumber(String identityNumber) {
		this.identityNumber = identityNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(LocalDate submissionDate) {
		this.submissionDate = submissionDate;
	}

	public String getAppointementDates() {
		return appointementDates;
	}

	public void setAppointementDates(String appointementDates) {
		this.appointementDates = appointementDates;
	}
	
}
