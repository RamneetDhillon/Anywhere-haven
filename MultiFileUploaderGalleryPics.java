import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vmm.DBLoader;
import vmm2.FileUploader;
@MultipartConfig
public class MultiFileUploaderGalleryPics extends HttpServlet {

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        int property_id = Integer.parseInt(request.getParameter("property_id"));
        String caption = request.getParameter("caption");
        String absolutepath = request.getServletContext().getRealPath("/gallery_pics");
        Date dt = new Date();

        String filename = FileUploader.savefileonserver(request.getPart("f1"), absolutepath, dt.getTime() + ".jpg");
        try {
            ResultSet rs = DBLoader.executeQuery("select * from property_gallery");
            rs.moveToInsertRow();
            
            
            rs.updateInt("property_id", property_id);
            rs.updateString("caption", caption);
            rs.updateString("photo", "gallery_pics/" + filename);
            rs.insertRow();
            out.println("success");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
