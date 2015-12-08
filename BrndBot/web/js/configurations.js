/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//var global_host_address = "http://clients.brndbot.com/BrndBot/";
//var global_host_address = "http://development.brndbot.com:8080/BrndBot/";
//var global_host_address = "http://45.33.92.14:8080/BrndBot/";
//var global_host_address = "http://45.79.184.221/BrndBot/";
var global_host_address = "http://localhost:8080/BrndBot/";

var error = "system failure error";  
var FroalaLicenseKey ="snJ-7c1krD-13fD1wzF-7==";

function getHost(){
//    return "http://clients.brndbot.com/BrndBot/";
//    return "http://development.brndbot.com:8080/BrndBot/";
//    return "http://45.33.92.14:8080/BrndBot/";
//    return "http://45.79.184.221/BrndBot/";
    return "http://localhost:8080/BrndBot/";
}

function debugAlert(message){
    alert(message);
}
function kUserPreference_HasLoggedInFacebook (){
    return "facebook";
}

function getColor1(){
    return "color1";
}

function getColor2(){
    return "color2";
}

function getColor3(){
    return "color3";
}

function getColor4(){
    return "color4";
}

function getColor5(){
    return "color5";
}

function getColor6(){
    return "color6";
}
function getemail(){
    return "Email";
}

function getfacebook(){
    return "Facebook";
}

function gettwitter(){
    return "Twitter";
}

function getnote(){
    return "Note";
}


String.prototype.contains = function(it) { return this.indexOf(it) != -1; };
