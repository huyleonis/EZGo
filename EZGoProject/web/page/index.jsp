<%-- 
    Document   : index
    Created on : Jun 10, 2017, 11:44:23 PM
    Author     : Huy Leonis
    Description: This is the index page as well as default page for website.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>

<c:import url="view/logo_simple.jsp" charEncoding="utf-8"/>
<c:import url="view/slide.jsp" charEncoding="utf-8"/>

<br/> 

<div class="container">
    <div class="search-bar">
        <label><h3> Tìm kiếm Tour: </h3></label>
        <form>
            <input type="text" name="kw" placeholder="Nhập từ khóa..." class="form-control" style="width:60%; display: inline;">
            <select name="category">
                <option>Tìm kiếm theo...</option>
                <option value="tour">Tour</option>
                <option value="destination">Điểm đến</option>
                <option value="agenda">Công ty cung cấp</option>
            </select>
            <button type="submit" name="action" value="Search" class="btn btn-default">
                Tìm kiếm
            </button>
        </form>
    </div>
</div>
<div class="container">
    <div class="filter">
        <h4 style="cursor: pointer;" onclick="openFilter()"> Tìm kiếm nâng cao &#x25BD;	</h4>

        <div class="filter-content" id="filterContent">

            <div class="filter-price">
                <h5>Mức giá</h5>				
                <input type="range" min="0" max="40000000" step="500000" value="5000000" ondrag="changePrice(value)">	
                <br/>			
                <span style="float: left; font-size: 0.7em;">0</span>
                <span style="float: right; font-size: 0.7em;" id="maxPrice">5</span>
            </div>
            <hr/>
            <div class="filter-area">
                <h5>Khu vực</h5>
                <table border="0">
                    <tr>
                        <td>
                            <input type="checkbox" name="ckbArea"  id="asean" value="asean"> <label for="asean">Đông Nam Á </label>
                        </td>
                        <td>
                            <input type="checkbox" name="ckbArea"  id="asia" value="asia"> <label for="asia"> Châu Á</label>		
                        </td>
                        <td>
                            <input type="checkbox" name="ckbArea"  id="europe" value="europe"> <label for="europe"> Châu Âu </label>			
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="checkbox" name="ckbArea"  id="american" value="american"> <label for="american"> Châu Mỹ</label>	
                        </td>
                        <td>
                            <input type="checkbox" name="ckbArea"  id="vietnam" value="vietnam"> <label for="vietnam"> Việt Nam</label>
                        </td>
                        <td>
                            <input type="checkbox" name="ckbArea"  id="other" value="other"> <label for="other"> Khu vực khác</label>
                        </td>
                    </tr>
                </table>								
            </div>
            <hr/>
            <div class="filter-time">
                <h5>Thời gian: </h5>
                <label>Từ: &nbsp</label>
                <input type="date" name="dateBegin"/>
                <br/>
                <label>Đến: </label>
                <input type="date" name="dateEnd"/>
            </div>
            <hr/>
            <div class="filter-apply">
                <button class="btn btn-default">Áp dụng</button>
            </div>
        </div>
    </div>
</div>


<h1 style="text-align: center; font-size: 2.5em;">Các tour nổi bật</h1>
<div id="list_tours">
    <c:set var="list" value="${sessionScope.LIST_TOUR}"/>
    <c:set var="favList" value="${sessionScope.LIST_FAVORITE}"/>
    
    <c:if test="${not empty list}">
        <c:import charEncoding="utf-8" url="xslt/tourCard.xsl" var="tourCard" />           
        <x:transform doc="${list}" xslt="${tourCard}">
            <x:param name="accId" value="${sessionScope.ACCOUNT_ID}"/>
            <x:param name="favList" value="${favList}"/>            
        </x:transform>
    </c:if>
</div>


