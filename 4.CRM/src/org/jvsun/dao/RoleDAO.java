package org.jvsun.dao;

import java.math.BigDecimal;
import java.util.List;

import org.jvsun.pojo.RolePOJO;


/**
 * @author dalin
 *角色接口
 */
public interface RoleDAO {
	public List<RolePOJO> findAll(String roleName,int isDelete,int pageSize,int pageCurrent);														
	public int findCountByNameState(String roleName,int isDelete);
	
	public  RolePOJO findByRoleId(BigDecimal roleId);
	public boolean doUpd(RolePOJO pojo);
	public boolean doIns(RolePOJO pojo);


}
