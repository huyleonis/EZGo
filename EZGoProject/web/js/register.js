/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var accountsObj;
var saveFile;
var xmlDOM = new ActiveXObject("Microsoft.XMLDOM");
var email = document.registration.email;
var username = document.registration.username;
var password = document.registration.password;
var repassword = document.registration.repassword;
var errorRegister = document.getElementById("registerError");
var loginMes = document.getElementById("loginMes");

function checkrepassword() {
    if (repassword.value != password.value) {
        return false;
    }
    return true;
}

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

function validateForm(evt) {
    var checkRepass = checkrepassword();
    if (!checkRepass) {
        errorRegister.innerHTML = "Lỗi: Mật khẩu xác nhận không hợp lệ.";
        repassword.focus();
        errorRegister.style.display = 'block';

        evt.preventDefault();
        returnToPreviousPage();
        return false;
    }

    if (!accountsObj) { // is null
        evt.preventDefault();

        returnToPreviousPage();
        return false;
    }
    if (accountsObj) { // not null
        xmlDOM.async = false;
        xmlDOM.loadXML(accountsObj);
        if (xmlDOM.parseError.errorCode != 0) {
            alert("Error: " + xmlDoc.parseError.reason);
        } else {
            var ifUsernameExist = searchIfUsernameExist(xmlDOM, username);
            var ifEmailExist = searchIfEmailExist(xmlDOM, email);
            if (ifUsernameExist) {
                errorRegister.innerHTML = 'Lỗi: Tên đăng nhập đã tồn tại.';
                errorRegister.style.display = 'block';

                evt.preventDefault();
                returnToPreviousPage();
                return false;
            }
            if (ifEmailExist) {
                errorRegister.innerHTML = 'Lỗi: Email đã tồn tại.';
                errorRegister.style.display = 'block';

                evt.preventDefault();
                returnToPreviousPage();
                return false;
            }
        }
    }
    writeXML();
    loginMes.style.display = 'block';
    return true;
}

function writeXML()
{
    if (saveFile != null) {
        var fso = new ActiveXObject("Scripting.FileSystemObject");
        var file = fso.OpenTextFile(saveFile, 8, true);
        file.WriteLine('<?xml version="1.0" encoding="utf-8"?>' +
                '<account>' +
                '<username>' + username + '</username>' +
                '<password>' + password + '</password>' +
                '<email>' + email + '</email>' +
                '</account>');
        file.Close();
    } else {
        alert(saveFile);
    }

} 