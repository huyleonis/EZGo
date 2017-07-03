<%-- 
    Document   : tour detail page
    Created on : Jun 10, 2017, 11:44:23 PM
    Author     : Huy Leonis
    Description: This is the tour detail page page.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:import url="view/logo_simple.jsp" charEncoding="utf-8"/>
<c:set var="tour" value="${requestScope.TOUR}"/>
<hr/> 

<h1 style="text-align: center;">~ Thông tin chi tiết Tour ~</h1>
<div class="container">    
    <div class="tour-info">
        <div class="tour-thumbnail">
            <img src="${tour.picture}" alt="${tour.name}" />
        </div>
        <div class="tour-info-detail">
            <table border="0" cellpadding="3">                
                <tr>
                    <td colspan="2">
                        <h2>${tour.name}</h2>
                    </td>
                </tr>
                <tr>
                    <td>Công ty:</td>
                    <td>
                        <strong>
                            <a href="index.jsp?p=agenda&amp;id=${tour.agendaID.agendaID}" class="agency">
                                ${tour.agendaID.name}
                            </a>
                        </strong>
                    </td>
                </tr>
                <tr>
                    <td>Ngày khởi hành:</td>
                    <td>
                        <strong>
                            <fmt:formatDate value="${tour.departureDay}" pattern="d / M / yyyy"/>
                        </strong>
                    </td>
                </tr>
                <tr>
                    <td>Nơi khởi hành:</td>
                    <td>
                        <strong>${tour.departure}</strong>
                    </td>
                </tr>
                <tr>
                    <td>Thời gian: </td>
                    <td>${tour.duration}</td>
                </tr>
                <tr>
                    <td style="vertical-align: top;">Giá mỗi khách:</td>
                    <td>
                        <div class="tour-card-price" style="display: inline;">
                            <strong style="float: left;">
                                <span class="amount">
                                    <fmt:formatNumber value="${tour.price}" groupingUsed="true" type="number"/>                                    
                                </span>
                                <span class="curr">
                                    ${tour.currency}
                                </span>
                                <br/>
                                <span class="old-amount">
                                    <fmt:formatNumber value="${tour.oldPrice}" groupingUsed="true" type="number"/>
                                </span>
                            </strong>
                        </div>
                    </td>
                </tr>    
                <tr>
                    <td>
                        Tải chương trình tour:
                    </td>
                    <td>
                        <button onclick="downloadSchedule(${tour.tourID})">
                            <i class="fa fa-download" style="font-size:1.6em;"></i>
                        </button>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <a href="${tour.link}" class="btn btn-orange" target="_blank">                            
                            Đặt Tour
                        </a>
                    </td>
                </tr>
            </table>
        </div>
    </div>    
</div>

<div class="container">    
    <div class="container" style="display: block; margin-top: 20px;">
        <div class="tab-button">
            <button class="tab-button-active" id="button-schedule" onclick="switchTab('schedule')">Lịch trình tour</button>
            <button id="button-policy" onclick="switchTab('policy')">Điều khoản tour</button>
        </div>
        <br/>
        <div class="tab-pane tab-active" id="schedule">
            ${tour.schedule}
        </div>
        <div class="tab-pane" id="policy">
            ${tour.policy}
        </div>
    </div>
</div>
<br/>


