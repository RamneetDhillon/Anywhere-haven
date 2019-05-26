

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vmm.DBLoader;


public class EditOwnerDetails extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session=request.getSession();
        String email = session.getAttribute("email").toString();

        String new_name = request.getParameter("new_name");
        String new_phone = request.getParameter("new_phone");
        String new_address = request.getParameter("new_address");

        try {
            ResultSet rs= DBLoader.executeQuery("select * from property_owner where email='" + email + "' ");

            if (rs.next()) {
                
                rs.updateString("name",new_name);
                rs.updateString("phone",new_phone);
                rs.updateString("address",new_address);
                rs.updateRow();
                out.println("success");
              
                             
            } else {
                out.println("failed");
            }

        } catch (Exception e) {
            e.printStackTrace();
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
