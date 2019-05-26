
import java.io.IOException;
import java.io.PrintWriter;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vmm.DBLoader;


public class CalculatePrice extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        int property_id = Integer.parseInt(request.getParameter("property_id"));
        String check_in = request.getParameter("check_in");
        
        String check_out= request.getParameter("check_out");
        float price;
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            System.out.println(check_in);
            System.out.println(check_out);
             Date dateBefore = myFormat.parse(check_in);
	     Date dateAfter = myFormat.parse(check_out);
             
	     long difference = dateAfter.getTime() - dateBefore.getTime();
	     float no_of_days = (difference / (1000*60*60*24));
             System.out.println(no_of_days);
            ResultSet rs= DBLoader.executeQuery("select * from property where property_id=" +property_id);
            if(rs.next())
            {
                int price_per_day = rs.getInt("price_per_day");
               
                price = (price_per_day)*(no_of_days+1);
                out.println(price);
                
            }
            
        } 
            catch (Exception ex)
        {
            ex.printStackTrace();
        }
        
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    

}
