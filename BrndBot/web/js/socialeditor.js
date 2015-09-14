/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var height;
var width;
var mapperrray = [];
var type;
var selectedTextareaId;
var selectedDivId;
//var selectedImgDiv;
 var blockId ="";
var title;
var teacher;
var date;
var head1 = "Head1";
var i = 1;



function setSocialParameters(title, teacher, date) {
    title = $("#title").val();
    teacher = $("#teacher").val();
    date = $("#date").val();
}

$(document).ready(function () {
    $("#galleryupload").click(function(){
        $(".fileclick").click();
    });
    
    $("#upload").click(function(){
       $("#myFile").upload("UploadImages",function(success){
           $("#image1").click();
            $(".close-reveal-modal").click();
       });
   });

    $("#left").hide();
    $("#right").hide();
    $("#center").hide();
    $("#justify").hide();
    $("#plus").hide();
    $("#minus").hide();
    var status = "true";
    $("#tabs-1").show();
    $("#tabs-2").hide();
     $("#tabs-3").hide();
     $("#openCustomColor").hide();
    $('.custom-color-box-text').colpick({
        colorScheme: 'light',
        layout: 'rgbhex',
        color: 'ff8800',
        onSubmit: function (hsb, hex, rgb, el) {

            $("#" + selectedTextareaId).css('color', '#' + hex);
            $("#picker").css('background-color', '#' + hex);
            $(el).colpickHide();
            $("#pickColorForText").css("display", "none");
        }
    })
            .css('background-color', '#ffffff');

    $('.custom-color-box').click(function () {
       
        
        $('.custom-color-box').colpick({
            colorScheme: 'light',
            layout: 'rgbhex',
            color: 'ff8800',
            onSubmit: function (hsb, hex, rgb, el) {
                $("#slider").show();
                $("#blockopacity").show();
                $("#selectedshapecolorbox").css('background-color', '#' + hex);
                var blockId = $(".blockname").val();
                $("#" + blockId).css('background-color', '#' + hex);
                $(el).colpickHide();
                $("#openCustomColor").hide();
              
            },
            onShow: function ()
            {
                $("#slider").hide();
                $("#blockopacity").hide();
            },
            onHide: function ()
            {
                $("#slider").show();
                $("#blockopacity").show();
            }
        });
    });

    $(".blankcolor-box").click(function () {
        var color = $("#" + this.id).css("background-color");
        var blockId = $(".blockname").val();
        $("#selectedshapecolorbox").css("background-color", "" + color);
//        $("#" + selectedDivId).css("background-color", "" + color);
        $("#" + blockId).css('background-color', '#' + color);
        $("#openCustomColor").hide();
    });
     $("#selectedshapecolorbox").click(function(){
       $("#openCustomColor").toggle();  
    } );  
  
$(".blockname").change(function (){
    var blockId = $(".blockname").val();
    var divBackgroundColor=$("#"+blockId).css("background-color");
    var opcity=$("#"+blockId).css("opacity");
     $('#selectedshapecolorbox').css("background-color",""+divBackgroundColor);
       $('#slider').slider({
        min: 0,
        max: 1,
        step: 0.01,
        value: ""+opcity,
        orientation: "horizontal" 
    });
});

    $(".blankcolor-box1").click(function () {
        var display = $("#pickColorForText").css("display");
        if (display === "none") {
            $("#pickColorForText").css("display", "block");
            $(".blankcolor-box-text").click(function () {
                var color = $("#" + this.id).css("background-color");
                $("#picker").css("background-color", "" + color);
                $("#" + selectedTextareaId).css("color", "" + color);
                $("#pickColorForText").css("display", "none");
            });
        }
        else if (display) {
            $("#pickColorForText").css("display", "none");
        }
    });
    $("#fontsize").change(function () {
        $("#" + selectedTextareaId).css("font-size", "" + $("#fontsize").val());
    });
    $("#minusFont").click(function () {
        var minusFont =  parseInt($("#" + selectedTextareaId).css("font-size").replace("px","")) - 5;
         $("#" + selectedTextareaId).css("font-size", ""+minusFont+"px");
         $("#" + selectedTextareaId).attr("orginial-size",minusFont);
    });
    $("#plusFont").click(function () {
        var plusFont =  parseInt($("#" + selectedTextareaId).css("font-size").replace("px","")) + 5;
         $("#" + selectedTextareaId).css("font-size",""+plusFont+"px");
         $("#" + selectedTextareaId).attr("orginial-size",plusFont);
    });

//    $("#fontname").change(function () {
////            alert($(this).val());
//        var text = $("#fontname").text();
//        var font_family_name = $("#fontname").val();
//        var font = font_family_name.split(",");
//        alert(font[0]);
//        
//        var ss = document.createElement("link");
//        ss.type = "text/css";
//        ss.rel = "stylesheet";
//        ss.href = "https://fonts.googleapis.com/css?family="+ font[0];
//        document.getElementsByTagName("head")[0].appendChild(ss);
//
//        var font_path = global_host_address + "DownloadFonts?file_name="+ font[1];
//        alert(font_path);
//        var styles = "@font-face {"+
//                     "font-family:"+ text + ";"+
//                     "src: url("+font[1]+");"
//        $('<style type="text/css">'+ styles +'</style>').appendTo(document.head);
//
//        $("#" + selectedTextareaId).css("font-family", text);
//        
//    });

//    alert("loding");

    jsondata;
    mindbodydataId = $("#mindbodydata").val();

    var layoutfilename = $("#clickid").val();

//   alert(layoutfilename);

    $.ajax({
        type: 'POST',
        url: "GetLayoutStyles?editorType=social",
        dataType: 'json',
        success: function (data) {
            var jsondataDefault = data;
            var allLayoutFilename = [];
//             alert(JSON.stringify(data));
            $(jsondataDefault).each(function (i, val) {
                var i = 0;
                $.each(val, function (k, v) {
                    allLayoutFilename[i] = v;
                    i++;
                });
//             alert( allLayoutFilename[i] );
            });
//           alert(mindbodydataId);
            var layout_mapper_url = "";
            if (mindbodydataId != "") {
                layout_mapper_url = 'MindBodyDetailServlet?mindbody_id=' + mindbodydataId + '&model_mapper_id=' + allLayoutFilename[0] + '&editor_type=social';
            } else {
                layout_mapper_url = 'GenericAnnouncementServlet?editor_type=social&model_mapper_id=' + allLayoutFilename[0];
            }

            $.ajax({
                type: 'GET',
                url: layout_mapper_url,
                dataType: 'json',
                success: function (data) {
                    jsondata = data;
                    $.ajax({
                        type: "GET",
                        url: global_host_address + "DownloadXml?file_name=" + allLayoutFilename[1] + ".xml",
                        dataType: "xml",
                        success: function (xml) {
                            $(xml).find('layout').each(function () {
                                height = $(this).find('container').attr("Height");
                                width = $(this).find('container').attr("Width");
                                $(".preview").css("width", width + "px");
                                $(".preview").css("height", height + "px");

                            }

                            );
                            var count = 1;
                            var blockcount = 1;
                            var textcount=1;
                            $(xml).find('element').each(function () {
                                var tag = $(this).attr("tag");
                                type = $(this).attr("type");
                                var h = "";
                                var t = "";
                                var elementdata;

                                $(jsondata).each(function (i, val) {

                                    $.each(val, function (k, v) {
//                                    alert(k + " : " + v+ ":"+ type);
                                        if (type.trim() == k.trim()) {
                                            elementdata = v;
                                        }

                                    });

                                });


                                var fontcolor;
                                var fontsize;
                                var fontstyle;
                                var left = $(this).attr("x-co-ordinates");
                                var top = $(this).attr("y-co-ordinates");
                                var width = $(this).attr("width");
                                var height = $(this).attr("height");
                                $(".crop-container").css("width",width);
                                $(".crop-container").css("width",height);
                                var opacity = $(this).attr("opacity");
                                if (tag === "text")
                                {
                                    var colorName = $(this).attr("font-color-name");
                                    for (var i = 1; i <= 6; i++)
                                    {
                                        if (colorName === "Font-Color-" + i)
                                        {
                                            fontcolor = $("#shapecolorbox" + i).css("background-color");
//                                                             fontcolor=user_preferences_colors.color+""+i; 
                                        }

                                    }
                                    textcount++;
//                                    fontcolor = $(this).attr("font-color");
                                    fontsize = $(this).attr("font-size");
                                    fontstyle = $(this).attr("font-style");
                                    var fontweight = $(this).attr("font-weight");
                                    var font = $(this).attr("font-family");
                                    var font_family_name = font.split("+").join(" ");
                                    var letterspacing = $(this).attr("letter-spacing");
                                    var lineheight = $(this).attr("line-height");

                                    var textalign = $(this).attr("text-align");

                                    var webkittransform = $(this).attr("webkit-transform");
                                    var dropshadow = $(this).attr("H-shadow") + " " + $(this).attr("V-shadow") + " " + $(this).attr("blur") + " " + $(this).attr("text-shadow");
                                    $(".preview").append("<div><textarea class=textAreas orginial-size='" + fontsize + "' onkeyup=textAreaKeyUp(event,'" + type + "')  onclick=getTectId(" + type + ") id=" + type + ">" + elementdata + "</textarea>");
                                    $("#" + type).css("color", "" + fontcolor)
                                            .css("position", "absolute")
                                            .css("overflow", "hidden")
                                            .css("margin-left", "" + left + "px")
                                            .css("margin-top", "" + top + "px")
                                            .css("width", "" + width)
                                            .css("height", "" + height)
                                            .css("font-size", "" + fontsize)
                                            .css("font-style", "" + fontstyle)
                                            .css("font-family", "" + font_family_name)
                                            .css("font-weight", "" + fontweight)
                                            .css("letter-spacing", "" + letterspacing)
                                            .css("line-height", "" + lineheight)
                                            .css("background-color", "inherit")
                                            .css("border", "0px")
                                            .css("opacity", "" + opacity)
                                            .css("text-align", "" + textalign)
                                            .css("text-shadow", "" + dropshadow)
                                            .css("webkit-transform", "rotate(" + webkittransform + "deg)");
                                    //$("#" + type).autogrow();

                                    //resize of text to fit bound - By Syed Ilyas 26/8/2015
                                    var tempfontsize = parseInt(fontsize.replace("px", ""));
                                    var tempHeight = parseInt(height.replace("px", ""));
                                    if ($("#" + type).get(0).scrollHeight > tempHeight)
                                    {
                                        $("#" + type).css("line-height", "initial");
                                        while ($("#" + type).get(0).scrollHeight > tempHeight) {
                                            tempfontsize = tempfontsize - 1;
                                            $("#" + type).css("font-size", "" + tempfontsize + "px");
                                        }
                                        var xxyy = parseInt(tempfontsize);
                                        xxyy = Math.round(xxyy * 1.2);
                                        $("#" + type).css("line-height", "" + xxyy + "px");
                                    }
                                    //resize end
                                    var addThis = $("#" + type).get(0).scrollHeight - $("#" + type).height();
                                    $("#" + type).attr("add-this",addThis);
                                }

                                if (tag === "logo")
                                {
                                    var userId=$("#userid").val();
                                    var userLogonmae = $("#userlogo").val();
                                    var blendmode = $(this).attr("background-blend-mode");
                                    $(".preview").append("<div onclick=getImageid(" + type + ") id=" + type + " ></div>");
                                    $("#" + type)
                                            .css("color", "" + fontcolor)
                                            .css("margin-left", "" + left + "px")
                                            .css("margin-top", "" + top + "px")
                                            .css("background-blend-mode", "" + blendmode)
                                            .css("opacity", "" + opacity)
                                            .css("width", "" + width)
                                            .css("height", "" + height)
                                            .css("background", "url('/BrndBot/DownloadImage?image_type=USER_LOGO&user_id="+userId+"&image_name="+userLogonmae+"')" )
                                            .css("background-repeat", "no-repeat")
                                            .css("background-position", "center center")

                                            .css("position", "absolute");
                                }

                                if (tag === "image")
                                {
                                    var background_image = $(this).attr("background-image");
                                    var blendmode = $(this).attr("background-blend-mode");
                                    $(".imagename").append("<option name=" + background_image + " value="+ type +">Image " + count + "</option>");
                                    count++;
                                    $(".preview").append("<div onclick=getImageid(" + type + ") id=" + type + " ></div>");
                                    $("#" + type)
                                            .css("color", "" + fontcolor)
                                            .css("margin-left", "" + left + "px")
                                            .css("margin-top", "" + top + "px")
                                            .css("background-blend-mode", "" + blendmode)
                                            .css("opacity", "" + opacity)
                                            .css("width", "" + width)
                                            .css("height", "" + height)
                                            .css("background", "" + background_image)
                                            .css("background-repeat", "no-repeat")
                                            .css("background-position", "center center")
                                            .css("position", "absolute")
                                    .css("background-size","100% 100%");
                                }

                                if (tag === "button")
                                {
                                    $(".preview").append("<div><img src='" + elementdata + "'id=" + type + " alt='button'/>");
                                    $("#" + type).css("margin-left", "" + left + "px").css("margin-top", "" + top + "px")
                                            .attr("src", "buttons/button1.png");
                                }

                                if (tag === "block")
                                {

                                    var colorName = $(this).attr("color-name");
                                    var borderRadius = $(this).attr("border-radius");
                                    var backgroundcolor;

                                    $(".blockname").append("<option value=" + type + ">Block " + blockcount + "</option>");
                                    blockcount++;

                                    for (var i = 1; i <= 6; i++)
                                    {
                                        if (colorName === "Color-" + i)
                                        {
                                            backgroundcolor = $("#shapecolorbox" + i).css("background-color");
                                        }

                                    }
                                    var width = $(this).attr("width");
                                    var height = $(this).attr("height");
                                    var drop_shadow = $(this).attr("Drop-shadow-color");
                                    var h_shadow = $(this).attr("H-shadow");
                                    var v_shadow = $(this).attr("V-shadow");
                                    var Blur = $(this).attr("blur");
                                    $(".preview").append("<div onclick=getDivId(" + type + ") id=" + type + "></div>");
                                    $("#" + type).css("background-color", "" + backgroundcolor)
                                            .css("margin-left", "" + left + "px")
                                            .css("margin-top", "" + top + "px")
                                            .css("width", "" + width)
                                            .css("border-radius", "" + borderRadius)
                                            .css("position", "absolute")
                                            .css("height", "" + height)
                                            .css("-webkit-filter", "drop-shadow(" + drop_shadow + " " + h_shadow + " " + v_shadow + " " + Blur + ")")
                                            .css("opacity", "" + opacity);

                                }

                            } );
                            if(count===1 ){$("#imagecontainer").hide();}                                                   
                            if(blockcount===1){$("#shapecontainer").hide();}
                            if(textcount===1){$("#textcontainer").hide();}

                        },
                        error: function (e)
                        {
                            alert("error in xml file read");
                        }
                    });


                }
            });
        }
    });
    
    
    
    $('#slider').slider({
        min: 0,
        max: 1,
        step: 0.01,
        value: 0,
        orientation: "horizontal",
        slide: function (e, ui) {
            $('#' + $(".blockname").val()).css('opacity', ui.value);
        }
    });
    $("#text").click(function () {
        $("#tabs-1").show();
        $("#tabs-2").hide();
        $("#tabs-3").hide();

    });

    $("#style").click(function () {
        $("#tabs-1").hide();
        $("#tabs-2").show();
        $("#tabs-3").hide();
    });

    $(".alignButton").click(function () {
        $("#" + selectedTextareaId).css("text-align", $(this).attr('id'));

        reload_alignButtons();
    });


    function reload_alignButtons()
    {
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
        
        if($("#" + selectedTextareaId).css("line-height") == "normal")
        {
            
        var xxyy = parseInt($("#" + selectedTextareaId).css("font-size"));
        xxyy = Math.round(xxyy * 1.2);
        $("#" + selectedTextareaId).css("line-height", "" + xxyy + "px");
        $("#" + selectedTextareaId).css("line-height");
        
        }
        var lineheight = $("#" + selectedTextareaId).css("line-height").replace("px", '');


        $("#" + selectedTextareaId).css("line-height", "" + (parseInt(lineheight) + 2) + "px");
    });
    $("#minus").click(function () {
        
        if($("#" + selectedTextareaId).css("line-height") == "normal")
        {
        var xxyy = parseInt($("#" + selectedTextareaId).css("font-size"));
        xxyy = Math.round(xxyy * 1.2);
        $("#" + selectedTextareaId).css("line-height", "" + xxyy + "px");
        }
        
        var lineheight = $("#" + selectedTextareaId).css("line-height").replace("px", '');

        $("#" + selectedTextareaId).css("line-height", "" + (parseInt(lineheight) - 2) + "px");
    });

    $("#hidealignbutton").click(function () {

        if (status === "true") {
            $("#left").show();
            $("#right").show();
            $("#center").show();
            $("#justify").show();
            $("#plus").show();
            $("#minus").show();
            status = "false";
        }
        else {
            $("#left").hide();
            $("#right").hide();
            $("#center").hide();
            $("#justify").hide();
            $("#plus").hide();
            $("#minus").hide();
            status = "true";
        }

    });


});
function textAreaKeyUp(event,id) {
       
   var orginialSize = parseInt($("#"+id).attr("orginial-size"));
    var tempfontsize = parseInt($("#"+id).css('font-size').replace("px", ""));
    var addThis = parseInt($("#" + id).attr("add-this"));
     if (event.keyCode == 8 || event.keyCode == 46) {
  
        while ( $("#" + id).get(0).scrollHeight <= ($("#"+id).height()+addThis) && tempfontsize <= orginialSize) {
           //alert(tempfontsize);
           $("#" + id).css("line-height", "initial");
            tempfontsize = tempfontsize +1;
            $("#" + id).css('font-size', ""+tempfontsize+"px");
           
 
        }
         
             tempfontsize = tempfontsize - 1;
          $("#" + id).css('font-size', ""+tempfontsize+"px");
 
//   var xxyy = parseInt(tempfontsize);
//        xxyy = Math.round(xxyy * 1.2);
        //$("#" + id).css("line-height", "" + xxyy + "px");
    }
    else if ($("#" + id).get(0).scrollHeight > ($("#"+id).height()+addThis))
    {
        if(tempfontsize == orginialSize)
        $("#" + id).css("line-height", "initial");
        while ($("#" + id).get(0).scrollHeight > ($("#"+id).height()+addThis) && tempfontsize > 5) {
        //    alert(tempfontsize);
                //alert($("#" + id).get(0).scrollHeight + ":" + ($("#"+id).height()+4));
            tempfontsize = tempfontsize - 1;
            //     alert(tempfontsize);
            $("#" + id).css("font-size", "" + tempfontsize + "px");
        }
        var xxyy = parseInt(tempfontsize);
        xxyy = Math.round(xxyy * 1.2);
        $("#" + id).css("line-height", "" + xxyy + "px");
        //alert($("#" + id).get(0).scrollHeight + ">" + ($("#"+id).height()+4));
    }
        //alert("last"+$("#" + id).get(0).scrollHeight + ">" + ($("#"+id).height()+4));
        //$("#" + id).css("line-height", "" + xxyy + "px");
}
function getTectId(id) {
    selectedTextareaId = id.id;
    $("textarea").click(function () {
        
        var textDefaultcolor = $("#" + selectedTextareaId).css("color");

        var textDefaultAline = $("#" + selectedTextareaId).css("text-align");
        var textDefaultFontSize = $("#" + selectedTextareaId).css("font-size");
        var textDefaultFontFamily = $("#" + selectedTextareaId).css("font-style");
        $("#picker").css("background-color", "" + textDefaultcolor);
        reload_alignButtons1(textDefaultAline);
    });
    $("#"+selectedTextareaId).focusout(function(){
        var t= $("#"+selectedTextareaId).val();
        $("#"+selectedTextareaId ).text(t);
        
    });
}

