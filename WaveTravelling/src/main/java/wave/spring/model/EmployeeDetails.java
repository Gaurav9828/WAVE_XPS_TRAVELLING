package wave.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
 
@Entity(name = "WAVE_XPS_ADMIN_DETAILS")
@Table(name = "WAVE_XPS_ADMIN_DETAILS", uniqueConstraints = { @UniqueConstraint(columnNames = { "USER_ID" }) })
public class EmployeeDetails {

	@Id
	@GeneratedValue
	@Column(name = "USER_ID")
	private Integer userId;
	
	@Column(name = "EMPLOYEE_ID")
	private String employeeId;
	
	@Column(name = "MOBILE_NUMBER")
	private String mobileNumber;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "MEMORABLE_WORD")	
	private String memorableWord;
	
	@Column(name = "ADMIN_LEVEL")
	private String adminLevel;
	
	@Column(name = "FIRST_NAME")
	private String firstName;
	
	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "EMAIL_ID")
	private String emailId;
	
	@Column(name = "LAST_LOGIN")
	private String lastLogin;
	
	@Column(name = "LAST_LOGOUT")
	private String lastLogout;
	
	@Column(name = "INVALID_PASSWORD_ATTEMPTS")
	private Integer invalidPasswordAttempts;
	
	@Column(name = "ADDED_BY_EMP_ID")
	private String addedByEmpId;
	
	public EmployeeDetails(){}


	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMemorableWord() {
		return memorableWord;
	}

	public void setMemorableWord(String memorableWord) {
		this.memorableWord = memorableWord;
	}

	public String getAdminLevel() {
		return adminLevel;
	}

	public void setAdminLevel(String adminLevel) {
		this.adminLevel = adminLevel;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Integer getInvalidPasswordAttempts() {
		return invalidPasswordAttempts;
	}

	public void setInvalidPasswordAttempts(Integer invalidPasswordAttempts) {
		this.invalidPasswordAttempts = invalidPasswordAttempts;
	}

	public String getLastLogin() {
		return lastLogin;
	}

	public String getLastLogout() {
		return lastLogout;
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


	public void setLastLogout(String lastLogout) {
		this.lastLogout = lastLogout;
	}


	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getAddedByEmpId() {
		return addedByEmpId;
	}

	public void setAddedByEmpId(String addedByEmpId) {
		this.addedByEmpId = addedByEmpId;
	}

}