package Logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DB.DatabaseManager;

public class User {
	private String fname;
	private String lname;
	private String email;
	private String sacid;
	private String password;
	private String mobile;
	
	public User(){
		
	}
	public User(String sacid, String password)
	{
		this.sacid=sacid;
		this.password=password;
	}
	public String getSacid() {
		return sacid;
	}
	public void setSacid(String sacid) {
		this.sacid = sacid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String authenticate(String sacid, String password)
	{
		String  dbsacid,dbPassword,dbFname;
		try {
	        	Connection conn = DatabaseManager.createConnection();
	        	Statement stmt = conn.createStatement();
	        	String query= "SELECT sacid, password,fname FROM registration where sacid='" + sacid + "'";
	        	// 
	        	ResultSet rs=stmt.executeQuery(query);
	        	
	        	System.out.println();
	        	while (rs.next()) {
	        		
	                System.out.println(rs.getString("sacid") +  "\t" + 
	                                   rs.getString("password")+"\t"+rs.getString("fname"));
	                dbsacid=rs.getString("sacid") ;
	                dbPassword=rs.getString("password");
	                dbFname=rs.getString("fname");
	                
	        		
	                if(dbsacid.equals(sacid) && dbPassword.equals(password)){
	                	setFname(dbFname);
	                	System.out.println("Login Success");
	                	return "success";	                	
	                }
	                else
	                {
	                	System.out.println("Login Failed");
	                	return "failure";
	                }
	                                      
	            }  		
	       
		}catch (SQLException e) {
	            System.out.println(e.getMessage());
     }
		return null;
		
	}
	
	public String register(String fname,String lname,String email, String sacid, String password, String mobile){
		try {
			
			String sql = "INSERT INTO registration(fname,lname,email,sacid,password,mobile) VALUES(?,?,?,?,?,?)"; // more insert 
			
	        	Connection conn = DatabaseManager.createConnection();
	            PreparedStatement pstmt = conn.prepareStatement(sql);
	           
	            pstmt.setString(1, fname);
	            pstmt.setString(2, lname);
	            pstmt.setString(3, email);
	            pstmt.setString(4, sacid);
	            pstmt.setString(5, password);
	            pstmt.setString(6, mobile);
	            pstmt.executeUpdate();
	            setFname(fname);
	            setLname(lname);
	            setSacid(sacid);
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	            return "failure";
	        }
		return "success";
		
		
		}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	 public String passwordchange (String old_password, String new_password, String sacid){
		 try {
				String sql = "UPDATE registration SET password = ?  WHERE sacid = ? AND password = ?";																									// insert
				
				Connection conn = DatabaseManager.createConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, new_password);
				pstmt.setString(2, sacid);	
				pstmt.setString(3, old_password);
				pstmt.executeUpdate();
				setPassword(new_password);
			
				
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				return "failure";
			}
		 return "Password Change success";
	}
	
	 
	public String emailChange (String new_email, String sacid){
		try {
			String sql = "UPDATE registration SET email = ?  WHERE sacid = ?";																									// insert
			Connection conn = DatabaseManager.createConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, new_email);
			pstmt.setString(2, sacid);	
			pstmt.executeUpdate();
			System.out.println("registersuccess.html");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return "Change success";
	}
	
	public String accountDelete(String email, String password, String sacid){
		try{
			String sql = "DELETE FROM registration WHERE email = ? AND password = ?";
			Connection conn = DatabaseManager.createConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2,password);
			pstmt.executeUpdate();
			
			System.out.println(email);
			System.out.println(password);
			System.out.println("registersuccess.html");
		}catch (SQLException e){
			System.out.println(e.getMessage());
					
		} return " Success ";
	}
	
}
	 


