package org.jvsun.dao;

import java.math.BigDecimal;

/**
 * @author dalin
 *修改密码接口
 */
public interface ModifyPasswordDAO {
	public boolean  doUpdate(String password,BigDecimal loginId);
}
