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
    
    $("#left").hide();
    $("#right").hide();
    $("#center").hide();
    $("#justify").hide();
    $("#plus").hide();
    $("#minus").hide();
    var status = "true";
    $("#tabs-1").show();
    $("#tabs-2").hide();
   blockId=$(".blockname").val();
    $('.color-box').colpick({
        colorScheme: 'dark',
        layout: 'rgbhex',
        color: 'ff8800',
        onSubmit: function (hsb, hex, rgb, el) {
            $(el).css('background-color', '#' + hex);
            $("#" + selectedTextareaId).css('color', '#' + hex);
            $(el).colpickHide();
        }
    })
            .css('background-color', '#ffffff');
    
     $('.custom-color-box').colpick({
        colorScheme: 'dark',
        layout: 'rgbhex',
        color: 'ff8800',
        onSubmit: function (hsb, hex, rgb, el) {
           $("#selectedshapecolorbox").css('background-color', '#' + hex);
//            place block selected block
            var  blockId=$(".blockname").val();
            $("#"+blockId).css('background-color', '#' + hex);
            $(el).colpickHide();
        }
    })
            .css('background-color', '#ffffff');
    
    
      $('#slider').slider({ 
        min: 0, 
        max: 1, 
        step: 0.01, 
        value: 1,
        orientation: "horizontal",
             slide: function(e,ui){
                     $('#'+$(".blockname").val()).css('opacity', ui.value);
             }                
        }); 

    $(".blankcolor-box").click(function () {
        var color = $("#" + this.id).css("background-color");
        $("#selectedshapecolorbox").css("background-color", "" + color);
        $("#" + selectedDivId).css("background-color", "" + color);
    });

    $("#fontsize").change(function () {
//          alert($("#fontsize").val());
        $("#" + selectedTextareaId).css("font-size", "" + $("#fontsize").val());
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

    $.ajax({
        type: "GET",
        url: "xml/san1.xml",
        dataType: "xml",
        success: function (xml) {
            var count = 0;
            $(xml).find('datamapper').each(function () {

                var element = $(this).attr("Element");
                var default1 = $(this).attr("Default");
                mapperrray[count] = element + ":" + default1;
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
      alert(JSON.stringify(data));
           $(jsondataDefault).each(function (i, val) {
               var i = 0;
               $.each(val, function (k, v) {
                   allLayoutFilename[i] = v;
                   i++;
               });
             alert( allLayoutFilename[i] );
           });


    $.ajax({
        type: 'GET',
        url: 'MindBodyDetailServlet?mindbody_id=' + mindbodydataId +'&editor_type=social',
        data: {get_param: 'value'},
        dataType: 'json',
        success: function (data) {
            jsondata = data;
            $.ajax({
                type: "GET",
                url: global_host_address + "DownloadXml?file_name="+ allLayoutFilename[1]+ ".xml",
                dataType: "xml",
                success: function (xml) {
                    $(xml).find('layout').each(function () {
                        height = $(this).find('container').attr("Height");
                        width = $(this).find('container').attr("Width");
                        $(".preview").css("width", width + "px");
                        $(".preview").css("height", height + "px");

                    }

                    );
                    var count=1;
                    var blockcount=1;
                    $(xml).find('element').each(function () {
                        var tag = $(this).attr("tag");
                        type = $(this).attr("type");
                        var h = "";
                        var t = "";
                        var elementdata;

                        $(jsondata).each(function (i, val) {

                            $.each(val, function (k, v) {
//                                alert(k + " : " + v+ ":"+ type);
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
//                    alert($(this).attr("text-shadow"));
                            $(".preview").append("<div><textarea class=textAreas onclick=getTectId(" + type + ") id=" + type + ">" + elementdata + "</textarea>");
                            $("#" + type).css("color", "" + fontcolor)
                                    .css("position", "absolute")
                                    .css("margin-left", "" + left + "px")
                                    .css("margin-top", "" + top + "px")
                                    .css("width", "" + width)
                                    .css("min-height", "" + height)
                                    .css("font-size", "" + fontsize)
                                    .css("font-style", "" + fontstyle)
                                    .css("font-family", "" + font_family_name)
                                    .css("font-weight", "" + fontweight)
                                    .css("letter-spacing", "" + letterspacing)
                                    .css("line-height", "" + lineheight)
                                    .css("background-color","inherit")
                                    .css("border","0px")
                                    .css("opacity", "" + opacity)
                                    .css("text-align", "" + textalign)
                                    .css("text-shadow", "" + dropshadow)
                                    .css("webkit-transform", "rotate(" + webkittransform + "deg)");
                            $("#" + type).autogrow();
                        }

                        if (tag === "logo")
                        {
                            var background_image = $(this).attr("background-image");
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
                                .css("background", ""+background_image)
                                .css("background-repeat", "no-repeat")
                                .css("background-position", "center center")

                                .css("position", "absolute"); 
                        }

                        if (tag === "image")
                        {
                            var background_image = $(this).attr("background-image");
                            var blendmode = $(this).attr("background-blend-mode");
                            $(".imagename").append("<option value="+background_image+">Image "+count+"</option>");
//                    alert("image");
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
                                    .css("background", ""+background_image)
                                    .css("background-repeat", "no-repeat")
                                    .css("background-position", "center center")
                                    .css("position", "absolute");   
                        }

                        if (tag === "button")
                        {
//                            alert("button");
                            $(".preview").append("<div><img src='" + elementdata + "'id=" + type + " alt='button'/>");
                            $("#" + type).css("margin-left", "" + left + "px").css("margin-top", "" + top + "px")
                                    .attr("src", "buttons/button1.png");
                        }

                        if (tag === "block")
                        {
                                    var colorName = $(this).attr("color-name");
                                    var backgroundcolor;
                                     $(".blockname").append("<option value="+type+">Block "+blockcount+"</option>")
                                     blockcount++;
                                    for (var i = 1; i <= 6; i++)
                                    {
                                        if (colorName === "Color-" + i)
                                        {
                                            backgroundcolor = $("#shapecolorbox" + i).css("background-color");
                                        }

                                    }
//                  alert("block");
                            var width = $(this).attr("width");
                            var height = $(this).attr("height");
//                            var backgroundcolor = $(this).attr("background-color");
//                 alert(backgroundcolor);
                            $(".preview").append("<div onclick=getDivId(" + type + ") id=" + type + "></div>");
                            $("#" + type).css("background-color", "" + backgroundcolor)
                                         .css("margin-left", "" + left + "px")
                                         .css("margin-top", "" + top + "px")
                                         .css("width", "" + width)
                                         .css("height", "" + height);
                        }

                    }

                    );

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

    $("#text").click(function () {
        $("#tabs-1").show();
        $("#tabs-2").hide();

    });

    $("#style").click(function () {
        $("#tabs-1").hide();
        $("#tabs-2").show();
    });

    $(".alignButton").click(function () {
        $("#" + selectedTextareaId).css("text-align", $(this).attr('id'));

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
        var lineheight = $("#" + selectedTextareaId).css("line-height").replace("px", '');


        $("#" + selectedTextareaId).css("line-height", "" + (parseInt(lineheight) + 5) + "px");
    });
    $("#minus").click(function () {
        var lineheight = $("#" + selectedTextareaId).css("line-height").replace("px", '');

        $("#" + selectedTextareaId).css("line-height", "" + (parseInt(lineheight) - 5) + "px");
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

function getTectId(id) {

    $("textarea").click(function () {
        selectedTextareaId = id.id;
       var textDefaultcolor= $("#"+selectedTextareaId).css("color");

    var textDefaultAline= $("#"+selectedTextareaId).css("text-align");
    var textDefaultFontSize= $("#"+selectedTextareaId).css("font-size");
    var textDefaultFontFamily= $("#"+selectedTextareaId).css("font-style");
    $("#picker").css("background-color",""+textDefaultcolor);
    reload_alignButtons1(textDefaultAline);
        
    });
}

 function reload_alignButtons1(align)
    {
//            alert(align);
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
}

//function getImgId(divid) {
//    selectedImgDiv = divid;
//}

function uploadimage() {
    $('#popup').show("slow");

    $('#popupclose').click(function () {
        $('#popup').hide("slow");
//        alert($("#uploadImage").val());
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