function reload_alignButtons1(align)
{
    if (align === "left")
    {
        $("#left").css("background-color", "#99b1f2");
        $("#center").css("background-color", "inherit");
        $("#right").css("background-color", "inherit");
        $("#justify").css("background-color", "inherit");
    }
    else if (align === "center")
    {
        $("#left").css("background-color", "inherit");
        $("#center").css("background-color", "#99b1f2");
        $("#right").css("background-color", "inherit");
        $("#justify").css("background-color", "inherit");
    }
    else if (align === "right")
    {
        $("#left").css("background-color", "inherit");
        $("#center").css("background-color", "inherit");
        $("#justify").css("background-color", "inherit");
        $("#right").css("background-color", "#99b1f2");
    }
    else if (align === "justify")
    {
        $("#left").css("background-color", "inherit");
        $("#right").css("background-color", "inherit");
        $("#center").css("background-color", "inherit");
        $("#justify").css("background-color", "#99b1f2");
    }
}



function getDivId(divid) {
    selectedDivId = divid.id;
    
     $('.blockname').val(""+selectedDivId).trigger('change');

}


//function getImgId(divid) {
//    selectedImgDiv = divid;
//}

function uploadimage() {
    $('#popup').show("slow");

    $('#popupclose').click(function () {
        $('#popup').hide("slow");
        $(".preview").append("<img src=" + $("#uploadImage").val() + ">");
    });

}



function saveImageEdit() {
    $("#textcontainer").show();
    $("#shapecontainer").show();
    $("#imagecontainer").show();

    $("#filtercontainer").hide();
    $("#cropImageContainer").hide();

}





