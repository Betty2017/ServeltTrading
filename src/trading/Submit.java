package trading;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Submit
 */
@WebServlet("/Submit")
public class Submit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Submit() {
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
		// TODO Auto-generated method stub
		String id=request.getParameter("id");
		String un=request.getParameter("username");
		String ti=request.getParameter("ticker");
		String qu=request.getParameter("quantity");
		String af=request.getParameter("Avalablefund");
		String ep=request.getParameter("executeprice");
		String ed=request.getParameter("executedate");
		
		 HttpSession session=request.getSession();  
	        session.setAttribute("id",id); 
	        
		if (request.getParameter("first") != null) {
		  			      	        
		    java.sql.Connection con=null;
			java.sql.Statement stmt=null;
			ResultSet rs=null;
			String sql = "Insert into execute(id,username,ticker,quantity,Avalablefund,executeprice,executedate)"
					+ " values('"+id+"','"+un+"','"+ti+"','"+qu+"','"+af+"','"+ep+"','"+ed+"')";
			
			
			System.out.println(sql);
			
			
			try{
				Class.forName("com.mysql.jdbc.Driver");
		        con = DriverManager.getConnection("jdbc:mysql://localhost:3307/trading_system?"
		                            + "user=root&password=password");
				stmt = con.createStatement();
				 if(!stmt.execute(sql))
				 {
										
					sql= "Select * from execute";
					stmt=con.createStatement();
					rs=(ResultSet) stmt.executeQuery(sql);
					
				
					
					while(rs.next()){
						System.out.print(rs.getString(1) + "\t");
						System.out.print(rs.getString(2));
						System.out.println();
					}
										
				}
				}catch (SQLException e) {
					e.printStackTrace();
				}catch (ClassNotFoundException e) {
					e.printStackTrace();
			} finally {
				try {
					rs.close();
					stmt.close();
					con.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
			
			
		}
			
			
else if (request.getParameter("second") != null) {
	String nextURL ="/adduser.jsp";
	    	    
    getServletContext().getRequestDispatcher(nextURL).forward(request,response);
}	


		}

	}


