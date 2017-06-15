package Logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import DB.DatabaseManager;

public class NewPost {
	
	private String sacid;
	private String category;
	private String title;
	private String description;
	
	public NewPost() {
		
	}
	public NewPost(String sacid, String category, String title, String description)
	{
		this.sacid = sacid;
		this.category = category;
		this.title = title;
		this.description = description;
		
	}
	
	private String getSacid(){
		return this.sacid;
	}
	private String getCategory(){
		return category;
	}
	private String getTitle(){
		return title;
	}
	private String getDescription(){
		return description;
	}
	
	public void newpost(){
		String dbsacid, dbPassword, dbFname;
		try{
			String query = "INSERT INTO shoprequest (sacid,category,title,description) VALUES (?,?,?,?)";
			
			Connection conn = DatabaseManager.createConnection();
			System.out.println(getCategory());
        	PreparedStatement stmt = conn.prepareStatement(query);
        	
        	System.out.println(getCategory());
        	System.out.println(getSacid());
        	System.out.println(getDescription());
        	
        	stmt.setString(1, getSacid());
        	stmt.setString(2, getCategory());
        	stmt.setString(3, getTitle());
        	stmt.setString(4, getDescription());
        	stmt.executeUpdate();
        	
		}
		catch (SQLException e) {
            System.out.println(e.getMessage());
          	
	}
	}		
}
