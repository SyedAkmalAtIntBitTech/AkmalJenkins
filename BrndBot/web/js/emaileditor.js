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
        $("#tabs-4").hide();
    $('.custom-color-box-text').colpick({
        colorScheme: 'dark',
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
        colorScheme: 'dark',
        layout: 'rgbhex',
        color: 'ff8800',
        onSubmit: function (hsb, hex, rgb, el) {
           $("#selectedshapecolorbox").css('background-color', '#' + hex);
//            place block selected block
            var blockId=$(".blockname").val();
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
 $(".blankcolor-box1").click(function () {
        var display= $("#pickColorForText").css("display");
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

    $("#fontname").change(function () {
        $("#" + selectedTextareaId).css("font-family", "" + $("#fontname").val());
    });

    

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

    $.ajax({
        type: 'GET',
        url: 'MindBodyDetailServlet?mindbody_id=' + mindbodydataId + '&model_mapper_id=' + allLayoutFilename[0] + "&editor_type=email",
        data: {get_param: 'value'},
        dataType: 'json',
        success: function (data) {
        jsondata = data;
        $(".preview").append("<div onclick=getBlockId(defaultblock1) id='defaultblock1' blockdetails='"+ allLayoutFilename[0] +"' name='"+mindbodydataId+"'></div>");
            $.ajax({
                type: "GET",
                url: global_host_address + "DownloadXml?file_name=" + allLayoutFilename[1] + ".xml",
                dataType: "xml",
                success: function (xml) {
                    $(xml).find('layout').each(function () {
                        height = $(this).find('container').attr("Height");
                        width = $(this).find('container').attr("Width");
                        
                        var tempWidth =  parseInt(width)+30;
                        
                        $(".preview").css("width", tempWidth + "px");
                        $(".preview").css("height", height*2 + "px");
                        $(".preview").css("overflow", "scroll");
                        $(".preview #defaultblock1").css("width", width + "px");
                        $(".preview #defaultblock1").css("height", height + "px");
                        $(".preview #defaultblock1").css("position", "relative");
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
                        var opacity = $(this).attr("opacity");
                        var width = $(this).attr("width");
                        var height = $(this).attr("height");
                            
                                if (tag === "text")
                                {
                                    var colorName = $(this).attr("font-color-name");
//                                  fontcolor = $(this).attr("font-color");
                                    fontsize = $(this).attr("font-size");
                                    fontstyle = $(this).attr("font-style");
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

                                     $(".preview #defaultblock1").append("<div><textarea class=textAreas onclick=getTectId("+type+"EEEdefaultblock1) id=" + type + "EEEdefaultblock1>" + elementdata + "</textarea>");
                                     $("#" + type + "EEEdefaultblock1").css("color", "" + fontcolor)
                                            .css("position", "absolute")
                                            .css("overflow", "hidden")
                                            .css("left", "" + left + "px")
                                            .css("top", "" + top + "px")
                                            .css("width", "" + width)
                                            
                                            .css("height", "" + height)
                                            
                                            .css("font-style", "" + fontstyle)
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
                                     var tempfontsize = parseInt(fontsize.replace("px",""));
                                     var tempHeight = parseInt(height.replace("px",""));
                                     $("#" + type + "EEEdefaultblock1").css("font-size", "" + tempfontsize +"px");
                                     //alert(tempfontsize+"#" + type + "EEEdefaultblock1");
                                     //alert($("#" + type + "EEEdefaultblock1").get(0).scrollHeight + ":" + tempHeight);
                                     if($("#" + type + "EEEdefaultblock1").get(0).scrollHeight > tempHeight)
                                     {
                                         $("#" + type + "EEEdefaultblock1").css("line-height", "initial");
                                     while ( $("#" + type + "EEEdefaultblock1").get(0).scrollHeight > tempHeight) {
                                         //alert(tempfontsize);
                                         //    alert($("#" + type + "EEEdefaultblock1").get(0).scrollHeight + ":" + tempHeight);
                                            tempfontsize = tempfontsize - 1;
                                       //     alert(tempfontsize);
                                           $("#" + type + "EEEdefaultblock1").css("font-size", "" + tempfontsize +"px");
                                     }
                                      var xxyy = parseInt(tempfontsize);
                                     xxyy = Math.round(xxyy * 1.2);
                                      $("#" + type + "EEEdefaultblock1").css("line-height",""+xxyy+"px");
                                     }
                                     //resize end
                                     
                                    
                                }

                        if (tag === "image")
                        {
                            var blendmode = $(this).attr("background-blend-mode");
                            var background_image = $(this).attr("background-image");
                            $(".imagename").append("<option value="+background_image+">Image "+count+"</option>");
                              count++;
                           $(".preview #defaultblock1").append("<div onclick=getImageid(" + type + "EEEdefaultblock1) id=" + type + "EEEdefaultblock1></div>");
                            $("#" + type +"EEEdefaultblock1")
                                    .css("color", "" + fontcolor)
                                    .css("position", "absolute")
                                    .css("left", "" + left + "px")
                                    .css("top", "" + top + "px")
                                    .css("background-blend-mode", "" + blendmode)
                                    .css("opacity", "" + opacity)
                                    .css("width", "" + width)
                                    .css("height", "" + height)
                                    .css("background",""+background_image)
                                    .css("background-repeat", "no-repeat")
                                    .css("background-position", "center center")
                                    .css("position", "absolute");
          
                        }
                        
                        if (tag === "logo")
                        {
                            var background_image = $(this).attr("background-image");
                            var blendmode = $(this).attr("background-blend-mode");
                            $(".preview #defaultblock1").append("<div onclick=getImageid(" + type + "EEEdefaultblock1) id=" + type + "EEEdefaultblock1></div>");
                            $("#" + type +"EEEdefaultblock1")
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
                            $(".preview #defaultblock1").append("<div><img src='" + elementdata + "'id=" + type + "EEEdefaultblock1 alt='button'/>");
                            $("#" + type + "EEEdefaultblock1").css("left", "" + left + "px")
                                    .css("top", "" + top + "px")
                                    .attr("src", "buttons/button1.png")
                                    .css("position", "absolute");   
                        }

                        if (tag === "block")
                        {
                            var colorName=$(this).attr("color-name");
                            var backgroundcolor;
                           var borderRadius = $(this).attr("border-radius");
                           
//                            var backgroundcolor = $(this).attr("background-color");
                                for(var i=1;i<=6; i++)
                                               {
                                                  if(colorName === "Color-"+i)
                                                     {
                                                       backgroundcolor= $("#shapecolorbox"+i).css("background-color");
//                                                              fontcolor=user_preferences_colors.color+""+i; 
                                                      }
                                                            
                                                } 

                            $(".blockname").append("<option value="+type+"EEEdefaultblock1>Block "+blockcount+"</option>");
                             blockcount++;
                            $(".preview #defaultblock1").append("<div onclick=getDivId(" + type + "EEEdefaultblock1) id=" + type + "EEEdefaultblock1></div>");
                            $("#" + type + "EEEdefaultblock1").css("background-color", "" + backgroundcolor)
                                    .css("left", "" + left + "px")
                                    .css("top", "" + top + "px")
                                    .css("width", "" + width)
                                    .css("border-radius", "" + borderRadius)
                                    .css("height", "" + height)
                                    .css("opacity", "" + opacity)
                                    .css("position", "absolute");   
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
$("#sortUpBlock").click(function(){

    var current = $("#"+$(selectedBlockId).attr("id"));
  current.prev().before(current);
});
 $("#deleteBlock").easyconfirm();
$("#deleteBlock").click(function(){
    var tempSelectedBlockId = $(selectedBlockId).attr("id");
    $("#"+tempSelectedBlockId).remove();
});
$("#sortDownBlock").click(function(){

    var current = $("#"+$(selectedBlockId).attr("id"));
  current.next().after(current);
});
    $("#text").click(function () {
        $("#tabs-1").show();
        $("#tabs-2").hide();
        $("#tabs-3").hide();
        $("#tabs-4").hide();
    });

    $("#style").click(function () {
        $("#tabs-1").hide();
        $("#tabs-2").show();
        $("#tabs-3").hide();
        $("#tabs-4").hide();
    });
    $("#block").click(function () {
        $("#tabs-1").hide();
        $("#tabs-2").hide();
        $("#tabs-3").show();
        $("#tabs-4").hide();
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
//function textAreaKeyUp(id) {
//    alert(id);
//    var fontsize = parseInt($(id).css('font-size').replace("px", ""));
//    alert("keyed1"+$(id).attr("id"));
//    alert($(id).get(0).scrollHeight + "<=" + $(id).height()+"Fontsize:"+fontsize);
//    if($(id).get(0).scrollHeight <= ($(id).height()+4))
//    {
//        
//        while ($(id).get(0).scrollHeight <= ($(id).width()+4) && fontsize <= parseInt($(id).css('font-size').replace("px", ""))) 		  {
//alert($(id).get(0).scrollHeight + "<=" + $(id).height()+"Fontsize:"+fontsize);
//            fontsize = fontsize + 1;
//            $(id).css('font-size', "" + fontsize + "px");
//
//        }
//
//        fontsize = fontsize - 1;
//        $(id).css('font-size', "" + fontsize + "px");
//
//    } else {
//        alert($(id).get(0).scrollHeight +":"+ $(id).height());
//        while ($(id).get(0).scrollHeight > ($(id).height()+4)) {
//            fontsize = fontsize - 1;
//            $(id).css('font-size', "" + fontsize + "px");
//        }
//    }
//}

function getBlockId(id) {
    
    selectedBlockId = id;
    
    mindbodydataId = $(selectedBlockId).attr("name").toString();
     $("#tabs-1").show();
        $("#tabs-2").hide();
        $("#tabs-3").hide();
        $("#tabs-4").hide();
}

function getTectId(id) {
    $("textarea").click(function () {
        selectedTextareaId = id.id;
    });
}



function getDivId(divid) {
    selectedDivId = divid.id;
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



