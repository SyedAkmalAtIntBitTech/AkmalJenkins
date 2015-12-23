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
var selectedBlockId;
var selectedDivId;
var selectedImgDiv;
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
    $("#lineHeightImage").hide();
    var status = "true";
    $("#tabs-1").show();
    $("#tabs-2").hide();
    $("#tabs-3").hide();
    $("#tabs-4").hide();
    $("#tabs-5").hide();
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
    
    $("#fontsize").change(function () {
        $("#" + selectedTextareaId).css("font-size", "" + $("#fontsize").val());
    });
    $("#minusFont").click(function () {
        var minusFont =  parseInt($("#" + selectedTextareaId).css("font-size").replace("px","")) - 5;
         $("#" + selectedTextareaId).css("font-size", ""+minusFont+"px");
    });
    $("#plusFont").click(function () {
        var plusFont =  parseInt($("#" + selectedTextareaId).css("font-size").replace("px","")) + 5;
         $("#" + selectedTextareaId).css("font-size",""+plusFont+"px");
    });

    $("#fontname").change(function () {
        $("#" + selectedTextareaId).css("font-family", "" + $("#fontname").val());
    });



    var jsondata;
    mindbodydataId = $("#mindbodydata").val();

    var layoutfilename = $("#clickid").val();

//    alert(layoutfilename);

    $.ajax({
        type: 'POST',
        url: "GetLayoutStyles?editorType=email",
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
                layout_mapper_url = 'MindBodyDetailServlet?mindbody_id=' + mindbodydataId + '&model_mapper_id=' + allLayoutFilename[0] + "&editor_type=email";
            } else {
                layout_mapper_url = 'GenericAnnouncementServlet?model_mapper_id=' + allLayoutFilename[0] + "&editor_type=email";
            }
            $.ajax({
                type: 'GET',
                url: layout_mapper_url,
                dataType: 'json',
                success: function (data) {
                    jsondata = data;
                    $(".preview").append("<div onclick=getBlockId(defaultblock1) id='defaultblock1' blockdetails='" + allLayoutFilename[0] + "' name='" + mindbodydataId + "'></div>");
                    $.ajax({
                        type: "GET",
                        url: global_host_address + "DownloadXml?file_name=" + allLayoutFilename[1] + ".xml",
                        dataType: "xml",
                        success: function (xml) {
                            $(xml).find('layout').each(function () {
                                height = $(this).find('container').attr("Height");
                                width = $(this).find('container').attr("Width");
                                var tempWidth = parseInt(width);

//                                $(".preview").css("width", tempWidth + "px");
//                                $(".preview").css("height", height + "px");
//                                $(".preview").css("overflow", "scroll");
                                $(".dataForEmail").css("width", width + "px").css("max-width","549px");
                                $(".preview #defaultblock1").css("width", width + "px");
                                $(".preview #defaultblock1").css("height", height + "px");
                                $(".preview #defaultblock1").css("position", "relative");
//                                alert($(".preview").css("width"));
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
//                                alert(k + " : " + v+ ":"+ type);
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
                                var opacity = $(this).attr("opacity");
                                var width = $(this).attr("width");
                                var height = $(this).attr("height");

                                if (tag === "text")
                                {
                                    var colorName = $(this).attr("font-color-name");
//                                  fontcolor = $(this).attr("font-color");
                                    fontsize = $(this).attr("font-size");
                                    fontstyle = $(this).attr("font-style");
                                    var font = $(this).attr("font-family");
                                    var font_family_name = font.split("+").join(" ");

                                    var fontweight = $(this).attr("font-weight");
                                    var letterspacing = $(this).attr("letter-spacing");
                                    var lineheight = $(this).attr("line-height");

                                    var textalign = $(this).attr("text-align");

                                    var webkittransform = $(this).attr("webkit-transform");
                                    var dropshadow = $(this).attr("H-shadow") + " " + $(this).attr("V-shadow") + " " + $(this).attr("blur") + " " + $(this).attr("text-shadow");

                                    for (var i = 1; i <= 6; i++)
                                    {
                                        if (colorName === "Font-Color-" + i)
                                        {
                                            fontcolor = $("#shapecolorbox" + i).css("background-color");
//                                                              fontcolor=user_preferences_colors.color+""+i; 
                                        }

                                    }
                                    textcount++;
                                    if(typeof(elementdata) === "undefined")
                                      {
                                        elementdata= $(this).attr("defaulttext");
                                       }
                                    $(".preview #defaultblock1").append("<textarea orginial-size='" + fontsize + "' onkeyup=textAreaKeyUp(event,'" + type + "EEEdefaultblock1')  class=textAreas onclick=getTectId(" + type + "EEEdefaultblock1) id=" + type + "EEEdefaultblock1>" + elementdata + "</textarea>");
                                    $("#" + type + "EEEdefaultblock1").css("color", "" + fontcolor)
                                            .css("position", "absolute")
                                            .css("overflow", "hidden")
                                            .css("left", "" + left + "px")
                                            .css("top", "" + top + "px")
                                            .css("width", "" + width)
                                            .css("height", "" + height)
                                            .css("font-style", "" + fontstyle)
                                            .css("font-family", "" + font_family_name)
                                            .css("font-weight", "" + fontweight)
                                            .css("letter-spacing", "" + letterspacing)
                                            .css("opacity", "" + opacity)
                                            .css("text-align", "" + textalign)
                                            .css("text-shadow", "" + dropshadow)
                                            .css("webkit-transform", "rotate(" + webkittransform + "deg)")
                                            .css("resize", "none")
                                            .css("background-color", "inherit")
                                            .css("border", "none")
                                            .css("focus", "none")
                                            .css("line-height", "" + lineheight);

                                    //$("#" + type + "EEEdefaultblock1").autogrow();


                                    //resize of text to fit bound - By Syed Ilyas 26/8/2015
                                    var tempfontsize = parseInt(fontsize.replace("px", ""));
                                    var tempHeight = parseInt(height.replace("px", ""));
                                    $("#" + type + "EEEdefaultblock1").css("font-size", "" + tempfontsize + "px");
                                    //alert(tempfontsize+"#" + type + "EEEdefaultblock1");
                                    //alert($("#" + type + "EEEdefaultblock1").get(0).scrollHeight + ":" + tempHeight);
                                    if ($("#" + type + "EEEdefaultblock1").get(0).scrollHeight > tempHeight)
                                    {
                                        $("#" + type + "EEEdefaultblock1").css("line-height", "initial");
                                        while ($("#" + type + "EEEdefaultblock1").get(0).scrollHeight > tempHeight) {
                                            //alert(tempfontsize);
                                            //    alert($("#" + type + "EEEdefaultblock1").get(0).scrollHeight + ":" + tempHeight);
                                            tempfontsize = tempfontsize - 1;
                                            //     alert(tempfontsize);
                                            $("#" + type + "EEEdefaultblock1").css("font-size", "" + tempfontsize + "px");
                                        }
                                        
//                                        var xxyy = parseInt(tempfontsize);
//                                        xxyy = Math.round(xxyy * 1.2);
//                                        $("#" + type + "EEEdefaultblock1").css("line-height", "" + xxyy + "px");
                                    }
                                    //resize end
                                    var addThis = $("#" + type + "EEEdefaultblock1").get(0).scrollHeight - $("#" + type + "EEEdefaultblock1").height();
                                    $("#" + type + "EEEdefaultblock1").attr("add-this",addThis);
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
                                    $(".imagename").append("<option name=" + background_image + " value="+ type + "EEEdefaultblock1>Image " + count + "</option>");
                                    count++;
                                    $(".preview #defaultblock1").append("<div class=images onclick=getImageid(" + type + "EEEdefaultblock1) id=" + type + "EEEdefaultblock1></div>");
                                    $("#" + type + "EEEdefaultblock1")
                                            .css("color", "" + fontcolor)
                                            .css("position", "absolute")
                                            .css("left", "" + left + "px")
                                            .css("top", "" + top + "px")
                                            .css("background-blend-mode", "" + blendmode)
                                            .css("opacity", "" + opacity)
                                            .css("width", "" + width)
                                            .css("height", "" + height)
                                            .css("background", "" + background_image)
                                            .css("background-repeat", "no-repeat")
                                            .css("background-position", "50% 50%")
                                            .css("background-color", ""+ background_color)
                                            .css("-webkit-background-size", "cover")
                                            .css("position", "absolute")
                                            .css("webkit-filter",""+ filter);

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
                                    $(".preview #defaultblock1").append("<div onclick=getImageid(" + type + "EEEdefaultblock1) id=" + type + "EEEdefaultblock1></div>");
                                    $("#" + type + "EEEdefaultblock1")
                                            .css("color", "" + fontcolor)
                                            .css("margin-left", "" + left + "px")
                                            .css("margin-top", "" + top + "px")
                                            .css("background-blend-mode", "" + blendmode)
                                            .css("opacity", "" + opacity)
                                            .css("width", "" + width)
                                            .css("height", "" + height)
                                            .css("background", "url('/BrndBot/DownloadImage?image_type=USER_LOGO&user_id="+userId+"&image_name="+userLogonmae+"')")
                                            .css("background-repeat", "no-repeat")
                                            .css("background-position", "center center")
                                            .css("background-color", ""+ background_color)
                                            .css("background-size","contain")
                                            .css("position", "absolute")
                                            .css("webkit-filter",""+ filter);
                                }

                                if (tag === "button")
                                {

                                    var src_image = $(this).attr("src").replace("../","");
                                    $(".preview #defaultblock1").append("<div><a href=\"#\" data-reveal-id=\"myModal\"><img src='" + src_image + "' buttonLink = '"+elementdata+"' id=" + type + "EEEdefaultblock1 onclick=getButtonid('" + type + "EEEdefaultblock1') alt='button'/></a>");
                                    $("#" + type + "EEEdefaultblock1").css("left", "" + left + "px")
                                            .css("top", "" + top + "px")
                                            .css("position", "absolute");
                                }

                                if (tag === "block")
                                {
                                    var colorName = $(this).attr("color-name");
                                    var backgroundcolor;
                                    var borderRadius = $(this).attr("border-radius");

//                            var backgroundcolor = $(this).attr("background-color");
                                    for (var i = 1; i <= 6; i++)
                                    {
                                        if (colorName === "Color-" + i)
                                        {
                                            backgroundcolor = $("#shapecolorbox" + i).css("background-color");
//                                                              fontcolor=user_preferences_colors.color+""+i; 
                                        }

                                    }

                                    $(".blockname").append("<option value=" + type + "EEEdefaultblock1>Shape " + blockcount + "</option>");
                                    blockcount++;
                                    $(".preview #defaultblock1").append("<div class=block onclick=getDivId(" + type + "EEEdefaultblock1) id=" + type + "EEEdefaultblock1></div>");
                                    $("#" + type + "EEEdefaultblock1").css("background-color", "" + backgroundcolor)
                                            .css("left", "" + left + "px")
                                            .css("top", "" + top + "px")
                                            .css("width", "" + width)
                                            .css("border-radius", "" + borderRadius)
                                            .css("height", "" + height)
                                            .css("opacity", "" + opacity)
                                            .css("position", "absolute");
                                }

                            });
                            if(count===1 ){$("#imagecontainer").hide();}                                                   
                            if(blockcount===1){$("#shapecontainer").hide();}
                            if(textcount===1){$("#textcontainer").hide();}

                        },
                        error: function (e)
                        {
                            alert("Error in xml file read!");
                        }
                    });


                }
            });
        }
    });
    
    $("#sortUpBlock").click(function () {

        var current = $("#" + $(selectedBlockId).attr("id"));
        current.prev().before(current);
    });
    //$("#deleteBlock").easyconfirm();
    $("#deleteBlock").click(function () {
        new $.flavr({
    content     : 'Are you sure you want to delete this style?',
    dialog      : 'confirm',
    
    onConfirm   : function( $container ){
        var tempSelectedBlockId = $(selectedBlockId).attr("id");
        $("#" + tempSelectedBlockId).remove();
         $(".imagename").find('option').remove().end();
         $(".blockname").find('option').remove().end();
    },
    onCancel    : function( $container ){
        
    }
});
        
        
        
    });
    $("#sortDownBlock").click(function () {

        var current = $("#" + $(selectedBlockId).attr("id"));
        current.next().after(current);
    });
    $("#text").click(function () {
        $("#tabs-1").show();
        $("#tabs-2").hide();
        $("#tabs-3").hide();
        $("#tabs-4").hide();
        $("#tabs-5").hide();
    });

    $("#style").click(function () {
        $("#tabs-1").hide();
        $("#tabs-2").show();
        $("#tabs-3").hide();
        $("#tabs-4").hide();
        $("#tabs-5").hide();
    });
    $("#block").click(function () {
        $("#tabs-1").hide();
        $("#tabs-2").hide();
        $("#tabs-3").show();
        $("#tabs-4").hide();
        $("#tabs-5").hide();
    });
    $("#data").click(function () {
        $("#tabs-1").hide();
        $("#tabs-2").hide();
        $("#tabs-3").hide();
        $("#tabs-4").show();
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



//    $('#cropButton').on("click", function () {
//        // grab width and height of .crop-img for canvas
//        var width = $('.crop-container').width() - 80, // new image width
//                height = $('.crop-container').height() - 80;  // new image height
//
//        $('canvas').remove();
//        $('.default').after('<canvas width="' + width + '" height="' + height + '" id="canvas"/>');
//
//        var ctx = document.getElementById('canvas').getContext('2d'),
//                img = new Image,
//                w = coordinates(one).w,
//                h = coordinates(one).h,
//                x = coordinates(one).x,
//                y = coordinates(one).y;
//
//        img.src = coordinates(one).image;
//
//        img.onload = function () {
//
//            // draw image
////            alert("crop test");
//            ctx.drawImage(img, x, y, w, h, 0, 0, width, height);
//
//            // display canvas image
//            $('canvas').addClass('output').show().delay('4000').fadeOut('slow');
//
//            // save the image to server
//            $.ajax({
//                type: "post",
//                dataType: "json",
//                url: "CropImage",
//                data: {image: canvas.toDataURL()}
//            })
//                    .done(function (data) {
//
//                        // You can pull the image URL using data.url, e.g.:
//                        // $('body').append('<img src="'+data.url+'" />');
//
//                    });
//
//        }
//
//    });

//    $('.uploadfile').change(function () {
//
//        loadImageFile();
//
//        // resets input file
//        $('.uploadfile').wrap('<form>').closest('form').get(0).reset();
//        $('.uploadfile').unwrap();
//
//    });


    //  get input type=file IMG through base64 and send it to the cropper
    // --------------------------------------------------------------------------

//    oFReader = new FileReader(), rFilter = /^(?:image\/bmp|image\/cis\-cod|image\/gif|image\/ief|image\/jpeg|image\/jpeg|image\/jpeg|image\/pipeg|image\/png|image\/svg\+xml|image\/tiff|image\/x\-cmu\-raster|image\/x\-cmx|image\/x\-icon|image\/x\-portable\-anymap|image\/x\-portable\-bitmap|image\/x\-portable\-graymap|image\/x\-portable\-pixmap|image\/x\-rgb|image\/x\-xbitmap|image\/x\-xpixmap|image\/x\-xwindowdump)$/i;
//    function loadImageFile() {
//
//        if (document.getElementById("uploadfile").files.length === 0)
//            return;
//
//        var oFile = document.getElementById("uploadfile").files[0];
//
//        if (!rFilter.test(oFile.type)) {
//            return;
//        }
//
//        oFReader.readAsDataURL(oFile);
//    }
//
//    oFReader.onload = function (oFREvent) {
//        $('.preview').html('<div class="default"><div class="cropMain"></div>');
//        // create new object crop
//        // you may change the "one" variable to anything
//        one = new CROP();
//
//        // link the .default class to the crop function
//        one.init('.default');
//
//        // load image into crop
//        one.loadImg(oFREvent.target.result);
//
//    };

});
function textAreaKeyUp(event,id) {
   var orginialSize = Math.round(parseInt($("#"+id).attr("orginial-size").replace("px","")));
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
 
//   var xxyy = parseInt(tempfontsize);
//        xxyy = Math.round(xxyy * 1.2);
        //$("#" + id).css("line-height", "" + xxyy + "px");
    }
    else if ($("#" + id).get(0).scrollHeight > ($("#"+id).height()+4))
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
//        var xxyy = parseInt(tempfontsize);
//        xxyy = Math.round(xxyy * 1.2);
//        $("#" + id).css("line-height", "" + xxyy + "px");
        //alert($("#" + id).get(0).scrollHeight + ">" + ($("#"+id).height()+4));
    }
        //alert("last"+$("#" + id).get(0).scrollHeight + ">" + ($("#"+id).height()+4));
        //$("#" + id).css("line-height", "" + xxyy + "px");
}
$(function(){
    $("#buttonOKURL").click(function(){
       
       $("#"+selectedButtonId).attr("buttonlink",$("#buttonURLText").val());
        $(".close-reveal-modal").click();
    });
});
function getBlockId(id) {
    selectedBlockId = id;
    var blockCount=1;
    var imageCount=1;
    var textCount=1;
    $(".imagename").find('option').remove().end();
    $(".blockname").find('option').remove().end();
    
   $('#'+selectedBlockId.id).children().each(function () {
       $("#edt").trigger('click');
        var Id=this.id;
        var className=$("#"+Id).attr("class");
        switch (className){
            case 'textAreas': textCount++;
                                break;
            case 'block':   
                            $(".blockname").append("<option value="+ Id +">Shape " + blockCount + "</option>");
                            blockCount++;
                            break;              
                            
            case 'images': 
                            var backgroundImage=$("#"+Id).css("background-image");
                            $(".imagename").append("<option name="+backgroundImage+" value="+ Id +">Image " + imageCount + "</option>");
                            imageCount++;
                            break; 
                        
            
        }
        if(blockCount <=1)
            $("#shapecontainer").hide();
        else
             $("#shapecontainer").show();
         if(imageCount <=1)
            $("#imagecontainer").hide();
        else{
        $("#imagecontainer").show();
        $("#filtercontainer").hide();
        $("#imageGallery").hide();
        }
        if(textCount <=1)
        $("#textcontainer").hide();
        else
          $("#textcontainer").show();
});
var mindbodydatacheck=$(selectedBlockId).attr("name");
if(typeof mindbodydatacheck != 'undefined' ){
    mindbodydataId = $(selectedBlockId).attr("name").toString();
}
    $("#tabs-1").show();
    $("#tabs-2").hide();
    $("#tabs-3").hide();
    $("#tabs-4").hide();
}
function getButtonid(ID) {
    
    //alert($("#"+ID).attr("buttonlink"));
    selectedButtonId = ID;
    $("#buttonURLText").val($("#"+ID).attr("buttonlink"));
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

function getImgId(divid) {
    selectedImgDiv = divid;
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




window.onload = function () {
    //get elements
    var f = 1,
            cvrt1 = document.getElementById('convert1'),
            cvrt2 = document.getElementById('convert2'),
            cvrt3 = document.getElementById('convert3'),
            cvrt4 = document.getElementById('convert4'),
            cvrt5 = document.getElementById('convert5'),
            cvrt6 = document.getElementById('convert6');

    //button click event
    cvrt1.onclick = function () {
        if (f) {
            $("#img").css("-webkit-filter", "grayscale(100%)");
            f = 0;
        }
        else {
            $("#img").css("-webkit-filter", "");

            f = 1;

        }
    };
    cvrt2.onclick = function () {
        if (f) {
            $("#img").css("-webkit-filter", "textured(100%)");
            f = 0;
        }
        else {
            $("#img").css("-webkit-filter", "");

            f = 1;

        }
    };
    cvrt3.onclick = function () {
        if (f) {
            $("#img").css("-webkit-filter", "brightness(150%)");
            f = 0;
        }
        else {
            $("#img").css("-webkit-filter", "");

            f = 1;

        }
    };
    cvrt4.onclick = function () {
        if (f) {
            $("#img").css("-webkit-filter", "grayscale(100%)");
            f = 0;
        }
        else {
            $("#img").css("-webkit-filter", "");

            f = 1;

        }
    };
    cvrt5.onclick = function () {
        if (f) {
            $("#img").css("-webkit-filter", "sepia(100%)");
            f = 0;
        }
        else {
            $("#img").css("-webkit-filter", "");

            f = 1;

        }
    };
    cvrt6.onclick = function () {
        if (f) {
            $("#img").css("-webkit-filter", "Statue(100%)");
            f = 0;
        }
        else {
            $("#img").css("-webkit-filter", "");

            f = 1;

        }
    };
};



