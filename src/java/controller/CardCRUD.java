/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.OrderDAO;
import dal.bookDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.Account;
import model.Book;
import model.card;
import model.item;

/**
 *
 * @author ADMIN
 */
public class CardCRUD extends HttpServlet {

    bookDao bookdao = new bookDao();
OrderDAO orderdao = new OrderDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        card card = null;
        Object o = session.getAttribute("card");
        if (o != null) {
            card = (card) o;
        } else {
            card = new card();
        }
        int num = Integer.parseInt(request.getParameter("num"));
        int id = Integer.parseInt(request.getParameter("id"));
        if (num == -1 && card.getQuantityById(id) <= 1) {
            card.remoteItem(id);
        } else {
            bookDao book = new bookDao();
            Book b = book.getBookByid(id);
            int price = b.getPrice();
            item t = new item(b, num, price);
            card.addItem(t);
        }
        List<item> list = card.getItem();
        session.setAttribute("card", card);
        session.setAttribute("size", list.size());
        request.getRequestDispatcher("card.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String action = request.getParameter("action");
        switch (action) {
            case "addtocard":
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
                int id = Integer.parseInt(request.getParameter("id"));
                bookDao bookdao = new bookDao();
                Book b = bookdao.getBookByid(id);
                int price = b.getPrice();
                item t = new item(b, 1, price);
                card.addItem(t);
                List<item> list = card.getItem();
                session.setAttribute("card", card);
                session.setAttribute("size", list.size());
                request.getRequestDispatcher("home.jsp").forward(request, response);
                break;
            case "deleteBookOnCard":
                card cardDe = null;
                Object oDe = session.getAttribute("card");
                if (oDe != null) {
                    cardDe = (card) oDe;
                } else {
                    cardDe = new card();
                }
                int idDe = Integer.parseInt(request.getParameter("id"));
                cardDe.remoteItem(idDe);
                List<item> listDe = cardDe.getItem();
                session.setAttribute("card", cardDe);
                session.setAttribute("size", listDe.size());
                request.getRequestDispatcher("card.jsp").forward(request, response);
            break;
            case "buyCard":
                 card cardB = null;
                Object oB = session.getAttribute("card");
                if (oB != null) {
                    cardB = (card) oB;
                } else {
                    cardB = new card();
                }
             Account a = (Account) session.getAttribute("account");
             String name =request.getParameter("name");
             String phone =request.getParameter("sdt");
             //check phone 
               if(isValidPhoneNumber(phone)==false)
               {
               request.setAttribute("mess", "Phone Number false!!");
               request.getRequestDispatcher("ConfirmCard.jsp").forward(request, response);
               }
               else{
                   String City = request.getParameter("City");
                   String Location = request.getParameter("location");
                OrderDAO odd= new OrderDAO();
                odd.addOrder(a, cardB ,name,phone,City,Location);
                session.removeAttribute("card");
                session.setAttribute("size", 0);
                response.sendRedirect("home.jsp");
               }
                break;
                case"confirmCard":
                        request.getRequestDispatcher("ConfirmCard.jsp").forward(request, response);
                        break;
            default:
                throw new AssertionError();
        }
    }
public boolean isValidPhoneNumber(String phoneNumber) {
    String regex = "^(036\\d{7}|09\\d{8})$";
    Pattern pattern = Pattern.compile(regex);

    if (pattern != null) {
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    return false;
}
}
