package wave.spring.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity(name = "WAVE_XPS_VECHILE_ATTRIBUTES")
@Table(name = "WAVE_XPS_VECHILE_ATTRIBUTES", uniqueConstraints = { @UniqueConstraint(columnNames = { "ID" }) })
public class VechileAttributes {
	
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Integer ID;
	
	@Column(name = "VECHILE_IMAGE")
	private byte[] image;
	
	@Column(name = "VECHILE_COMPANY")
	private String vechileCompany ;
	
	@Column(name = "VECHILE_MODEL")
	private String vechileModel;
	
	@Column(name = "SEATS")
	private Integer seats;

	

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

	public String getVechileCompany() {
		return vechileCompany;
	}

	public void setVechileCompany(String vechileCompany) {
		this.vechileCompany = vechileCompany;
	}

	public String getVechileModel() {
		return vechileModel;
	}

	public void setVechileModel(String vechileModel) {
		this.vechileModel = vechileModel;
	}

	public Integer getSeats() {
		return seats;
	}

	public void setSeats(Integer seats) {
		this.seats = seats;
	}
	
}
