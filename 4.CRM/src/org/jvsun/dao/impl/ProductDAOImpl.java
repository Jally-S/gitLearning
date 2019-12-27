package org.jvsun.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jvsun.dao.ProductDAO;
import org.jvsun.pojo.ProductPOJO;

/**
 * @author dalin
 *
 */
public class ProductDAOImpl implements ProductDAO {
	Connection conn;
	public ProductDAOImpl(Connection conn){
		this.conn = conn;
	}
	/* (non-Javadoc)
	 * @see org.jvsun.dao.ProductDAO#doIns(org.jvsun.pojo.ProductPOJO)
	 * 新增产品
	 */
	public boolean doIns(ProductPOJO pojo) {
		boolean flag=false;
		PreparedStatement pstate = null;
		try {
			this.conn.setAutoCommit(false);
			String sql="insert into product(product_id ,product_name ,product_describe ,product_class, product_inventory, product_price)values(DH2.nextval,?,?,?,?,?)";
			pstate = this.conn.prepareStatement(sql);
			pstate.setString(1, pojo.getProductName());
			pstate.setString(2, pojo.getProductDescribe());
			pstate.setInt(3, pojo.getProductClass());
			pstate.setInt(4, pojo.getProductInventory());
			pstate.setDouble(5, pojo.getProductPrice());
			pstate.execute();
			this.conn.commit();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				this.conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			try {
				pstate.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	/* (non-Javadoc)
	 * @see org.jvsun.dao.ProductDAO#doDel(int)
	 * 删除产品信息
	 */
	public boolean doDel(int productId) {
		boolean flag = false;
		PreparedStatement pstate = null;
		try {
			this.conn.setAutoCommit(false);
			String sql = "update product set is_delete=0 where product_id = ?";
			pstate = this.conn.prepareStatement(sql);
			pstate.setInt(1, productId);
			pstate.execute();
			this.conn.commit();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				this.conn.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} finally{
			try {
				pstate.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return flag;
	}

	/* (non-Javadoc)
	 * @see org.jvsun.dao.ProductDAO#doUpd(org.jvsun.pojo.ProductPOJO)
	 * 更新产品信息
	 */
	public boolean doUpd(ProductPOJO pojo) {
		boolean flag = false;
		PreparedStatement pstate = null;
		try {
			this.conn.setAutoCommit(false);
			String sql = "update product set product_name = ?,product_describe =? ,product_class = ?, product_inventory = ?, product_price = ? where product_id = ?";
			pstate = this.conn.prepareStatement(sql);
			pstate.setString(1, pojo.getProductName());
			pstate.setString(2, pojo.getProductDescribe());
			pstate.setInt(3, pojo.getProductClass());
			pstate.setInt(4, pojo.getProductInventory());
			pstate.setDouble(5, pojo.getProductPrice());
			pstate.setInt(6, pojo.getProductId());
			pstate.execute();
			this.conn.commit();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				this.conn.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} finally{
			try {
				pstate.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return flag;
	}

	public List<ProductPOJO> findAllByNamePriceClass(String name,double price,int clas,int pageSize, int pageCurrent) {
		List<ProductPOJO> list = new ArrayList<ProductPOJO>();
		PreparedStatement pstate = null;
		ResultSet res = null;
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("select product_id ,product_name, product_describe ,product_class, product_inventory ,product_price from (select  product_id ,product_name, product_describe ,product_class, product_inventory ,product_price ,is_delete ,rownum as rn from product where product_name like ? ");
			if(price!=0){
				if(price==1){
					sql.append(" and product_price <= "+3000);
				}
				 if(price==2){
					sql.append(" and product_price > "+3000);
					sql.append(" and product_price <= "+4000);
				}
				 if(price==3){
					sql.append(" and product_price > "+4000);
					sql.append(" and product_price <= "+5000);
				}
				 if(price==4){
					sql.append(" and product_price > "+5000);
				}
			}
			if(clas!=0){
				sql.append(" and product_class = "+clas);
			}
			sql.append(" ) where rn>? and rn<=? and is_delete=1 order by product_id");
			pstate = this.conn.prepareStatement(sql.toString());
			pstate.setString(1, "%"+name+"%");
			pstate.setInt(2, (pageCurrent-1)*pageSize);
			pstate.setInt(3, pageCurrent*pageSize);
			res = pstate.executeQuery();
			while(res.next()){
				ProductPOJO pojo = new ProductPOJO(res.getInt(1),res.getString(2),res.getString(3),res.getInt(4),res.getInt(5),res.getDouble(6));
				list.add(pojo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				res.close();
				pstate.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return list;
	}

	
	/* (non-Javadoc)
	 * @see org.jvsun.dao.ProductDAO#findAllCount()
	 * 查询产品数量
	 */
	public int findAllCount() {
		int count = 0;
		PreparedStatement pstate = null;
		ResultSet res = null;
		String sql = "select count(product_id) from product where is_delete=1" ;
		try {
			pstate = this.conn.prepareStatement(sql);
			res = pstate.executeQuery();
			while(res.next()){
				count = res.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				res.close();
				pstate.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return count;
	}
	public ProductPOJO findById(int pid) {
		ProductPOJO pojo = null;
		PreparedStatement pstate = null;
		ResultSet res = null;
		String sql = "select product_name, product_describe ,product_class, product_inventory ,product_price ,is_delete from product where product_id = ? and is_delete = 1";
		try {
			pstate = this.conn.prepareStatement(sql);
			pstate.setInt(1,pid);
			System.out.println(pid+"&&&&&&&&&&&&&");
			res = pstate.executeQuery();
			while(res.next()){
				pojo=new ProductPOJO(pid,res.getString(1),res.getString(2),res.getInt(3),res.getInt(4),res.getDouble(5),res.getInt(6));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				res.close();
				pstate.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return pojo;
	}
}
