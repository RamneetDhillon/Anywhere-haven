

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vmm.DBLoader;
import vmm.sendsms;
import static vmm.sendsms.send;


public class UserForgetPassword extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String email = request.getParameter("email");
        String phone;
        String phone1;
        int otp;
        String otp1;
        try {

            ResultSet res = DBLoader.executeQuery("Select * from user where email='" + email + "'");
            if (res.next()) 
            {
                System.out.println("success");
                phone=res.getString("phone");
                phone1=phone.substring(7);
                System.out.println(phone1);
                out.println(phone1);
                otp = (int)(Math.random()*9000)+1000;
                otp1 = ""+otp;
                System.out.println(otp1);
                out.println(otp1);
                
                sendsms.send(phone,otp1);

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
