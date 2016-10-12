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
var kGlobalDateFormat = "YYYY-MM-DD";
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

var kGlobalFooterTop = "<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\" bgcolor=\"#f2f2f4\" style=\"border-collapse:collapse\"> <tbody> <tr> <td valign=\"top\"> <center style=\"width:100%\"> <div style=\"max-width:680px\"> <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" align=\"center\" bgcolor=\"#f2f2f4\" width=\"100%\" style=\"max-width:680px\"> <tbody> <tr> <td style=\"padding-top:40px; text-align:center;\" class=\"mobile-padding\"> <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" align=\"center\" width=\"200px\" style=\"max-width:300px;background-color:#inherit\"> <tbody> <tr>";

var kGlobalFooterFB = "<td style=\"text-align:center\"> <table align=\"center\"> <tbody> <tr> <td style=\"text-align:center\"> <a href=\"$$$footerFB$$$\" target=\"_blank\"><img src=\"" + global_host_address + "images/Facebook_Filled.png" + "\" alt=\"Facebook Icon\" style=\"border:0;width:30px\"></a> </td> </tr> </tbody> </table> </td>";

var kGlobalFooterTwitter = "<td style=\"text-align:center\"> <table align=\"center\"> <tbody> <tr> <td style=\"text-align:center\"> <a href=\"$$$footerTwitter$$$\" target=\"_blank\"><img src=\"" + global_host_address + "images/Twitter_Filled.png" + "\" alt=\"Twitter Icon\" style=\"border:0;width:30px\"></a> </td> </tr> </tbody> </table> </td>";

var kGlobalFooterWebsite = "<tr> <td style=\"text-align:center; padding: 40px 0px 20px 0px;font-family:sans-serif;font-size:12px;line-height:120%;text-align:center;color:#555555;\"> <a href=\"$$$footerWebsite$$$\" target=\"_blank\">Visit Our Website</a> </td> </tr>";

var kGlobalFooterInstagram = "<td style=\"text-align:center\"> <table align=\"center\"> <tbody> <tr> <td style=\"text-align:center\"> <a href=\"$$$footerInstagram$$$\" target=\"_blank\"><img src=\"" + global_host_address + "images/Insta_Filled.png" + "\" alt=\"Instagram Icon\" style=\"border:0;width:30px\"></a> </td> </tr> </tbody> </table> </td>";

var kGlobalFooterMiddle = "</tr> </tbody> </table> </td> </tr>";

var kGlobalFooterAddress = "<tr> <td style=\"font-family:sans-serif;font-size:12px;line-height:120%;text-align:center;color:#555555;padding:20px 55px 60px 55px\"> $$$footerAddress$$$ </td> </tr>";

var kGlobalFooterBottom = "</tbody> </table> </div> </center> </td> </tr> </tbody> </table>";


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
