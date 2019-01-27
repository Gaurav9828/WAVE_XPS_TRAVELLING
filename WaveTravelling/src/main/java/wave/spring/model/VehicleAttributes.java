package wave.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity(name = "WAVE_XPS_VEHICLE_ATTRIBUTES")
@Table(name = "WAVE_XPS_VEHICLE_ATTRIBUTES", uniqueConstraints = { @UniqueConstraint(columnNames = { "ID" }) })
public class VehicleAttributes {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Integer ID;

	@Column(name = "VEHICLE_IMAGE")
	private byte[] image;

	@Column(name = "VEHICLE_COMPANY")
	private String vehicleCompany;

	@Column(name = "VEHICLE_MODEL")
	private String vehicleModel;

	@Column(name = "SEATS")
	private Integer seats;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "ADDED_BY")
	private String addedBy;

	@Column(name = "APPROVED_BY")
	private String approvedBy;
	
	@Column(name = "REJECTED_BY")
	private String rejectedBy;
	
	@Column(name = "BLOCKED_BY")
	private String blockedBy;
	
	@Column(name = "UNBLOCKED_BY")
	private String unblockedBy;

	private String base64Image;

	public String getBase64Image() {
		return base64Image;
	}

	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
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

	public Integer getSeats() {
		return seats;
	}

	public void setSeats(Integer seats) {
		this.seats = seats;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public String getRejectedBy() {
		return rejectedBy;
	}

	public void setRejectedBy(String rejectedBy) {
		this.rejectedBy = rejectedBy;
	}

	public String getBlockedBy() {
		return blockedBy;
	}

	public void setBlockedBy(String blockedBy) {
		this.blockedBy = blockedBy;
	}

	public String getUnblockedBy() {
		return unblockedBy;
	}

	public void setUnblockedBy(String unblockedBy) {
		this.unblockedBy = unblockedBy;
	}
}
