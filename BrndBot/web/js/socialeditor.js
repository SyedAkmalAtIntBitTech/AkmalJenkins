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
    
    $("#left").hide();
    $("#right").hide();
    $("#center").hide();
    $("#justify").hide();
    $("#plus").hide();
    $("#minus").hide();
    $("#lineHeightImage").hide();
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
                
                if($("#"+blockId).children().length > 0)
                {
                    var svgElement = SVG.get($("#"+blockId).children("svg").attr("id"));
                    if(svgElement.attr("id").indexOf("Svgjs") >= 0)
                    {

                        svgElement.style("fill", '#' + hex);
                        svgElement.each(function(i, children) {
                            this.style("fill", '#' + hex);

                        }, true); 
                    }
                }
                else
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
        if($("#"+blockId).children().length > 0)
        {
            var svgElement = SVG.get($("#"+blockId).children("svg").attr("id"));
            if(svgElement.attr("id").indexOf("Svgjs") >= 0)
            {

                svgElement.style("fill", color);
                svgElement.each(function(i, children) {
                    this.style("fill", color);

                }, true); 
            }
        }
        else
            $("#" + blockId).css('background-color', '#' + color);
        $("#openCustomColor").hide();
    });
     $("#selectedshapecolorbox").click(function(){
       $("#openCustomColor").toggle();  
    } );  
  
