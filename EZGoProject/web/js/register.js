/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var accountsObj;
var saveFile;

function checkrepassword(password, repassword) {
    var errorRegister = document.getElementById("registerError");
    if (repassword.value != password.value) {
        errorRegister.innerHTML = "Lỗi: Mật khẩu xác nhận không hợp lệ.";
        repassword.focus();
        errorRegister.style.display = 'block';
    }
}

function clearError(){
    var errorRegister = document.getElementById("registerError");
    errorRegister.innerHTML = '';
    errorRegister.style.display = "none";
}

function validateForm() {

    var email = document.registration.email;
    var username = document.registration.username;
    var errorRegister = document.getElementById("registerError");
    var password = document.registration.password;
    var repassword = document.registration.repassword;
    
    checkrepassword(password.value, repassword.value);

    var searchEmail = "<email>" + email.value + "</email>";
    var searchUsername = "<username>" + username.value + "</username>";
    if (accountsObj.indexOf(searchEmail) > -1) {
        errorRegister.innerHTML = 'Lỗi: Email này đã được dùng.';
        errorRegister.style.display = 'block';
        email.focus();
    }
    if (accountsObj.indexOf(searchUsername) > -1) {
        errorRegister.innerHTML = 'Lỗi: Tên đăng nhập này đã được dùng.';
        errorRegister.style.display = 'block';
        username.focus();
    }

    if (errorRegister.innerHTML == '') {
//        writeXML();
        document.registration.action = "process?action=Register&" + 
                "email=" + email.value + 
                "&username=" + username.value +
                "&password=" + password.value;
        document.registration.submit();
    }
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

function checkTabChangePass(passCheck){
    var oldpassword = document.tabchangepass.oldpass;
    var password = document.tabchangepass.newpass;
    var repassword = document.tabchangepass.renewpass;
    var errorRegister = document.getElementById("registerError");
    if (password.value != repassword.value) {
        errorRegister.innerHTML = "Lỗi: Mật khẩu xác nhận không hợp lệ.";
        repassword.focus();
        errorRegister.style.display = 'block';
    }
    if (oldpassword.value != passCheck){
        errorRegister.innerHTML = "Lỗi: Mật khẩu hiện tại không hợp lệ.";
        oldpassword.focus();
        errorRegister.style.display = 'block';
    }
    if(oldpassword.value == password.value){
        errorRegister.innerHTML = "Lỗi: Mật khẩu mới không trùng với mật khẩu cũ.";
        oldpassword.focus();
        errorRegister.style.display = 'block';
    }
    if(errorRegister.innerHTML == ''){
        document.tabchangepass.action = "process?action=changepass" + 
                "&oldpass=" + oldpassword.value + "&newpass=" + password.value;
        document.tabchangepass.submit();
    }
}