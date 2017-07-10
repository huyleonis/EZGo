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
        <form action="process" method="POST" name="registration">
            <script>
                var accountsObj = '${requestScope.ACCOUNTLIST}';
                var saveFile = '${requestScope.FILEPATH}';
            </script>
            
            <h3 class="title">Đăng ký mới</h3>
            <label>Email <span style="color: red;">*</span></label>
            <input type="email" class="form-control" name="email" onchange="clearError()" placeholder="Email" required="required" />

            <label>Tên Đăng nhập <span style="color: red;">*</span></label>
            <input type="text" class="form-control" name="username" onchange="clearError()" placeholder="Tên đăng nhập" required="required" />

            <label>Mật khẩu <span style="color: red;">*</span></label>
            <input type="password" class="form-control" name="password" onchange="clearError()" placeholder="Mật khẩu" required="required" />

            <label>Xác nhận mật khẩu <span style="color: red;">*</span></label>
            <input type="password" class="form-control" name="repassword" onchange="clearError()" placeholder="Xác nhận mật khẩu" required="required" />
            
            <c:set var="errorMes" value="${requestScope.ERROR}"/>
            <c:if test="${not empty errorMes}">
                <font color="red" id="registerError" style="display:none;">${errorMes}</font>
            </c:if>
            <c:if test="${empty errorMes}">
                <font color="red" id="registerError" style="display:none;"></font>
            </c:if>
            
            
            <button type="button" name="action" class="btn btn-green" onclick="validateForm()" value="Register">Gửi</button>
            <a href="?p=login"><font color="red" id="loginMes" style="display:none;">Bạn đã đăng ký thành công. Bấm vào đây để đăng nhập.</font></a>
        </form>
    </div>
</div>

<br/>

