<%-- 
    Document   : Register Page
    Created on : Jun 11, 2017, 4:31:35 PM
    Author     : hp
--%>

<%@page import="project.ezgo.BLO.AccountMng"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="view/logo.jsp" charEncoding="utf-8"/>
<hr/>

<div class="container"> 
    <div class="login-card register-card" id="registerCard">
        <form action="process" method="POST" name="registration" onsubmit="return validateForm();">
            <script>
                var accountsObj = '${requestScope.ACCOUNTLIST}';
                var saveFile = "WEB_INF/account.xml";
            </script>
            
            <h3 class="title">Đăng ký mới</h3>
            <label>Email <span style="color: red;">*</span></label>
            <input type="email" class="form-control" name="email" placeholder="Email" required="required" />

            <label>Tên Đăng nhập <span style="color: red;">*</span></label>
            <input type="text" class="form-control" name="username" placeholder="Tên đăng nhập" required="required" />

            <label>Mật khẩu <span style="color: red;">*</span></label>
            <input type="password" class="form-control" name="password" placeholder="Mật khẩu" required="required" />

            <label>Xác nhận mật khẩu <span style="color: red;">*</span></label>
            <input type="password" class="form-control" name="repassword" placeholder="Xác nhận mật khẩu" required="required" />
            
            <font color="red" id="registerError" style="display:none;">Lỗi: Email không đúng cú pháp</font>
            
            <button type="submit" class="btn btn-green" onclick="checkIfExist()" value="Register">Gửi</button>
            <a href="?p=login"><font color="red" id="registerError" style="display:none;">Bạn đã đăng ký thành công. Bấm vào đây để đăng nhập.</font></a>
        </form>
    </div>
</div>

<br/>

