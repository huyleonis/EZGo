/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function sendRegisterForm(){
    var email = document.registration.email;
    var username = document.registration.username;
    var password = document.registration.password;
    
    
    
}

function checkrepassword() {
    var password = document.registration.password;
    var errorRegister = document.getElementById("registerError");
    var repassword = document.registration.repassword;
    
    if (repassword.value != password.value) {
        return false;
    }
    return true;
}

var accountsObj;
var xmlDOM = new ActiveXObject("Microsoft.XMLDOM");
var count = 0;

function searchIfUsernameExist(node, strSearch) {
    if (node == null) {
        return;
    }
    if (node.tagName == "username") {
        var tmp = node.firstChild.nodeValue;
        if (tmp.indexOf(strSearch, 0) > -1) {
            return true;
        }
        var childs = node.childNodes;
        for (var i = 0; i < childs.length; i++) {
            searchIfUsernameExist(childs[i], strSearch);
        }
        return false;
    }
}

function searchIfEmailExist(node, strSearch) {
    if (node == null) {
        return;
    }
    if (node.tagName == "email") {
        var tmp = node.firstChild.nodeValue;
        if (tmp.indexOf(strSearch, 0) > -1) {
            return true;
        }
        var childs = node.childNodes;
        for (var i = 0; i < childs.length; i++) {
            searchIfEmailExist(childs[i], strSearch);
        }
        return false;
    }
}

function validateForm(){
    var errorRegister = document.getElementById("registerError");
    var repassword = document.registration.repassword;
    alert(accountsObj);
    
    var checkRepass = checkrepassword();
    if(!checkRepass){
        errorRegister.innerHTML = "Lỗi: Mật khẩu xác nhận không hợp lệ.";
        repassword.focus();
        errorRegister.style.display = 'block';
        return false;
    }
    
    if (!accountsObj) { // is null
        return false;
    }
    if (accountsObj) { // not null
        xmlDOM.async = false;
        xmlDOM.loadXML(accountsObj);
        if (xmlDOM.parseError.errorCode != 0) {
            alert("Error: " + xmlDoc.parseError.reason);
        } else { 
            var ifUsernameExist = searchIfUsernameExist(xmlDOM, document.registration.username.value);
            var ifEmailExist = searchIfEmailExist(xmlDOM, document.registration.email.value);
            if(ifUsernameExist || ifEmailExist){
                errorRegister.innerHTML = 'Lỗi: Tên đăng nhập đã tổn tại.';
                errorRegister.style.display = 'block';
                return false;
            } 
        }
    }
    return true;
}