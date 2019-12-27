package com.hzit.dao;

import java.util.List;

import com.hzit.entity.Book;
import com.hzit.entity.BookCase;
import com.hzit.entity.BookType;
import com.hzit.entity.Borrow;

public interface IBookDAO {
	public List<Book> getAll(int start,int end);
	public BookCase getBookCaseByBookCaseId(int bookcaseid);
	public BookType getBookTypeByBookTypeId(int booktypeid);
	public int getDataCounts();
	public Book getBookById(int id);
	public void addBorrow(Borrow borrow);
	public List<Borrow> getAllBorrow(int readerid);
	public List<Borrow> getExamineBorrow();
	public int doBorrow(int borrowid,int adminid,int state);
	public int setAbled(int bookid,int abled);
	public List<Borrow> getAllBorrowByState(int state);
	public int returnBook(int bookid,int adminid,int readerid);
	public int setBorrowState(int borrowid,int state);
}
