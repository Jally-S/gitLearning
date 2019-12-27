package com.hzit.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hzit.dao.IBookDAO;
import com.hzit.dao.impl.BookDAOImpl;
import com.hzit.entity.Book;
import com.hzit.entity.Borrow;
import com.hzit.entity.Reader;

public class BookServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		if(method.equals("borrow")){
			borrow(request,response);
		}
		if(method.equals("getAll")){
			getAll(request,response);
		}
		if(method.equals("getBorrowByReaderId")){
			String readeridStr = request.getParameter("readerid");
			int readerid = Integer.parseInt(readeridStr);
			getBorrowByReaderId(request,response,readerid);
		}
		if(method.equals("getExamineBorrow")){
			getExamineBorrow(request,response);
		}
		if(method.equals("agreeBorrow")){
			String borrowidStr = request.getParameter("borrowid");
			String adminidStr = request.getParameter("adminid");
			String bookidStr = request.getParameter("bookid");
			int borrowid = Integer.parseInt(borrowidStr);
			int adminid = Integer.parseInt(adminidStr);
			int bookid = Integer.parseInt(bookidStr);
			//state==1 同意
			doBorrow(borrowid,adminid,1,bookid,0);
			response.sendRedirect("BookServlet?method=getExamineBorrow");
		}
		if(method.equals("refuseBorrow")){
			String borrowidStr = request.getParameter("borrowid");
			String adminidStr = request.getParameter("adminid");
			String bookidStr = request.getParameter("bookid");
			int borrowid = Integer.parseInt(borrowidStr);
			int adminid = Integer.parseInt(adminidStr);
			int bookid = Integer.parseInt(bookidStr);
			//state==2 拒绝
			doBorrow(borrowid,adminid,2,bookid,1);
			response.sendRedirect("BookServlet?method=getExamineBorrow");
		}
		if(method.equals("getAllBorrowToReturn")){
			String stateStr = request.getParameter("state");
			int state = Integer.parseInt(stateStr);
			getAllBorrowToReturn(request,response,1);
		}
		if(method.equals("returnBook")){
			returnBook(request,response);
		}
	}
	
	public void returnBook(HttpServletRequest request, HttpServletResponse response){
		String bookidStr = request.getParameter("bookid");
		String readeridStr = request.getParameter("readerid");
		String adminidStr = request.getParameter("adminid");
		String borrowidStr = request.getParameter("borrowid");
		int bookid = Integer.parseInt(bookidStr);
		int readerid = Integer.parseInt(readeridStr);
		int adminid = Integer.parseInt(adminidStr);
		int borrowid = Integer.parseInt(borrowidStr);
		IBookDAO ibd = new BookDAOImpl();
		ibd.returnBook(bookid, adminid, readerid);
		//设置图书状态为可借(0不可借,1可借)
		ibd.setAbled(bookid, 1);
		//设置借书表中状态为已还书(0未审核,1同意,2拒绝,3已还书)
		ibd.setBorrowState(borrowid, 3);
		try {
			response.sendRedirect("BookServlet?method=getAllBorrowToReturn&state=1");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getAllBorrowToReturn(HttpServletRequest request, HttpServletResponse response,int state){
		IBookDAO ibd = new BookDAOImpl();
		List<Borrow> list = ibd.getAllBorrowByState(state);
		request.setAttribute("list", list);
		try {
			request.getRequestDispatcher("../return.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void doBorrow(int borrowid,int adminid,int state,int bookid,int abled){
		IBookDAO iba = new BookDAOImpl();
		int i = iba.doBorrow(borrowid, adminid,state);
		int j = iba.setAbled(bookid, abled);
	}
	
	public void getExamineBorrow(HttpServletRequest request, HttpServletResponse response){
		IBookDAO iba = new BookDAOImpl();
		List<Borrow> list = iba.getExamineBorrow();
		request.setAttribute("list", list);
		try {
			request.getRequestDispatcher("../examine.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void borrow(HttpServletRequest request, HttpServletResponse response){
		String bookid = request.getParameter("bookid");
		String readerid = request.getParameter("readerid");
		Date date=new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
		String today = format.format(date);
		Calendar calendar = Calendar.getInstance();  
	    calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 14);
	    Date returnTime = calendar.getTime();
	    String returnTimeStr = format.format(returnTime);
	    
	    Borrow borrow = new Borrow();
	    Book book = new Book();
	    book.setId(Integer.parseInt(bookid));
	    borrow.setBook(book);
	    Reader reader = new Reader();
	    reader.setId(Integer.parseInt(readerid));
	    borrow.setReader(reader);
	    borrow.setBorrowTime(today);
	    borrow.setReturnTime(returnTimeStr);
	    borrow.setState(0);
	    
	    IBookDAO iba = new BookDAOImpl();
	    iba.addBorrow(borrow);
	    
	    iba.setAbled(Integer.parseInt(bookid), 0);
	    
	    try {
			response.sendRedirect("BookServlet?method=getBorrowByReaderId&readerid="+readerid);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}

	public void getBorrowByReaderId(HttpServletRequest request, HttpServletResponse response,int readerid)
	{
		IBookDAO iba = new BookDAOImpl();
		List<Borrow> list = iba.getAllBorrow(readerid);
	    request.setAttribute("list", list);
	    try {
			request.getRequestDispatcher("../myBorrow.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getAll(HttpServletRequest request, HttpServletResponse response){
		String currentPageStr = request.getParameter("currentPage");
		String dataPrePageStr = request.getParameter("dataPrePage");
		int currentPage = 0;
		int dataPrePage = 0;
		if(currentPageStr!=null && dataPrePageStr!=null){
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
			dataPrePage = Integer.parseInt(request.getParameter("dataPrePage"));
		}else{
			currentPage = 1;
			dataPrePage = 5;
		}
		
		//通过前台传来的两个参数,确定start和end
		int start = (currentPage-1)*dataPrePage;
		int end = dataPrePage;
		
		IBookDAO ibd = new BookDAOImpl();
		int count = ibd.getDataCounts();
		
		//计算总共有多少页
		int pages = count/dataPrePage;
		if(count%dataPrePage!=0){
			pages++;
		}
		
		List<Book> list = ibd.getAll(start,end);
		request.setAttribute("list", list);
		request.setAttribute("pages",pages);
		request.setAttribute("currentPage",currentPage);
		try {
			request.getRequestDispatcher("../index.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

}
