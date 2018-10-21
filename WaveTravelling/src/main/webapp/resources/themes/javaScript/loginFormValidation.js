function validateAdminLoginForm()
{
    var id=document.forms["loginForm"]["authValue1"].value;  
    if(id==null || id=="" )
    {
    	document.getElementById("errorMsg").innerHTML="Please enter employee id ..";
        return false;
    }

    var pass=document.forms["loginForm"]["authValue2"].value;
    if(pass==null || pass=="")
    {
    	document.getElementById("errorMsg").innerHTML="Please enter your password ..";
        return false;
    }
    
    var capp=document.forms["loginForm"]["captcha"].value;
    if(capp==null || capp=="")
    {
    	document.getElementById("errorMsg").innerHTML="Please enter captcha ..";
        return false;
    }
    var key=document.forms["loginForm"]["keyCaptcha"].value;
    if(key!=capp){
    	setTimeout(function(){
    		   window.location.reload(1);
    	}, 1500);
    	document.getElementById("errorMsg").innerHTML="Invalid captcha entered ..";
        return false;
    }
    else
    {
        return true;
    }
}

function validateAdminPasswordResetForm()
{
    var currentPass=document.forms["passwordResetForm"]["authValue1"].value;  
    if(currentPass==null || currentPass=="" )
    {
    	document.getElementById("errorMsg").innerHTML="Please enter your current password ..";
        return false;
    }

    var pass=document.forms["passwordResetForm"]["password"].value;
    if(pass==null || pass=="")
    {
    	document.getElementById("errorMsg").innerHTML="Please enter your new password ..";
        return false;
    }
    
    var newPass=document.forms["passwordResetForm"]["authValue2"].value;
    if(newPass==null || newPass=="")
    {
    	document.getElementById("errorMsg").innerHTML="Please confirm your password ..";
        return false;
    }
    
    if(pass!=newPass){
    	document.getElementById("errorMsg").innerHTML="Please enter same password ..";
        return false;
    }
    else
    {
        return true;
    }
}

function validateAdminMemorableWordPasswordResetForm()
{
	 var firstName=document.forms["memorableWordPasswordResetForm"]["firstName"].value;  
	 if(firstName==null || firstName=="" )
	 {
	   	document.getElementById("errorMsg").innerHTML="Please enter your first name ..";
	    return false;
	 }
	 var lastName=document.forms["memorableWordPasswordResetForm"]["lastName"].value;  
	 if(lastName==null || lastName=="")
	 {
	  	document.getElementById("errorMsg").innerHTML="Please enter your last name ..";
	    return false;
	 }
	 var secretWord=document.forms["memorableWordPasswordResetForm"]["secretWord"].value;  
	 if(secretWord==null || secretWord=="")
	 {
	  	document.getElementById("errorMsg").innerHTML="Please enter your secret memorable word ..";
	    return false;
	 }
	 var mobileNumber=document.forms["memorableWordPasswordResetForm"]["mobileNumber"].value;  
	 if(mobileNumber==null || mobileNumber=="")
	 {
	  	document.getElementById("errorMsg").innerHTML="Please enter your mobile number ..";
	    return false;
	 }
	 var city=document.forms["memorableWordPasswordResetForm"]["city"].value;  
	 if(city==null || city=="")
	 {
	  	document.getElementById("errorMsg").innerHTML="Please enter your city name ..";
	    return false;
	 }
	 var pinCode=document.forms["memorableWordPasswordResetForm"]["pinCode"].value;  
	 if(pinCode==null || pinCode=="")
	 {
	  	document.getElementById("errorMsg").innerHTML="Please enter your pin code ..";
	    return false;
	 }
	 var identityType=document.forms["memorableWordPasswordResetForm"]["identityType"].value;  
	 if(identityType==null || identityType=="")
	 {
	  	document.getElementById("errorMsg").innerHTML="Please select your identity type ..";
	    return false;
	 }
	 var identityNumber=document.forms["memorableWordPasswordResetForm"]["identityNumber"].value;  
	 if(identityNumber==null || identityNumber=="")
	 {
	  	document.getElementById("errorMsg").innerHTML="Please enter your identity number ..";
	    return false;
	 }
	 var landMark=document.forms["memorableWordPasswordResetForm"]["landMark"].value;  
	 if(landMark==null || landMark=="")
	 {
	  	document.getElementById("errorMsg").innerHTML="Please enter your land mark ..";
	    return false;
	 }
	 var emailId=document.forms["memorableWordPasswordResetForm"]["emailId"].value;  
	 if(emailId==null || emailId=="")
	 {
	  	document.getElementById("errorMsg").innerHTML="Please enter your email id ..";
	    return false;
	 }
     else
    {
        return true;
    }
}


function validateAdminLevel1SelfRegistartionForm(){
	var id=document.forms["adminLevel1Registration"]["authValue1"].value; 
}

