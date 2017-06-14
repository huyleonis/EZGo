
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