/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




        

                $(document).ready(function(){

                            
                        $('#picker').colpick({
                flat:true,
                        layout:'hex',
                        onSubmit:function(hsb, hex, rgb, el) {
                        //for haking hex value alert(hex);

                        $('#' + ElementID).css("background-color", "#" + hex);
                        }

                });
                
                
                
                
                    
                $("#test").click(function(){
                   
                        var s1 =$("#elementToPutStyleInto1").css("background-color");                    
                        var s2 =$("#elementToPutStyleInto2").css("background-color");
                        var s3 = $("#elementToPutStyleInto3").css("background-color");                    
                        var s4 = $("#elementToPutStyleInto4").css("background-color");                     
                        var s5 = $("#elementToPutStyleInto5").css("background-color");                       
                        var s6 = $("#elementToPutStyleInto1").css("background-color");
                        document.getElementById("finalcolor1").value = s1;
                        document.getElementById("finalcolor2").value = s2;
                        document.getElementById("finalcolor3").value = s3;
                        document.getElementById("finalcolor4").value = s4;
                        document.getElementById("finalcolor5").value = s5;
                        document.getElementById("finalcolor6").value = s6;
                });
                });
//  Reset pslette color   

                $(document).ready(function(){

                $("#resetpalette").click(function(){

                $("#elementToPutStyleInto1").css("background-color", "#FFFFFF");
                        $("#elementToPutStyleInto2").css("background-color", "#FFFFFF");
                        $("#elementToPutStyleInto3").css("background-color", "#FFFFFF");
                        $("#elementToPutStyleInto4").css("background-color", "#FFFFFF");
                        $("#elementToPutStyleInto5").css("background-color", "#FFFFFF");
                        $("#elementToPutStyleInto6").css("background-color", "#FFFFFF");
                });
                });

