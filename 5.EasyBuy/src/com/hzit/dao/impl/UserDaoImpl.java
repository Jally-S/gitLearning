package com.hzit.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.hzit.dao.UserDao;
import com.hzit.entity.News;
import com.hzit.entity.Product;
import com.hzit.entity.ProductCategory;
import com.hzit.entity.User;
import com.hzit.utils.JDBCTools;

public class UserDaoImpl implements UserDao {
	
	@Override
	public User queryUser(String name,String password) {
		Connection connection = JDBCTools.getConn();
		String sql = "select * from easybuy_user where loginName =? and password = ? ";
		User user = null;
		//用dbutils来封装数据
		QueryRunner queryRunner = new QueryRunner();
		BeanHandler<User> rsh = new BeanHandler<User>(User.class);
		try {
			user = queryRunner.query(connection, sql, rsh,name,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTools.release(connection, null, null);
		}
		return user;
	}
	public static void main(String[] args) {
		UserDaoImpl daoImpl =new UserDaoImpl();
		System.out.println(daoImpl.queryUser("admin", "123123"));
		
	}

	@Override
	public List<ProductCategory> queryFirstAll(int id) {
		Connection connection = JDBCTools.getConn();
		String sql = "select * from easybuy_product_category where type = ?";
		QueryRunner queryRunner = new QueryRunner();
		BeanListHandler<ProductCategory> beanHandler = new BeanListHandler<ProductCategory>(ProductCategory.class);
		List<ProductCategory> list = new ArrayList<ProductCategory>();
		try {
			list = queryRunner.query(connection, sql, beanHandler,id);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTools.release(connection, null, null);
		}
		return list;
	}
	
	//查询所有商品
	@Override
	public List<Product> queryAll() {
		Connection connection = JDBCTools.getConn();
		String sql = "Select id,name,price,fileName,categoryLevel1Id,categoryLevel2Id,categoryLevel3Id from easybuy_product";
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<Product> list =  new ArrayList<Product>();
		try {
			statement = connection.prepareStatement(sql);
			rs = statement.executeQuery();
			while(rs.next()){
				int id = rs.getInt(1);
				String name = rs.getString(2);
				float price = rs.getFloat(3);
				String fileName = rs.getString(4);
				int categoryLevel1Id = rs.getInt(5);
				int categoryLevel2Id = rs.getInt(6);
				int categoryLevel3Id = rs.getInt(7);
				Product product = new Product();
				product.setId(id);
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
		}
		return list;
	}
	@Override
	public void add(User user) {
		Connection connection = JDBCTools.getConn();
		String sql = "insert into easybuy_user (loginName,userName,password,sex,identityCode,email,mobile,type) values(?,?,?,?,?,?,?,?)";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, user.getLoginName());
			statement.setString(2, user.getUserName());
			statement.setString(3, user.getPassword());
			statement.setInt(4, user.getSex());
			statement.setString(5, user.getIdentityCode());
			statement.setString(6, user.getEmail());
			statement.setString(7, user.getMobile());
			statement.setInt(8, user.getType());
			
			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTools.release(connection, null, null);
		}
	}
	@Override
	public List<News> queryAllNews() {
		Connection connection = JDBCTools.getConn();
		String sql = "SELECT * from easybuy_news";
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<News> list = new ArrayList<News>();
		try {
			statement = connection.prepareStatement(sql);
			rs = statement.executeQuery();
			while(rs.next()){
				int id = rs.getInt(1);
				String title = rs.getString(2);
				String content = rs.getString(3);
				String createTime = rs.getString(4);
				News news = new News(id, title, content, createTime);
				
				list.add(news);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTools.release(connection, statement, rs);
		}
		return list;
	}
}
