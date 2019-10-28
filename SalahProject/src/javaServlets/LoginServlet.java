package javaServlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javaDAO.ProductsDAO;
import javaModels.Products;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		//Get the input from the text fields as Strings
		String productID = request.getParameter("ProductID");
		
		String productName = request.getParameter("ProductName");
		
		String productPrice = request.getParameter("ProductPrice");
		
		//Turn input string into the corresponding types
		try {
			
			Integer ID = Integer.parseInt(productID);
			
			Double price = Double.parseDouble(productPrice);
			
			System.out.println(ID + " " + productName + " " + price);
			
			Products insertP = new Products(ID,productName,price);
			
			//Create the DAO for the Products class
			ProductsDAO pDOA = new ProductsDAO();
		
			//pDOA.testConnection();
			try {
				pDOA.registerBrand(insertP);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}catch(Exception e) {
			
			System.out.println("Unable to insert product");
			
		}
		
		
		
		
		
	}

}
