/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 var height;
 var width;
 var mapperrray=[];
 var type;
 var selectedTextareaId;
 var title;
 var teacher;
 var date;
 
 function setSocialParameters(){
        title = $("#title").val();
        teacher = $("#teacher").val();
        date = $("#date").val();
        alert(title);
 }
 
$(document).ready(function () {
        alert(head1);
        $.ajax({
        type: "GET",
        url: "xml/san1.xml",
        dataType: "xml",
        success: function (xml) {
           var count=0;
            $(xml).find('datamapper').each(function () {

                var element = $(this).attr("Element");
                var default1 = $(this).attr("Default");
                mapperrray[count]=element+":"+default1;
                count++;
            }
            
            );
//            alert(mapperrray);
        },
        error: function (e)
        {
            alert("error in xml file read");
        }
    });
    
    
    $.ajax({
        type: "GET",
        url: "xml/yyy.xml",
        dataType: "xml",
        success: function (xml) {
            $(xml).find('layout').each(function () {
                 height = $(this).find('container').attr("Height");
                 width = $(this).find('container').attr("Width");
                $(".preview").css("width",width+"px");
             
            }
            
            );
            $(xml).find('element').each(function () {
                var tag = $(this).attr("tag");
                type = $(this).attr("type");
                var h = "";
                var t = "";
                if (type === "header1"){
                    h = "yoga power";
                }
                else if(type === "body1"){
                     t = "teacher1";
                }
                var fontcolor;
                var fontsize;
                var fontstyle;
                var left = $(this).attr("x-co-ordinates");
                var top = $(this).attr("y-co-ordinates");
                var opacity=$(this).attr("opacity");
                if (tag === "text")
                {
                    
                    
                     fontcolor = $(this).attr("font-color");
                     fontsize=$(this).attr("font-size");
                     fontstyle=$(this).attr("font-style");
                    var fontweight=$(this).attr("font-weight"); 
                    var letterspacing=$(this).attr("letter-spacing");
                    var lineheight=$(this).attr("line-height");
                   
                    var textalign=$(this).attr("text-align");
                
                    var webkittransform=$(this).attr("webkit-transform") ; 
                    var dropshadow=$(this).attr("H-shadow")+" "+$(this).attr("V-shadow")+" "+$(this).attr("blur")+" "+$(this).attr("text-shadow");
//                    alert($(this).attr("text-shadow"));
                    $(".preview").append("<div><textarea class=textAreas onclick=getTectId() id=" + type + ">" + type + "</textarea>");
                    $("#" + type).css("color", "" + fontcolor).css("position", "absolute").css("margin-left", "" + left + "px").css("margin-top", "" + top + "px")
                                 .css("font-size",""+fontsize).css("font-style",""+fontstyle).css("font-weight",""+fontweight)
                                 .css("letter-spacing",""+letterspacing).css("line-height",""+lineheight)
                                 .css("opacity",""+opacity).css("text-align",""+textalign)
                                 .css("text-shadow",""+dropshadow).css("webkit-transform","rotate("+webkittransform+"deg)");
                }
                
                if (tag === "image")
                {
                   var blendmode=$(this).attr("background-blend-mode");
                    var width=$(this).attr("width");
                    var height=$(this).attr("height");
//                    alert("image");
                    $(".preview").append("<div><img src='#' id=" + type +  " alt='image'/>");
                     $("#" + type).css("color", "" + fontcolor).css("position", "absolute").css("margin-left", "" + left + "px").css("margin-top", "" + top + "px")
                             .css("background-blend-mode", "" + blendmode).css("opacity", "" + opacity)
                            .css("width", "" + width).css("height", "" + height)
                            .attr("src","images/default.png");
                }
                
                if(tag==="button")
                {
                  alert("button");  
                  $(".preview").append("<div><img src='#' id=" + type+  " alt='button'/>");
                   $("#" + type).css("margin-left", "" + left + "px").css("margin-top", "" + top + "px")
                           .attr("src","buttons/button1.png");
                }
                
                if(tag==="block")
                {
//                  alert("block");
                  var width=$(this).attr("width");
                  var height=$(this).attr("height");
                 var backgroundcolor = $(this).attr("background-color");
//                 alert(backgroundcolor);
                   $(".preview").append("<div id=" + type+"></div>");
                    $("#" + type).css("background-color", ""+backgroundcolor).css("margin-left", "" + left + "px")
                                 .css("margin-top", "" + top + "px").css("width",""+width)
                                 .css("height",""+height);
                }

            }

            );
    
        },
        error: function (e)
        {
            alert("error in xml file read");
        }
    });
     
  
$("#text").click(function (){
    $("#tabs-1").show();
    $("#tabs-2").hide();
    

    
});
    
 $("#style").click(function (){
    $("#tabs-1").hide();
    $("#tabs-2").show();
    $("#stylecontainer").append("<div id=style1></div>");
//    $("#style1").css("width","100px").css("height","100pxs").css("background-color","#FF0000");
});   
    
 $(".alignButton").click(function () {
//     alert(selectedTextareaId);
//        alert($(this).attr('id'));
            $("#"+ selectedTextareaId).css("text-align", $(this).attr('id'));
           
            reload_alignButtons();
        });  
        
   
function reload_alignButtons()
        {
//            alert($("#"+selectedTextareaId).css("text-align"));
            if ($("#" + selectedTextareaId).css("text-align") === "left")
            {
                $("#left").css("background-color", "#99b1f2");
                $("#center").css("background-color", "inherit");
                $("#right").css("background-color", "inherit");
                $("#justify").css("background-color", "inherit");
            }
            else if ($("#" + selectedTextareaId).css("text-align") === "center")
            {
                $("#left").css("background-color", "inherit");
                $("#center").css("background-color", "#99b1f2");
                $("#right").css("background-color", "inherit");
                $("#justify").css("background-color", "inherit");
            }
            else if ($("#" + selectedTextareaId).css("text-align") === "right")
            {
                $("#left").css("background-color", "inherit");
                $("#center").css("background-color", "inherit");
                $("#justify").css("background-color", "inherit");
                $("#right").css("background-color", "#99b1f2");
            }
            else if ($("#" + selectedTextareaId).css("text-align") === "justify")
            {
                $("#left").css("background-color", "inherit");
                $("#right").css("background-color", "inherit");
                $("#center").css("background-color", "inherit");
                $("#justify").css("background-color", "#99b1f2");
            }
        }       

    $("#plus").click(function () {
        $("#" + selectedTextareaId).css("font-size", "+=1");
    });
    $("#minus").click(function () {
        $("#" + selectedTextareaId).css("font-size", "-=1");
    });  
    
});

function getTectId(){
$("textarea").click(function (){
  selectedTextareaId=this.id;
}); 
}

function uploadimage(){
    $('#popup').show("slow");
    
    $('#popupclose').click(function(){
        $('#popup').hide("slow");
      alert($("#uploadImage").val());
      $(".preview").append("<img src="+$("#uploadImage").val()+">");
    });	

}	

