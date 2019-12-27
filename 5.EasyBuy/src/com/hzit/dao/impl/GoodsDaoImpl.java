package com.hzit.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.hzit.dao.GoodsDao;
import com.hzit.entity.News;
import com.hzit.entity.Product;
import com.hzit.entity.User;
import com.hzit.entity.UserAddress;
import com.hzit.utils.JDBCTools;
import com.sun.jndi.cosnaming.IiopUrl.Address;

import freemarker.cache.StrongCacheStorage;

public class GoodsDaoImpl implements GoodsDao{

	@Override
	public List<Product> getGoodsLevel(int start, int end,int type,int id){
		Connection connection = JDBCTools.getConn();
		String sql = "select id,name,price,fileName,categoryLevel1Id,categoryLevel2Id,categoryLevel3Id from easybuy_product where categoryLevel?Id = ? limit ?,?";
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<Product> list = new ArrayList<Product>();
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, type);
			statement.setInt(2, id);
			statement.setInt(3, start);
			statement.setInt(4, end);
			rs = statement.executeQuery();
			while(rs.next()){
				int pid = rs.getInt(1);
				String name = rs.getString(2);
				float price = rs.getFloat(3);
				String fileName = rs.getString(4);
				int categoryLevel1Id = rs.getInt(5);
				int categoryLevel2Id = rs.getInt(6);
				int categoryLevel3Id = rs.getInt(7);
				Product product = new Product();
				product.setId(pid);
				product.setName(name);
				product.setPrice(price);
				product.setFileName(fileName);
				product.setCategoryLevel1Id(categoryLevel1Id);
				product.setCategoryLevel2Id(categoryLevel2Id);
				product.setCategoryLevel3Id(categoryLevel3Id);
				
				list.add(product);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTools.release(connection, statement, rs);
		}
		return list;
	}

	@Override
	public int getCount(int type,int id) {
		Connection connection = JDBCTools.getConn();
		String sql = "select count(*) from easybuy_product where categoryLevel?Id = ?";
		PreparedStatement statement = null;
		ResultSet rs = null;
		int count = 0;
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, type);
			statement.setInt(2, id);
			rs = statement.executeQuery();
			if(rs.next()){
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTools.release(connection, statement, rs);
		}
		return count;
	}

	@Override
	public List<News> getAllNews(int start,int end) {
		Connection connection = JDBCTools.getConn();
		String sql = "Select * from easybuy_news limit ?,?";
		QueryRunner queryRunner = new QueryRunner();
		BeanListHandler< News> beanListHandler = new BeanListHandler<News>(News.class);
		List<News> list = new ArrayList<News>();
		try {
			list = queryRunner.query(connection, sql, beanListHandler,start,end);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTools.release(connection, null, null);
		}
		return list;
	}

	@Override
	public int getNewsCount() {
		Connection connection = JDBCTools.getConn();
		String sql = "Select count(*) from easybuy_news";
		PreparedStatement statement = null;
		ResultSet rs = null;
		int count = 0;
		try {
			statement = connection.prepareStatement(sql);
			rs = statement.executeQuery();
			if(rs.next()){
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTools.release(connection, statement, rs);
		}
		return count;
	}

	@Override
	public Product getProductById(int id) {
		Connection connection = JDBCTools.getConn();
		String sql = "Select * from easybuy_product where id = ?";
		QueryRunner queryRunner = new QueryRunner();
		BeanHandler<Product> beanHandler = new BeanHandler<Product>(Product.class);
		Product product = null;
		try {
			product = queryRunner.query(connection, sql,beanHandler,id);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTools.release(connection, null, null);
		}
		return product;
	}

	@Override
	public List<Product> queryByName(int start,int end,String keyWord) {
		Connection connection = JDBCTools.getConn();
		String sql = "select id,name,price,fileName,categoryLevel1Id,categoryLevel2Id,categoryLevel3Id from easybuy_product where name like '%"+keyWord+"%' limit ?,?";
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<Product> list = new ArrayList<Product>();
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, start);
			statement.setInt(2, end);
//			statement.setInt(3, start);
//			statement.setInt(4, end);
			rs = statement.executeQuery();
			while(rs.next()){
				int pid = rs.getInt(1);
				String name = rs.getString(2);
				float price = rs.getFloat(3);
				String fileName = rs.getString(4);
				int categoryLevel1Id = rs.getInt(5);
				int categoryLevel2Id = rs.getInt(6);
				int categoryLevel3Id = rs.getInt(7);
				Product product = new Product();
				product.setId(pid);
				product.setName(name);
				product.setPrice(price);
				product.setFileName(fileName);
				product.setCategoryLevel1Id(categoryLevel1Id);
				product.setCategoryLevel2Id(categoryLevel2Id);
				product.setCategoryLevel3Id(categoryLevel3Id);
				
				list.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTools.release(connection, statement, rs);
		}
		return list;
	}

	@Override
	public int getProdctCount(String keyWord) {
		Connection connection = JDBCTools.getConn();
		String sql = "Select count(*) from easybuy_product where name like '%"+keyWord+"%'";
		PreparedStatement statement = null;
		ResultSet rs = null;
		int count = 0;
		try {
			statement = connection.prepareStatement(sql);
			rs = statement.executeQuery();
			if(rs.next()){
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTools.release(connection, statement, rs);
		}
		return count;
	}

	@Override
	public List<UserAddress> getAddressById(int entity) {
		Connection connection = JDBCTools.getConn();
		String sql = "select id,address,createtime,isdefault,remark from easybuy_user_address where userid = ?";
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<UserAddress> list = new ArrayList<UserAddress>();
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, entity);
			rs = statement.executeQuery();
			while (rs.next()) {
				UserAddress address = new UserAddress();
				int id = rs.getInt(1);
				String address2 = rs.getString(2);
				String createTime = rs.getString(3);
				int defaul = rs.getInt(4);
				String remark = rs.getString(5);
				address.setAddress(address2);
				address.setId(id);
				address.setCreateTime(createTime);
				address.setIsDefault(defaul);
				address.setRemark(remark);
				list.add(address);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTools.release(connection, statement, rs);
		}
		return list;
	}

	@Override
	public void addAdress(String newAddress, String newRemark, String date,
			User user) {
		Connection connection = JDBCTools.getConn();
		String sql = "insert into easybuy_user_address(userid,address,createtime,remark)values(?,?,?,?);";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, user.getId());
			statement.setString(2, newAddress );
			statement.setString(3, date);
			statement.setString(4, newRemark);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTools.release(connection, statement, null);
		}
	}

	@Override
	public void addOrders(int counts, User user, String seriaNumber,
			String data, String userAddress) {
		Connection connection = JDBCTools.getConn();
		String sql = "insert into easybuy_order(userid,loginName,userAddress,createtime,cost,serialNumber)values(?,?,?,?,?,?);";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, user.getId());
			statement.setString(2, user.getLoginName() );
			statement.setString(3, userAddress);
			statement.setString(4, data);
			statement.setInt(5, counts);
			statement.setString(6, seriaNumber);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTools.release(connection, statement, null);
		}
	}

	@Override
	public UserAddress getAddressByAddressId(int addressId) {
		Connection connection = JDBCTools.getConn();
		String sql = "select address from easybuy_user_address where id = ?";
		PreparedStatement statement = null;
		ResultSet rs = null;
		UserAddress address = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, addressId);
			rs = statement.executeQuery();
			if (rs.next()) {
				address = new UserAddress();
				address.setAddress(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTools.release(connection, statement, rs);
		}
		return address;
	}
	
//	public static void main(String[] args) {
////		GoodsDaoImpl daoImpl = new GoodsDaoImpl();
////		System.out.println(daoImpl.getAddressById(22).size());
//		
//		
//	}

}
