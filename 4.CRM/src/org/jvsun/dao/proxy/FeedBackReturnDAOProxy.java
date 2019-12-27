package org.jvsun.dao.proxy;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.jvsun.dao.FeedBackReturnDAO;
import org.jvsun.dao.impl.FeedBackReturnDAOImpl;
import org.jvsun.pojo.FeedBackReturnPOJO;
import org.jvsun.tools.JDBCHelper;

public class FeedBackReturnDAOProxy implements FeedBackReturnDAO {
	Connection conn = null;
	FeedBackReturnDAOImpl impl = null;
	
	public FeedBackReturnDAOProxy(){
		try {
			this.conn = JDBCHelper.getConn();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.impl =new FeedBackReturnDAOImpl(this.conn);
	}
	
	
	
	//删除代理类
	public boolean doDel(BigDecimal feedId) {
		boolean flag = this.impl.doDel(feedId);
		this.close();
		return flag;
	}

	
	//新增代理类
	public boolean doIns(FeedBackReturnPOJO pojo) {
		boolean flag= this.impl.doIns(pojo);
		this.close();
		return flag;
	}

	
	//修改代理类
	public boolean doUpd(FeedBackReturnPOJO pojo) {
		boolean flag= this.impl.doUpd(pojo);
		this.close();
		return flag;
	}

	
	
	
	//id查询反馈回执单代理类
	public FeedBackReturnPOJO findByFeedId(BigDecimal feedId) {
		FeedBackReturnPOJO pojo = this.impl.findByFeedId(feedId);
		this.close();
		return pojo;
	}

	
	//查询反馈回执单数据笔数
	public List<FeedBackReturnPOJO> findByFeedName(String feedName,
			int pageSize, int pageCurrent) {
		List<FeedBackReturnPOJO> pojo = this.impl.findByFeedName(feedName, pageSize, pageCurrent);
		this.close();
		return pojo;
		
	}

	

	
	//姓名查询反馈回执单所有数据
	public int findCountByFeedName(String feedName) {
		int count = this.impl.findCountByFeedName(feedName);
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



/*	public int countRepairReturn(String feedName, String productName) {
		int count = this.impl.countRepairReturn(feedName,productName);
		this.close();
		return count;
	}



	public List<FeedbackReturnVwPOJO> findFeedBackReturn(int pageSize,
			int pageCurrent, String feedName, String productName) {
		List<FeedbackReturnVwPOJO> list = this.impl.findFeedBackReturn( pageSize, pageCurrent,feedName,productName);
		this.close();
		return list;
	}
*/
}
