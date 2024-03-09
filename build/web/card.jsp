<%-- 
    Document   : card
    Created on : Oct 16, 2023, 8:56:51 PM
    Author     : ADMIN
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
        <link href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap" rel="stylesheet">

        <!-- Css Styles -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
              integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">

    </head>
    <body>
                    <%
if( session.getAttribute("account") == null)
{
      response.sendRedirect("login.jsp");
}
%>
        <!--header-->
           <div class="header container ">
            <div class="firtHeader row" style="    margin-top: -20px;">
                <div class="logoHearder col-md-2">
                    <img style="height: 200px;"
                         src="https://png.pngtree.com/template/20191125/ourlarge/pngtree-book-store-logo-template-sale-learning-logo-designs-vector-image_335046.jpg"
                         alt="">
                </div>
                <div class="rightHeader col-md-10" style="margin-top: 79px;">
                    <!--search book-->
                    <div class="seachHeader col-md-8">

                        <form action="home?action=searchByName" method="post" role="search">
                            <input style="    margin-left: 20px;
                                   width: 500px;
                                   height: 35px;" type="text" name="bookNameSerach"  placeholder="Tìm kiếm sản phẩm... "
                                   class="input-group-field st-default-search-input search-text" autocomplete="off">

                            <button style="    width: 35px; border: 0px;
                                    height: 33px;" tyle="submit " class="glyphicon glyphicon-search">
                                <i class="fa fa-search"></i>
                            </button>
                        </form>
                    </div>
                    <div class="itemHearder col-md-4" style="    padding-top: 7px;
                         margin-left: -33px;">
                        <!--Dang ky ,Dang Nhap-->
                        <!--tai khoan truoc khi dang nhap-->
                        <%
                        if( session.getAttribute("account") ==null)
                        {
                        %>
                        <div class="taikhoan col-md-6">
                            <div onclick="LoginLogout()" class="login">

                                <a href="#" class="cart-label header-icon">
                                    <div class="cart-info" style="padding-left: 20px;">
                                        <span class="glyphicon glyphicon-user header-icon"></span>
                                    </div>
                                </a>
                                <span class="block">Tài khoản</span>
                            </div>
                            <div class="top-cart-content" id="LoginLogout" style="display: none;     margin-left: -30px;">
                                <ul>

                                    <a href="login.jsp"><i class=""></i> Đăng nhập
                                    </a></li>
                                    <a href="register.jsp"><i class="fa fa-shopping-cart"></i> Đăng ký
                                    </a></li>

                                </ul>
                            </div>
                        </div>
                        <%  
                            }else{
                        %>

                        <!--Tai khoan sau khi dang nhap-->
                        <div class="taikhoan col-md-6">
                            <div onclick="LoginLogout()" class="login">

                                <a href="#" class="cart-label header-icon">
                                    <div class="cart-info" style="padding-left: 20px;">
                                        <span class="glyphicon glyphicon-user header-icon"></span>
                                    </div>
                                </a>
                                <span class="block">${account.username}</span>
                            </div>
                            <div class="top-cart-content" id="LoginLogout" style="display: none;     margin-left: -30px;">
                                <ul>
                                    <a href="login?action=logout"><i class=""></i> LOG OUT
                                    </a></li>
                                </ul>
                            </div>
                        </div>
                        <%   }
                        %>
                        <!-- gior hangf -->
                        <div class="giohang col-md-6" style=" padding-left: 0px;">

                            <a href="card.jsp" class="cart-label header-icon">
                                <div class="cart-info" style="padding-left: 20px;">
                                    <span class="glyphicon glyphicon-shopping-cart" id="cart-total"></span>
                                </div>
                            </a>
                            <span  class="block">Giỏ hàng</span>

                        </div>
                    </div>
                </div>
            </div>
            <!-- list the loai sach -->
            <div class="ThanhSelect">
                <nav class="navbar navbar-default " style="margin-top: -20px;">
                    <div class="container">

                        <ul class="nav nav-pills">
                            <li>
                                <form action="home?action=allbook" method="post">
                                     <input style="border: 0px;
                                               height: 50px;" type="submit" value="ALL BOOK" >
                                </form>
                            </li>
                            <!--sẽ dùng c:foreach trong jsp và sử dụng list thể lại sách để cho vào tên và link-->
                            <c:forEach items="${listcate}" var="cate">
                                <li style="margin-right: 15px;" role="presentation">
                                    <form action="home?action=searchByCate&id=${cate.id}" method="post">
                                        <input style="border: 0px;
                                               height: 50px;" type="submit" value="${cate.categori}" >
                                    </form>
                                </li>
                            </c:forEach>

                        </ul>

                    </div>
                </nav>
            </div>
        </div>
        <!--body-->
        <div>
        <table border="1">
            <thead>
                <tr>
                    <th>BOOK NAME</th>
                    <th>Quantity</th>
                    <th>PRICE</th>
                     <th>TotalMoney</th>
                     <th>Action</th>


                </tr>
            </thead>
                
            <tbody>
                <c:set var="b" value="${sessionScope.card}"/>
                <c:forEach items="${b.item}" var="book">
                <tr>
                    <td>${book.book.bookname}</td>
                    <td>
                        <button><a href="card?num=-1&id=${book.book.id}">-</a></button>
                        <input type="text" readonly value="${book.quantity}"> 
                        <button><a href="card?num=1&id=${book.book.id}">+</a></button>
                    </td>
                    <td>${book.price}</td>
                    <td>${book.quantity*book.price}</td>
                    <td>
                        <form action="card?action=deleteBookOnCard" method="post">
                            <input type="hidden" name="id" value="${book.book.id}">
                            <input type="submit" value="Delete">
                        </form>
                    </td>
                </tr>
                
            </c:forEach>
            </tbody>
        </table>
             
</div>
     <!--Buy Card-->
     <form action="card?action=confirmCard" method="post">
         <input type="submit" value="BUY">
     </form>
     <c:set value="${sessionScope.account}" var="a"></c:set>
     <h2><a href="order?action=history&id=${a.username}">purchase history</a></h2>
     <h2> <a href="home.jsp">HOME</a></h2>
     
    </body>
 <script>
        function LoginLogout() {
            let x = document.getElementById('LoginLogout');
            if (x.style.display == 'none') {
                x.style.display = 'block';
            } else {
                x.style.display = 'none';
            }
        }
    </script>
    
</html>

