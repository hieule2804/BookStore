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
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import model.Book;
import model.card;
import model.item;

/**
 *
 * @author ADMIN
 */
public class BookDetail extends HttpServlet {

   
bookDao bookdao = new bookDao();
CommentDAO cmtdao = new CommentDAO();
     categoriDAO catedao = new categoriDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "getbook":
                int id = Integer.parseInt(request.getParameter("id"));
                request.setAttribute("detailBook", bookdao.getBookByid(id));
                request.setAttribute("listcate", catedao.getListCate());
                request.setAttribute("listcomment", cmtdao.getlistCommentbyId(id));
        request.getRequestDispatcher("productdetails.jsp").forward(request, response);
                break;
            default:
                throw new AssertionError();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
       String action= request.getParameter("action");
        switch (action) {
            case "buy":
                int id = Integer.parseInt(request.getParameter("id"));
                request.setAttribute("detailBook", bookdao.getBookByid(id));
                request.getRequestDispatcher("productdetails.jsp").forward(request, response);
                break;
            case "buybook":
                if(session.getAttribute("account")==null)
                {
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
                card card = null;
                Object o = session.getAttribute("card");
                if (o != null) {
                    card = (card) o;
                } else {
                    card = new card();
                }
                int idb = Integer.parseInt(request.getParameter("id"));
                bookDao bookdao = new bookDao();
                Book b = bookdao.getBookByid(idb);
                int price = b.getPrice();
                item t = new item(b, 1, price);
                card.addItem(t);
                List<item> list = card.getItem();
                session.setAttribute("card", card);
                session.setAttribute("size", list.size());
                request.getRequestDispatcher("card.jsp").forward(request, response);
                break;
            case "addtocard" :
                 if(session.getAttribute("account")==null)
                {
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
                card cardd = null;
                Object od = session.getAttribute("card");
                if (od != null) {
                    cardd = (card) od;
                } else {
                    cardd = new card();
                }
                int idd = Integer.parseInt(request.getParameter("id"));
                bookDao bookdaod = new bookDao();
                Book bd = bookdaod.getBookByid(idd);
                int priced = bd.getPrice();
                item td = new item(bd, 1, priced);
                cardd.addItem(td);
                List<item> listd = cardd.getItem();
                session.setAttribute("card", cardd);
                session.setAttribute("size", listd.size());
                request.getRequestDispatcher("card.jsp").forward(request, response);
                break;
            default:
                throw new AssertionError();
        }
    }

   
}
