
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
public class MultiFileUploaderPrimaryPhoto extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        int property_id = Integer.parseInt(request.getParameter("property_id"));
        try {
             Date dt = new Date();
            String absolutepath = request.getServletContext().getRealPath("/property_pics");

            String filename = FileUploader.savefileonserver(request.getPart("f1"), absolutepath, dt.getTime() + ".jpg");
            ResultSet rs = DBLoader.executeQuery("select * from property where property_id=" + property_id);
            if (rs.next()) {
                rs.updateString("primary_photo", "property_pics/" + filename);
                rs.updateRow();
                response.sendRedirect("AddPropertyStep4.jsp?property_id="+property_id);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
