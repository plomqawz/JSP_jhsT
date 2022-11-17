package com.file;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class FileController2
 */
@WebServlet("/file2/upload2")
public class FileController2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileController2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   request.setCharacterEncoding("utf-8");
	   String savePath = "upload";//저장될 파일의 위치(폴더)
	   int uploadFileSizeLimit = 5*1024*1024; //최대 5MB로 제한
	   String encType = "utf-8";
	   ServletContext context = getServletContext();
		String uploadFilePath = context.getRealPath(savePath); //실제경로
		System.out.println("서버상의 실제 디렉토리 : " + uploadFilePath);
		
	   MultipartRequest multi = new MultipartRequest(
			   request,   //request객체
			   uploadFilePath,  //서버상의 실제 디렉토리
			   uploadFileSizeLimit,  // 최대 업로드 파일크기
			   encType,  //인코딩
			   new DefaultFileRenamePolicy() ); //동일 이름 존재할 때 새이름 부여
	   //업로드한 파일 이름	   
	   String fileName  = multi.getFilesystemName("uploadFile");
	   if(fileName == null) fileName="";
	   String name = multi.getParameter("name");
	   String title = multi.getParameter("title");
	   String image = fileName;
	   FileDTO file = new FileDTO(name, title, image);
	   FileDAO dao = FileDAO.getInstance();
	   dao.fileInsert(file);
	   response.sendRedirect("imageList");
	   
	}

}





