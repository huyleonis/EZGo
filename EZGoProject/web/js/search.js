/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var accountsObj;
var xmlDOM = new ActiveXObject("Microsoft.XMLDOM");
var count = 0;
var cells = [];

function addRow(tableID, cells) {
    var tableEle = document.getElementById(tableID);
    var newRow = tableEle.insertRow(tableEle.rows.length);
    var newCell;
    for (var i = 0; i < cells.length; i++) {
        newCell = newRow.insertCell(newRow.cells.length);
        newCell.innerHTML = cells[i];
    }
    return newRow;
}

function deleteRow(tableID, rowNumber) {
    var tableEle = document.getElementById(tableID);
    if (rowNumber > 0 && rowNumber < tableEle.rows.length) {
        tableEle.deleteRow(rowNumber);
    } else {
        alert("Failed");
    }
}

function searchNode(node, strSearch, tableName) {
    if (node == null) {
        return;
    }
    if (node.tagName == "username") {
        var tmp = node.firstChild.nodeValue;
        if (tmp.indexOf(strSearch, 0) > -1) {
            count++;
            cells[0] = count;
            cells[1] = node.firstChild.nodeValue; // username
            var sibling = node.nextSibling;
            cells[2] = sibling.firstChild.nodeValue; // password
            var sibling = sibling.nextSibling;
            cells[3] = sibling.firstChild.nodeValue; // lastname
            var sibling = sibling.nextSibling;
            cells[4] = sibling.firstChild.nodeValue; // roles
            var sibling = sibling.nextSibling;
            addRow(tableName, cells);
        }
        var childs = node.childNodes;
        for (var i = 0; i < childs.length; i++) {
            searchNode(childs[i], strSearch, tableName);
        }
    }
}

function searchProcess(tableName) {
    alert(accountsObj);
    if (!accountsObj) { // is null
        return false;
    }
    if (accountsObj) { // not null
        xmlDOM.async = false;
        xmlDOM.loadXML(accountsObj);
        if (xmlDOM.parseError.errorCode != 0) {
            alert("Error: " + xmlDoc.parseError.reason);
        } else {
            // delete all table
            var tableEle = document.getElementById(tableName);
            var i = 1;
            while (i < tableEle.rows.length) {
                deleteRow(tableName, i);
            }
            count = 0;
            // parsing
            searchNode(xmlDOM, myForm.txtName.value, tableName);
        }
    }
}