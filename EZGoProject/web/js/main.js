var favXml;
var favRoot;

var serializer = new XMLSerializer(); //use to serialize DOM Object to XML String
var accId;

/**
 * Function will be executed first when the list tour div is load
 * @returns {undefined}
 */
function initial() {    
    if (document.getElementById("list_tours") !== null) {
        createFavXml();

        var accIdInput = document.getElementById("accountId");
        if (accIdInput !== null) {                        
            setInterval(saveFavorite, 1000 * 60 * 60);
        }
    }
}

function createFavXml() {
    favXml = document.implementation.createDocument(null, "favorites");
    favRoot = favXml.getElementsByTagName("favorites")[0];
    var accIdInput = document.getElementById("accountId");
    if (accIdInput !== null) {
        accId = accIdInput.value;        
        
        var idNode = favXml.createElement("account-id");
        var idText = favXml.createTextNode(accId);
        idNode.appendChild(idText);
        favRoot.appendChild(idNode);                
    }
}

/**
 * Function will be executed when page unload
 * @returns {undefined}
 */
function destroy() {    
    if (document.getElementById("list_tours") !== null) {
        saveFavorite();
    }    
}

function saveFavorite() {    
    var request = getXMLRequest();
    if (request === null) {
        alert("Trình duyệt không hỗ trợ tính năng \"Lưu danh sách yêu thích\".\nHãy thay đổi trình duyệt và thực hiện lại.");
        return;
    }
    
    request.open("POST","process", true);
    request.onreadystatechange = function() {
        if (request.readyState === 4) {            
            createFavXml();                        
        }
    }
    
    var xmlString = serializer.serializeToString(favXml);
    alert(xmlString);
    xmlString = '<?xml version="1.0" encoding="UTF-8"?>' + xmlString;
    
    request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    var url = "action=SaveFavorite&xml=" + xmlString;
    
    request.send(url);
    
}

function getXMLRequest() {
    var request;    
    try { // Firefox, Safari, Opera
        request = new XMLHttpRequest();
    } catch (e) { //IE
        try {
            request = new ActiveXObject("Msxml2.XMLHTTP");
        } catch (e) {
            request = new ActiveXObject("Microsoft.XMLHTTP");
        }
    }
    return request;

}

/**
* Function used to open collapsed menu, when browser width less than 700px
* This function is used to toggle add/remove class .responsive to div.top-menu
*/
function openMenu() {
	var menu = document.getElementById("topMenu");

	if (menu.className === "top-menu") {
		menu.className += " responsive";
	} else {
		menu.className = "top-menu";
	}
}


/**
* Function used to toggle opening/closing filter bar
*/
function openFilter() {
	var filter = document.getElementById("filterContent");

	if (filter.className === "filter-content") {
		filter.className += " filter-active";
	} else {
		filter.className = "filter-content";
	}
}

function changePrice(val) {
	var maxPrice = document.getElementById("maxPrice");

	val = val/1000000;
	debugger
	maxPrice.innerHTML = val;
}

function switchTab(tab) {
    var btnPolicy = document.getElementById("button-policy");
    var btnSchedule = document.getElementById("button-schedule");
    var tabPolicy = document.getElementById("policy");
    var tabSchedule = document.getElementById("schedule");
    
    if (tab === "policy") {
        btnPolicy.className = "tab-button-active";
        tabPolicy.className = "tab-pane tab-active";
        btnSchedule.className = "";
        tabSchedule.className = "tab-pane";
    } else {
        btnSchedule.className = "tab-button-active";
        tabSchedule.className = "tab-pane tab-active";
        btnPolicy.className = "";
        tabPolicy.className = "tab-pane";
    }
}

//--------------------------------THULNN-------------------------------------
function openAccountInfoTab(evt, tabName) {
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.getElementById(tabName).style.display = "block";
    evt.currentTarget.className += " active";
}

function toggleFavorite(id, img) {
    
    if (img.getAttribute("status") === "Off") {
        //toggle On
        
        //create <action> text node
        var action = favXml.createElement("action");
        var actionText = favXml.createTextNode("add");
        action.appendChild(actionText);
        
        //create <tour-id> text node
        var tourIdNode = favXml.createElement("tour-id");
        var tourIdText = favXml.createTextNode(id);
        tourIdNode.appendChild(tourIdText);
        
        //create <favorite> contain node
        var favNode = favXml.createElement("favorite");
        favNode.appendChild(action);
        favNode.appendChild(tourIdNode);
        
        // append to root
        favRoot.appendChild(favNode);
        
        
        img.style.opacity = 1;
        img.src = "img/star1.png";
        img.setAttribute("status", "On");
    } else {
        //toggle Off
        
        //create <action> text node
        var action = favXml.createElement("action");
        var actionText = favXml.createTextNode("remove");
        action.appendChild(actionText);
        
        //create <tour-id> text node
        var tourIdNode = favXml.createElement("tour-id");
        var tourIdText = favXml.createTextNode(id);
        tourIdNode.appendChild(tourIdText);
        
        //create <favorite> contain node
        var favNode = favXml.createElement("favorite");
        favNode.appendChild(action);
        favNode.appendChild(tourIdNode);
        
        // append to root
        favRoot.appendChild(favNode);
        
        img.style.opacity = 0.5;
        img.src = "img/star.png";
        img.setAttribute("status", "Off");
    }
    
    alert(serializer.serializeToString(favXml));
}