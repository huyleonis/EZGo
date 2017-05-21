
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


function openRegisterCard() {
	var registerCard = document.getElementById("registerCard");
	var email = document.querySelectorAll(".register-card input[type='email']");

	email[0].focus();

	

	
}