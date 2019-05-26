

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vmm.DBLoader;


public class AdminChangePasswordCheck extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session=request.getSession();
        String username = session.getAttribute("username").toString();

        String old_password = request.getParameter("old_password");
        String new_password = request.getParameter("new_password");

        try {
            ResultSet rs= DBLoader.executeQuery("select * from admin where username='" + username + "' and password='" + old_password + "' ");
            if (rs.next()) 
            {
                rs.updateString("password", new_password);
                rs.updateRow();
                out.println("success");
            }
            else 
            {
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
