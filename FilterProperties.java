
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vmm.RDBMS_TO_JSON;


public class FilterProperties extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        int min_price = Integer.parseInt(request.getParameter("min_price"));
        int max_price = Integer.parseInt(request.getParameter("max_price"));
        String city = request.getParameter("city");
        String type_of_place = request.getParameter("type_of_place");
        String type_of_property = request.getParameter("type_of_property");
        String mainjson = new RDBMS_TO_JSON().generateJSON("Select * from property where  type_of_place like'%" + type_of_place + "%'and type_of_property like'%" + type_of_property + "%'and address like'%" + city + "%'and price_per_day>='" + min_price + "'and price_per_day<='" + max_price + "' and status = 'approved'");
        System.out.println(mainjson);
        out.println(mainjson);
        
       
        
    }

    // 
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
