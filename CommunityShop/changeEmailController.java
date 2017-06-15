package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
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
@WebServlet("/changeEmailController")
public class changeEmailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public changeEmailController() {
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

		String new_email = request.getParameter("new_email");
		
		User user = new User();
		
		HttpSession session = request.getSession();
		String sacid = session.getAttribute("dbsacid").toString();
		
		if ((session.getAttribute("dbsacid").equals(sacid))) {
			System.out.println("Valid User");
			
			String result = user.emailChange(new_email, sacid);
			System.out.println("Change Sucess");
			response.sendRedirect("ChangeSuccess.jsp");
		}
		else {
			System.out.println("Invalid User");
           	response.sendRedirect("loginerror.html");
		}
		}
		
//			try {
//				String sql = "UPDATE registration SET email = ?  WHERE sacid = ?";																									// insert
//				Connection conn = DatabaseManager.createConnection();
//				PreparedStatement pstmt = conn.prepareStatement(sql);
//				pstmt.setString(1, new_email);
//				pstmt.setString(2, sacid);	
//				pstmt.executeUpdate();
//				response.sendRedirect("registersuccess.html");
//			} catch (SQLException e) {
//				System.out.println(e.getMessage());
//			}
	
	}


