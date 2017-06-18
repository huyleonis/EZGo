<%-- 
    Document   : summaryagenda
    Created on : Jun 18, 2017, 10:46:51 AM
    Author     : Dells
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="view/logo.jsp" charEncoding="utf-8"/>
<hr/>

<div class="container">
    <div class="search-bar">
        <label><h3> Tìm kiếm Công ty du lịch: </h3></label>
        <form>
            <input type="text" name="kw" placeholder="Nhập từ khóa..." class="form-control" style="width:60%; display: inline;">
            <button type="submit" name="action" value="SearchAgenda" class="btn btn-default">
                Tìm kiếm theo tên
            </button>
        </form>
    </div>
</div>

<div class="container tour-view">
    <div class="summary-agenda-card">
        <img src="img/thai-lan.jpg" alt="Thái Lan" width="100%">
        <div class="agenda-card-content">
            <a href=""><h2>Asian Tourist</h2></a>
            <hr/>					
            <button class="btn btn-red">
                Xem chi tiết
            </button>
        </div>
    </div>

    <div class="summary-agenda-card">
        <img src="img/da-lat.jpg" alt="Thái Lan" width="100%">
        <div class="agenda-card-content">
            <a href=""><h2>Vietnam Tourist</h2></a>
            <hr/>			
            <button class="btn btn-red">
                Xem chi tiết
            </button>
        </div>
    </div>

    <div class="summary-agenda-card">
        <img src="img/hong-kong.jpg" alt="Thái Lan" width="100%">
        <div class="agenda-card-content">
            <a href=""><h2>Vietnam Tourist</h2></a>
            <hr/>		
            <button class="btn btn-red">
                Xem chi tiết
            </button>
        </div>
    </div>

    <div class="summary-agenda-card">
        <img src="img/nha-trang.jpg" alt="Thái Lan" width="100%">
        <div class="agenda-card-content">
            <a href=""><h2>Asian Tourist</h2></a>
            <hr/>			
            <button class="btn btn-red">
                Xem chi tiết
            </button>
        </div>
    </div>

    <div class="summary-agenda-card">
        <img src="img/thai-lan.jpg" alt="Thái Lan" width="100%">
        <div class="agenda-card-content">
            <a href=""><h2>Asian Tourist</h2></a>
            <hr/>						
            <button class="btn btn-red">
                Xem chi tiết
            </button>
        </div>
    </div>

    <div class="summary-agenda-card">
        <img src="img/da-lat.jpg" alt="Thái Lan" width="100%">
        <div class="agenda-card-content">
            <a href=""><h2>Asian Tourist</h2></a>
            <hr/>		
            <button class="btn btn-red">
                Xem chi tiết
            </button>
        </div>
    </div>

    <div class="summary-agenda-card">
        <img src="img/hong-kong.jpg" alt="Thái Lan" width="100%">
        <div class="agenda-card-content">
            <a href=""><h2>Asian Tourist</h2></a>
            <hr/>			
            <button class="btn btn-red">
                Xem chi tiết
            </button>
        </div>
    </div>

    <div class="summary-agenda-card">
        <img src="img/nha-trang.jpg" alt="Thái Lan" width="100%">
        <div class="agenda-card-content">
            <a href=""><h2>Asian Tourist</h2></a>
            <hr/>		
            <button class="btn btn-red">
                Xem chi tiết
            </button>
        </div>
    </div>
</div>
