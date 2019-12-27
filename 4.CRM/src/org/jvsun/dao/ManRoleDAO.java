package org.jvsun.dao;

import java.math.BigDecimal;
import java.util.List;

import org.jvsun.pojo.RolePOJO;
import org.jvsun.pojo.VLoginPOJO;

/**
 * @author dalin
 *人员与角色接口
 */
public interface ManRoleDAO {

	public List<VLoginPOJO> findAll(String userName,int pageSize,int pageCurrent);
	public List<String> findUserRole(BigDecimal userId);
	public List<RolePOJO> findAll();
	public int findCountByNameState(String userName);
	public boolean doDel(int loginId,BigDecimal roleId);
	public boolean doIns(int loginId,BigDecimal roleId);
	
}
