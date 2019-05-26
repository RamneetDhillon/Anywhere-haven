
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vmm.DBLoader;
import vmm2.FileUploader;

@MultipartConfig
public class MultiFileUploaderChangeProfilePic extends HttpServlet {

    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session=request.getSession();
        String email = session.getAttribute("email").toString();
        
        try{
            Date dt = new Date();
         String absolutepath = request.getServletContext().getRealPath("/owner_pics");
             
         String filename = FileUploader.savefileonserver(request.getPart("f1"), absolutepath,dt.getTime()+".jpg"); 
          ResultSet rs= DBLoader.executeQuery("select * from property_owner where email='" + email +"' ");
            if(rs.next())
            {
                rs.updateString("photo", "owner_pics/" + filename);
                rs.updateRow();
                out.println("success");
            }
           
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
            
        
    }

    

}