<%--
<div class="container tour-view">
    <div class="tour-card">
        <img src="img/thai-lan.jpg" alt="Thái Lan" width="100%">
        <div class="tour-card-content">
            <a href=""><h2>Thái Lan 4N3Đ</h2></a>
            <span class="departure">Khởi hành từ Tp. Hồ Chí Minh</span>
            <hr/>
            <div class="tour-card-price">
                Giá 1 khách:  
                <strong>
                    <span class="amount">9.999.000</span><span class="curr">đ</span>
                    <br/>
                    <span class="old-amount">13.000.000đ</span>
                </strong>
            </div>
            <hr/>
            <div class="tour-card-descripiton">
                <p>
                    Cung cấp bởi: <a href="" class="agency">Asian Tourist</a>
                </p>	
            </div>						
            <button class="btn btn-red">
                Xem chi tiết
            </button>
        </div>
    </div>

    <div class="tour-card">
        <img src="img/da-lat.jpg" alt="Thái Lan" width="100%">
        <div class="tour-card-content">
            <a href=""><h2>Đà Lạt 3N2Đ</h2></a>
            <span class="departure">Khởi hành từ Tp. Hồ Chí Minh</span>
            <hr/>
            <div class="tour-card-price">
                Giá 1 khách:  
                <strong>
                    <span class="amount">2.999.000</span><span class="curr">đ</span>
                    <br/>
                    <span class="old-amount">5.000.000đ</span>
                </strong>
            </div>
            <hr/>
            <div class="tour-card-descripiton">
                <p>
                    Cung cấp bởi: <a href="" class="agency">Vietnam Tourist</a>
                </p>	
            </div>			
            <button class="btn btn-red">
                Xem chi tiết
            </button>
        </div>
    </div>

    <div class="tour-card">
        <img src="img/hong-kong.jpg" alt="Thái Lan" width="100%">
        <div class="tour-card-content">
            <a href=""><h2>Hong Kong 5N4Đ</h2></a>
            <span class="departure">Khởi hành từ Hà Nội</span>
            <hr/>
            <div class="tour-card-price">
                Giá 1 khách:  
                <strong>
                    <span class="amount">15.999.000</span><span class="curr">đ</span>
                    <br/>
                    <span class="old-amount">19.000.000đ</span>
                </strong>
            </div>
            <hr/>
            <div class="tour-card-descripiton">
                <p>
                    Cung cấp bởi: <a href="" class="agency">Asian Tourist</a>
                </p>	
            </div>			
            <button class="btn btn-red">
                Xem chi tiết
            </button>
        </div>
    </div>

    <div class="tour-card">
        <img src="img/nha-trang.jpg" alt="Thái Lan" width="100%">
        <div class="tour-card-content">
            <a href=""><h2>Nha Trang - Phan Thiết - Vũng Tàu 4N3Đ</h2></a>
            <span class="departure">Khởi hành từ Đà Nẵng</span>
            <hr/>
            <div class="tour-card-price">
                Giá 1 khách:  
                <strong>
                    <span class="amount">6.999.000</span><span class="curr">đ</span>
                    <br/>
                    <span class="old-amount">10.000.000đ</span>
                </strong>
            </div>
            <hr/>
            <div class="tour-card-descripiton">
                <p>
                    Cung cấp bởi: <a href="" class="agency">Danang Travels</a>
                </p>	
            </div>			
            <button class="btn btn-red">
                Xem chi tiết
            </button>
        </div>
    </div>

    <div class="tour-card">
        <img src="img/thai-lan.jpg" alt="Thái Lan" width="100%">
        <div class="tour-card-content">
            <a href=""><h2>Thái Lan 4N3Đ</h2></a>
            <span class="departure">Khởi hành từ Tp. Hồ Chí Minh</span>
            <hr/>
            <div class="tour-card-price">
                Giá 1 khách:  
                <strong>
                    <span class="amount">9.999.000</span><span class="curr">đ</span>
                    <br/>
                    <span class="old-amount">13.000.000đ</span>
                </strong>
            </div>
            <hr/>
            <div class="tour-card-descripiton">
                <p>
                    Cung cấp bởi: <a href="" class="agency">Asian Tourist</a>
                </p>	
            </div>						
            <button class="btn btn-red">
                Xem chi tiết
            </button>
        </div>
    </div>

    <div class="tour-card">
        <img src="img/da-lat.jpg" alt="Thái Lan" width="100%">
        <div class="tour-card-content">
            <a href=""><h2>Đà Lạt 3N2Đ</h2></a>
            <span class="departure">Khởi hành từ Tp. Hồ Chí Minh</span>
            <hr/>
            <div class="tour-card-price">
                Giá 1 khách:  
                <strong>
                    <span class="amount">2.999.000</span><span class="curr">đ</span>
                    <br/>
                    <span class="old-amount">5.000.000đ</span>
                </strong>
            </div>
            <hr/>
            <div class="tour-card-descripiton">
                <p>
                    Cung cấp bởi: <a href="" class="agency">Vietnam Tourist</a>
                </p>	
            </div>			
            <button class="btn btn-red">
                Xem chi tiết
            </button>
        </div>
    </div>

    <div class="tour-card">
        <img src="img/hong-kong.jpg" alt="Thái Lan" width="100%">
        <div class="tour-card-content">
            <a href=""><h2>Hong Kong 5N4Đ</h2></a>
            <span class="departure">Khởi hành từ Hà Nội</span>
            <hr/>
            <div class="tour-card-price">
                Giá 1 khách:  
                <strong>
                    <span class="amount">15.999.000</span><span class="curr">đ</span>
                    <br/>
                    <span class="old-amount">19.000.000đ</span>
                </strong>
            </div>
            <hr/>
            <div class="tour-card-descripiton">
                <p>
                    Cung cấp bởi: <a href="" class="agency">Asian Tourist</a>
                </p>	
            </div>			
            <button class="btn btn-red">
                Xem chi tiết
            </button>
        </div>
    </div>

    <div class="tour-card">
        <img src="img/nha-trang.jpg" alt="Thái Lan" width="100%">
        <div class="tour-card-content">
            <a href=""><h2>Nha Trang - Phan Thiết - Vũng Tàu 4N3Đ</h2></a>
            <span class="departure">Khởi hành từ Đà Nẵng</span>
            <hr/>
            <div class="tour-card-price">
                Giá 1 khách:  
                <strong>
                    <span class="amount">6.999.000</span><span class="curr">đ</span>
                    <br/>
                    <span class="old-amount">10.000.000đ</span>
                </strong>
            </div>
            <hr/>
            <div class="tour-card-descripiton">
                <p>
                    Cung cấp bởi: <a href="" class="agency">Danang Travels</a>
                </p>	
            </div>			
            <button class="btn btn-red">
                Xem chi tiết
            </button>
        </div>
    </div>
</div>--%>

<div class="container"></div>