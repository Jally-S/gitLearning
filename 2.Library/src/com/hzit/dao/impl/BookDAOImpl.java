package com.hzit.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hzit.dao.IBookDAO;
import com.hzit.dao.IReaderDAO;
import com.hzit.entity.Book;
import com.hzit.entity.BookCase;
import com.hzit.entity.BookType;
import com.hzit.entity.Borrow;
import com.hzit.entity.Reader;
import com.hzit.util.DatabaseConnection;

public class BookDAOImpl implements IBookDAO {

	@Override
	public List<Book> getAll(int start,int end) {
		// TODO Auto-generated method stub
		try {
			DatabaseConnection db = new DatabaseConnection();
			Connection conn = db.getConn();
			
//			String sql = "select * from book where id between "+start+" and "+end;
			String sql = "select * from book where abled = 1 limit "+start+","+end;
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			Book book = null;
			BookCase bookCase = null;
			BookType bookType = null;
			List<Book> list = new ArrayList<Book>();
			
			while(rs.next()){
				
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String author = rs.getString(3);
				String publish = rs.getString(4);
				int pages = rs.getInt(5);
				float price = rs.getFloat(6);
				int bookcaseid = rs.getInt(7);
				int booktypeid = rs.getInt(8);
				
				bookCase = getBookCaseByBookCaseId(bookcaseid);
				bookType = getBookTypeByBookTypeId(booktypeid);
				
				book = new Book();
				book.setId(id);
				book.setName(name);
				book.setAuthor(author);
				book.setPublish(publish);
				book.setPages(pages);
				book.setPrice(price);
				book.setBookCase(bookCase);
				book.setBookType(bookType);
				
				list.add(book);
			}
			rs.close();
			pstmt.close();
			db.close();
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public BookCase getBookCaseByBookCaseId(int bookcaseid) {
		// TODO Auto-generated method stub
		try {
			DatabaseConnection db = new DatabaseConnection();
			Connection conn = db.getConn();
			
			String sql = "select * from bookcase where id="+bookcaseid;
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				int id = rs.getInt(1);
				String name = rs.getString(2);
				BookCase bookcase = new BookCase();
				bookcase.setId(id);
				bookcase.setName(name);
				rs.close();
				pstmt.close();
				db.close();
				return bookcase;
			}
			rs.close();
			pstmt.close();
			db.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public BookType getBookTypeByBookTypeId(int booktypeid) {
		// TODO Auto-generated method stub
		try {
			DatabaseConnection db = new DatabaseConnection();
			Connection conn = db.getConn();
					
			String sql = "select * from booktype where id="+booktypeid;
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				int id = rs.getInt(1);
				String name = rs.getString(2);
				int days = rs.getInt(3);
				BookType bookType = new BookType();
				bookType.setId(id);
				bookType.setName(name);
				bookType.setDays(days);
				rs.close();
				pstmt.close();
				db.close();
				return bookType;
			}
			rs.close();
			pstmt.close();
			db.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return null;
	}

	@Override
	public int getDataCounts() {
		// TODO Auto-generated method stub
		try {
			DatabaseConnection db = new DatabaseConnection();
			Connection conn = db.getConn();
							
			String sql = "select count(*) from book where abled = 1";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				int count = rs.getInt(1);
				return count;
			}
			rs.close();
			pstmt.close();
			db.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Book getBookById(int id) {
		// TODO Auto-generated method stub
		try {
			DatabaseConnection db = new DatabaseConnection();
			Connection conn = db.getConn();
					
			String sql = "select * from book where id="+id;
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				int bookid = rs.getInt(1);
				String name = rs.getString(2);
				String author = rs.getString(3);
				String publish = rs.getString(4);
				int pages = rs.getInt(5);
				float price = rs.getFloat(6);
				int bookcaseid = rs.getInt(7);
				int booktypeid = rs.getInt(8);
				
				BookCase bookCase = getBookCaseByBookCaseId(bookcaseid);
				BookType bookType = getBookTypeByBookTypeId(booktypeid);
				
				Book book = new Book();
				book.setId(bookid);
				book.setName(name);
				book.setAuthor(author);
				book.setPublish(publish);
				book.setPages(pages);
				book.setPrice(price);
				book.setBookCase(bookCase);
				book.setBookType(bookType);
				return book;
			}
			rs.close();
			pstmt.close();
			db.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void addBorrow(Borrow borrow) {
		// TODO Auto-generated method stub
		try {
			DatabaseConnection db = new DatabaseConnection();
			Connection conn = db.getConn();
			
			String sql = "insert into borrow(bookid,readerid,borrowtime,returntime,state) values("+borrow.getBook().getId()
			+","+borrow.getReader().getId()+",'"+borrow.getBorrowTime()+"','"+borrow.getReturnTime()+"',"+borrow.getState()+")";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			pstmt.close();
			db.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Borrow> getAllBorrow(int readerid) {
		// TODO Auto-generated method stub
		try {
			DatabaseConnection db = new DatabaseConnection();
			Connection conn = db.getConn();
			String sql = "select * from borrow where readerid = "+readerid;
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			Borrow borrow = null;
			List<Borrow> list = new ArrayList<Borrow>();
			while(rs.next()){
				int id = rs.getInt(1);
				int bookid = rs.getInt(2);
				Book book = getBookById(bookid);
				String borrowTime = rs.getString(4);
				String returnTime = rs.getString(5);
				int state = rs.getInt(7);
 				borrow = new Borrow();
				borrow.setId(id);
				borrow.setBook(book);
				borrow.setBorrowTime(borrowTime);
				borrow.setReturnTime(returnTime);
				borrow.setState(state);
				list.add(borrow);
				}
			rs.close();
			pstmt.close();
			db.close();
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Borrow> getExamineBorrow() {
		try {
			DatabaseConnection db = new DatabaseConnection();
			Connection conn = db.getConn();
			
			String sql = "select * from borrow where state=0";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			Borrow borrow = null;
			Book book = null;
			Reader reader = null;
			List<Borrow> list = new ArrayList<Borrow>();
			IReaderDAO ird = new ReaderDAOImpl();
			
			while(rs.next()){
				int id = rs.getInt(1);
				int bookid = rs.getInt(2);
				int readerid = rs.getInt(3);
				String borrowTime = rs.getString(4);
				String returnTime = rs.getString(5);
				int state = rs.getInt(6);
				
				book = getBookById(bookid);
				reader = ird.getReaderById(readerid);
				borrow = new Borrow();
				
				borrow.setId(id);
				borrow.setBook(book);
				borrow.setReader(reader);
				borrow.setBorrowTime(borrowTime);
				borrow.setReturnTime(returnTime);
				borrow.setState(state);
				
				list.add(borrow);
				
			}
			rs.close();
			pstmt.close();
			db.close();
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public int doBorrow(int borrowid,int adminid,int state) {
		// TODO Auto-generated method stub
		try {
			DatabaseConnection db = new DatabaseConnection();
			Connection conn = db.getConn();
					
			String sql = "update borrow set state = "+state+",adminid = "+adminid+" where id = "+borrowid;

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			pstmt.close();
			db.close();
			return 1;	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int setAbled(int bookid, int abled) {
		// TODO Auto-generated method stub
		try {
			DatabaseConnection db = new DatabaseConnection();
			Connection conn = db.getConn();
			String sql = "update book set abled="+abled+" where id = "+bookid;
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			pstmt.close();
			db.close();
			return 1;	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Borrow> getAllBorrowByState(int state) {
		// TODO Auto-generated method stub
		try {
			DatabaseConnection db = new DatabaseConnection();
			Connection conn = db.getConn();
			String sql = "select * from borrow where state = "+state;
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			Borrow borrow = null;
			Book book = null;
			Reader reader = null;
			List<Borrow> list = new ArrayList<Borrow>();
			IReaderDAO ird = new ReaderDAOImpl();
			
			while(rs.next()){
				int id = rs.getInt(1);
				int bookid = rs.getInt(2);
				int readerid = rs.getInt(3);
				book = getBookById(bookid);
				reader = ird.getReaderById(readerid);
				String borrowTime = rs.getString(4);
				String returnTime = rs.getString(5);
				borrow = new Borrow();
				borrow.setId(id);
				borrow.setBook(book);
				borrow.setBorrowTime(borrowTime);
				borrow.setReturnTime(returnTime);
				borrow.setReader(reader);
				list.add(borrow);
			}
			rs.close();
			pstmt.close();
			db.close();
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int returnBook(int bookid, int adminid, int readerid) {
		// TODO Auto-generated method stub
		try {
			DatabaseConnection db = new DatabaseConnection();
			Connection conn = db.getConn();
			Date date=new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
			String today = format.format(date);
			String sql = "insert into returnbook(bookid,readerid,returntime,adminid) values("+bookid+","+readerid+",'"+today+"',"+adminid+")";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			pstmt.close();
			db.close();
			return 1;	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int setBorrowState(int borrowid, int state) {
		// TODO Auto-generated method stub
		try {
			DatabaseConnection db = new DatabaseConnection();
			Connection conn = db.getConn();
			String sql = "update borrow set state="+state+" where id = "+borrowid;
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			pstmt.close();
			db.close();
			return 1;	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}
