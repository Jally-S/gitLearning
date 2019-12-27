package org.jvsun.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

import org.jvsun.dao.factory.PhotoDAOFactory;
import org.jvsun.pojo.PhotoPOJO;
import org.jvsun.tools.PhotoName;
import org.lxh.smart.SmartUpload;
import org.lxh.smart.SmartUploadException;

public class UpServlet extends HttpServlet {

	private static final long serialVersionUID = 7073977826792674963L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ip = request.getRemoteAddr();	
		ip = ip.replaceAll(":","");
		PhotoName pn=new PhotoName(ip);
		String pName = pn.getPhotoName();//照片名字，是由IP加当前时间组成
		SmartUpload smartupload = new SmartUpload();//实例化上传操作的对象
		
		  //PageContext是jsp的内置对象，在servlet不能直接使用，需要做一些处理
		JspFactory _jspxFactory = null;
		PageContext pageContext = null;
		_jspxFactory = JspFactory.getDefaultFactory();
		pageContext = _jspxFactory.getPageContext(this,request,response,"",true,8192,true);
		//初始化上传文件
		smartupload.initialize(pageContext);
		
		//准备上传
		try {
			smartupload.upload();
		} catch (SmartUploadException e1) {
			e1.printStackTrace();
		}
		System.out.println(smartupload.getRequest().getParameter("pid")+"id是");
		int pid=Integer.parseInt(smartupload.getRequest().getParameter("pid"));
		//取得文件的后缀
		String endName = smartupload.getFiles().getFile(0).getFileExt();
		//文件保存的路径
		String p_url = pName+"."+endName;
		String local_url="D:/photos/"+pName+"."+endName;
		//String p_url="K:/workspace/Xiangce/WebRoot/file/"+pName+"."+endName;
		//保存文件
		try {
			smartupload.getFiles().getFile(0).saveAs(local_url);
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}
		PhotoPOJO pojo=new PhotoPOJO(p_url,pid);
		boolean flag = PhotoDAOFactory.getDAOInstance().doIns(pojo);
		if(flag){
			response.sendRedirect("manager/success.jsp");
		}else{
			response.sendRedirect("manager/error.jsp");
		}
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
