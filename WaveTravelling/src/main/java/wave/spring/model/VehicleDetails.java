package wave.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity(name = "WAVE_XPS_VEHICLE_DETAILS")
@Table(name = "WAVE_XPS_VEHICLE_DETAILS", uniqueConstraints = { @UniqueConstraint(columnNames = { "VEHICLE_ID" }) })
public class VehicleDetails {

	@Id
	@GeneratedValue
	@Column(name = "VEHICLE_ID")
	private Integer vehicleId;

	@Column(name = "VEHICLE_NUMBER")
	private String vehicleNumber;

	@Column(name = "VEHICLE_COMPANY")
	private String vehicleCompany;

	@Column(name = "VEHICLE_MODEL")
	private String vehicleModel;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "ADDED_ON")
	private String addedOn;

	@Column(name = "OWNER_ID")
	private String ownerId;

	@Column(name = "OWNER_NAME")
	private String ownerName;

	@Column(name = "APPROVED_BY")
	private String approvedBy;

	@Column(name = "REJECTED_BY")
	private String rejectedBy;

	@Column(name = "MESSAGE")
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRejectedBy() {
		return rejectedBy;
	}

	public void setRejectedBy(String rejectedBy) {
		this.rejectedBy = rejectedBy;
	}

	

	public Integer getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public String getVehicleCompany() {
		return vehicleCompany;
	}

	public void setVehicleCompany(String vehicleCompany) {
		this.vehicleCompany = vehicleCompany;
	}

	public String getVehicleModel() {
		return vehicleModel;
	}

	public void setVehicleModel(String vehicleModel) {
		this.vehicleModel = vehicleModel;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAddedOn() {
		return addedOn;
	}

	public void setAddedOn(String addedOn) {
		this.addedOn = addedOn;
	}

}
