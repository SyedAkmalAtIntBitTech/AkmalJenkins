/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var facebookcheck;    
var twittercheck;
$(document).ready(function () {
$("#facebook").click(function(){
               facebookcheck=document.getElementById("facebook").checked;
               alert(facebookcheck);
               if(facebookcheck)
               { 
            
	
               }

               $("#close").click(function(){
                   
                   $("#popup").hide();

               });
            });
            
        $("#twitter").click(function(){
            twittercheck=document.getElementById("twitter").checked;
            alert(twittercheck);
            
        });  
        
       if(twittercheck){
           $("#twitterimage").show();
           
       } 
       
        
        
         
   });