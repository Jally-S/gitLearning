package org.jvsun.dao.proxy;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

import org.jvsun.dao.FeedBackDAO;
import org.jvsun.dao.impl.FeedBackDAOImpl;
import org.jvsun.pojo.FeedBackPOJO;
import org.jvsun.pojo.ProductPOJO;
import org.jvsun.tools.JDBCHelper;
/**
 * ������Ϣ������
 * @author Administrator
 *
 */
public class FeedBackDAOProxy implements FeedBackDAO {
	Connection conn = null;
	FeedBackDAOImpl impl=null;
	/*
	 * ���췽��
	 */
	public FeedBackDAOProxy(){
		try {
			this.conn = JDBCHelper.getConn();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.impl=new FeedBackDAOImpl(this.conn);
	}
	/*
	 * ������Ϣ��ɾ��
	 * @see org.jvsun.dao.FeedBackDAO#doDel(java.math.BigDecimal)
	 */
	public boolean doDel(BigDecimal feedBackId) {
		boolean flag = this.impl.doDel(feedBackId);
		this.close();
		return flag;
	}
	/*
	 * ������Ϣ�����
	 * @see org.jvsun.dao.FeedBackDAO#doIns(org.jvsun.pojo.FeedBackPOJO)
	 */
	public boolean doIns(FeedBackPOJO pojo) {
		boolean flag = this.impl.doIns(pojo);
		this.close();
		return flag;
	}
	/*
	 * ��ѯĳ���ͻ�������Ϣ����
	 * @see org.jvsun.dao.FeedBackDAO#findById(java.math.BigDecimal)
	 */
	public int findById(BigDecimal customerId) {
		int count = this.impl.findById(customerId);
		this.close();
		return count;
	}
	/*
	 * ��ҳ��ѯ�ͻ�������¼
	 * @see org.jvsun.dao.FeedBackDAO#findById(java.lang.String, java.math.BigDecimal, int, int)
	 */
	public List<FeedBackPOJO> findById(String productName,BigDecimal customerId,int pageSize, int pageCurrent) {
		List<FeedBackPOJO> list = this.impl.findById(productName,customerId,pageSize,pageCurrent);
		this.close();
		return list;
	}
	/*
	 * �޸ķ�����Ϣ
	 * @see org.jvsun.dao.FeedBackDAO#update(java.math.BigDecimal, java.lang.String)
	 */
	public boolean update(BigDecimal feedBackId,
			String FeedbackContent) {
		boolean flag = this.impl.update(feedBackId, FeedbackContent);
		this.close();
		return flag;
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
	/*
	 * ��ѯ���з�����Ϣ
	 * @see org.jvsun.dao.FeedBackDAO#findByCustomerId(java.math.BigDecimal)
	 */
	public List<ProductPOJO> findByCustomerId(BigDecimal customerId) {
		List<ProductPOJO> list = this.impl.findByCustomerId(customerId);
		this.close();
		return list;
	}
	/*
	 * ��ѯ���ʷ�����Ϣ
	 * @see org.jvsun.dao.FeedBackDAO#findOneByCustomerId(java.math.BigDecimal)
	 */
	public FeedBackPOJO findOneByCustomerId(BigDecimal feedBackId) {
		FeedBackPOJO pojo = this.impl.findOneByCustomerId(feedBackId);
		this.close();
		return pojo;
	}
	
	

	//id查询反馈单代理类
	public FeedBackPOJO findByFeedBackId(BigDecimal feedBackId) {
		FeedBackPOJO pojo = this.impl.findByFeedBackId(feedBackId);
		this.close();
		return pojo;
	}

	
	

	//查询反馈单数据笔数
	public List<FeedBackPOJO> findByProductName(String productName,int isOver,
			int pageSize, int pageCurrent) {
		List<FeedBackPOJO> pojo = this.impl.findByProductName(productName, isOver,pageSize, pageCurrent);
		this.close();
		return pojo;
	}
	
	
	

	//姓名查询反馈单所有数据
	public int findCountByProductName(String productName,int isOver) {
		int count = this.impl.findCountByProductName(productName,isOver);
		this.close();
		return count;
	}
	
}
