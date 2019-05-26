

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vmm.DBLoader;

/**
 *
 * @author vansh
 */
@MultipartConfig
public class MultiFileUploaderUser extends HttpServlet {
protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        System.out.println(email+" "+name);
        String phone = request.getParameter("phone");
       
        // String photo = request.getParameter("photo");
        try {

         ResultSet res = DBLoader.executeQuery("Select * from user where email='" + email + "'");
            if (res.next()) {
                out.println("email already exists");
            } else {

       String absolutepath = request.getServletContext().getRealPath("/user_pics");
       String filename = vmm2.FileUploader.savefileonserver(request.getPart("f1"), absolutepath, email + ".jpg");

                res.moveToInsertRow();
                res.updateString("email", email);
                res.updateString("name", name);
                res.updateString("password", password);

                res.updateString("phone", phone);
            
                res.updateString("photo", "user_pics/" + filename);
                res.insertRow();
                out.println("success");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

            
        
    }

    

}
