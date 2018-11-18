function validateAdminLoginForm() {
	var id = document.forms["loginForm"]["authValue1"].value;
	if (id == null || id == "") {
		document.getElementById("errorMsg").innerHTML = "Please enter employee id ..";
		return false;
	}

	var pass = document.forms["loginForm"]["authValue2"].value;
	if (pass == null || pass == "") {
		document.getElementById("errorMsg").innerHTML = "Please enter your password ..";
		return false;
	}

	var capp = document.forms["loginForm"]["captcha"].value;
	if (capp == null || capp == "") {
		document.getElementById("errorMsg").innerHTML = "Please enter captcha ..";
		return false;
	}
	var key = document.forms["loginForm"]["keyCaptcha"].value;
	if (key != capp) {
		//$( "#cap" ).load(window.location.href + " #cap>*","" );
		setTimeout(function() {
			window.location.reload(1);
		}, 1500);
		document.getElementById("errorMsg").innerHTML = "Invalid captcha entered ..";
		return false;
	} else {
		return loadPage();
	}

	return true;
}

function adminForceMemorableWordSet() {
	var memorableWord = document.forms["adminForceMemorableWordSetForm"]["authValue1"].value;
	if (memorableWord == null || memorableWord == "") {
		document.getElementById("errorMsg").innerHTML = "Please enter your secret memorable word ..";
		return false;
	}
	var confirm = document.forms["adminForceMemorableWordSetForm"]["authValue2"].value;
	if (confirm == null || confirm == "") {
		document.getElementById("errorMsg").innerHTML = "Please confirm your secret memorable word ..";
		return false;
	}
	if (memorableWord != confirm) {
		document.getElementById("errorMsg").innerHTML = "Please same memorable word ..";
		return false;
	} else {
		return true;
	}
}

function validateAdminPasswordResetForm() {
	var currentPass = document.forms["passwordResetForm"]["authValue1"].value;
	if (currentPass == null || currentPass == "") {
		document.getElementById("errorMsg").innerHTML = "Please enter your current password ..";
		return false;
	}

	var pass = document.forms["passwordResetForm"]["password"].value;
	if (pass == null || pass == "") {
		document.getElementById("errorMsg").innerHTML = "Please enter your new password ..";
		return false;
	}

	var newPass = document.forms["passwordResetForm"]["authValue2"].value;
	if (newPass == null || newPass == "") {
		document.getElementById("errorMsg").innerHTML = "Please confirm your password ..";
		return false;
	}

	if (pass != newPass) {
		document.getElementById("errorMsg").innerHTML = "Please enter same password ..";
		return false;
	} else {
		return loadPage();
	}
	return true;
}

function validateAdminMemorableWordPasswordResetForm() {
	var id = document.forms["memorableWordPasswordResetForm"]["authValue1"].value;
	if (id == null || id == "") {
		document.getElementById("errorMsg").innerHTML = "Please enter employee id ..";
		return false;
	}
	var secretWord = document.forms["memorableWordPasswordResetForm"]["authValue2"].value;
	if (secretWord == null || secretWord == "") {
		document.getElementById("errorMsg").innerHTML = "Please enter your secret memorable word ..";
		return false;
	} else {
		return loadPage();
	}
	return true;
}

function validateMerchantRegistrationForm() {
	var numExp = /^[0-9]+$/;
	var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	var firstName = document.forms["merchantRegistrationForm"]["firstName"].value;
	if (firstName == null || firstName == "") {
		document.getElementById("errorMsg").innerHTML = "Please enter your first name ..";
		return false;
	}
	var lastName = document.forms["merchantRegistrationForm"]["lastName"].value;
	if (lastName == null || lastName == "") {
		document.getElementById("errorMsg").innerHTML = "Please enter your last name ..";
		return false;
	}
	var mobileNumber = document.forms["merchantRegistrationForm"]["mobileNumber"].value;
	if (mobileNumber == null || mobileNumber == "") {
		document.getElementById("errorMsg").innerHTML = "Please enter your mobile number ..";
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
	var city = document.forms["merchantRegistrationForm"]["city"].value;
	if (city == null || city == "") {
		document.getElementById("errorMsg").innerHTML = "Please enter your city name ..";
		return false;
	}
	var pinCode = document.forms["merchantRegistrationForm"]["pinCode"].value;
	if (pinCode == null || pinCode == "") {
		document.getElementById("errorMsg").innerHTML = "Please enter your pin code ..";
		return false;
	}
	if (!pinCode.match(numExp)) {
		document.getElementById("errorMsg").innerHTML = "PIN code should be a numeric value ..";
		return false;
	}
	var identityType = document.forms["merchantRegistrationForm"]["identityType"].value;
	if (identityType == null || identityType == "") {
		document.getElementById("errorMsg").innerHTML = "Please select your identity type ..";
		return false;
	}
	var identityNumber = document.forms["merchantRegistrationForm"]["identityNumber"].value;
	if (identityNumber == null || identityNumber == "") {
		document.getElementById("errorMsg").innerHTML = "Please enter your identity number ..";
		return false;
	}
	var landMark = document.forms["merchantRegistrationForm"]["landMark"].value;
	if (landMark == null || landMark == "") {
		document.getElementById("errorMsg").innerHTML = "Please enter your land mark ..";
		return false;
	}
	var emailId = document.forms["merchantRegistrationForm"]["emailId"].value;
	if (emailId == null || emailId == "") {
		document.getElementById("errorMsg").innerHTML = "Please enter your email id ..";
		return false;
	}
	if (!emailId.match(mailformat)) {
		document.getElementById("errorMsg").innerHTML = "Please enter a valid email id ..";
		return false;
	}
	var capp = document.forms["merchantRegistrationForm"]["captcha"].value;
	if (capp == null || capp == "") {
		document.getElementById("errorMsg").innerHTML = "Please enter captcha ..";
		return false;
	}
	var key = document.forms["merchantRegistrationForm"]["keyCaptcha"].value;
	if (key != capp) {
		setTimeout(function() {
			window.location.reload(1);
		}, 1500);
		document.getElementById("errorMsg").innerHTML = "Invalid captcha entered ..";
		return false;
	} else {
		loadPage();
	}
	return true;

}
