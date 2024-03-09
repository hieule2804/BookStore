<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->
<html>

    <head>
        <title>For Shipper</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap" rel="stylesheet">

        <!-- Css Styles -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
              integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">

    </head>

    <body>
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

                                    <li>  <a href="login?action=logout"><i class=""></i> LOG OUT
                                        </a></li>
                                    <li>
                                        <a href="login?action=changePass"><i class=""></i>Change Password
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <%   }
                        %>

                    </div>
                </div>
            </div>
            <!-- list the loai sach -->
            <div class="ThanhSelect">
                <nav class="navbar navbar-default " style="margin-top: -5px;">
                    <div class="container">

                        <ul class="nav nav-pills">
                            <li>
                                <form action="order?action=listOrderForShiper" method="post">
                                    <input style="border: 0px;
                                           height: 50px;" type="submit" value="ALL ORDER" >
                                </form>
                            </li>
                        </ul>

                    </div>
                </nav>
            </div>
        </div>
        <!--body-->
        <!--list don-->
        <table border="1">
            <thead>
                <tr>
                    <th>OrderID</th>
                    <th>Name User</th>
                    

                    <th>Phone Number</th>
                    <th>City</th>
                    <th>Location</th>
                    <th>Totalmoney</th>
                    <th>status</th>
                    <th>Confirm</th>
                </tr>
            </thead>
            <tbody>
                <c:set value="${account}" var="a"></c:set>
                <c:forEach items="${listOrderForShipper}" var="s">
                <form action="order?action=confirmFromShipper&id=${s.orderid}&name=${a.username}" method="post">
                    <tr>
                        <td>${s.orderid}</td>
                        <td>${s.name}</td>
                                                

                        <td>${s.phone}</td>
                        <td>${s.city}</td>
                        <td>${s.location}</td>
                        <td>${s.totalMoney+15}VND</td>
                        <td>
                            <select name="status">
                                <option value="2">Da Giao</option>
                                <option value="3">Huy</option>
                            </select>
                        </td>
                        <td>
                            <input type="submit" value="Confirm">
                        </td>
                </form>
            </td>
        </tr>
    </c:forEach>
</tbody>
</table>
<!-- lich su don -->
<h1>History Of Order</h1>
<table border="1">
    <thead>
        <tr>
            <th>OrderID</th>
                        <th>Date</th>

            <th>Totalmoney</th>
            <th>status</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${listHistoryOfShipper}" var="l">
            <tr>
                <td>${l.orderid}</td>
                                <td>${l.date}</td>

                <td>${l.totalMoney}</td>
                <td>${l.status}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>

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
