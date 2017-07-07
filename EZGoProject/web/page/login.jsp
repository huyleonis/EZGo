<%-- 
    Document   : Login Page
    Created on : Jun 11, 2017, 2:45:50 PM
    Author     : Huy Leonis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="view/logo.jsp" charEncoding="utf-8"/>
<hr/>

<div class="container"> 
    <div class="login-card" id="loginCard">
        <form action="process" method="POST">			
            <img src="img/EZgo.png" class="logo" alt="EZgo">
            <h3 class="title">Đăng nhập</h3>
            <label>Tên đăng nhập/Email:</label>
            <input type="text" class="form-control" name="email" placeholder="Nhập email/tên đăng nhập..." required="required" />
            <label>Mật khẩu:</label>
            <input type="password" class="form-control" name="password" placeholder="Nhập mật khẩu..." required="required" />

            <label><input type="checkbox" name="remember"> Nhớ mật khẩu</label>
            <c:if test="${not empty sessionScope.LOGIN_ERROR}">
                <br/>
                <font color="red" id="registerError">Lỗi: ${sessionScope.LOGIN_ERROR}</font>
                <%  request.getSession().removeAttribute("LOGIN_ERROR");  %>
            </c:if>
            <button type="submit" class="btn btn-green" name="action" value="Login">Đăng nhập</button>
        </form>

        <a href="?p=register"><i>Chưa có tài khoản? Đăng ký ngay.</i></a>
    </div>

</div>

<br/>
