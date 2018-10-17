package trading;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/TradingController")
public class TradingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TradingController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id= request.getParameter("id");		
		String aq=request.getParameter("amountquantity");
		String sy= request.getParameter("symbol");		
		String ac=request.getParameter("action");
		
		int amountquantity = Integer.parseInt(aq);			
					
		HttpSession session = request.getSession();
		request.setAttribute("aq", aq);	
		request.setAttribute("id", id);
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		double totalamount;
		
		String sql = "select * from execute where id='"+id+"'";
		
		System.out.println("test conn");
		
		
		 
		 try{
			 
			 	String url = "jdbc:mysql://localhost:3307/trading_system?useSSL=false";
				Class.forName("com.mysql.jdbc.Driver");
		        con = DriverManager.getConnection(url ,"root", "password");
				
		        stmt = con.createStatement();
				rs = stmt.executeQuery(sql);
				
				
				 
			      System.out.println("Moving cursor to the last...");
			      rs.last();
			      
			      //STEP 5: Extract data from result set
			      System.out.println("Displaying record...");
			     
			      String username = rs.getString("username");
			      String ticker = rs.getString("ticker");
			      String quantity = rs.getString("quantity");
			      String Avalablefund = rs.getString("Avalablefund");
			      String executeprice = rs.getString("executeprice");
		          String executedate = rs.getString("executedate");
			      
		          int price = Integer.parseInt(executeprice);			
		          double fund = Integer.parseInt(Avalablefund);
		          int quant = Integer.parseInt(quantity);
		          
			      //Display values
			   
			      System.out.print(", First: " + username);
			      System.out.print(", First: " + ticker);
			      System.out.print(", First: " + quantity);
			      System.out.print(", First: " + aq);
			      
			      totalamount = amountquantity * price;
			      fund = fund - totalamount;
			      quant = quant - amountquantity;
			      
			      String sqlUpdate = "UPDATE execute " + " SET quantity = '"+quant+"' , Avalablefund = '"+fund+"' where id = '"+id+"'";
			      System.out.println(sqlUpdate);  
					
			      if(!stmt.execute(sqlUpdate))
					 {
											
			    	  sqlUpdate= "Select * from execute";
						stmt=con.createStatement();
						rs=(ResultSet) stmt.executeQuery(sqlUpdate);
						
					
						
						while(rs.next()){
							System.out.print(rs.getString(1) + "\t");
							System.out.print(rs.getString(2));
							System.out.println();
						}
											
					} 
			/*	
	        while (rs.next()) {
	        	
	        	
	        	String username = rs.getString("username");
	            String ticker = rs.getString("ticker");
	            String quantity = rs.getString("quantity");
	            String Avalablefund = rs.getString("Avalablefund");
	            String executeprice = rs.getString("executeprice");
	            String executedate = rs.getString("executedate");
	            
	            System.out.println(username + "\t" + ticker + "\t" + quantity);*/
	           
	            session.setAttribute("id_session", id);
	            session.setAttribute("user_session", username);
	            session.setAttribute("ticker_session", ticker);
	            session.setAttribute("quantity_session", quantity);
	            session.setAttribute("Avalablefund_session", Avalablefund);
	            session.setAttribute("executeprice_session", executeprice);
	            session.setAttribute("executedate_session", executedate);
	            session.setAttribute("fund_session", fund);
	            session.setAttribute("quant_session", quant);

	        
	    } catch (SQLException e ) {
	    	System.out.println(e);
	    }catch (ClassNotFoundException e ) {
	    	System.out.println(e);
	    }
	    finally {
	        if (stmt != null) { 
	        	try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} }
	    }
	    
		 
	    
	    String nextURL ="/output.jsp";
				
	    	    
	    getServletContext().getRequestDispatcher(nextURL).forward(request,response);
	}
}

		
		

				
				
	



		