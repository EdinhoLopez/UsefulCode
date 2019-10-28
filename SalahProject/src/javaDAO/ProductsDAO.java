package javaDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javaDatabase.MariaDBConnection;
import javaModels.Products;


public class ProductsDAO {
	
	//Connection and statement that will be used in each method
	Connection con = null;
	Statement stm = null;
	
	public ProductsDAO() {
	}
			
			
	public Connection testConnection() {
				
		//Creates object from MariaDBConnection so you can use its getConnection Method
		MariaDBConnection mariadbConnection = new MariaDBConnection();
				
		try {
					
			//Variable "con" is assigned a connection to the Database
			con = mariadbConnection.getConnection();
			System.out.println("Connected!!!!!");
			return con;
					
					
		}
		catch(Exception e) {
								
			System.out.println("Connection failed to MariaDb");
			return null;
					
		}
		finally {
					
			if(con!=null) {
						
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
						
			}
					
		}
				
	}

	//Returns an ArrayList filled with Brands objects from database
		public ArrayList<Products> getAllBrands() throws SQLException {
			// Declare variables
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			Products u = null;
			ArrayList<Products> brandList = null;

			// Assign query string to a variable
			String qString = "select * from product";

			// Create MySqlConnection class instance
			MariaDBConnection mysql = new MariaDBConnection();

			// Begin try/catch block to query the database
			try
			{
				// Connect to database
				conn = mysql.getConnection();
				// If the connection fails the application won't make it to this point
				
				// Create Statement instance/object
				stmt = conn.createStatement();
			
				// Run query and assign to the ResultSet instance
				rs = stmt.executeQuery(qString);
				//Create list to hold User objects
				brandList = new ArrayList<Products>();
				// Read the ResultSet instance
				while (rs.next()) {
					// Each iteration creates a new user
					u = new Products();
					// Assign columns/fields to related fields in the User object
					// 1,2 and 3 represent column numbers/positions
					u.setProductId(rs.getInt(1));
					u.setProductName(rs.getString(2));
					u.setProductPrice(rs.getDouble(3));
					// Add the user to the list
					brandList.add(u);
					// Repeat until rs.next() returns false (i.e., end of ResultSet)
				}
			}
			catch (ClassNotFoundException | IOException | SQLException e)
			{
				System.out.println("Error: " + e.getMessage());
				e.getStackTrace();
			}
			finally
			{
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			}
			return brandList;
		} // End of getAllUsers method	

		//Creates a Brand object into the database
		public Integer registerBrand(Products inputBrand) throws SQLException, ClassNotFoundException, IOException {
			// Declare variables
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			// Assign insert statement string to variable
			String insertString = "insert into product (productId, productName, productPrice) values (?,?,?)";
			
		    int ID = inputBrand.getProductId();
		    String[] COL = {"productID"};
		    
		    MariaDBConnection mysql = new MariaDBConnection();
		    
		    try
		    {
		        conn = mysql.getConnection();
		        stmt = conn.prepareStatement(insertString, COL);
		        
		        stmt.setInt(1, inputBrand.getProductId());
		        stmt.setString(2, inputBrand.getProductName());
		        stmt.setDouble(3, inputBrand.getProductPrice());
		        
		        stmt.executeUpdate();
		        
		        rs = stmt.getGeneratedKeys();
		        if(rs != null && rs.next()) {
		            ID = rs.getInt(1);
		        }
		        System.out.println(ID);
		    }
		    catch (SQLException e)
			{
				System.out.println("Error: " + e.getMessage());
			}
			finally
			{
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			}
		    
			return ID;
		} // End of registerBrand() method

		//Retrieves a Brands object from the DB through an ID provided
		public Products getBrandById(int brandId) throws ClassNotFoundException, IOException, SQLException {
			// Declare variables
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			Products u = null;
			
			// Assign query string to variable
			String qString = "select * from product where productId = ?";
			
			// Create MySqlConnection class instance
			MariaDBConnection mysql = new MariaDBConnection();
			
			// Begin try/catch block to query the database
			try
			{
				// Connect to database and assign query string to PreparedStatement object
				conn = mysql.getConnection();
				stmt = conn.prepareStatement(qString);
				
				// Set query parameters (?)
				stmt.setInt(1, brandId); // user_id if from String parameter passed to method
				
				// Run query and assign to ResultSet
				rs = stmt.executeQuery();
				
				// Retrieve ResultSet and assign to new User
				if (rs.next()) {
					u = new Products();
					u.setProductId(rs.getInt(1));
					u.setProductName(rs.getString(2));
					u.setProductPrice(rs.getDouble(3));
				}
			}
			catch (ClassNotFoundException | IOException | SQLException e)
			{
				System.out.println("Error: " + e.getMessage());
				e.getStackTrace();
			}
			finally
			{
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			}
			return u;
		} // End of getBrandById() method
		
