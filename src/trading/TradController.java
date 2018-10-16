package trading;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginController
 */
@WebServlet(description = "Controller in the MVC paradigm to connect to the trading system", urlPatterns = { "/LoginController" })
public class TradController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection conn;

    /**
     * Default constructor. 
     */
    public TradController() {
        // TODO Auto-generated constructor stub
    }
    
    /**
     * init. Initialized database connection
     */
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            // db parameters
            System.out.println("befor connection");
        	Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3307/trading_system";
        
            // create a connection to the database
            
            
          
            conn = DriverManager.getConnection(url, "root", "password");
        } catch (SQLException e) {
        	e.printStackTrace();
        } catch (ClassNotFoundException e) {
        	e.printStackTrace();
        }
        System.out.println("after connection");
      }
    
    /** 
     * destroy method closes db connection
     */
    public void destroy() {
    	try {
    		conn.close();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
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
		String un=request.getParameter("username");
		String pw=request.getParameter("password");
		//response.getWriter().append("<p>").append(un).append("</p>");
		String storedPassword = null;
	        try {

	            String sql = "select password from login where username ='" + un + "'";
	    		response.getWriter().append("<p>").append(sql).append("</p>n");
	            
	             Statement stmt  = conn.createStatement();
	             ResultSet rs    = stmt.executeQuery(sql);
	            
	            // loop through the result set
	            if (rs.next()) {
	            	storedPassword = rs.getString("password");
		    		response.getWriter().append("<p>").append(storedPassword).append("</p>");
	            }
	            stmt.close();

	        } catch (SQLException e) {
	        	e.printStackTrace();
	        }
		
		if(pw.equals(storedPassword))
		{
			response.sendRedirect("success.html");
    		//response.getWriter().append("<p>").append("success").append("</p>");
			return;
		}
		else
		{
    		//response.getWriter().append("<p>").append("error").append("</p>");
			response.sendRedirect("error.html");
			return;
		}
	}

}
