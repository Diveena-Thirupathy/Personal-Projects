package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Spliterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DB.DatabaseManager;
import Logic.NewPost;
import Logic.User;

/**
 * Servlet implementation class RegisterController
 */
@WebServlet("/postController")
public class postController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public postController() {
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
		
		HttpSession session= request.getSession(true);	
		String [] url = request.getQueryString().replaceAll("%20"," ").toString().split("&");
		String category = url[0].split("=")[1];
		String title = url[1].split("=")[1];
		String description = url[2].split("=")[1];
		
		
		
		// System.out.println(session.getAttributeNames().nextElement().toString());
		String sacid = session.getAttribute("dbsacid").toString();
		
		NewPost newpost = new NewPost(sacid, category,title,description);
		newpost.newpost();
		
	}

}
