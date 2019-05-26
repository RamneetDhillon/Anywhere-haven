/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vmm.DBLoader;

/**
 *
 * @author vansh
 */
public class UserLoginCheck extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        System.out.println(email);
        System.out.println(password);
        try {

            ResultSet res = DBLoader.executeQuery("Select * from user where email='" + email + "' and password= '" + password + "'");
            if (res.next()) {
                System.out.println("success");
                HttpSession session = request.getSession();
                session.setAttribute("user", email);
                out.println("success");
            } else {
                System.out.println("fail");
                out.println("Fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
