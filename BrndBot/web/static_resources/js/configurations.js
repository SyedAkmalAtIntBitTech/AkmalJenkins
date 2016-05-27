/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//var global_host_address = "http://clients.brndbot.com/BrndBot/";
//var global_host_address = "http://development.brndbot.com/BrndBot/";
//var global_host_address = "http://development2.brndbot.com/BrndBot/";
//var global_host_address = "http://dev1.intbittech.com/BrndBot/";
var global_host_address = "http://localhost:8080/BrndBot/";

var error = "system failure error";  
var FroalaLicenseKey ="snJ-7c1krD-13fD1wzF-7==";
var defaultFroalaColors=["#61BD6D", "#19BC9C","#54ADD2", "#2B82C9", "#9365B8", "#475577", "#CCCCCC", "#41A85F", "#01A884", "#3D8EB9",  "#2969B0", "#553982", "#27324E", "#000000", "#F7DA64", "#FBA026", "#EB6B56", "#A38F84", "#EFEFEF",  "#FFFFFF", "#FAC51B", "#F37934", "#D14841", "#7C706B", "#D1D5D8","REMOVE"];

function getHost(){
     return global_host_address;
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
    return "Reminder";
}

String.prototype.contains = function(it) { 
    return this.indexOf(it) != -1; 
};
