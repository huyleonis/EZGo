<%-- 
    Document   : accountinfo
    Created on : Jun 12, 2017, 8:18:56 PM
    Author     : Dells
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="account" value="${requestScope.ACCOUNT}" />

<c:import url="view/logo.jsp" charEncoding="utf-8"/>
<hr/>
<br/>
<div class="container">
    <div class="vertical-menu">
        <a href="#" class="tablinks active"
           onclick="openAccountInfoTab(event, 'view-acc-info'); return false;">Thông tin cá nhân</a>
        <a href="#" class="tablinks"
           onclick="openAccountInfoTab(event, 'edit-acc-info'); return false;">Sửa thông tin cá nhân</a>
        <a href="#" class="tablinks"
           onclick="openAccountInfoTab(event, 'change-pass'); return false;">Đổi mật khẩu</a>
    </div>

    <div class="profile-info login-card">            
        <!--------------------- Tab account info ---------------------->
        <div id="view-acc-info" class="tabcontent">
            <h3 class="title">Thông tin cơ bản</h3>
            <div class="basic-info">
                <dl>
                    <dt>Tên tài khoản</dt>
                    <dd>${account.username}</dd>
                </dl>
                <dl>    
                    <dt>Họ tên</dt>
                    <dd>${account.fullname}</dd>
                </dl>
                <dl>
                    <dt>Ngày sinh</dt>
                    <dd>
                        <fmt:formatDate value="${account.birthday}" 
                                        pattern="dd/MM/yyyy" var="formattedDate"/>
                        ${formattedDate}
                    </dd>
                </dl>
                
            </div>

            <h3 class="title">Thông tin liên lạc</h3>
            <div class="contact-info">
                <dl>
                    <dt>Di động</dt>
                    <dd>${account.phone}</dd>
                </dl>
                <dl>
                    <dt>Email</dt>
                    <dd>${account.email}</dd>
                </dl>
                <dl>
                    <dt>Địa chỉ hiện tại</dt>
                    <dd>${account.address}</dd>
                </dl>
            </div>
        </div>


        <!--------------------- Tab edit account info ---------------->
        <div id="edit-acc-info" class="tabcontent">
            <form action="" method="POST">						
                <h3 class="title">Chỉnh sửa thông tin</h3>
                <label>Ngày sinh <span style="color: red;">*</span></label>
                <input type="date" class="form-control" 
                       name="birthday" required="required" />
                <label>Tiểu sử <span style="color: red;">*</span></label>
                <textarea class="form-control" name="description" rows="10" 
                          cols="50" placeholder="Tiểu sử"></textarea>
                <label>Di động <span style="color: red;">*</span></label>
                <input type="number" class="form-control" name="phone" 
                       placeholder="Số di động" required="required" />
                <label>Email <span style="color: red;">*</span></label>
                <input type="email" class="form-control" name="email" 
                       placeholder="Email" required="required" />
            </form>
        </div>

        <!--------------------- Tab change password ------------------>
        <div id="change-pass" class="tabcontent">
            <form action="" method="POST">						
                <h3 class="title">Đổi mật khẩu</h3>
                <label>Mật khẩu cũ <span style="color: red;">*</span></label>
                <input type="password" class="form-control" 
                       name="oldpass" required="required" />
                <label>Mật khẩu mới <span style="color: red;">*</span></label>
                <input type="password" class="form-control" 
                       name="newpass" required="required" />
                <label>Xác nhận mật khẩu <span style="color: red;">*</span></label>
                <input type="password" class="form-control" 
                       name="newpass" required="required" />
                <button type="submit" class="btn btn-yellow" 
                        name="action" value="changepass">Đổi mật khẩu</button>
            </form>
        </div>
    </div>
</div>
<br/>