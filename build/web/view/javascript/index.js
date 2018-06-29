var alertRedInput = "#8C1010";
var defaultInput = "rgba(10, 180, 180, 1)";

function userNameValidation() {
    var x = document.getElementById("username");
    var y = document.getElementById("login-btn");
    if (x.value.length == 0) {
        x.style.borderColor = alertRedInput;
        y.style.borderColor = alertRedInput;
    }
    else {
        x.style.borderColor = defaultInput;
        y.style.borderColor = defaultInput;
    }
} 

function passwordValidation() {
    var x = document.getElementById("password");
    var y = document.getElementById("login-btn");
    if (x.value.length == 0) {
        x.style.borderColor = alertRedInput;
        y.style.borderColor = alertRedInput;
    }
    else {
        x.style.borderColor = defaultInput;
        y.style.borderColor = defaultInput;
    }
}

$(document).ready(function(){
    // Set appropriate screen resolution.
    var p = ($(window).height() / 2160) + ($(window).width() / 3840);
    $(".signupSection").css("zoom", p);
    $(".signupSection").css("-moz-transform", "scale(" + p + ")");

    // Check login attempt status.
    var url = new URL(window.location.href);
    var re = url.searchParams.get("re");
    if (re == '1') {
      var x = document.getElementById("warning");
      x.style.display = "block";
    }  
})  