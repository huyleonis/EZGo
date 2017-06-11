<%-- 
    Document   : Register Page
    Created on : Jun 11, 2017, 4:31:35 PM
    Author     : hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="view/logo.jsp" charEncoding="utf-8"/>
<hr/>

<div class="container"> 

    <div class="login-card register-card" id="registerCard">
        <form action="" method="POST">						
            <h3 class="title">Đăng ký mới</h3>
            <label>Email <span style="color: red;">*</span></label>
            <input type="email" class="form-control" name="email" placeholder="Email" required="required" />

            <label>Tên Đăng nhập <span style="color: red;">*</span></label>
            <input type="text" class="form-control" name="email" placeholder="Tên đăng nhập" required="required" />

            <label>Mật khẩu <span style="color: red;">*</span></label>
            <input type="password" class="form-control" name="password" placeholder="Mật khẩu" required="required" />

            <label>Xác nhận mật khẩu <span style="color: red;">*</span></label>
            <input type="password" class="form-control" name="password" placeholder="Xác nhận mật khẩu" required="required" />
            
            <font color="red" id="registerError" style="display:none;">Lỗi: Email không đúng cú pháp</font>
            <button type="submit" class="btn btn-green" name="action" value="login">Gửi</button>
        </form>
    </div>
</div>

<br/>

