<%-- 
    Document   : productdetails
    Created on : Oct 22, 2023, 12:12:04 AM
    Author     : dell
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
        <link href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap" rel="stylesheet">

        <!-- Css Styles -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
              integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">


    </head>
    <body class="container">
        <%
if(  session.getAttribute("account")==null)
                         {
                        request.getRequestDispatcher("login.jsp").forward(request, response);
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

                                    <li>  <a href="login?action=logout"><i class=""></i> LOG OUT
                                        </a></li>
                                     <li>
                                       <a href="login?action=changePass"><i class=""></i> Change Password
                                    </a>
                                    </li>
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
                            <span  class="block">Giỏ hàng(${size})</span>

                        </div>
                    </div>
                </div>
            </div>
            <!-- list the loai sach -->
            <div class="ThanhSelect">
                <nav class="navbar navbar-default " style="margin-top: -5px;">
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
        <c:set value="${detailBook}" var="d"></c:set>
            <!--book detail-->
            <div class="mt-4" style="width: 100%; height: 100vh">
                <div style="width: 100%; height: 100vh">
                    <div class="col-md-12" style="width: 100%; height: 100vh">
                        <div class="card">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="images p-3" style="height: 100%;">
                                        <div class="text-center p-4"> <img id="main-image" src="${d.image}" width="250"
                                                                       style="height: 500px;
                                                                       width: 450px; " /> </div>
                                    <!--<div class="thumbnail text-center"> <img onclick="change_image(this)" src="https://doanducdong.com/wp-content/uploads/2021/10/dac-nhan-tam-1.jpg" width="70"> <img onclick="change_image(this)" src="https://doanducdong.com/wp-content/uploads/2021/10/dac-nhan-tam-1.jpg" width="70"> </div>-->
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="product p-4">
                                    <div class="mt-4 mb-3"> <span style="font-size: 50px;" class="text-uppercase text-muted brand">${d.bookname} </span>
                                        <h3 class="text-uppercase">Author : ${d.author} </h3>
                                        <div style="font-size: 30px;" class="price d-flex flex-row align-items-center"> <span class="act-price"> Price :${d.price} VND</span>
                                            <!--<div class="ml-2"> <small class="dis-price">$59</small> <span>40% OFF</span> </div>-->
                                        </div>
                                    </div>
                                    <p style="font-size: 30px;" class="about">Descirble :${d.describle} </p>

                                    <div class="cart mt-4 align-items-center"> 
                                        <form action="bookdetail?action=buybook&id=${d.id}" method="post">
                                            <button class="btn btn-primary text-uppercase mr-2 px-4">BUY</button> 
                                        </form>
                                        <form action="bookdetail?action=addtocard&id=${d.id}" method="post">
                                            <button class="btn btn-danger text-uppercase mr-2 px-4">Add to cart</button> 
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <h1 style="color: red;">Đánh Giá Sản Phẩm</h1>
                         <!--danh gia san pham-->
                    <c:set value="${account}" var="a"></c:set>

                        <div>
                            <form action="comment?action=AddComment&id=${detailBook.id}" method="post">

                            <img style="border-radius: 50%;width: 40px; height: 40px;"
                                 src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQWT51mhd8Qcq6Pux_gY1H9FwXBqvHGBXPTHYRYXs-AWSr52Tribwj3gbiPYn9ORSRI0yc&usqp=CAU">
                            <input style="border: 0px;font-size: 30px" type="text" readonly name="username" value="${a.username}"><br>
                            <input style="margin-left: 50px; width: 1050px;" type="text" name="commnent"
                                   placeholder="Write Comment"><br>
                            <input style="margin-left: 1000px ;width: 100px" type="submit" value="Send">
                        </form>
                    </div>
                    <!-- list comment -->
                    <c:forEach items="${listcomment}" var="cmt" >

                        <img style="border-radius: 50%;width: 40px; height: 40px;"
                             src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQWT51mhd8Qcq6Pux_gY1H9FwXBqvHGBXPTHYRYXs-AWSr52Tribwj3gbiPYn9ORSRI0yc&usqp=CAU">
                        <input style="border: 0px;font-size: 30px" type="text" readonly name="username" value="${cmt.cmtuser}"><br>
                        COMMENT:<input style="margin-left: 50px; width: 1000px;" type="text" readonly name="commnent" value="${cmt.cmtdetail}"
                                       ><br>
                    </c:forEach>
                    
                </div>
            </div>
        </div>
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
