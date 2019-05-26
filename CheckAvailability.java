
import java.io.*;
import java.sql.ResultSet;
import java.time.LocalDate;
import javax.servlet.*;
import javax.servlet.http.*;
import vmm.DBLoader;



public class CheckAvailability extends HttpServlet
{

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        int property_id = Integer.parseInt(request.getParameter("property_id"));
        String check_in = request.getParameter("check_in");
        String check_out = request.getParameter("check_out");
        
        System.out.println(property_id);
        System.out.println(check_in);
        System.out.println(check_out);
        
        int no_of_persons = 0;
        int no_of_guests = 0;
        String type_of_place = "";
        int flag = 0;
        int booking_id = 0;
        try
        {
            ResultSet rs = DBLoader.executeQuery("select * from property where property_id=" + property_id);
            if (rs.next())
            {
                no_of_guests = rs.getInt("no_of_guests");
                type_of_place = rs.getString("type_of_place");
                LocalDate start = LocalDate.parse(check_in);
                LocalDate end = LocalDate.parse(check_out);

                while (!start.isAfter(end))
                {

                    start = start.plusDays(1);
                    System.out.println(start+" "+property_id);
                    ResultSet rs2 = DBLoader.executeQuery("select * from booking_details where date='" + start.toString() + "' and property_id=" + property_id);
                    if (rs2.next())
                    {
                        System.out.println("in above if");
                        booking_id = rs2.getInt("booking_id");
                        flag = 1;
                        break;
                    }
                }
                if (flag == 0)
                {
                    
                    Thread.sleep(2000);
                    out.println(no_of_guests);
                } else if (!type_of_place.equals("Shared Room"))
                {
                   
                    out.println("Not Available");
                } else
                {
                   
                    ResultSet rs3 = DBLoader.executeQuery("select sum(no_of_persons) as p1 from booking where booking_id in (select distinct(booking_id)from booking_details where date between '"+check_in+"' and '"+check_out+"' and property_id='" + property_id+"')");               
                    if (rs3.next())
                    {
                        no_of_persons = rs3.getInt("p1");
                        out.println((no_of_guests) - (no_of_persons));
                    }

                }
            }
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }
}
