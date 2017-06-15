package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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
 * Servlet implementation class RegisterController
 */
@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
        super();
    
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
		
		
		String firstname=request.getParameter("fname");
		String lastname=request.getParameter("lname");
		String email=request.getParameter("email");
		String sacid=request.getParameter("sacid");
		String password=request.getParameter("password");
		String mobile=request.getParameter("mobile");
		User user=new User();
		HttpSession session= request.getSession();
		String result=user.register(firstname, lastname, email, sacid, password, mobile);
		
		if(result.equals("success")){
			//System.out.println("enter");
			response.sendRedirect("ProfilePage.jsp");
			request.setAttribute("user", user);
			session.setAttribute("sacid", user.getSacid());
			session.setAttribute("dbfname", user.getFname());
		}
		
		else{
			response.sendRedirect("/loginerror.html");
		}
		
	}

}
