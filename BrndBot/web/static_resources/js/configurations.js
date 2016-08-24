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
var defaultFroalaColors= ["61BD6D","clolor1",
                          "19BC9C","clolor2",
                          "54ADD2","clolor3",
                          "2B82C9","clolor4",
                          "9365B8","clolor5",
                          "475577","clolor6",
                          "CCCCCC","clolor7",
                          "41A85F","clolor8",
                          "01A884","clolor9",
                          "3D8EB9","clolor10",
                          "2969B0","clolor11",
                          "553982","clolor12",
                          "27324E","clolor13",
                          "000000","clolor14",
                          "F7DA64","clolor15",
                          "FBA026","clolor16",
                          "EB6B56","clolor17",
                          "A38F84","clolor18",
                          "EFEFEF","clolor19",
                          "FFFFFF","clolor20",
                          "FAC51B","clolor21",
                          "F37934","clolor22",
                          "D14841","clolor23",
                          "7C706B","clolor24",
                          "D1D5D8","clolor25"
                      ];
var defaultSendEmailFrom = "mail@brndbot.com"; 
var bitlyUserName = "sandeep264328";
var bitlyKey = "R_63e2f83120b743bc9d9534b841d41be6";
var aviaryApiKey = "75ed2ca5-7d87-454f-ab9f-e46446ed542d";


function getHost(){
     return global_host_address;
}

function debugAlert(message){
    growl(message);
}

function kUserPreference_HasLoggedInFacebook (){
    return "facebook";
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
    return this.indexOf(it) !== -1; 
};
function getDefaultEmailId(){
    return defaultSendEmailFrom;
}
function getBitlyUserName(){
    return bitlyUserName;
}
function getBitlyKey(){
    return bitlyKey;
}
function getAviaryApiKey(){
    return aviaryApiKey;
}
function growl(message,type)
{
    if(!type)
        $.growl.notice({message: message});
    else
        $.growl.error({message: message});
}
