
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vmm.DBLoader;
import vmm.SimpleMailDemo;


public class RejectProperty extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        int property_id = Integer.parseInt(request.getParameter("property_id"));
        try
        {
            
            ResultSet rs= DBLoader.executeQuery("select * from property where property_id=" + property_id);
            if(rs.next())
            {
                rs.updateString("status","rejected");
                rs.updateRow();
                String email = rs.getString("email");
                String address = rs.getString("address");
                SimpleMailDemo obj = new SimpleMailDemo(email, "Property Information", "Your property at " +address+" has been rejected by Admin.");
                out.println("success");
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
