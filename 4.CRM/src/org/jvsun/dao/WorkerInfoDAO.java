package org.jvsun.dao;

import java.math.BigDecimal;

import org.jvsun.pojo.WorkerPOJO;
/**
 * @author dalin
 *职工信息接口
 */
public interface WorkerInfoDAO {
	public  WorkerPOJO findByWorkerId(BigDecimal workerId);
	public boolean doUpd(WorkerPOJO pojo);
}
