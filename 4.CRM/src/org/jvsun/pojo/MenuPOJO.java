package org.jvsun.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

public class MenuPOJO implements Serializable {
	private BigDecimal menuId; // ²Ëµ¥ID
	private String menuPath;// Â·¾¶
	private String menuName;// Ãû×Ö
	private int isDelete;

	public BigDecimal getMenuId() {
		return menuId;
	}

	public void setMenuId(BigDecimal menuId) {
		this.menuId = menuId;
	}

	public String getMenuPath() {
		return menuPath;
	}

	public void setMenuPath(String menuPath) {
		this.menuPath = menuPath;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public int getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}

	public MenuPOJO(BigDecimal menuId, String menuPath, String menuName,
			int isDelete) {
		super();
		this.menuId = menuId;
		this.menuPath = menuPath;
		this.menuName = menuName;
		this.isDelete = isDelete;
	}

	public MenuPOJO(String menuPath, String menuName, int isDelete) {
		super();

		this.menuPath = menuPath;
		this.menuName = menuName;
		this.isDelete = isDelete;
	}

	public MenuPOJO() {
		super();
		// TODO Auto-generated constructor stub
	}
}
