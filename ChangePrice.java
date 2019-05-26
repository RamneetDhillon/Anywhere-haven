
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vmm.DBLoader;
import vmm2.FileUploader;


public class ChangePrice extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        int property_id = Integer.parseInt(request.getParameter("property_id"));
        int new_price = Integer.parseInt(request.getParameter("new_price"));
       
        try {
            ResultSet rs = DBLoader.executeQuery("select * from property  where property_id=" +property_id);
           if (rs.next())
                   
           {
             rs.updateInt("price_per_day", new_price); 
             rs.updateRow();
             out.println("success");
           }
           
        } catch (Exception ex) {
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
