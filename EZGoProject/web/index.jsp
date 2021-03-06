<%-- 
    Document   : index
    Created on : May 21, 2017, 4:08:24 PM
    Author     : Huye Leonis
    Description: This is the template for website.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
    <head>  
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <meta charset="utf-8"/>
        <link rel="stylesheet" href="css/main.css"/>  
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
        <script type="text/javascript" src="js/main.js"></script>
        <script type="text/javascript" src="js/register.js"></script>
        <title>EZGo | Easy Go, Easy Travel, Easy Discover the World</title>
        <link href="img/logo.png" rel="shortcut icon" type="image/x-icon" />
    </head>
    <body onload="initial()" onbeforeunload="destroy()">
        <c:import url="view/navigator.jsp" charEncoding="utf-8"/>
        <c:set value="${param.p}" var="page"/>
        <c:choose>
            <c:when test="${page == 'account'}">
                <c:import url="page/accountinfo.jsp" charEncoding="utf-8" />
            </c:when>
            <c:when test="${page == 'login'}">
                <c:import url="page/login.jsp" charEncoding="utf-8" />
            </c:when>
            <c:when test="${page == 'register'}">
                <c:import url="page/register.jsp" charEncoding="utf-8" />
            </c:when>
            <c:when test="${page == 'tourDetail'}">
                <c:import url="page/tourdetail.jsp" charEncoding="utf-8" />
            </c:when>
            <c:when test="${page == 'domestic'}">                
                <c:import url="page/tourRegion.jsp" charEncoding="utf-8">
                    <c:param name="region" value="1"/>
                </c:import>
            </c:when>
            <c:when test="${page == 'abroad'}">
                <c:import url="page/tourRegion.jsp" charEncoding="utf-8">
                    <c:param name="region" value="2"/>
                </c:import>
            </c:when>
            <c:otherwise>
                <c:import url="page/index.jsp" charEncoding="utf-8" />
            </c:otherwise>
        </c:choose>        
        
        <c:import url="view/footer.jsp" charEncoding="utf-8"/>
        <img src="img/star1.png" style="display: none;"/>
        <c:if test="${not empty sessionScope.ACCOUNT_ID}">
            <input type="hidden" name="accountId" id="accountId" value="${sessionScope.ACCOUNT_ID}" />
        </c:if>
    </body>
</html>