$(".blockname").change(function (){
    var blockId = $(".blockname").val();
    var divBackgroundColor, opacity;
    if($("#"+blockId).children().length > 0)
    {
        var svgElement = SVG.get($("#"+blockId).children("svg").attr("id"));
        divBackgroundColor = svgElement.style("fill");
        opacity = svgElement.opacity();
    }
    else
    {
        divBackgroundColor=$("#"+blockId).css("background-color");
        opacity=$("#"+blockId).css("opacity");
    }
    $('#selectedshapecolorbox').css("background-color",""+divBackgroundColor);
    $('#slider').slider({
     min: 0,
     max: 1,
     step: 0.01,
     value: ""+opacity,
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

    var jsondata;
    mindbodydataId = $("#mindbodydata").val();

    var layoutfilename = $("#clickid").val();
    var media_type=$("#media_type").val();

    $.ajax({
        type: 'POST',
        url: "GetLayoutStyles?editorType=social&media_type="+media_type+"",
        dataType: 'json',
        success: function (data) {
            var jsondataDefault = data;
            var allLayoutFilename = [];
            $(jsondataDefault).each(function (i, val) {
                var i = 0;
                $.each(val, function (k, v) {
                    allLayoutFilename[i] = v;
                    i++;
                });
            });
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
                                        if (type.trim() == k.trim()) {
                                            elementdata = v;
                                        }
                                    });
                                });

                                var fontcolor;
                                var fontsize;
                                var fontstyle;
                                var filter;
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
                                        }
                                    }
                                    textcount++;
                                    if(typeof(elementdata) === "undefined")
                                    {
                                        elementdata= $(this).attr("defaulttext");
                                    }
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
                                            .css("resize", "none")
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
                                    var addThis = $("#" + type).get(0).scrollHeight - $("#" + type).height();
                                    $("#" + type).attr("add-this",addThis);
                                }

                                if (tag === "logo")
                                {
                                    if($(this).attr("filterEnable")== "true"){
                                          filter="blur("+$(this).attr('blur')+") grayscale("+$(this).attr('grayscale')+") sepia("+$(this).attr('sepia')+") saturate("+$(this).attr('saturate')+") hue-rotate("+$(this).attr('huerotate')+") invert("+$(this).attr('invert')+") brightness("+$(this).attr('brightness')+") contrast("+$(this).attr('contrast')+")";
                                       }
                                    else
                                    {
                                         filter="drop-shadow("+$(this).attr("Drop-shadow-color")+" "+$(this).attr("H-shadow")+" "+$(this).attr("V-shadow")+" "+$(this).attr("blur")+")";
                                    }
                                    var userId=$("#userid").val();
                                    var userLogonmae = $("#userlogo").val();
                                                    var blendmode = $(this).attr("Blend");
                                                    var background_color=$(this).attr("blend-background-color");
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
                                            .css("background-color", ""+ background_color)
                                            .css("background-size","contain")
                                            .css("position", "absolute")
                                            .css("webkit-filter",""+ filter);                                          
                                }

                                if (tag === "image")
                                {                                  
                                    if($(this).attr("filterEnable")== "true"){
                                          filter="blur("+$(this).attr('blur')+") grayscale("+$(this).attr('grayscale')+") sepia("+$(this).attr('sepia')+") saturate("+$(this).attr('saturate')+") hue-rotate("+$(this).attr('huerotate')+") invert("+$(this).attr('invert')+") brightness("+$(this).attr('brightness')+") contrast("+$(this).attr('contrast')+")";
                                       }
                                    else
                                    {
                                         filter="drop-shadow("+$(this).attr("Drop-shadow-color")+" "+$(this).attr("H-shadow")+" "+$(this).attr("V-shadow")+" "+$(this).attr("blur")+")";
                                    }
                                    var blendmode = $(this).attr("Blend");
                                    var background_image = $(this).attr("background-image");
                                    var background_color=$(this).attr("blend-background-color");
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
                                            .css("background-size", "cover")
                                            .css("background-color", ""+background_color)
                                            .css("webkit-filter",""+ filter);
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

                                    $(".blockname").append("<option value=" + type + ">Shape " + blockcount + "</option>");
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
                                
                                if (tag === "svg")
                                {
                                    var colorName = $(this).attr("color-name");
                                    var borderRadius = $(this).attr("border-radius");
                                    var backgroundcolor;
                                    $(".blockname").append("<option value=" + type + ">Shape " + blockcount + "</option>");
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
                                    var filename = $(this).attr("filename");
                                    $(".preview").append("<div onclick=getDivId(" + type + ") id=" + type + "></div>");
                                    var draw = SVG(type);
                                    $("#" + type)
                                            .css("margin-left", "" + left + "px")
                                            .css("margin-top", "" + top + "px")
                                            .css("width", "" + width)
                                            .css("position", "absolute")
                                            .css("height", "" + height)
                                            ;
                                    $.get('/BrndBot/DownloadSVGServlet?file_name='+filename, function(data) {
                                    var svg1 = draw.svg(data);
                                    var realsvg = svg1.last();  

                                    svg1.attr("viewBox",realsvg.attr("viewBox"));
                                    svg1.attr("enable-background",realsvg.attr("enable-background"));
                                    svg1.attr("x",realsvg.attr("x"));
                                    svg1.attr("y",realsvg.attr("y"));
                                    svg1.attr("xml:space",realsvg.attr("xml:space"));
                                    svg1.attr("width",width);
                                    svg1.attr("height",height);
                                    svg1.opacity(opacity);                                    
                                    svg1.style("fill",backgroundcolor);
                                    svg1.style("-webkit-filter", "drop-shadow(" + drop_shadow + " " + h_shadow + " " + v_shadow + " " + Blur + ")");
                                    svg1.each(function(i, children) {
                                        this.style("fill",backgroundcolor);
                                        this.opacity(opacity);
                                    }, true); 
                                   },"html");            
                                }

                            } );
                            if(count===1 ){$("#imagecontainer").hide();}                                                   
                            if(blockcount===1){$("#shapecontainer").hide();}
                            if(textcount===1){$("#textcontainer").hide();}

                        },
                        error: function (e)
                        {
                            alert(xmlerror);
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
            var blockId = $(".blockname").val();
            if($("#"+blockId).children().length > 0)
            {
                var svg1 = SVG.get($("#"+blockId).children("svg").attr("id"));
                svg1.opacity(ui.value);
                svg1.each(function(i, children) {
                    this.opacity(ui.value);
                }, true); 
            }
            else
            $('#' + blockId).css('opacity', ui.value);
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
            $("#lineHeightImage").show();
            status = "false";
        }
        else {
            $("#left").hide();
            $("#right").hide();
            $("#center").hide();
            $("#justify").hide();
            $("#plus").hide();
            $("#minus").hide();
            $("#lineHeightImage").hide();
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
          
           $("#" + id).css("line-height", "initial");
            tempfontsize = tempfontsize +1;
            $("#" + id).css('font-size', ""+tempfontsize+"px");     
        }         
            tempfontsize = tempfontsize - 1;
          $("#" + id).css('font-size', ""+tempfontsize+"px");
    }
    else if ($("#" + id).get(0).scrollHeight > ($("#"+id).height()+addThis))
    {
        if(tempfontsize == orginialSize)
        $("#" + id).css("line-height", "initial");
        while ($("#" + id).get(0).scrollHeight > ($("#"+id).height()+addThis) && tempfontsize > 5) {
            tempfontsize = tempfontsize - 1;
            $("#" + id).css("font-size", "" + tempfontsize + "px");
        }
        var xxyy = parseInt(tempfontsize);
        xxyy = Math.round(xxyy * 1.2);
        $("#" + id).css("line-height", "" + xxyy + "px");
    }
}
function getTectId(id) {
    selectedTextareaId = id.id;
    $("textarea").click(function () {        
        var textDefaultcolor = $("#" + selectedTextareaId).css("color");
        var textDefaultAline = $("#" + selectedTextareaId).css("text-align");
        var textDefaultFontSize = $("#" + selectedTextareaId).css("font-size");
        var textDefaultFontFamily = $("#" + selectedTextareaId).css("font-family");
        $("#picker").css("background-color", "" + textDefaultcolor);
        reload_alignButtons1(textDefaultAline);
        var font_name = textDefaultFontFamily.split(' ').join('+');
        if(font_name.contains("+")){
            font_name=font_name.replace(/'/g,"");
        }
        $("#fontname").val(""+font_name).trigger('change');       
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





