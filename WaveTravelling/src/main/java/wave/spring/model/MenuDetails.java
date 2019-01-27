package wave.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity(name = "WAVE_XPS_ADMIN_MENU")
@Table(name = "WAVE_XPS_ADMIN_MENU", uniqueConstraints = { @UniqueConstraint(columnNames = { "MENU_ID" }) })
public class MenuDetails {
	@Id
	@GeneratedValue
	@Column(name = "MENU_ID")
	private Integer menuId;
	
	@Column(name = "MENU_NAME")
	private String menuName;
	
	@Column(name = "MENU_ACTION")
	private String menuAction;
	
	@Column(name = "MENU_ADMIN_LEVEL")
	private String menuAdminLevel;
	
	@Column(name = "MENU_VISIBILITY")
	private String menuVisibility;
	
	@Column(name = "MENU_DOMAIN")
	private String menuDomain;

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuAction() {
		return menuAction;
	}

	public void setMenuAction(String menuAction) {
		this.menuAction = menuAction;
	}

	public String getMenuAdminLevel() {
		return menuAdminLevel;
	}

	public void setMenuAdminLevel(String menuAdminLevel) {
		this.menuAdminLevel = menuAdminLevel;
	}

	public String getMenuVisibility() {
		return menuVisibility;
	}

	public void setMenuVisibility(String menuVisibility) {
		this.menuVisibility = menuVisibility;
	}

	public String getMenuDomain() {
		return menuDomain;
	}

	public void setMenuDomain(String menuDomain) {
		this.menuDomain = menuDomain;
	}
	
}
