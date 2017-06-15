package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DB.DatabaseManager;
import Logic.User;

/**
 * Servlet implementation class changepasswordController
 */
@WebServlet("/changepasswordController")
public class changepasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public changepasswordController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String old_password = request.getParameter("old_password");
		String new_password = request.getParameter("new_password");
		
		User user = new User();
		
		HttpSession session = request.getSession();
		String sacid = session.getAttribute("dbsacid").toString();
		
//		System.out.println(sacid);
//		System.out.println(session.getAttribute("dbPassword")+"I am not null");
//		System.out.println(old_password + "Hi! ");
		//System.out.println("Parameter: "+((ServletRequest) session).getParameter("dbsacid")+"/t"+"Attribute:"+session.getAttribute("dbsacid"));
		
		if ((session.getAttribute("dbPassword").equals(old_password))) {
			System.out.println("Correct old password");
			
			String result = user.passwordchange(old_password, new_password, sacid);
			System.out.println("Change Sucess");
			response.sendRedirect("ChangeSuccess.jsp");
//			try {
//				String sql = "UPDATE registration SET password = ?  WHERE sacid = ? AND password = ?";																									// insert
//				Connection conn = DatabaseManager.createConnection();
//				PreparedStatement pstmt = conn.prepareStatement(sql);
//				pstmt.setString(1, new_password);
//				pstmt.setString(2, sacid);	
//				pstmt.setString(3, old_password);
//				pstmt.executeUpdate();
//				response.sendRedirect("registersuccess.html");
//			} catch (SQLException e) {
//				System.out.println(e.getMessage());
//			}
		}
		else
		{
			System.out.println("Invalid Password");
           	response.sendRedirect("loginerror.html");
		}
	}

}
