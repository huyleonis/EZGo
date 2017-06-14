<%-- 
    Document   : navigator fragment
    Created on : Jun 11, 2017, 12:21:28 AM
    Author     : Huy Leonis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="fullname" value="${sessionScope.ACCOUNT_FULLNAME}"/>
<c:set var="accId" value="${sessionScope.ACCOUNT_ID}"/>


<div class="top-menu" id="topMenu">

    <a href="." ><i class="fa fa-home" style="font-size: 21px;"></i></a>
    <a href="#" >Giới thiệu</a>
    <a href="#" >Tour trong nước</a>
    <a href="#" >Tour nước ngoài</a>
    <a href="#" >Khuyến mãi</a>
    <div class="dropdown">
        <a class="dropdown-btn" >
            <i class="fa fa-user-circle" style="font-size: 21px;"></i> 
            <c:if test="${not empty fullname}">
                ${fullname}
            </c:if>
        </a>
        <div class="dropdown-content">
            <c:if test="${empty accId}">
                <a href="?p=login">Đăng nhập</a>
                <a href="?p=register">Đăng ký</a>				
            </c:if>
            <c:if test="${not empty accId}">
                <c:url var="accountInfo" value="process">
                    <c:param name="action" value="AccountInfo"/>
                    <c:param name="id" value="${accId}"/>
                </c:url>
                <a href="${accountInfo}">Thông tin tài khoản</a>
                <a href="logout">Đăng xuất</a>				
            </c:if>
        </div>
    </div>

    <a class="icon" onclick="openMenu()">&#9776;</a>

</div>

