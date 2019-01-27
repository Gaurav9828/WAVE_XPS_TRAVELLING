function validateAddNewVehicle() {
	var company = document.forms["newVehicleAdditionForm"]["vehicleCompany"].value;
	if (company == null || company == "") {
		document.getElementById("errorMsg").innerHTML = "Please enter company name of vehicle..";
		return false;
	}

	var model = document.forms["newVehicleAdditionForm"]["vehicleModel"].value;
	if (model == null || model == "") {
		document.getElementById("errorMsg").innerHTML = "Please enter vehicle model ..";
		return false;
	}
	
	var seat = document.forms["newVehicleAdditionForm"]["seats"].value;
	if (seat == null || seat == "") {
		document.getElementById("errorMsg").innerHTML = "Please enter number of seats ..";
		return false;
	}
	
	var image = document.forms["newVehicleAdditionForm"]["image"].value;
	if (image == null || image == "") {
		document.getElementById("errorMsg").innerHTML = "Please select image of the vehicle ..";
		return false;
	} else {
		return loadPage();
	}

	return true;
}

function validateAdminCreationForm(){
	var numExp = /^[0-9]+$/;
	var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	
	var firstName = document.forms["adminCreateForm"]["firstName"].value;
	if (firstName == null || firstName == "") {
		document.getElementById("errorMsg").innerHTML = "Please enter first name ..";
		return false;
	}
	var lastName = document.forms["adminCreateForm"]["lastName"].value;
	if (lastName == null || lastName == "") {
		document.getElementById("errorMsg").innerHTML = "Please enter last name ..";
		return false;
	}
	var mobileNumber = document.forms["adminCreateForm"]["mobileNumber"].value;
	if (mobileNumber == null || mobileNumber == "") {
		document.getElementById("errorMsg").innerHTML = "Please enter mobile number ..";
		return false;
	}
	if (!mobileNumber.match(numExp)) {
		document.getElementById("errorMsg").innerHTML = "Mobile number should be a numeric value ..";
		return false;
	}
	if (mobileNumber.length < 10) {
		document.getElementById("errorMsg").innerHTML = "Mobile number should be of 10 digit ..";
		return false;
	}
	var emailId = document.forms["adminCreateForm"]["emailId"].value;
	if (emailId == null || emailId == "") {
		document.getElementById("errorMsg").innerHTML = "Please enter your email id ..";
		return false;
	}
	if (!emailId.match(mailformat)) {
		document.getElementById("errorMsg").innerHTML = "Please enter a valid email id ..";
		return false;
	}
	else {
		return loadPage();
	}
	return true;
}

function validateAddVehicleForm(){
	var company = document.forms["addVehicleForm"]["vehicleCompany"].value;
	if (company == null || company == "") {
		document.getElementById("errorMsg").innerHTML = "Please select vehicle company..";
		return false;
	}

	var model = document.forms["addVehicleForm"]["vehicleModel"].value;
	if (model == null || model == "") {
		document.getElementById("errorMsg").innerHTML = "Please select vehicle model..";
		return false;
	}
	
	var vehicleNumber = document.forms["addVehicleForm"]["vehicleNumber"].value;
	if (vehicleNumber == null || vehicleNumber == "") {
		document.getElementById("errorMsg").innerHTML = "Please enter vehicle number..";
		return false;
	}else {
		return loadPage();
	}

	return true;
	
}

function validateEditProfile(){
	var numExp = /^[0-9]+$/;
	var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	
	var firstName = document.forms["editProfileForm"]["firstName"].value;
	if (firstName == null || firstName == "") {
		document.getElementById("errorMsg").innerHTML = "Please enter first name ..";
		return false;
	}
	var lastName = document.forms["editProfileForm"]["lastName"].value;
	if (lastName == null || lastName == "") {
		document.getElementById("errorMsg").innerHTML = "Please enter last name ..";
		return false;
	}
	var mobileNumber = document.forms["editProfileForm"]["mobileNumber"].value;
	if (mobileNumber == null || mobileNumber == "") {
		document.getElementById("errorMsg").innerHTML = "Please enter mobile number ..";
		return false;
	}
	if (!mobileNumber.match(numExp)) {
		document.getElementById("errorMsg").innerHTML = "Mobile number should be a numeric value ..";
		return false;
	}
	if (mobileNumber.length < 10) {
		document.getElementById("errorMsg").innerHTML = "Mobile number should be of 10 digit ..";
		return false;
	}
	var emailId = document.forms["editProfileForm"]["emailId"].value;
	if (emailId == null || emailId == "") {
		document.getElementById("errorMsg").innerHTML = "Please enter your email id ..";
		return false;
	}
	if (!emailId.match(mailformat)) {
		document.getElementById("errorMsg").innerHTML = "Please enter a valid email id ..";
		return false;
	}
	
	var password = document.forms["editProfileForm"]["password"].value;
	if (password == null || password == "") {
		document.getElementById("errorMsg").innerHTML = "Password section can't be empty..";
		return false;
	}
	var pass2 = document.forms["editProfileForm"]["pass2"].value;
	if (pass2 == null || pass2 == "") {
		document.getElementById("errorMsg").innerHTML = "Please enter your current password..";
		return false;
	}
	else {
		return loadPage();
	}
	return true;
}