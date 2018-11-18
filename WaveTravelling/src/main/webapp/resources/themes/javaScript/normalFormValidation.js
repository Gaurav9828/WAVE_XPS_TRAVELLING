function validateAddNewVechile() {
	var company = document.forms["newVechileAdditionForm"]["vechileCompany"].value;
	if (company == null || company == "") {
		document.getElementById("errorMsg").innerHTML = "Please enter company name of vechile..";
		return false;
	}

	var model = document.forms["newVechileAdditionForm"]["vechileModel"].value;
	if (model == null || model == "") {
		document.getElementById("errorMsg").innerHTML = "Please enter vechile model ..";
		return false;
	}
	
	var seat = document.forms["newVechileAdditionForm"]["seats"].value;
	if (seat == null || seat == "") {
		document.getElementById("errorMsg").innerHTML = "Please enter number of seats ..";
		return false;
	}
	
	var image = document.forms["newVechileAdditionForm"]["image"].value;
	if (image == null || image == "") {
		document.getElementById("errorMsg").innerHTML = "Please select image of the vechile ..";
		return false;
	} else {
		return loadPage();
	}

	return true;
}