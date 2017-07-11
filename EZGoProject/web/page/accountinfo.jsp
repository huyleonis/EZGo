<%-- 
    Document   : accountinfo
    Created on : Jun 12, 2017, 8:18:56 PM
    Author     : Dells
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>

<c:set var="account" value="${requestScope.ACCOUNT}" />

<c:import url="view/logo.jsp" charEncoding="utf-8"/>
<hr/>
<br/>
<c:set var="currentTab" value="${requestScope.currentTab}"/>
<c:if test="${empty currentTab}">
    <c:set var="currentTab" value="edit-acc-info"/>
</c:if>
<div class="container" onload="openAccountInfoTab(event, '${currentTab}')">
    <div class="vertical-menu">
        <a href="#" class="tablinks active"
           onclick="openAccountInfoTab(event, 'view-acc-info'); return false;">Thông tin cá nhân</a>
        <a href="#" class="tablinks"
           onclick="openAccountInfoTab(event, 'edit-acc-info'); return false;">Sửa thông tin cá nhân</a>
        <a href="#" class="tablinks"
           onclick="openAccountInfoTab(event, 'change-pass'); return false;">Đổi mật khẩu</a>
        <c:set var="roleID" value="${sessionScope.ROLEID}"/>
        <c:if test="${roleID == '1'}">
            <hr/>
            <a href="#" class="tablinks"
               onclick="openAccountInfoTab(event, 'manage-acc'); return false;">Quản lý tài khoản</a>
            <a href="#" class="tablinks"
               onclick="openAccountInfoTab(event, 'manage-tour'); return false;">Quản lý Tour</a>
            <!--            <a href="#" class="tablinks"
                           onclick="openAccountInfoTab(event, 'manage-agenda'); return false;">Quản lý Công ty du lịch</a>-->
        </c:if>

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
            <c:set var="oldpassword" value="${account.password}"/>
            <form action="process" name="tabchangepass" method="POST">						
                <h3 class="title">Đổi mật khẩu</h3>
                <label>Mật khẩu cũ <span style="color: red;">*</span></label>
                <input type="password" class="form-control" onchange="clearError()" 
                       name="oldpass" required="required" />
                <label>Mật khẩu mới <span style="color: red;">*</span></label>
                <input type="password" class="form-control" onchange="clearError()" 
                       name="newpass" required="required" />
                <label>Xác nhận mật khẩu <span style="color: red;">*</span></label>
                <input type="password" class="form-control" onchange="clearError()" 
                       name="renewpass" required="required" />

                <c:set var="errorMes" value="${requestScope.ERROR}"/>
                <c:if test="${not empty errorMes}">
                    <font color="red" id="registerError" style="display:none;">${errorMes}</font>
                </c:if>
                <c:if test="${empty errorMes}">
                    <font color="red" id="registerError" style="display:none;"></font>
                </c:if>

                <button type="button" class="btn btn-yellow" onclick="checkTabChangePass('${oldpassword}')"
                        name="action" value="changepass">Đổi mật khẩu</button>

            </form>
        </div>

        <c:if test="${roleID == '1'}">
            <!--------------------- Tab manage account ------------------>
            <div class="tabcontent" id="manage-acc" >
                <div class="search-bar">
                    <label><h3> Quản lý tài khoản: </h3></label>
                    <script type="text/javascript" src="../js/search.js">
                    reqObj = '${requestScope.ACCOUNTLIST}'
                    </script>
                    <form>
                        <input type="text" name="kw" placeholder="Tên đăng nhập" class="form-control" style="width:60%; display: inline;">
                        <button type="button" onclick="return searchProcess('table')" 
                                name="action" value="SearchAccount" class="btn btn-default">
                            Tìm kiếm
                        </button>
                    </form>

                    <c:set var="list" value="${requestScope.ACCOUNTLIST}"/>
                    <c:if test="${not empty list}">
                        <c:import charEncoding="UTF-8" url="xslt/accountListView.xsl" var="accountListView" />           
                        <x:transform doc="${list}" xslt="${accountListView}"/>
                    </c:if>

                </div> <!--End search div-->
            </div> <!--End manage account tab div-->    

            
            <!--------------------- Tab manage tour ------------------>
            <div class="tabcontent" id="manage-tour" >
                <div class="search-bar">
                    <label><h3> Quản lý tài tour: </h3></label>
                    <form>
                        <input type="text" name="kw" placeholder="Tên tour" class="form-control" style="width:60%; display: inline;">
                        <button type="button" onclick="return searchProcess('table')" 
                                name="action" value="SearchTour" class="btn btn-default">
                            Tìm kiếm
                        </button>
                    </form>

                    <c:set var="tourList" value="${requestScope.TOURLIST}" />
                    <c:if test="${not empty tourList}">
                        <c:import charEncoding="UTF-8" url="xslt/tourListView.xsl" var="tourListView" />           
                        <x:transform doc="${tourList}" xslt="${tourListView}"/>
                    </c:if>

                </div> <!--End search div-->
            </div> <!--End manage account tab div-->    
        </c:if>

    </div>
</div>
<br/>