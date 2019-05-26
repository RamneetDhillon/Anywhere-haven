

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vmm.RDBMS_TO_JSON;


public class SendMyProperties extends HttpServlet {

    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
         HttpSession session=request.getSession();
        String email = session.getAttribute("email").toString();
         
        String mainjson = new RDBMS_TO_JSON().generateJSON("select * from property where email='"+email+"'");
        out.println(mainjson);
        

    }

   
}
