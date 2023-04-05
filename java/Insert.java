

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Servlet implementation class Insert
 */
@WebServlet("/Insert")
public class Insert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Insert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FileInputStream fileInput=new FileInputStream("C:\\Users\\admin\\eclipse-workspace\\DSAProject\\TreeFile");
		ObjectInputStream objectInput=new ObjectInputStream(fileInput);
		String c=request.getParameter("count");
		String text=request.getParameter("nodes");
		String nodes[]=text.split(" ");
		int count=Integer.parseInt(c);
		AVL Tree=null;
		try {
			Tree=(AVL)objectInput.readObject();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		objectInput.close();
		for(int i=0;i<count;i++)
				Tree=AVLImplementation.insertNode(Tree,Integer.parseInt(nodes[i]));
		FileOutputStream fileOutput=new FileOutputStream("C:\\\\Users\\\\admin\\\\eclipse-workspace\\\\DSAProject\\\\TreeFile");
		ObjectOutputStream objectOutput=new ObjectOutputStream(fileOutput);
		objectOutput.writeObject(Tree);
		objectOutput.close();
		response.sendRedirect("inserted.html");
	}
}
//
//package kce;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
///**
// * Servlet implementation class LoginServlet
// */
//@WebServlet("/Login")
//public class LoginServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public LoginServlet() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
//	{
//		String mail=request.getParameter("mail");
//		String password=request.getParameter("pass");
//     	boolean valid=false;
//		try {
//			valid = FileInterface.validateUser(mail, password);
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//
//			System.out.println("File Not Found...");
//		}
////    	response.setContentType("text/html");
////    	PrintWriter pw=response.getWriter();
////    	pw.write("<h1>Name : "+valid+"</h1>");
////    	pw.write("<h1>Dept : "+password+"</h1>");
//		if(valid)
//			response.sendRedirect("index.html");
//		else
//			response.sendRedirect("LoginFail.html");
//	}
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto
//	}
//}