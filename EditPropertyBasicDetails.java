
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vmm.DBLoader;


public class EditPropertyBasicDetails extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
         String address=request.getParameter("address") ;
            int no_of_guests= Integer.parseInt(request.getParameter("no_of_guests")); 
                int no_of_bedrooms = Integer.parseInt(request.getParameter("no_of_bedrooms"));
                int no_of_bathrooms= Integer.parseInt(request.getParameter("no_of_bathrooms")); 
                String cancelation_policy=request.getParameter("cancelation_policy") ;
                
       
       int property_id = Integer.parseInt(request.getParameter("property_id"));
        try{
          
           ResultSet rs=DBLoader.executeQuery("select * from property where property_id="+
                                        property_id);
          
         if (rs.next())
           {
               rs.updateString("address", address);
               rs.updateString("cancelation_policy", cancelation_policy);
               rs.updateInt("no_of_bathrooms", no_of_bathrooms);
               rs.updateInt("no_of_bedrooms", no_of_bedrooms);
               rs.updateInt("no_of_guests", no_of_guests);
               
               rs.updateRow();
               
               response.sendRedirect("PropertyDetails.jsp?property_id="+property_id);
              
           }
        
        
        }
        catch(Exception e){
            e.printStackTrace();
        }
       
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
