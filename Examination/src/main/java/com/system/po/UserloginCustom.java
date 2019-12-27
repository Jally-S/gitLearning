package com.system.po;

/**
 * Userlogin扩展类
 */
public class UserloginCustom extends Userlogin
{
	private static final long serialVersionUID = 746848860777769967L;
	
	private Role role_ob;

	public void setRole(Role role_ob)
	{
		this.role_ob = role_ob;
	}

	public Role getRole_ob()
	{
		return role_ob;
	}

}
