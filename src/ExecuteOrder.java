

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ExecuteOrder
 */
@WebServlet("/ExecuteOrder")
public class ExecuteOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteOrder() {
        super();
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
			
	    
	         // Register JDBC driver
	    	    
				Connection c = null;
				Statement stmt = null;
				try {
					Class.forName("org.sqlite.JDBC");
					c = DriverManager.getConnection("jdbc:sqlite:c:\\testdata\\trading.db");
					c.setAutoCommit(false);
					System.out.println("Opened database successfully");

					stmt = c.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT id,user FROM trade;");

					while (rs.next()) {
						int id = rs.getInt("id");
						String user = rs.getString("user");

						System.out.println("ID = " + id);
						System.out.println("NAME = " + user);

						System.out.println();
					}
					rs.close();
					stmt.close();
					c.close();
				} catch (Exception e ) {
				      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				      System.exit(0);
				   }
				   System.out.println("Operation done successfully");
				  }
		
	
}
