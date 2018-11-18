package wave.spring.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity(name = "WAVE_XPS_VECHILE_DETAILS")
@Table(name = "WAVE_XPS_VECHILE_DETAILS", uniqueConstraints = { @UniqueConstraint(columnNames = { "VECHILE_ID" }) })
public class VechileDetails {
	
	@Id
	@GeneratedValue
	@Column(name = "VECHILE_ID")
	private Integer vechileId;
	
	@Column(name = "VECHILE_NUMBER")
	private String vechileNumnber ;
	
	@Column(name = "VECHILE_NAME")
	private String vechileName;
		
	@Column(name = "OWNER_ID")
	private String ownerId;
	
	@Column(name = "INSURENCE_NUMBER")
	private Integer insurenceNumber;
	
	@Column(name = "STATUS")
	private String status = "";
	
	@Column(name = "ADDITION_DATE_TIME")
	private LocalDate additionDateTime;

	public Integer getVechileId() {
		return vechileId;
	}

	public void setVechileId(Integer vechileId) {
		this.vechileId = vechileId;
	}

	public String getVechileNumnber() {
		return vechileNumnber;
	}

	public void setVechileNumnber(String vechileNumnber) {
		this.vechileNumnber = vechileNumnber;
	}

	public String getVechileName() {
		return vechileName;
	}

	public void setVechileName(String vechileName) {
		this.vechileName = vechileName;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public Integer getInsurenceNumber() {
		return insurenceNumber;
	}

	public void setInsurenceNumber(Integer insurenceNumber) {
		this.insurenceNumber = insurenceNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getAdditionDateTime() {
		return additionDateTime;
	}

	public void setAdditionDateTime(LocalDate additionDateTime) {
		this.additionDateTime = additionDateTime;
	}


	
}
