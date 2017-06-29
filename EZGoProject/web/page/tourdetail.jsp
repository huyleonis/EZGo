<%-- 
    Document   : tour detail page
    Created on : Jun 10, 2017, 11:44:23 PM
    Author     : Huy Leonis
    Description: This is the tour detail page page.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="view/logo_simple.jsp" charEncoding="utf-8"/>
<br/> 

<h1 style="text-align: center;">-- Thông tin chi tiết Tour --</h1>
<div class="container">    
    <div class="tour-info">
        <div class="tour-thumbnail">
            <img src="../img/da-lat.jpg" alt="Đà Lạt" />
        </div>
        <div class="tour-info-detail">
            <table border="0" cellpadding="3">                
                <tr>
                    <td colspan="2">
                        <h2>Hồ Ba Bể - Thác Bản Giốc – Động Ngườm Ngao -Giá Siêu Tiết Kiệm</h2>
                    </td>
                </tr>
                <tr>
                    <td>Công ty:</td>
                    <td>
                        <strong>
                            <a href="index.jsp?p=agenda&amp;id{agencyId}" class="agency">
                                Saigon Tours Asia
                            </a>
                        </strong>
                    </td>
                </tr>
                <tr>
                    <td>Ngày khởi hành:</td>
                    <td>
                        <strong>28/6/2017</strong>
                    </td>
                </tr>
                <tr>
                    <td>Nơi khởi hành:</td>
                    <td>
                        <strong>Tp. Hồ Chí Minh</strong>
                    </td>
                </tr>
                <tr>
                    <td>Thời gian: </td>
                    <td>4 ngày</td>
                </tr>
                <tr>
                    <td style="vertical-align: top;">Giá mỗi khách:</td>
                    <td>
                        <div class="tour-card-price">
                            <strong style="float: left;">
                                <span class="amount">
                                    12.000.000
                                </span>
                                <span class="curr">
                                    VND
                                </span>
                                <br/>
                                <span class="old-amount">
                                    18.000.000
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
                        <button>
                            <i class="fa fa-download" style="font-size:1.6em;"></i>
                        </button>
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
        <div class="tab-pane tab-active" id="schedule">
            <p><strong>NGÀY 1: HÀ N?I – SEOUL </strong></p><p><strong>22h30: </strong>Xe và hu?ng d?n viên c?a&nbsp;<strong>Công ty</strong>&nbsp;dón quý khách t?i di?m h?n kh?i hành di sân bay N?i Bài.</p><p><strong>01h10:</strong> Ðoàn dáp chuy?n bay di Seoul – Hàn Qu?c. Ngh? dêm trên máy bay.</p><p><strong>NGÀY 2: SEOUL CITY TOUR – B?O TÀNG GREVIN (An sáng, trua, t?i)</strong></p><p>Sau b?a sáng, doàn d?n tham quan <strong>Cung di?n hoàng gia Kyong-bok, b?o tàng dân gian qu?c gia, Nhà Xanh – Ph?</strong> <strong>t?ng th?ng</strong>.</p><p>Dùng b?a trua v?i th?c don Hàn Qu?c.Ði tham quan <strong>làng dân t?c Hanok,</strong> tham dòng su?i <strong>Cheng Gye, </strong>ch?p ?nh luu ni?m t?i qu?ng tru?ng Ghwanghwamun.</p><p>Ðoàn di chuy?n d?n <strong><em>B?o tang Grevin – Noi trung bày tu?ng sáp c?a các ngôi sao n?i ti?ng c?a Hàn Qu?c và th? gi?i</em></strong></p><p>Ðoàn&nbsp; an t?i v?i món th?t l?n nu?ng t?m u?p gia v? Hàn, doàn tham quan ch? <strong>Myeongdong</strong> – khu ch? trung tâm s?m u?t ? Seoul.</p><p>Ðoàn ngh? dêm t?i khách s?n Urban*** ho?c tuong duong</p><p><strong>NGÀY 3: SEOUL – EVERLAND – L? H?I HOA H?NG , MUA S?M (An sáng, trua, t?i)</strong></p><p>Sau b?a sáng, xe dua doàn d?n công viên gi?i trí Everland – dua doàn viên gi?i trí c?a Everland công viên ngoài tr?i x?p th? 5 trong top 10 công viên l?n nh?t th? gi?i v?i vu?n thú Safari v?i nh?ng loài H? tr?ng, g?u B?c c?c kh?ng l?, d?o choi trong vu?n hoa r?c r? s?c màu và nhi?u trò choi m?o hi?m, thú v?.</p><p>Trên du?ng v? Quý khách du?c tham gia vào <strong>L? h?i hoa h?ng Hàn Qu?c</strong><strong> - </strong>&nbsp;<em>vu?n h?ng Bucheon là m?t trong nh?ng vu?n hoa h?ng l?n nh?t và d?p nh?t ? Hàn Qu?c, v?i hàng tri?u dóa h?ng dua nhau khoe s?c vào mùa l? h?i.</em></p><p>Sau dó quý khách tha h? mua s?m t?i c?a hàng M? ph?m và Nhân sâm n?i ti?ng Hàn Qu?c, ki?m tra s?c kh?e mi?n phí b?ng phuong pháp hi?n d?i t?i Trung tâm Tinh d?u thông d?.&nbsp; An t?i v?i món gà h?m sâm truy?n th?ng.</p><p>Ðoàn ngh? dêm t?i khách s?n Urban*** ho?c tuong duong</p><p><strong>NGÀY 4: Ð?O NAMI – &nbsp;H?C LÀM KIM CHI – M?C HANBOOK . (An sáng, trua, t?i)</strong></p><p><strong>Sau b?a sáng,</strong> HDV dua doàn d?n d?o Nami- cách Seoul 63km, n?m bình yên trên m?t sông Cheongpyung tho m?ng. Hòn d?o r?ng 260.000 km2 ph? m?t l?p c? xanh mu?t, cây c?i xanh tuoi xen l?n v?i các lo?i hoa khoe s?c cùng nh?ng cây h?t d? và b?ch duong bao quanh. Hòn d?o nhu m?t b?n hòa ca gi?a cây c?, nh?ng chú dà di?u thong dong, v?t tr?ng mu?t t?m du?i h? th?t thanh bình. Noi dây cung là noi du?c s? d?ng làm phi tru?ng cho b? phim “B?n tình ca mùa dông” lãng m?n.<strong>An trua v?i l?u gà nu?ng.</strong></p><p>Trên du?ng v? Seoul, Quý khách d?ng chân mua sâm n?m linh chi Hàn Qu?c, v?i nhi?u lo?i n?m t? n?m trang tr?i, n?m núi…, thu?c b? tr? gan t? th?o du?c thiên nhiên, c?a hàng mi?n thu?. Ðoàn an t?i l?u Shabu. Ðoàn ngh? dêm t?i khách s?n Urban*** ho?c tuong duong</p><p><strong>NGÀY 5: SEOUL – &nbsp;HÀ N?I </strong></p><p>Sau b?a sáng, doàn di tham quan <strong>tháp truy?n hình Seoul, </strong>m?t trong nh?ng d?a di?m thu hút khách du l?ch nh?t ? th? dô Seoul v?i kho?ng 8.4 lu?t tri?u khách m?i nam.&nbsp;<strong>N Seoul Tower</strong>&nbsp;?n tu?ng v?i các du khách d?n tham tháp là không gian tình yêu d?y ?n tu?ng. Ngoài b?c tu?ng ‘G?ch lát tình yêu’, d?c di?m n?i b?t nh?t t?i dây chính là “? khóa tình nhân”. Các c?p tình nhân treo nh?ng chi?c ? khóa ? kho?ng sân thu?ng n?m ngay l?i vào c?a tháp kèm theo m?t m?u gi?y nh?n nhu là m?t bi?u tu?ng cho tình yêu c?a mình<strong>. </strong></p><p>Sau b?a trua, doàn t? do mua s?m t?i siêu th? mi?n thu?.</p><p>An t?i và dáp chuy?n bay v? Hà n?i lúc EZ (21:00 – 23h45). V? d?n sân bay N?i bài, xe dua doàn v? di?m h?n ban d?u. Chia tay Quý khách. K?t thúc chuong trình. H?n g?p l?i chuy?n di sau.</p><p>&nbsp;</p>
        </div>
        <div class="tab-pane" id="policy">
            <p><strong>GIA´ BAO G?M: </strong></p><p style="margin-left:27.0pt;"><em>- Vé máy bay kh? h?i HAN – TPE - HAN: Hàng không VJ ( 7 kg xách tay + 20kg ký g?i) </em></p><p><em>- Thu? sân bay 2 nu?c + thu? an ninh + phí xang d?u. </em></p><p><em>- Khách s?n 3 sao (tiêu chu?n 1 phòng 2 ngu?i, n?u l? s? ? 3 ngu?i/phòng). </em></p><p><em>- Vé tham quan, an sáng buffet t?i khách s?n + 8 b?a chính ( 10 món) theo chuong trình.</em></p><p><em>- Xe du l?ch máy l?nh d?i m?i su?t chuong trình.</em></p><p><em>- Hu?ng D?n Viên su?t tuy?n cho doàn 15 khách và HDV b?n d?a cho doàn du?i 15 khách</em></p><p><em>- B?o hi?m du l?ch; </em></p><p><em>- Visa du l?ch Ðài Loan.</em></p><p><strong>KHÔNG BAO G?M</strong></p><p><em>- H? chi?u (còn th?i h?n trên 6 tháng so v?i ngày kh?i hành + 2 ?nh 4 x 6 cm).</em></p><p><em>- Ph? thu phòng don.</em></p><p><em>- Chi phí cá nhân, di?n tho?i, an u?ng, v?n chuy?n ngoài chuong trình.</em></p><p><em>- Hành lý quá cu?c.</em></p><p><em>- Phi´ ta´ch doa`n: 50 USD/Kha´ch/Nga`y</em></p><p><em>- Phí xin Visa tái nh?p Vi?t Nam (d?i v?i khách ngo?i ki?u).</em></p><p><em>- Phí ph?c v? cho Hu?ng d?n viên và Tài x? su?t chuong trình: 5usd/khách/ngày.</em></p><p style="margin-left:.5in;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><p align="center" style="margin-left:.5in;"><strong>TH? T?C XIN VISA ÐÀI LOAN</strong></p><p>H? chi?u còn hi?u l?c trên 6 tháng và 02 ?nh 4 x6 cm n?n tr?ng, ch?p m?i nh?t không gi?ng h? chi?u.</p><p><strong><em>Tr? em di cùng:</em></strong></p><p>*Ð?i v?i con ru?t: Gi?y khai sinh ho?c s? h? kh?u</p><p>*Ð?i v?i tr? em là cháu, không ph?i là con ru?t: gi?y khai sinh c?a bé, ch?ng minh m?i quan h? v?i ngu?i d?n di ( khai sinh ho?c s? h? kh?u) và ch?ng minh tài chính, công vi?c c?a b? m? ru?t + Gi?y ?y quy?n c?a c? 2 b? m?.</p><p>*Ð?i v?i khách dã có visa: M?, Anh, Úc, Nh?t, Hàn, Canada và các kh?i Châu Âu thì Ch? c?n h? chi?u.</p><p>Th?i gian n?p gi?y t? làm visa Ðài Loan tru?c 15 ngày so v?i ngày kh?i hành. ÐSQ không ch?u trách nhi?m cho s? t? ch?i ho?c ch?m tr? c?p visa do thi?u thông tin trong t? khai và gi?y t? n?p kèm t? ngu?i xin c?p visa.</p><p><strong>THÔNG TIN C?N BI?T:</strong></p><ul><li>Khi k?t thúc tour 1 ngày, Quý khách dã xin visa Ðài Loan, vui lòng dem h? chi?u v? công ty d? h?i báo v?i Van phòng kinh t? van hóa Ðài B?c. (Trình báo xong s? tr? l?i khách hàng, dây là quy d?nh c?a Van phòng kinh t? van hóa Ðài B?c).</li></ul><p style="margin-left:.25in;">&nbsp;</p><p align="center">&nbsp;</p><p>&nbsp;</p>
        </div>
    </div>
</div>



