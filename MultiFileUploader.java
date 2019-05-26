

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import vmm.DBLoader;
import vmm2.FileUploader;

@MultipartConfig
public class MultiFileUploader extends HttpServlet {

   protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
            
        String email=request.getParameter("email");
        try
        {
            
            ResultSet rs= DBLoader.executeQuery("select * from property_owner where email='" + email +"' ");
            if(rs.next())
            {
                out.println("Email already exists");
            }
            else
            {
              String absolutepath = request.getServletContext().getRealPath("/owner_pics");
              Date dt = new Date();
             String filename = FileUploader.savefileonserver(request.getPart("f1"), absolutepath,dt.getTime()+".jpg");
            
            
                String name = request.getParameter("name");
                String password = request.getParameter("password");
                String phone = request.getParameter("phone");
                String address = request.getParameter("address");
                
             

                rs.moveToInsertRow();
                rs.updateString("password", password);
                rs.updateString("name", name);
                rs.updateString("email", email);
                rs.updateString("phone", phone);
                 rs.updateString("address",address);
                rs.updateString("photo", "owner_pics/" + filename);
                rs.insertRow();

                out.println("success");
  
            }
        } 
            catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
