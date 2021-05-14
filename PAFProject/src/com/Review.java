package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Review {
	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.cj.jdbc.Driver");

	 //Provide the correct details: DBServer/DBName, username, password
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/review", "root", "");
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;
}
	
	
	public String readReviews()
	 {
	 String output = "";
		 try
		 {
			 Connection con = connect();
			 if (con == null)
			 {
			 return "Error while connecting to the database for reading.";
			 }
			 
			 // Prepare the html table to be displayed
			 output = "<table border='1'>"
				 		+ "<tr><th>Review ID</th> "
				 		+ "<th>Review Type</th> "
				 		+ "<th>Review Description</th> "
				 		+ "<th>Rating Value</th> "
				 		+ "<th>Update</th>"
				 		+ "<th>Remove</th></tr>";
			 
			 String query = "select * from reviews";
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery(query);
			 // iterate through the rows in the result set
			 while (rs.next())
			 {
				 String reviewID = Integer.toString(rs.getInt("reviewID"));
				 String reviewType = rs.getString("reviewType");
				 String reviewDesc = rs.getString("reviewDesc");
				 String reviewValue = Integer.toString(rs.getInt("reviewValue"));
				 
				 
				 // Add into the html table
							 
				 output += "<td>" + reviewID + "</td>";
				 output += "<td>" + reviewType + "</td>";
				 output += "<td>" + reviewDesc + "</td>";   
				 output += "<td>" + reviewValue + "</td>"; 
				 	
				 
			 // buttons
				 output +=	 "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-outline-warning'></td>"
				 + "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-outline-danger' data-itemid='" + reviewID + "'></td></tr>";
			 }
			 con.close();
			 // Complete the html table
			 output += "</table>";
		 }
		 catch (Exception e)
		 {
		 output = "Error while reading the items.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
	
	
	public String insertReview(String reviewType,String reviewDesc, String reviewValue) {
	 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {
		 return "Error while connecting to the database for inserting.";
		 }
		 
		
			 // create a prepared statement
			 String query = " insert into reviews(reviewID,reviewType,reviewDesc,reviewValue) values (?, ?, ?, ?)";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 // binding values
			 preparedStmt.setInt(1, 0);
			 preparedStmt.setString(2, reviewType);
			 preparedStmt.setString(3, reviewDesc);
			 preparedStmt.setInt(4, Integer.parseInt(reviewValue));
			 	 
			// execute the statement
	
			 preparedStmt.execute();
			 con.close();
			 String newItems = readReviews();
			 output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";		
			 }
		 
		 catch (Exception e)
		 {
		 output = "{\"status\":\"error\", \"data\":\"Error while inserting the item.\"}";
		 System.err.println(e.getMessage());
		 }
		 return output;
		}
	}
	
	
	
	
	public String updateReview(String reviewID, String reviewType,String reviewDesc, String reviewValue)

	 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 
		 
		 if (con == null)
		 {return "Error while connecting to the database for updating."; }
		 // create a prepared statement
		
		 String query = "UPDATE reviews SET reviewType=?,reviewDesc=?,reviewValue=? WHERE reviewID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setString(1, reviewType);
		 preparedStmt.setString(2, reviewDesc);
		 preparedStmt.setInt(3, Integer.parseInt(reviewValue));
		 
		 preparedStmt.setInt(4, Integer.parseInt(reviewID));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 
		 String newItems = readReviews();
		 output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";		
		 }
		 
		 
		 catch (Exception e)
		 {
			 output = "{\"status\":\"error\", \"data\":\"Error while updating the item.\"}";
			 System.err.println(e.getMessage());
		 }
		 return output;
	 }
		
	
	public String deleteReview(String reviewID)
	{
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for deleting."; }
		 
		 // create a prepared statement
		 String query = "delete from reviews where reviewID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(reviewID));
		 
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 String newItems = readReviews();

		 output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";

		 }
		 catch (Exception e)
		 {
		 output = "{\"status\":\"error\", \"data\":\"Error while deleting the item.\"}";
		 System.err.println(e.getMessage());
		 }
		 return output;
	 }
}