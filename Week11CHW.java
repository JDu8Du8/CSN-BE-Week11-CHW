import java.io.*;
import java.sql.*;  
import java.util.*;

public class Week11CHW {
	   static final String DB_URL = "jdbc:mysql://localhost:3306/";
	   static final String USER = "root";
	   static final String PASS = "root";
	   private static Scanner scanner = new Scanner(System.in);
	   
	public static void main(String[] args) throws IOException{
		// Variables used for input.
			int selection = 0;
			
		// Refresh and create database.
		 try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		         Statement stmt = conn.createStatement();
		      ) {
			     stmt.executeUpdate("DROP DATABASE animals");
		         stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS animals");
		         stmt.executeUpdate("USE animals");
		         stmt.executeUpdate("CREATE TABLE IF NOT EXISTS dogs" 
		        		 + "(id INTEGER AUTO_INCREMENT not NULL, " 
		        		 + "name VARCHAR(255),"
		        		 + "PRIMARY KEY (id)) ");
		         stmt.executeUpdate("INSERT INTO dogs (name)" 
		        		 + "VALUES('Chihuahua'), ('Poodle'), ('Great Pyrenees')"
		         		 );
		      } catch (SQLException e) {
		         e.printStackTrace();}
		 
		do {
		System.out.println("Table Functions");
		System.out.println("1. Insert a row");
		System.out.println("2. Show list");
		System.out.println("3. Update a row");
		System.out.println("4. Delete row");
		System.out.println("0. Exit");
		System.out.print("\nSelect Function: ");
		
		selection = scanner.nextInt();
	
		switch(selection) {
			case 1:
			//Add new data.
				menuFunctions.createRow(DB_URL+"animals", USER, PASS);
				break;
			
			case 2:
			//Read data.                                    
				menuFunctions.readRow(DB_URL+"animals", USER, PASS);
				menuFunctions.pressAnyKeyToContinue();
				break;
			
			case 3:
			//Present list, get update, present updated list.	
				menuFunctions.readRow(DB_URL+"animals", USER, PASS);
				menuFunctions.updateRow(DB_URL+"animals", USER, PASS);
				System.out.println("Updated List");
				menuFunctions.readRow(DB_URL+"animals", USER, PASS);
				menuFunctions.pressAnyKeyToContinue();
				break;
			
			case 4:
			//Delete data.
				menuFunctions.readRow(DB_URL+"animals", USER, PASS);
				menuFunctions.deleteRow(DB_URL+"animals", USER, PASS);
				break;
			}
	  }while(selection != 0);
	}
}
