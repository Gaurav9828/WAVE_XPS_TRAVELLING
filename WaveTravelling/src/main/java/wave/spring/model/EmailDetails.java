package wave.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
 
@Entity(name = "WAVE_XPS_EMAILS")
@Table(name = "WAVE_XPS_EMAILS", uniqueConstraints = { @UniqueConstraint(columnNames = { "S_NO" }) })
public class EmailDetails {
	
	@Id
	@GeneratedValue
	@Column(name = "S_NO")
	private Integer serialNumber;
	
	@Column(name = "SOURCE_EMAIL_ID")
	private String sourceEmailId;
	
	@Column(name = "DESTINATION_EMAIL_ID")
	private String destinationEmailId;
	
	@Column(name = "SUBJECT")
	private String subject;
	
	@Column(name = "MESSAGE")
	private String message;
	
	@Column(name = "DATE_TIME")	
	private String dateTime;
	
	@Column(name = "EMAIL_STATUS")	
	private String emailStatus;

	public Integer getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getSourceEmailId() {
		return sourceEmailId;
	}

	public void setSourceEmailId(String sourceEmailId) {
		this.sourceEmailId = sourceEmailId;
	}

	public String getDestinationEmailId() {
		return destinationEmailId;
	}

	public void setDestinationEmailId(String destinationEmailId) {
		this.destinationEmailId = destinationEmailId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getEmailStatus() {
		return emailStatus;
	}

	public void setEmailStatus(String emailStatus) {
		this.emailStatus = emailStatus;
	}
	
	
}