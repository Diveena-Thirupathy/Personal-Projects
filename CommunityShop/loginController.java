package servlets;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DB.DatabaseManager;
import Logic.User;

/**
 * Servlet implementation class loginController
 */
@WebServlet("/loginController")
public class loginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String userid = "";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public loginController() {
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sacid = request.getParameter("sacid");
		String password = request.getParameter("password");

		HttpSession session = request.getSession();
		System.out.println(sacid + " " + password);
		userid = sacid;
		User user = new User(sacid, password);

		String result = user.authenticate(sacid, password);

		if (result.equals("success")) {
			// System.out.println("enter");
			response.sendRedirect("ProfilePage.jsp");
			request.setAttribute("user", user);
			session.setAttribute("dbsacid", user.getSacid());
			session.setAttribute("dbPassword", password);
			session.setAttribute("dbfname", user.getFname());
			System.out.println(user.getFname());
			System.out.println(session.getAttribute("dbsacid"));
		}

		else {
			response.sendRedirect("/loginerror.html");
		}

		// try { //do not remove
		//
		// // String sql = "SELECT password FROM registration where
		// sacid="+sacid+"";
		//
		// Connection conn = DatabaseManager.createConnection();
		// Statement stmt = conn.createStatement();
		// String query= "SELECT sacid, password,fname FROM registration where
		// sacid='" + sacid + "'";
		// //
		// ResultSet rs=stmt.executeQuery(query);
		// //ResultSet rs = stmt.getResultSet();
		// //System.out.println(rs.getString("sacid"));
		// System.out.println();
		// while (rs.next()) {
		//
		// System.out.println(rs.getString("sacid") + "\t" +
		// rs.getString("password")+"\t"+rs.getString("fname"));
		// dbsacid=rs.getString("sacid") ;
		// dbPassword=rs.getString("password");
		// dbFname=rs.getString("fname");
		//
		// session.setAttribute("dbFname", dbFname);
		// session.setAttribute("dbsacid", sacid);
		// session.setAttribute("dbpassword", password);
		//
		// if(dbsacid.equals(sacid) && dbPassword.equals(password)){
		// System.out.println("Login Success");
		// response.sendRedirect("dashboard.jsp");
		//
		// }
		// else
		// {
		// System.out.println("NOT OK");
		// response.sendRedirect("loginerror.html");
		// }
		//
		// }
		//// System.out.println("here?");
		//// dbUsername = rs.getString("sacid");
		//// dbPassword = rs.getString("password");
		//// System.out.println(dbUsername+" "+dbPassword);
		//// if(dbUsername.equals(sacid) && dbPassword.equals(password)){
		//// System.out.println("OK");
		//// System.out.println("NOT OK");
		//
		//
		//
		//
		//
		// }catch (SQLException e) {
		// System.out.println(e.getMessage());
		// }
	}
}