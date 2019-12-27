package org.jvsun.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

public class RolePOJO implements Serializable {

	private BigDecimal roleId;// 角色ID
	private String roleName;// 角色名
	private int roleMark;// 角色标识
	private int isDelete;

	public BigDecimal getRoleId() {
		return roleId;
	}

	public void setRoleId(BigDecimal roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getRoleMark() {
		return roleMark;
	}

	public void setRoleMark(int roleMark) {
		this.roleMark = roleMark;
	}

	public int getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}

	public RolePOJO(BigDecimal roleId, String roleName, int roleMark,
			int isDelete) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.roleMark = roleMark;
		this.isDelete = isDelete;
	}

	public RolePOJO(String roleName, int roleMark, int isDelete) {
		super();

		this.roleName = roleName;
		this.roleMark = roleMark;
		this.isDelete = isDelete;
	}

	public RolePOJO() {
		super();
		// TODO Auto-generated constructor stub
	}

}
