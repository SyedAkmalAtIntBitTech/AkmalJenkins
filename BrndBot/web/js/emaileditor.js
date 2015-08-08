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
//
//    alert("clicked");
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

    $(".blankcolor-box").click(function () {
        var color = $("#" + this.id).css("background-color");
        $("#selectedshapecolorbox").css("background-color", "" + color);
        $("#" + selectedDivId).css("background-color", "" + color);
    });

    $("#fontsize").change(function () {
//          alert($("#fontsize").val());
        $("#" + selectedTextareaId).css("font-size", "" + $("#fontsize").val());
    });

    $("#fontname").change(function () {
//            alert($(this).val());
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
        type: 'GET',
        url: 'MindBodyDetailServlet?mindbody_id=' + mindbodydataId,
        data: {get_param: 'value'},
        dataType: 'json',
        success: function (data) {
        jsondata = data;
        $(".preview").append("<div onclick=getBlockId(block1) id='block1'></div>")
            $.ajax({
                type: "GET",
                url: "xml/layout.xml",
                dataType: "xml",
                success: function (xml) {
                    $(xml).find('layout').each(function () {
                        height = $(this).find('container').attr("Height");
                        width = $(this).find('container').attr("Width");
                        
                        var tempWidth =  parseInt(width)+30;
                        
                        $(".preview").css("width", tempWidth + "px");
                        $(".preview").css("height", height*2 + "px");
                        $(".preview").css("overflow", "scroll");
                        $(".preview #block1").css("width", width + "px");
                        $(".preview #block1").css("height", height + "px");
                    }

                    );

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
                        if (tag === "text")
                        {

                            fontcolor = $(this).attr("font-color");
                            fontsize = $(this).attr("font-size");
                            fontstyle = $(this).attr("font-style");
                            var fontweight = $(this).attr("font-weight");
                            var letterspacing = $(this).attr("letter-spacing");
                            var lineheight = $(this).attr("line-height");

                            var textalign = $(this).attr("text-align");

                            var webkittransform = $(this).attr("webkit-transform");
                            var dropshadow = $(this).attr("H-shadow") + " " + $(this).attr("V-shadow") + " " + $(this).attr("blur") + " " + $(this).attr("text-shadow");
//                    alert($(this).attr("text-shadow"));
                            $(".preview #block1").append("<div><textarea class=textAreas readonly onclick=getTectId(block1"+type+") id=block1" + type + ">" + elementdata + "</textarea>");
                            $("#" + type).css("color", "" + fontcolor)
                                        .css("position", "relative")
                                        .css("left", "" + left + "px")
                                        .css("top", "" + top + "px")
                                        .css("font-size", "" + fontsize)
                                        .css("font-style", "" + fontstyle)
                                        .css("font-weight", "" + fontweight)
                                        .css("letter-spacing", "" + letterspacing)
                                        .css("line-height", "" + lineheight)
                                        .css("opacity", "" + opacity)
                                        .css("text-align", "" + textalign)
                                        .css("text-shadow", "" + dropshadow)
                                        .css("webkit-transform", "rotate(" + webkittransform + "deg)")
                                        .css("resize", "none")
                                        .css("border", "none")
                                        .css("focus", "none");
                        }

                        if (tag === "image")
                        {
                            var blendmode = $(this).attr("background-blend-mode");
                            var width = $(this).attr("width");
                            var height = $(this).attr("height");
//                    alert("image");
                           $(".preview #block1").append("<div onclick=getImageid(block1" + type + ") id=block1" + type + " ></div>");
                            $("#block1" + type)
                                    .css("color", "" + fontcolor)
                                    .css("left", "" + left + "px")
                                    .css("top", "" + top + "px")
                                    .css("background-blend-mode", "" + blendmode)
                                    .css("opacity", "" + opacity)
                                    .css("width", "" + width)
                                    .css("height", "" + height)
                                    .css("background","url(http://www.hdwallpapersimages.com/wp-content/uploads/2014/01/Winter-Tiger-Wild-Cat-Images.jpg)")
                                    .css("background-repeat", "no-repeat")
                                    .css("-webkit-background-size","contain")
                                    .css("position", "relative");   
                        }

                        if (tag === "button")
                        {
//                            alert("button");
                            $(".preview #block1").append("<div><img src='" + elementdata + "'id=block1" + type + " alt='button'/>");
                            $("#block1" + type).css("left", "" + left + "px")
                                    .css("top", "" + top + "px")
                                    .attr("src", "buttons/button1.png")
                                    .css("position", "relative");   
                        }

                        if (tag === "block")
                        {
//                  alert("block");
                            var width = $(this).attr("width");
                            var height = $(this).attr("height");
                            var backgroundcolor = $(this).attr("background-color");
//                 alert(backgroundcolor);
                            $(".preview #block1").append("<div onclick=getDivId(block1" + type + ") id=block1" + type + "></div>");
                            $("#block1" + type).css("background-color", "" + backgroundcolor)
                                    .css("left", "" + left + "px")
                                    .css("top", "" + top + "px")
                                    .css("width", "" + width)
                                    .css("height", "" + height)
                                    .css("position", "relative");   
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
$("#sortUpBlock").click(function(){
    alert("up clicked"+selectedBlockId);
    var current = $("#"+$(selectedBlockId).attr("id"));
  current.prev().before(current);
});
$("#deleteBlock").click(function(){
    $("#"+$(selectedBlockId).attr("id")).remove();
});
$("#sortDownBlock").click(function(){
    alert("down clicked"+$(selectedBlockId).attr("id"));
    var current = $("#"+$(selectedBlockId).attr("id"));
  current.next().after(current);
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
    $("#block").click(function () {
        $("#tabs-1").hide();
        $("#tabs-2").hide();
        $("#tabs-3").show();
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
    var status = "false";
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
function getBlockId(id) {
    selectedBlockId = id;
    alert($(id).attr("id"));
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



