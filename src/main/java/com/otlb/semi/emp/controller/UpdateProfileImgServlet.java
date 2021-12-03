package com.otlb.semi.emp.controller;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;
import com.otlb.semi.emp.model.vo.Emp;

/**
 * Servlet implementation class UpdateProfileImgServlet
 */
@WebServlet("/emp/updateProfileImg")
@MultipartConfig(fileSizeThreshold=1024*1024*10, 	// 10 MB 
					maxFileSize=1024*1024*50,      	// 50 MB
					maxRequestSize=1024*1024*100)   // 100 MB
public class UpdateProfileImgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIR = "img";

	/**
	 * 프사변경페이지
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/emp/updateProfileImg.jsp")
		.forward(request, response);
	}

	/**
	 * 프사변경
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("utf-8");
		System.out.println("++++++++++++++++++++++++++++");
		
//		HttpSession session = request.getSession();
//		Emp loginEmp = (Emp) session.getAttribute("loginEmp");

		// 파일이 저장되는 경로
	    String path = getServletContext().getRealPath("/img");
	    System.out.println("경로:" + path);
	    int size = 1024 * 1024 * 10; // 저장가능한 파일 크기
	    String file = ""; // 업로드 한 파일의 이름(이름이 변경될수 있다)
	    String originalFile = ""; // 이름이 변경되기 전 실제 파일 이름

	    // 실제로 파일 업로드하는 과정
	    try{
	        MultipartRequest multi = new MultipartRequest(request, path, size, "UTF-8", new DefaultFileRenamePolicy());

	        Enumeration files = multi.getFileNames();
	        String str = (String)files.nextElement(); // 파일 이름을 받아와 string으로 저장

	        file = multi.getFilesystemName(str); // 업로드 된 파일 이름 가져옴
	        originalFile = multi.getOriginalFileName(str); // 원래의 파일이름 가져옴

	        String location = request.getContextPath() + "/emp/updateProfileImg";
			response.sendRedirect(location);
			
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}