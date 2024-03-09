<%-- 
    Document   : ConfirmCard
    Created on : Oct 27, 2023, 9:25:14 AM
    Author     : ADMIN
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>ConFirm Page</title>

    <link href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap" rel="stylesheet">

    <!-- Css Styles -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
        integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">

</head>

<body>
    <!--Thong Tin Khach hang-->
         <form action="card?action=buyCard" method="post">
    <div>
        <h1>information </h1>
       Name: <input type="text" name="name" placeholder="Name">
       Phone Number: <input type="text" name="sdt" placeholder="Phone Number"><br>
       City: <input type="text" name="City" placeholder="City"><br>
       Location: <input type="text" name="location" placeholder="Street"><br> 
    </div>
    <!--Xac nhan Don Hang-->

    <div>
        <!--list don hang-->
           <h1>List Card </h1>
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
                        <input type="text" readonly value="${book.quantity}"> 
                    </td>
                    <td>${book.price}VND</td>
                    <td>${book.quantity*book.price}VND</td>
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
        <!--buy don hang-->
         <input type="submit" value="BUY">
    </div>
     </form>
</body>

</html>
