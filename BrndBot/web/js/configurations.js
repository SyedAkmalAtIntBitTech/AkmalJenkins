/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//var global_host_address = "http://production.brndbot.intbittech.com:8080/BrndBot/";
//var global_host_address = "http://development.brndbot.intbittech.com:8080/BrndBot/";
var global_host_address = "http://localhost:8084/BrndBot/";

var error = "system failure error";    

function getHost(){
//    return "http://production.brndbot.intbittech.com:8080/BrndBot/";
//    return "http://development.brndbot.intbittech.com:8080/BrndBot/";
    return "http://localhost:8084/BrndBot/";
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

String.prototype.contains = function(it) { return this.indexOf(it) != -1; };
