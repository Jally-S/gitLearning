package org.jvsun.dao;

import java.math.BigDecimal;
import java.util.List;

import org.jvsun.pojo.MenuPOJO;
import org.jvsun.pojo.RolePOJO;

/**
 * @author dalin
 *角色菜单接口
 */
public interface RoleMenuDAO {
	public List<RolePOJO> findAll(String roleName,int pageSize,int pageCurrent);
	public List<String> findRoleMenu(BigDecimal roleId);
	public List<MenuPOJO> findAll();
	public int findCountByNameState(String roleName);
	public boolean doDel(BigDecimal roleId,BigDecimal menuId);
	public boolean doIns(BigDecimal roleId,BigDecimal menuId);
}