		//Retrieves a Brands object from the DB through a Name provided
		public Products getBrandByName(String brandName) throws ClassNotFoundException, IOException, SQLException {
		 // Declare variables
		 Connection conn = null;
		 PreparedStatement stmt = null;
		 ResultSet rs = null;
		 Products u = null;
		 
		 // Assign query string to variable
		 String qString = "select * from product where productName = ?";
		 
		 // Create MySqlConnection class instance
		 MariaDBConnection mysql = new MariaDBConnection();
		 
		 // Begin try/catch block to query the database
		 try
		 {
		 	// Connect to database and assign query string to PreparedStatement object
		 	conn = mysql.getConnection();
		 	stmt = conn.prepareStatement(qString);
		 	
		 	// Set query parameters (?)
		 	stmt.setString(1, brandName); 
		 	
		 	// Run query and assign to ResultSet
		 	rs = stmt.executeQuery();
		 	
		 	// Retrieve ResultSet and assign to new User
		 	if (rs.next()) {
		 		u = new Products();
		 		u.setProductId(rs.getInt(1));
		 		u.setProductName(rs.getString(2));
		 		u.setProductPrice(rs.getDouble(3));
		 	}
		 }
		 catch (ClassNotFoundException | IOException | SQLException e)
		 {
		 	System.out.println("Error: " + e.getMessage());
		 	e.getStackTrace();
		 }
		 finally
		 {
		 	if (rs != null) {
		 		rs.close();
		 	}
		 	if (stmt != null) {
		 		stmt.close();
		 	}
		 	if (conn != null) {
		 		conn.close();
		 	}
		 }
		 return u;
			} // End of getBrandByName() method
			
		//Updates a Brands object from the DB through a given Brands object
		public Boolean updateBrand(Products u) throws SQLException, ClassNotFoundException, IOException {
			// Declare variables
			Connection conn = null;
			PreparedStatement stmt = null;
			Integer updateResult = null;
			
			// Assign update string to variable
			String updateString = "update product "
					+ "set productName = ?, productPrice = ? "
					+ "where productId = ?";
			
			// Create MySqlConnection class instance
			MariaDBConnection mysql = new MariaDBConnection();
			// Begin try/catch block to query the database
			try
			{
				// Connect to database and assign query string to PreparedStatement object
				conn = mysql.getConnection();
				stmt = conn.prepareStatement(updateString);
				
				// Set query parameters (?)
				stmt.setString(1, u.getProductName());
				stmt.setDouble(2, u.getProductPrice());
				stmt.setInt(3, u.getProductId());
				// Run query and assign to ResultSet
				updateResult = stmt.executeUpdate();
			}
			catch (ClassNotFoundException | SQLException e)
			{
				System.out.println("Error: " + e.getMessage());
			}
			finally
			{
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			}
			if (updateResult > 0) {
				return true;
			}
			return false;
		} // End of updateBrand() method
		
		//Deletes a Brands object from the DB through an ID provided
		public Boolean removeBrandById(int brandId) throws IOException, SQLException {
			// Declare variables
			Connection conn = null;
			PreparedStatement stmt = null;
			Integer updateResult = null;
			
			// Assign delete string to variable
			String deleteString = "delete from product where productId = ?";
			
			// Create MySqlConnection class instance
			MariaDBConnection mysql = new MariaDBConnection();
			// Begin try/catch block to query the database
			try
			{
				// Connect to database and assign query string to PreparedStatement object
				conn = mysql.getConnection();
				stmt = conn.prepareStatement(deleteString);
				
				// Set query parameters (?)
				stmt.setInt(1, brandId);
				// Run query and assign to ResultSet
				updateResult = stmt.executeUpdate();
			}
			catch (ClassNotFoundException | SQLException e)
			{
				System.out.println("Error: " + e.getMessage());
			}
			finally
			{
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			}
			if (updateResult > 0) {
				return true;
			}
			return false;
		} // End of removeBrand() method

		//Deletes a Brands object from the DB through a name provided
		public Boolean removeBrandByName(String brandName) {
			
			
			try {
				//Uses existing method to get Brand to be deleted
				Products deletedBrand = getBrandByName(brandName);
				
				//Uses existing method to delete brand and returns outcome.
				return removeBrandById(deletedBrand.getProductId());
				
			}
			catch(Exception e) {
				
				System.out.println("Unable to delete Brand");
				return false;
				
			}
			
		}
	
		public void test() {
			
			System.out.println("DAO works");
			
		}
		
		public static void main(String args[]) {
			
			ProductsDAO p1 = new ProductsDAO();
			
			try {
				p1.registerBrand(new Products(5,"Cheese",50.78));
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
}
