package wave.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity(name = "WAVE_XPS_ADMIN_MENU")
@Table(name = "WAVE_XPS_ADMIN_MENU", uniqueConstraints = { @UniqueConstraint(columnNames = { "MENU_ID" }) })
public class Employee1MenuList {
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
	
	public Employee1MenuList() {}

	public Employee1MenuList(Integer menuId, String menuName, String menuAction, String menuAdminLevel,
			String menuVisibility) {
		this.menuId = menuId;
		this.menuName = menuName;
		this.menuAction = menuAction;
		this.menuAdminLevel = menuAdminLevel;
		this.menuVisibility = menuVisibility;
	}

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
}
