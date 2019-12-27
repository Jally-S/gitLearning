package org.jvsun.dao.proxy;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

import org.jvsun.dao.RepairDAO;
import org.jvsun.dao.impl.RepairDAOImpl;
import org.jvsun.pojo.ProductPOJO;
import org.jvsun.pojo.RepairPOJO;
import org.jvsun.tools.JDBCHelper;
/**
 * ������Ϣ������
 * @author Administrator
 *
 */
public class RepairDAOProxy implements RepairDAO {
	Connection conn = null;
	RepairDAOImpl impl=null;
	/*
	 * ���췽��
	 */
	public RepairDAOProxy(){
		try {
			this.conn = JDBCHelper.getConn();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.impl=new RepairDAOImpl(this.conn);
	}
	/*
	 * ɾ����(non-Javadoc)
	 * @see org.jvsun.dao.RepairDAO#doDel(java.math.BigDecimal)
	 */
	public boolean doDel(BigDecimal repairId) {
		boolean flag = this.impl.doDel(repairId);
		this.close();
		return flag;
	}
	/*
	 * ��ӱ���(non-Javadoc)
	 * @see org.jvsun.dao.RepairDAO#doIns(org.jvsun.pojo.RepairPOJO)
	 */
	public boolean doIns(RepairPOJO pojo) {
		boolean flag = this.impl.doIns(pojo);
		this.close();
		return flag;
	}
	/*
	 * �޸ı���(non-Javadoc)
	 * @see org.jvsun.dao.RepairDAO#doUpd(java.math.BigDecimal, java.lang.String)
	 */
	public boolean doUpd(BigDecimal repairId, String repairContent) {
		boolean flag = this.impl.doUpd(repairId, repairContent);
		this.close();
		return flag;
	}
	/*
	 * ��ѯĳ���ͻ����еķ���(non-Javadoc)
	 * @see org.jvsun.dao.RepairDAO#findByCustomerId(java.math.BigDecimal)
	 */
	public List<ProductPOJO> findByCustomerId(BigDecimal customerId) {
		List<ProductPOJO> list = this.impl.findByCustomerId(customerId);
		this.close();
		return list;
	}
	/*
	 * ��ҳ��ѯ���з���(non-Javadoc)
	 * @see org.jvsun.dao.RepairDAO#findByProduct(java.lang.String, java.math.BigDecimal, int, int)
	 */
	public List<RepairPOJO> findByProduct(String productName,
			BigDecimal customerId, int pageSize, int pageCurrent) {
		List<RepairPOJO> list = this.impl.findByProduct(productName, customerId, pageSize, pageCurrent);
		this.close();
		return list;
	}
	/*
	 * ��ѯ���ʷ�����Ϣ(non-Javadoc)
	 * @see org.jvsun.dao.RepairDAO#findByRepairId(java.math.BigDecimal)
	 */
	public RepairPOJO findByRepairId(BigDecimal repairId) {
		RepairPOJO pojo = this.impl.findByRepairId(repairId);
		this.close();
		return pojo;
	}
	/*
	 * ��ѯĳ���ͻ�������Ϣ���ܱ���(non-Javadoc)
	 * @see org.jvsun.dao.RepairDAO#findCountById(java.math.BigDecimal)
	 */
	public int findCountById(BigDecimal customerId) {
		int count = this.impl.findCountById(customerId);
		this.close();
		return count;
	}
	
	
	
	//id查询报修单代理类
	public RepairPOJO findByRepairIdJi(BigDecimal repairId) {
		RepairPOJO pojo = this.impl.findByRepairIdJi(repairId);
		this.close();
		return pojo;
	}

	
	//查询报修单数据笔数代理类
	public List<RepairPOJO> findByProductName(String productName,int isOver, int pageSize,
			int pageCurrent) {
		List<RepairPOJO> pojo = this.impl.findByProductName(productName,isOver, pageSize, pageCurrent);
		this.close();
		return pojo;
	}
	
	
	

	//姓名查询报修单所有数据代理类
	public int findCountByProductName(String productName,int isOver) {
		int count = this.impl.findCountByProductName(productName,isOver);
		this.close();
		return count;
	}
	
	
	
	/*
	 * �ر���ݿ�����
	 */
	public void close(){
		try {
			this.conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
