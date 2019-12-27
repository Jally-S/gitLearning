package com.hzit.dao;

import com.hzit.entity.Admin;

public interface IAdminDAO {
	public Admin login(String username,String password);
}
