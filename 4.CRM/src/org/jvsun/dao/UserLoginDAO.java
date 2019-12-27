package org.jvsun.dao;

import java.math.BigDecimal;
import java.util.List;

import org.jvsun.pojo.MenuPOJO;
import org.jvsun.pojo.VLoginPOJO;

/**
 * @author dalin
 *用户登录接口
 */
public interface UserLoginDAO {

	public boolean checkLogin(String loginaccount ,String loginPassword );

	public  VLoginPOJO findUserByLogin(String loginaccount);

	public List<MenuPOJO> findMenuByLoginId(int loginId);

	public List<MenuPOJO> findChildMenuByFaherId(BigDecimal menuId);

}
