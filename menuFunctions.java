import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public abstract class menuFunctions{
	
private static Scanner scanner = new Scanner(System.in);	
private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void createRow(String DB_URL, String USER, String PASS) {
		String cSQL = "INSERT INTO dogs(name) values (?)";
		System.out.println("Enter new dog name: ");
		String newName = scanner.nextLine();
		 try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);		 
				 PreparedStatement pStatement = conn.prepareStatement(cSQL);
			){
				 pStatement.setString(1,newName); 
				 pStatement.executeUpdate();
		  } catch (SQLException e) {
		         e.printStackTrace();  
		  	}
	}
	
	public static void readRow(String DB_URL, String USER, String PASS) {
		String rSQL = "SELECT * FROM dogs";
		 try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);		 
				 PreparedStatement pStatement = conn.prepareStatement(rSQL);
			){
				 ResultSet rs = pStatement.executeQuery();
				 while (rs.next()) {
					 System.out.print(rs.getInt("id") + ". ");
					 System.out.println(rs.getString("name"));
				 }
		  } catch (SQLException e) {
		         e.printStackTrace();
		  	}
	}

	public static void updateRow(String DB_URL, String USER, String PASS) {
		//
		String uSQL = "UPDATE dogs SET name = ? WHERE id = ?";
		 try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);		 
				 PreparedStatement pStatement = conn.prepareStatement(uSQL);
			){
				 System.out.println("\nSelect number of name to change: ");
				 int dogID = Integer.parseInt(br.readLine());
				 System.out.println("Enter new name: ");
				 String newName = br.readLine();
			 	 pStatement.setString(1, newName);
			 	 pStatement.setInt(2, dogID);
				 pStatement.executeUpdate();
		  } catch (SQLException e) {
		         e.printStackTrace();
		  } catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteRow(String DB_URL, String USER, String PASS) {
		System.out.print("\nSelect number to delete: ");
		int dogID = scanner.nextInt();
		String dSQL = "DELETE FROM dogs WHERE id = ?";
		 try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);		 
				 PreparedStatement pStatement = conn.prepareStatement(dSQL);
			){
			 	 pStatement.setInt(1, dogID);
				 pStatement.executeUpdate();
		  } catch (SQLException e) {
		         e.printStackTrace();
		  	}
	}
	
	 public static void pressAnyKeyToContinue()
	 { 
	     System.out.println("Press Enter key to continue...");
	        try {
	            System.in.read();
	        }  
	        catch(Exception e)  {
	        	}  
	        }
	 
}
