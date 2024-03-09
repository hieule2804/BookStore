/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CommentDAO;
import dal.bookDao;
import dal.categoriDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author ADMIN
 */
public class CommentServlet extends HttpServlet {

    CommentDAO comDAO = new CommentDAO();
bookDao bookdao = new bookDao();
     categoriDAO catedao = new categoriDAO();
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
  
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
String action = request.getParameter("action");
        switch (action) {
            case "AddComment":
                int bid = Integer.parseInt(request.getParameter("id"));
                String username = request.getParameter("username");
                String comment = request.getParameter("commnent");
                comDAO.addcmt(bid,username,comment);
                request.setAttribute("detailBook", bookdao.getBookByid(bid));
                request.setAttribute("listcate", catedao.getListCate());
                request.setAttribute("listcomment", comDAO.getlistCommentbyId(bid));
                request.getRequestDispatcher("productdetails.jsp").forward(request, response);
                break;
            case"getlistcomment":
                request.setAttribute("listcomment", comDAO.getListComment());
                request.getRequestDispatcher("listComment.jsp").forward(request, response);
                break;
            case"delete":
                int id =Integer.parseInt(request.getParameter("id"));
                comDAO.deleteByid(id);
                    request.setAttribute("listcomment", comDAO.getListComment());
                    request.getRequestDispatcher("listComment.jsp").forward(request, response);
            default:
                throw new AssertionError();
        }
    }



}
