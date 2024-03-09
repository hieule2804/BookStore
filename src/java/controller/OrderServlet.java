/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AccountDAO;
import dal.InforOrderDAO;
import dal.OrderDAO;
import dal.categoriDAO;
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
public class OrderServlet extends HttpServlet {

    AccountDAO acc = new AccountDAO();
    OrderDAO orderdao = new OrderDAO();
    categoriDAO cateDAO = new categoriDAO();
    InforOrderDAO indao = new InforOrderDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "history":
                String name = request.getParameter("id");
                request.setAttribute("listHistory", orderdao.getHistory(name));
                request.setAttribute("listcate", cateDAO.getListCate());
                request.getRequestDispatcher("purchaseHistory.jsp").forward(request, response);
                break;
            default:
                throw new AssertionError();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        switch (action) {
            case "getOrder":
                session.setAttribute("listorder", orderdao.getlistOrder());
                session.setAttribute("thebestuser", orderdao.getTheMostUser());
                session.setAttribute("listConfirm", indao.getlistConfirm());
                session.setAttribute("listshipper", acc.getListShipper());
                session.setAttribute("numberOfOrder", orderdao.getnumberOfOrderBymostUSer(orderdao.getTheMostUser()));
                session.setAttribute("themostselling", orderdao.getbestSelling());
                request.getRequestDispatcher("listOrder.jsp").forward(request, response);
                break;
            case "detail":
                int id = Integer.parseInt(request.getParameter("id"));
                session.setAttribute("listdetail", orderdao.getListDetail(id));
                request.getRequestDispatcher("listOrderDetail.jsp").forward(request, response);
                break;
            case "confirm":
                int orderid = Integer.parseInt(request.getParameter("id"));
                String shipper = request.getParameter("shipper");
                indao.updateOrder(orderid, shipper);
                request.getRequestDispatcher("ForAdmin.jsp").forward(request, response);
                break;
            case "listOrderForShiper":
                session.setAttribute("listOrderForShipper", indao.getlistOrderForShipper());
                request.getRequestDispatcher("ForShipper.jsp").forward(request, response);

                break;
            case "confirmFromShipper":
                int orderFromshipperid = Integer.parseInt(request.getParameter("id"));
                int status = Integer.parseInt(request.getParameter("status"));
                indao.updateFromShipper(orderFromshipperid, status);
                String name = request.getParameter("name");
                session.setAttribute("listOrderForShipper", indao.getlistOrderForShipper());
                session.setAttribute("listHistoryOfShipper", indao.getHistoryOfShipper(name));
                request.getRequestDispatcher("ForShipper.jsp").forward(request, response);

                break;

        }
    }

}
