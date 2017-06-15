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
 * Servlet implementation class deleteAccountController
 */
@WebServlet("/deleteAccountController")
public class deleteAccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public deleteAccountController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		{
			// String email = request.getParameter("email");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
	
				User user = new User();

			HttpSession session = request.getSession();
			String sacid = session.getAttribute("dbsacid").toString();
			
			if((session.getAttribute("dbsacid").equals(sacid))){
				System.out.println("Valid User");
				
				
				String result = user.accountDelete(email, password, sacid);
				System.out.println("Account Deleted");
				response.sendRedirect("ChangeSuccess.jsp");
			} else {
				System.out.println("Invalid Information");
			}
					
			

//			try {
//				String sql = "DELETE from registration WHERE email= ? AND password = ?"; // insert
//				Connection conn = DatabaseManager.createConnection();
//				PreparedStatement pstmt = conn.prepareStatement(sql);
//				pstmt.setString(1, email);
//				pstmt.setString(2, pwd);
//				pstmt.executeUpdate();
//				response.sendRedirect("registersuccess.html");
//			} catch (SQLException e) {
//				System.out.println(e.getMessage());
//			}

		}

	}

}
