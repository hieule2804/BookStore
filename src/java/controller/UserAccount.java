/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AccountDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author ADMIN
 */
public class UserAccount extends HttpServlet {
    AccountDAO accdao = new AccountDAO();

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
    String action = request.getParameter("action");
        switch (action) {
            case "changepass":
                String username = request.getParameter("id");
                String old_pass= request.getParameter("old_pass");
                String new_pass= request.getParameter("new_pass");
                String re_pass= request.getParameter("re_pass");
                if(accdao.checkOldPass(username,old_pass)==true)
                {
                    if(new_pass.equals(re_pass))
                    {
                    accdao.changePass(username,new_pass);
                    request.setAttribute("changeSucces", "Change Successful!");
                    }else{
                     request.setAttribute("notDuplicate", "Re_pass Khong Giong New_pass!");
                    }
                }else{
                     request.setAttribute("oldpassKhongDung", "old_pass Khong Dung!");
                }
                request.getRequestDispatcher("ChangePassword.jsp").forward(request, response);
                break;
            default:
                throw new AssertionError();
        }
    }

 

   

}
