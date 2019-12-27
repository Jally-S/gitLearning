package org.jvsun.dao;

import java.math.BigDecimal;
import java.util.List;

import org.jvsun.pojo.MenuPOJO;

/**
 * @author dalin
 *菜单接口
 */
public interface MenuDAO {
	public List<MenuPOJO> findAll(String menuName, int isDelete, int pageSize,
			int pageCurrent);													
	public int findCountByNameState(String menuName,int isDelete);
	public  MenuPOJO findByMenuId(BigDecimal menuId);
	public boolean doUpd(MenuPOJO pojo);
	public boolean doIns(MenuPOJO pojo);
	
	
}
