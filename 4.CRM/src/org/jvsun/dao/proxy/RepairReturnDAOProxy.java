package org.jvsun.dao.proxy;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.jvsun.dao.RepairReturnDAO;
import org.jvsun.dao.impl.RepairRetrunDAOImpl;
import org.jvsun.pojo.RepairReturnPOJO;
import org.jvsun.tools.JDBCHelper;

public class RepairReturnDAOProxy implements RepairReturnDAO {
	Connection conn = null;
	RepairRetrunDAOImpl impl = null;
	
	public RepairReturnDAOProxy(){
		try {
			this.conn = JDBCHelper.getConn();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.impl = new RepairRetrunDAOImpl(this.conn);
	}
	
	
	//删除代理类
	public boolean doDel(BigDecimal reptId) {
		boolean flag = this.impl.doDel(reptId);
		this.close();
		return flag;
	}

	//新增代理类
	public boolean doIns(RepairReturnPOJO pojo) {
		boolean flag = this.impl.doIns(pojo);
		this.close();
		return flag;
	}

	
	//修改代理类
	public boolean doUpd(RepairReturnPOJO pojo) {
		boolean flag = this.impl.doUpd(pojo);
		this.close();
		return flag;
	}

	
	

	
	//id查询维修回执单代理类
	public RepairReturnPOJO findByReptId(BigDecimal reptId) {
		RepairReturnPOJO pojo = this.impl.findByReptId(reptId);
		this.close();
		return pojo;
	}

	
	//查询维修单回执单数据笔数代理类
	public List<RepairReturnPOJO> findByReptName(String reptName, int pageSize,
			int pageCurrent) {
		List<RepairReturnPOJO> pojo = this.impl.findByReptName(reptName, pageSize, pageCurrent);
		this.close();
		return pojo;
	}

	
	

	
	//姓名查询维修回执单所有数据
	public int findCountByReptName(String reptName) {
		int count = this.impl.findCountByReptName(reptName);
		this.close();
		return count;
	}

	
	
	public void close(){
		try {
			this.conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

/*
	public int countRepairReturn(String reptName, String repairName) {
		int count = this.impl.countRepairReturn( reptName,  repairName);
		this.close();
		return count;
	}


	public List<RepairReturnVwPOJO> findRepairReturn(int pageSize,
			int pageCurrent, String reptName, String repairName) {
		List<RepairReturnVwPOJO> list = this.impl.findRepairReturn(pageSize, pageCurrent, reptName, repairName);
		this.close();
		return list;
	}*/
}
