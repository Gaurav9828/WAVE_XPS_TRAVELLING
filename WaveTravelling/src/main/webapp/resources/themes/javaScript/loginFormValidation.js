function validateAdminLoginForm()
{
    var id=document.forms["loginForm"]["id"].value;  
    if(id==null || id=="" )
    {
    	document.getElementById("errorMsg").innerHTML="Please enter employee id ..";
        return false;
    }

    var pass=document.forms["loginForm"]["password"].value;
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
    var currentPass=document.forms["passwordResetForm"]["currentPassword"].value;  
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
    
    var newPass=document.forms["passwordResetForm"]["newPassword"].value;
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