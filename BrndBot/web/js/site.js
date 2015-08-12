var x, y;
var bold;
var widthContainer;
var heightContainer;
var containerY, containerX;
var selectedTextID;
var addTextCount, addImageCount, addButtonCount, addBlockCount, addElementCount,addLogocount;
var textareadetails = [];
var selectElementsArray = [];
var allElementsTextArray = [];
var allElementsValueArray = [];
var addElementsArray = [];
var textcount = 1;
$(document).ready(function () {
    bold = false;
    widthContainer = "0px";
    heightContainer = "0px";

    x = 0;
    y = 0;
    selectedTextID = "none";
    addTextCount = 1;
    addImageCount = 1;
    addButtonCount = 1;
    addBlockCount = 1;
    addElementCount = 0;
});

$(document).ready(function () {
    $(".basic").spectrum({
        preferredFormat: "hex",
        showPalette: true,
        palette: [
            ["#000", "#444", "#666", "#999", "#ccc", "#eee", "#f3f3f3", "#fff"],
            ["#f00", "#f90", "#ff0", "#0f0", "#0ff", "#00f", "#90f", "#f0f"],
            ["#f4cccc", "#fce5cd", "#fff2cc", "#d9ead3", "#d0e0e3", "#cfe2f3", "#d9d2e9", "#ead1dc"],
            ["#ea9999", "#f9cb9c", "#ffe599", "#b6d7a8", "#a2c4c9", "#9fc5e8", "#b4a7d6", "#d5a6bd"],
            ["#e06666", "#f6b26b", "#ffd966", "#93c47d", "#76a5af", "#6fa8dc", "#8e7cc3", "#c27ba0"],
            ["#c00", "#e69138", "#f1c232", "#6aa84f", "#45818e", "#3d85c6", "#674ea7", "#a64d79"],
            ["#900", "#b45f06", "#bf9000", "#38761d", "#134f5c", "#0b5394", "#351c75", "#741b47"],
            ["#600", "#783f04", "#7f6000", "#274e13", "#0c343d", "#073763", "#20124d", "#4c1130"]
        ]
    });
    $("#colorPick").spectrum({
        change: function (color) {
            $("#" + selectedTextID).css("color", color.toHexString());
            //alert(color.toHexString()); 
        },
        preferredFormat: "hex",
        showPalette: true,
        palette: [
            ["#000", "#444", "#666", "#999", "#ccc", "#eee", "#f3f3f3", "#fff"],
            ["#f00", "#f90", "#ff0", "#0f0", "#0ff", "#00f", "#90f", "#f0f"],
            ["#f4cccc", "#fce5cd", "#fff2cc", "#d9ead3", "#d0e0e3", "#cfe2f3", "#d9d2e9", "#ead1dc"],
            ["#ea9999", "#f9cb9c", "#ffe599", "#b6d7a8", "#a2c4c9", "#9fc5e8", "#b4a7d6", "#d5a6bd"],
            ["#e06666", "#f6b26b", "#ffd966", "#93c47d", "#76a5af", "#6fa8dc", "#8e7cc3", "#c27ba0"],
            ["#c00", "#e69138", "#f1c232", "#6aa84f", "#45818e", "#3d85c6", "#674ea7", "#a64d79"],
            ["#900", "#b45f06", "#bf9000", "#38761d", "#134f5c", "#0b5394", "#351c75", "#741b47"],
            ["#600", "#783f04", "#7f6000", "#274e13", "#0c343d", "#073763", "#20124d", "#4c1130"]
        ]
    });

    $.ajax({
        type: "GET",
        url: "elements.xml",
        dataType: "xml",
        success: function (xml) {
//Parse the givn XML
//            alert("from ajax");
            var count = 0;
            $(xml).find('elements').each(function () {

                var elementText = $(this).find('element').attr("tag");
                var elementValue = $(this).find('element').attr("type");
                allElementsTextArray[count] = elementText;
                allElementsValueArray[count++] = elementValue;
                $("#elementText").append(new Option(elementText, elementValue));

            }

            );
        },
        error: function (e)
        {
            $("#elementText").append(new Option("header1", "label"));
            $("#elementText").append(new Option("backgroundimage", "image"));
            $("#elementText").append(new Option("button1", "button"));
            $("#elementText").append(new Option("colorblock1", "colorblock"));
        }
    });

});

$(document).ready(function () {
    function componentToHex(c) {
        var hex = c.toString(16);
        return hex.length == 1 ? "0" + hex : hex;
    }

    function rgbToHex(r, g, b) {
        return componentToHex(r) + componentToHex(g) + componentToHex(b);
    }

    function addElements(addText)
    {
        //alert(addText);
        addElementsArray[addElementCount++] = addText;
        //clear elementText dropdown code goes here
        $("#elementText").find('option').remove();

        var checkAdded = false;
        for (var i = 0; i < allElementsTextArray.length; i++)
        {
            checkAdded = false;
            for (var j = 0; j < addElementsArray.length; j++)
            {

                if (allElementsTextArray[i] == addElementsArray[j])
                    checkAdded = true;

            }
            if (!checkAdded)
            {

                $("#elementText").append(new Option(allElementsTextArray[i], allElementsValueArray[i]));
            }
        }
    }

    function addDeletedElement()
    {
        $("#elementText").find('option').remove();
        var checkAdded = false;
        for (var i = 0; i < allElementsTextArray.length; i++)
        {
            checkAdded = false;
            for (var j = 0; j < addElementsArray.length; j++)
            {
                if (allElementsTextArray[i] == addElementsArray[j])
                    checkAdded = true;

            }
            if (!checkAdded)
            {

                $("#elementText").append(new Option(allElementsTextArray[i], allElementsValueArray[i]));
            }
        }
    }

    function deleteElements(deleteText)
    {

        var duplicateAddElementsArray = addElementsArray;
        addElementCount = 0;
        addElementsArray = [];

        for (var i = 0; i < duplicateAddElementsArray.length; i++)
        {


            if (duplicateAddElementsArray[i] != deleteText)
            {

                addElementsArray[addElementCount++] = duplicateAddElementsArray[i];

            }
        }

        addDeletedElement();


    }

    //changing container from resizable(code above) to fixed using text boxes
    $(function () {
        function position() {
            $(".container").css("width", $("#containerWidth").val() + "px");
            $(".container").css("height", $("#containerHeight").val() + "px");
        }
        $("#containerWidth, #containerHeight").spinner({
            min: 1,
            max: 1000,
            step: 1,
            start: 400,
            change: position,
            stop: position
        });

    });
    $("#containerButton").click(function () {
        $(".container").css("width", $("#containerWidth").val() + "px");
        $(".container").css("height", $("#containerHeight").val() + "px");
    });

    $("#rotate").keyup(function (e) {

        if (e.keyCode == 13)
        {
            $("#" + selectedTextID).css("-webkit-transform", "rotate(" + $("#rotate").val() + "deg)");
        }
//alert(selectedTextID + " - " + e.keyCode);
    });

    $("#opacity").keyup(function (e) {

        if (e.keyCode == 13)
        {
            $("#" + selectedTextID).css("opacity", $("#opacity").val());
        }
//alert(selectedTextID + " - " + e.keyCode);
    });

    $(function () {
        function dropShadow() {

            //	alert($("#dropShadowColorPick").val());
            $("#" + selectedTextID).css("text-shadow", $("#hShadow").val() + "px " + $("#vShadow").val() + "px " + $("#blur").val() + "px" + " " + $("#dropShadowColorPick").val());

        }
        $("#blur, #hShadow , #vShadow").spinner({
            min: 1,
            max: 99,
            step: 1,
            change: dropShadow,
            stop: dropShadow
        });
        $("#dropShadowColorPick").spectrum({
            change: function (color) {
                dropShadow();
                //$("#"+selectedTextID).css("color",color.toHexString());
                //alert(color.toHexString()); 
            },
            preferredFormat: "hex",
            showPalette: true,
            palette: [
                ["#000", "#444", "#666", "#999", "#ccc", "#eee", "#f3f3f3", "#fff"],
                ["#f00", "#f90", "#ff0", "#0f0", "#0ff", "#00f", "#90f", "#f0f"],
                ["#f4cccc", "#fce5cd", "#fff2cc", "#d9ead3", "#d0e0e3", "#cfe2f3", "#d9d2e9", "#ead1dc"],
                ["#ea9999", "#f9cb9c", "#ffe599", "#b6d7a8", "#a2c4c9", "#9fc5e8", "#b4a7d6", "#d5a6bd"],
                ["#e06666", "#f6b26b", "#ffd966", "#93c47d", "#76a5af", "#6fa8dc", "#8e7cc3", "#c27ba0"],
                ["#c00", "#e69138", "#f1c232", "#6aa84f", "#45818e", "#3d85c6", "#674ea7", "#a64d79"],
                ["#900", "#b45f06", "#bf9000", "#38761d", "#134f5c", "#0b5394", "#351c75", "#741b47"],
                ["#600", "#783f04", "#7f6000", "#274e13", "#0c343d", "#073763", "#20124d", "#4c1130"]
            ]
        });
    });

    $(function () {
        function leading() {

            //	alert($("#dropShadowColorPick").val());
            $("#" + selectedTextID).css("line-height", $("#lineHeight").val() + "px");

        }
        $("#lineHeight").spinner({
            min: 5,
            max: 100,
            step: 5,
            change: leading,
            stop: leading
        });
    });

    $(function () {
        function kerning() {

            //	alert($("#dropShadowColorPick").val());
            $("#" + selectedTextID).css("letter-spacing", $("#letterSpace").val() + "px");

        }
        $("#letterSpace").spinner({
            min: 0,
            max: 100,
            step: 5,
            change: kerning,
            stop: kerning
        });
    });


    $("#boldButton").click(function () {
        //alert("button");
        if ($("#" + selectedTextID).css("font-weight") != "bold")
        {
            $("#" + selectedTextID).css({"font-weight": "bold"});
            $("#boldButton").css("background-color", "99b1f2");
        }
        else
        {
            //bold = false;
            $("#" + selectedTextID).css({"font-weight": "normal"});
            $("#boldButton").css("background-color", "inherit");
        }
    });
    $("#italicButton").click(function () {
        //alert("button");
        if ($("#" + selectedTextID).css("font-style") != "italic")
        {
            $("#" + selectedTextID).css({"font-style": "italic"});
            $("#italicButton").css("background-color", "99b1f2");
        }
        else
        {
            //bold = false;
            $("#" + selectedTextID).css({"font-style": "normal"});
            $("#italicButton").css("background-color", "inherit");
        }
    });
    $("#textSize").change(function () {
        //alert($( "#textSize" ).val());
        $("#" + selectedTextID).css("font-size", $("#textSize").val() + "px");
    });
//    function changeFont(){
//        alert("text");
//          var font_name = $("#textFontFamily").find('option:selected').text();
//            $("#" + selectedTextID).css("font-family", $("#textFontFamily").val());
//    }

    $("#textFontFamily").change(function () {
//        alert($("#textFontFamily").val());
        $("#" + selectedTextID).css("font-family", $("#textFontFamily").val());
    });
     $("#fontColor").change(function () {
//        alert($("#fontColor").val());
//            alert($("#fontColor option:selected").text());
        $("#" + selectedTextID).css("color", $("#fontColor").val());
    });
    
//confirmation plugin is used to show model dialogue box
    $("#deleteTextButton").easyconfirm();
    $("#deleteTextButton").click(function () {
        $("#" + selectedTextID).parent().remove();
        deleteElements(selectedTextID);
    });
    $("#deleteImageButton").easyconfirm();
    $("#deleteImageButton").click(function () {
        $("#" + selectedTextID).parent().remove();
        deleteElements(selectedTextID);
    });
    $("#deleteButton").easyconfirm();
    $("#deleteButton").click(function () {
        $("#" + selectedTextID).parent().remove();
        deleteElements(selectedTextID);
    });

    $("#deleteBlockButton").easyconfirm();
    $("#deleteBlockButton").click(function () {
        $("#" + selectedTextID).parent().remove();
        deleteElements(selectedTextID);
    });

    $("#deleteLogoButton").easyconfirm();
    $("#deleteLogoButton").click(function () {
        $("#" + selectedTextID).parent().remove();
        deleteElements(selectedTextID);
    });
    $(function () {
        $("#tabs").tabs();
    });
    function reloadTabs(tab)
    {

        $("#tabs").tabs("option", "active", parseInt(tab));

    }
    $("#addBlockButton").click(function () {
        //alert("<div class=\"draggableBlock\"><div width=\"50px\" height=\"100px\" title=" + $("#elementText").val() +" id=\"block" + addBlockCount + "></div></div>");
        $(".container").append("<div class=\"draggableBlock\"><div id=\"" + $("#elementText").find('option:selected').text() + "\"></div></div>");
        selectedTextID = $("#elementText").find('option:selected').text();
        addBlockCount++;

        addElements($("#elementText").find('option:selected').text());
        //$("#"+selectedTextID).css("background","url('http://www.hdwallpapersimages.com/wp-content/uploads/images/Child-Girl-with-Sunflowers-Images.jpg')");
        $("#" + selectedTextID).css("background-color", "#000000");
        $("#" + selectedTextID).css("width", "80px");
        $("#" + selectedTextID).css("height", "40px");
        //$("#"+selectedTextID).css("background-size","contain");
//$("#"+selectedTextID).css("-webkit-background-size","contain");
//$("#"+selectedTextID).css("background-repeat","no-repeat");
        //$("#"+selectedTextID).css("mix-blend-mode","screen");
        reloadTabs(3);
        addDefault();
        $("#blockButton").click(function () {
            $("#" + selectedTextID).css("width", $("#blockWidth").val());
            $("#" + selectedTextID).css("height", $("#blockHeight").val());
            $("#" + selectedTextID).css("opacity", $("#opacityBlock").val());
            $("#" + selectedTextID).css("background-color", $("#blockColor").val());

        });


        $(function () {
            $(".dropShadowBlock").spinner({
                min: 1,
                max: 99,
                step: 1,
                change: dropShadowBlock,
                stop: dropShadowBlock
            });
            function dropShadowBlock() {
                var dropShadowBlock = "drop-shadow(" + $("#hShadowDropShadowBlock").val() + "px " + $("#vShadowDropShadowBlock").val() + "px " + $("#blurDropShadowBlock").val() + "px" + " " + $("#dropShadowColorPickBlock").val() + ")";
//alert(dropShadowFilter1);            
                $("#" + selectedTextID).css('filter', dropShadowBlock)
                        .css('-webkit-filter', dropShadowBlock);
            }

            $("#dropShadowColorPickBlock").spectrum({
                change: dropShadowBlock,
                preferredFormat: "hex",
                showPalette: true,
                palette: [
                    ["#000", "#444", "#666", "#999", "#ccc", "#eee", "#f3f3f3", "#fff"],
                    ["#f00", "#f90", "#ff0", "#0f0", "#0ff", "#00f", "#90f", "#f0f"],
                    ["#f4cccc", "#fce5cd", "#fff2cc", "#d9ead3", "#d0e0e3", "#cfe2f3", "#d9d2e9", "#ead1dc"],
                    ["#ea9999", "#f9cb9c", "#ffe599", "#b6d7a8", "#a2c4c9", "#9fc5e8", "#b4a7d6", "#d5a6bd"],
                    ["#e06666", "#f6b26b", "#ffd966", "#93c47d", "#76a5af", "#6fa8dc", "#8e7cc3", "#c27ba0"],
                    ["#c00", "#e69138", "#f1c232", "#6aa84f", "#45818e", "#3d85c6", "#674ea7", "#a64d79"],
                    ["#900", "#b45f06", "#bf9000", "#38761d", "#134f5c", "#0b5394", "#351c75", "#741b47"],
                    ["#600", "#783f04", "#7f6000", "#274e13", "#0c343d", "#073763", "#20124d", "#4c1130"]
                ]
            });

        });


        $(".draggableBlock").draggable({
            scroll: false,
            cursor: "move"
        });

        $(".draggableBlock").click(function (e) {
            //	alert('imagedrag');

            var id = $(this).children("div").attr("id");

            selectedTextID = id;
            hideMapper();
            //alert(selectedTextID);
            var childPos = $("#" + id).offset();
            var parentPos = $(this).parent().offset();
//alert(Math.round(childPos.left) + ", "+ parentPos.left);
            var childOffset = {
                top: childPos.top - parentPos.top,
                left: childPos.left - parentPos.left
            }
            //alert(childOffset.left+ " ,"+childOffset.top);
            x = Math.round(childOffset.left);
            y = Math.round(childOffset.top);
            $(".selectedElement").text("Selected item: " + $("#" + selectedTextID).attr("id"));
            $(".position").text("Co ordinates: X=" + x + ", Y=" + y);
            //$("#size").text("Height="+$("#"+id).css("height")+", Width="+$("#"+id).css("width"));
            reloadTabs(3);
        });
    });
    $("#addButton").click(function () {
        $(".container").append("<div class=\"draggableButton\"><img src=\"../buttons/button1.png\" id=\"" + $("#elementText").find('option:selected').text() + "\"></div>");
        selectedTextID = $("#elementText").find('option:selected').text();
        addButtonCount++;
        addElements($("#elementText").find('option:selected').text());
        reloadTabs(2);
        addDefault();
        $("#buttonSelect").change(function () {
            //alert("buttons/button"+$("#buttonSelect").val());
            $("#" + selectedTextID).attr('src', "buttons/button" + $("#buttonSelect").val() + ".png");

        });

        $(".draggableButton").draggable({
            scroll: false,
            cursor: "move"
        });

        $(".draggableButton").click(function (e) {
            //	alert('imagedrag');

            var id = $(this).children("img").attr("id");

            selectedTextID = id;
            //alert(selectedTextID);
            hideMapper();
            var childPos = $("#" + id).offset();
            var parentPos = $(this).parent().offset();
//alert(Math.round(childPos.left) + ", "+ parentPos.left);
            var childOffset = {
                top: childPos.top - parentPos.top,
                left: childPos.left - parentPos.left
            }
            //alert(childOffset.left+ " ,"+childOffset.top);
            x = Math.round(childOffset.left);
            y = Math.round(childOffset.top);
            $(".selectedElement").text("Selected item: " + $("#" + selectedTextID).attr("id"));
            $(".position").text("Co ordinates: X=" + x + ", Y=" + y);
            //$("#size").text("Height="+$("#"+id).css("height")+", Width="+$("#"+id).css("width"));
            reloadTabs(2);
        });

    });
    $("#addImageButton").click(function () {

        //$(".container").append("<div class=\"draggable\"><img src=\"images/default.png\" height='100px' width='100px' name=" + $("#elementText").val() +" id=\"image" + addImageCount + "\"></div>");
        $(".container").append("<div class=\"draggableImage\"><div id=\"" + $("#elementText").find('option:selected').text() + "\"></div></div>");
        selectedTextID = $("#elementText").find('option:selected').text();

        addImageCount++;
        addElements($("#elementText").find('option:selected').text());
        $("#" + selectedTextID).css("background", "url(../images/default.png)");
        $("#" + selectedTextID).css("background-size", "contain");
        $("#" + selectedTextID).css("-webkit-background-size", "contain");
        $("#" + selectedTextID).css("background-repeat", "no-repeat");
        $("#" + selectedTextID).css("width", "100px");
        $("#" + selectedTextID).css("height", "100px");
        addDefault();
        reloadTabs(1);

        $("#opacityImage").keyup(function (e) {

            if (e.keyCode == 13)
            {

                $("#" + selectedTextID).css("opacity", $("#opacityImage").val());

            }
        });
        $(function () {
            $(".filters").spinner({
                min: 0,
                max: 100,
                step: 1,
                stop: filters,
                change: filters
            });
            $(".moreFilters").spinner({
                min: 0,
                max: 200,
                step: 1,
                stop: filters,
                change: filters
            });
            $(".someMoreFilters").spinner({
                min: 0,
                max: 360,
                step: 1,
                stop: filters,
                change: filters
            });


            function filters() {

                var filters = "blur(" + $("#blurFilter").val() + "px) grayscale(" + $("#grayscaleFilter").val() + "%) sepia(" + $("#sepiaFilter").val() + "%)" + " saturate(" + $("#saturateFilter").val() + "%) hue-rotate(" + $("#hueRotateFilter").val() + "deg) invert(" + $("#invertFilter").val() + "%) brightness(" + $("#brightnessFilter").val() + "%) contrast(" + $("#contrastFilter").val() + "%)";
//alert(filters);
                $("#" + selectedTextID).css('filter', filters)
                        .css('-webkit-filter', filters)
                        .css('mozFilter', filters)
                        .css('oFilter', filters)
                        .css('msFilter', filters);

            }
        });
        $("#fileButton").click(function () {
            $("#" + selectedTextID).css('background', "url('" + $("#filePath").val() + "')");
            $("#" + selectedTextID).css("background-size", "contain");
            $("#" + selectedTextID).css("-webkit-background-size", "contain");
            $("#" + selectedTextID).css("background-repeat", "no-repeat");
        });
        $(function () {
            function blendColor() {
                //alert("test");
                $("#" + selectedTextID).css("background-color", $("#blendColorPick").val());
                $("#" + selectedTextID).css("background-blend-mode", $("#blendColorSelect").find('option:selected').text());
                $("#" + selectedTextID).css("-webkit-background-color", $("#blendColorPick").val());
                $("#" + selectedTextID).css("-webkit-background-blend-mode", $("#blendColorSelect").find('option:selected').text());
            }
            $("#blendColorSelect").change(function () {
                blendColor();
            });
            $("#blendColorPick").spectrum({
                change: blendColor,
                stop: blendColor,
                preferredFormat: "hex",
                showPalette: true,
                palette: [
                    ["#000", "#444", "#666", "#999", "#ccc", "#eee", "#f3f3f3", "#fff"],
                    ["#f00", "#f90", "#ff0", "#0f0", "#0ff", "#00f", "#90f", "#f0f"],
                    ["#f4cccc", "#fce5cd", "#fff2cc", "#d9ead3", "#d0e0e3", "#cfe2f3", "#d9d2e9", "#ead1dc"],
                    ["#ea9999", "#f9cb9c", "#ffe599", "#b6d7a8", "#a2c4c9", "#9fc5e8", "#b4a7d6", "#d5a6bd"],
                    ["#e06666", "#f6b26b", "#ffd966", "#93c47d", "#76a5af", "#6fa8dc", "#8e7cc3", "#c27ba0"],
                    ["#c00", "#e69138", "#f1c232", "#6aa84f", "#45818e", "#3d85c6", "#674ea7", "#a64d79"],
                    ["#900", "#b45f06", "#bf9000", "#38761d", "#134f5c", "#0b5394", "#351c75", "#741b47"],
                    ["#600", "#783f04", "#7f6000", "#274e13", "#0c343d", "#073763", "#20124d", "#4c1130"]
                ]
            });
        });

        $(function () {
            $(".dropShadowFilters").spinner({
                min: 1,
                max: 99,
                step: 1,
                change: dropShadowFilter,
                stop: dropShadowFilter
            });
            function dropShadowFilter() {
                var dropShadowFilter1 = "drop-shadow(" + $("#hShadowDropShadowFilter").val() + "px " + $("#vShadowDropShadowFilter").val() + "px " + $("#blurDropShadowFilter").val() + "px" + " " + $("#dropShadowFilterColorPick").val() + ")";
//alert(dropShadowFilter1);            
                $("#" + selectedTextID).css('filter', dropShadowFilter1)
                        .css('-webkit-filter', dropShadowFilter1);
            }

            $("#dropShadowFilterColorPick").spectrum({
                change: dropShadowFilter,
                preferredFormat: "hex",
                showPalette: true,
                palette: [
                    ["#000", "#444", "#666", "#999", "#ccc", "#eee", "#f3f3f3", "#fff"],
                    ["#f00", "#f90", "#ff0", "#0f0", "#0ff", "#00f", "#90f", "#f0f"],
                    ["#f4cccc", "#fce5cd", "#fff2cc", "#d9ead3", "#d0e0e3", "#cfe2f3", "#d9d2e9", "#ead1dc"],
                    ["#ea9999", "#f9cb9c", "#ffe599", "#b6d7a8", "#a2c4c9", "#9fc5e8", "#b4a7d6", "#d5a6bd"],
                    ["#e06666", "#f6b26b", "#ffd966", "#93c47d", "#76a5af", "#6fa8dc", "#8e7cc3", "#c27ba0"],
                    ["#c00", "#e69138", "#f1c232", "#6aa84f", "#45818e", "#3d85c6", "#674ea7", "#a64d79"],
                    ["#900", "#b45f06", "#bf9000", "#38761d", "#134f5c", "#0b5394", "#351c75", "#741b47"],
                    ["#600", "#783f04", "#7f6000", "#274e13", "#0c343d", "#073763", "#20124d", "#4c1130"]
                ]
            });

        });

        $("#" + selectedTextID).resizable({
        });

        $(".draggableImage").draggable({
            scroll: false,
            cursor: "move"
        });

        $(".draggableImage").click(function (e) {
            //alert($("#"+selectedTextID).css("background-blend-mode"));
            var id = $(this).children("div").attr("id");

            selectedTextID = id;
            //alert(selectedTextID);
            hideMapper();
            var childPos = $("#" + id).offset();
            var parentPos = $(this).parent().offset();
//alert(Math.round(childPos.left) + ", "+ parentPos.left);
            var childOffset = {
                top: childPos.top - parentPos.top,
                left: childPos.left - parentPos.left
            }
            //alert($("#"+id).css("height"));
            x = Math.round(childOffset.left);
            y = Math.round(childOffset.top);
            $(".selectedElement").text("Selected item: " + $("#" + selectedTextID).attr("id"));
            $(".position").text("Co ordinates: X=" + x + ", Y=" + y);
            $(".size").text("Height=" + $("#" + id).css("height") + ", Width=" + $("#" + id).css("width"));
            reloadTabs(1);
        });


    });
    $("#addTextButton").click(function () {

        $(".container").append("<div class=\"draggableText\"><textarea class =\"textAreas\" id=\"" + $("#elementText").find('option:selected').text() + "\">" + $("#elementText").find('option:selected').text() + "</textarea></div>");
        $("#textArea").val($("#elementText").find('option:selected').text());
        selectedTextID = $("#elementText").find('option:selected').text();
        selectElementsArray[textcount - 1] = selectedTextID;
        textcount++;
        addElements($("#elementText").find('option:selected').text());
        reload_textButtons();
        reload_alignButtons();
        reload_textSize();
        reload_textFontFamily();
        reload_color();
        reloadTabs(0);

        addDefault();
        $("#lineHeight").val($("#" + selectedTextID).css("line-height").replace('px', ''));

        if ($("#" + selectedTextID).css("letter-spacing") == "0")
            $("#letterSpace").val($("#" + selectedTextID).css("letter-spacing"));
        else
            $("#letterSpace").val($("#" + selectedTextID).css("letter-spacing").replace('px', ''));
//TODO: reload_dropShadow();
        $("#opacity").val($("#" + selectedTextID).css("opacity"));
//$("#start").text("Selected item: "+ $("#"+selectedTextID).attr("name") +" and Co ordinates: X="+ x + ", Y="+ y);	


        $(".alignButton").click(function () {
            $("#" + selectedTextID).css("text-align", $(this).attr("value"));
            reload_alignButtons();
        });



        $(".draggableText").draggable(
                {
                    scroll: false,
                    cancel: "text",
                    cursor: "move"
                }


        );
        function reload_dropShadow()
        {
            var data = $("#" + selectedTextID).css("text-shadow");
            //alert(data);
            if (data == "none")
            {
                $("#hShadow").val("0");
                $("#vShadow").val("0");
                $("#blur").val("0");

                $('#dropShadow').attr('checked', false);
            }
            else
            {
                var arr = data.split(' ');
                $("#hShadow").val(arr[1].replace('px', ''));
                $("#vShadow").val(arr[2].replace('px', ''));
                $("#blur").val(arr[3].replace('px', ''));
                $('#dropShadow').attr('checked', true);
            }
        }
        function reload_color()
        {

            var data = $("#" + selectedTextID).css("color").replace('rgb(', '').replace(')', '').replace(/\ /g, '').trim();
            var arr = data.split(',');
//$('#colorPick').val(rgbToHex(parseInt(arr[0]),parseInt(arr[1]),parseInt(arr[2])));
//$('#colorPick').val($("#"+selectedTextID).css("color"));
            $("#colorPick").spectrum("set", $("#" + selectedTextID).css("color"));
        }

        function resetButtons()
        {
            $("#boldButton").css("background-color", "inherit");
            $("#italicButton").css("background-color", "inherit");
            $("#leftButton").css("background-color", "inherit");
            $("#centerButton").css("background-color", "inherit");
            $("#rightButton").css("background-color", "inherit");
            $("#textSize").val(12);
            $("#textFontFamily").val("Arial");

        }
        function reload_alignButtons()
        {
            //alert($("#"+selectedTextID).css("text-align"));
            if ($("#" + selectedTextID).css("text-align") == "left")
            {
                $("#leftButton").css("background-color", "99b1f2");
                $("#centerButton").css("background-color", "inherit");
                $("#rightButton").css("background-color", "inherit");
            }
            else if ($("#" + selectedTextID).css("text-align") == "center")
            {
                $("#leftButton").css("background-color", "inherit");
                $("#centerButton").css("background-color", "99b1f2");
                $("#rightButton").css("background-color", "inherit");
            }
            else if ($("#" + selectedTextID).css("text-align") == "right")
            {
                $("#leftButton").css("background-color", "inherit");
                $("#centerButton").css("background-color", "inherit");
                $("#rightButton").css("background-color", "99b1f2");
            }
        }
        function reload_textButtons() {
            if ($("#" + selectedTextID).css("font-weight") != "bold")
            {
                $("#boldButton").css("background-color", "inherit");
            }
            else
            {
                $("#boldButton").css("background-color", "99b1f2");
            }

            if ($("#" + selectedTextID).css("font-style") != "italic")
            {

                $("#italicButton").css("background-color", "inherit");
            }
            else
            {

                $("#italicButton").css("background-color", "99b1f2");
            }
        }
        function reload_textSize() {
            //alert("test"+$("select").val());
            $("#textSize").val($("#" + selectedTextID).css("font-size").replace('px', ''));
            //$(select).value("9");//$("#"+selectedTextID).css("font-size").replace('px','').toString().trim();
        }
        function reload_textFontFamily() {
            //alert($("#"+selectedTextID).css("font-family").replace(/\'/g,''));
            $("#textFontFamily").val($("#" + selectedTextID).css("font-family").replace(/\'/g, ''));
            //$(select).value("9");//$("#"+selectedTextID).css("font-size").replace('px','').toString().trim();
        }
        $("#textArea").keyup(function (e) {
            $("#" + selectedTextID).val($("#textArea").val());
        });
        $(".draggableText").click(function (e) {
            hideMapper();
            //	alert('drag');
            //$(".draggableText").css("padding","5px");
            var id = $(this).children("textarea").attr("id");
            //var id = $(".draggable > ").children("area").attr("id");
            selectedTextID = id;
            $("#textArea").val($("#" + selectedTextID).val());
            //alert($("#"+id).css("-webkit-transform"));
            //alert(selectedTextID);
            var childPos = $("#" + id).offset();
            var parentPos = $(this).parent().offset();
//alert(Math.round(childPos.left) + ", "+ parentPos.left);
            var childOffset = {
                top: childPos.top - parentPos.top,
                left: childPos.left - parentPos.left
            };
            //alert(childOffset.left+ " ,"+childOffset.top);
            x = Math.round(childOffset.left);
            y = Math.round(childOffset.top);
            $(".selectedElement").text("Selected item: " + $("#" + selectedTextID).attr("id"));
            $(".position").text("Co ordinates: X=" + x + ", Y=" + y);
            reload_textButtons();
            reload_alignButtons();
            reload_textSize();
            reload_textFontFamily();
            reload_color();
            $("#lineHeight").val($("#" + selectedTextID).css("line-height").replace('px', ''));

            if ($("#" + selectedTextID).css("letter-spacing") === "0")
                $("#letterSpace").val($("#" + selectedTextID).css("letter-spacing"));
            else
                $("#letterSpace").val($("#" + selectedTextID).css("letter-spacing").replace('px', ''));
            $("#opacity").val($("#" + selectedTextID).css("opacity"));
            //reload_dropShadow();
            reloadTabs(0);
        });
    });
        $("#addLogoButton").click(function () {

        //$(".container").append("<div class=\"draggable\"><img src=\"images/default.png\" height='100px' width='100px' name=" + $("#elementText").val() +" id=\"image" + addImageCount + "\"></div>");
        $(".container").append("<div class=\"draggableLogo\"><div id=\"" + $("#elementText").find('option:selected').text() + "\"></div></div>");
        selectedTextID = $("#elementText").find('option:selected').text();

        addLogocount++;
        addElements($("#elementText").find('option:selected').text());
        $("#" + selectedTextID).css("background", "url(../images/logo.png)");
        $("#" + selectedTextID).css("background-size", "contain");
        $("#" + selectedTextID).css("-webkit-background-size", "contain");
        $("#" + selectedTextID).css("background-repeat", "no-repeat");
        $("#" + selectedTextID).css("width", "100px");
        $("#" + selectedTextID).css("height", "100px");
        addDefault();
        reloadTabs(1);

        $("#opacityImage").keyup(function (e) {

            if (e.keyCode == 13)
            {

                $("#" + selectedTextID).css("opacity", $("#opacityImage").val());

            }
        });
        $(function () {
            $(".filters").spinner({
                min: 0,
                max: 100,
                step: 1,
                stop: filters,
                change: filters
            });
            $(".moreFilters").spinner({
                min: 0,
                max: 200,
                step: 1,
                stop: filters,
                change: filters
            });
            $(".someMoreFilters").spinner({
                min: 0,
                max: 360,
                step: 1,
                stop: filters,
                change: filters
            });


            function filters() {

                var filters = "blur(" + $("#blurFilter").val() + "px) grayscale(" + $("#grayscaleFilter").val() + "%) sepia(" + $("#sepiaFilter").val() + "%)" + " saturate(" + $("#saturateFilter").val() + "%) hue-rotate(" + $("#hueRotateFilter").val() + "deg) invert(" + $("#invertFilter").val() + "%) brightness(" + $("#brightnessFilter").val() + "%) contrast(" + $("#contrastFilter").val() + "%)";
//alert(filters);
                $("#" + selectedTextID).css('filter', filters)
                        .css('-webkit-filter', filters)
                        .css('mozFilter', filters)
                        .css('oFilter', filters)
                        .css('msFilter', filters);

            }
        });
        $("#fileButton").click(function () {
            $("#" + selectedTextID).css('background', "url('" + $("#filePath").val() + "')");
            $("#" + selectedTextID).css("background-size", "contain");
            $("#" + selectedTextID).css("-webkit-background-size", "contain");
            $("#" + selectedTextID).css("background-repeat", "no-repeat");
        });
        $(function () {
            function blendColor() {
                //alert("test");
                $("#" + selectedTextID).css("background-color", $("#blendColorPick").val());
                $("#" + selectedTextID).css("background-blend-mode", $("#blendColorSelect").find('option:selected').text());
                $("#" + selectedTextID).css("-webkit-background-color", $("#blendColorPick").val());
                $("#" + selectedTextID).css("-webkit-background-blend-mode", $("#blendColorSelect").find('option:selected').text());
            }
            $("#blendColorSelect").change(function () {
                blendColor();
            });
            $("#blendColorPick").spectrum({
                change: blendColor,
                stop: blendColor,
                preferredFormat: "hex",
                showPalette: true,
                palette: [
                    ["#000", "#444", "#666", "#999", "#ccc", "#eee", "#f3f3f3", "#fff"],
                    ["#f00", "#f90", "#ff0", "#0f0", "#0ff", "#00f", "#90f", "#f0f"],
                    ["#f4cccc", "#fce5cd", "#fff2cc", "#d9ead3", "#d0e0e3", "#cfe2f3", "#d9d2e9", "#ead1dc"],
                    ["#ea9999", "#f9cb9c", "#ffe599", "#b6d7a8", "#a2c4c9", "#9fc5e8", "#b4a7d6", "#d5a6bd"],
                    ["#e06666", "#f6b26b", "#ffd966", "#93c47d", "#76a5af", "#6fa8dc", "#8e7cc3", "#c27ba0"],
                    ["#c00", "#e69138", "#f1c232", "#6aa84f", "#45818e", "#3d85c6", "#674ea7", "#a64d79"],
                    ["#900", "#b45f06", "#bf9000", "#38761d", "#134f5c", "#0b5394", "#351c75", "#741b47"],
                    ["#600", "#783f04", "#7f6000", "#274e13", "#0c343d", "#073763", "#20124d", "#4c1130"]
                ]
            });
        });

        $(function () {
            $(".dropShadowFilters").spinner({
                min: 1,
                max: 99,
                step: 1,
                change: dropShadowFilter,
                stop: dropShadowFilter
            });
            function dropShadowFilter() {
                var dropShadowFilter1 = "drop-shadow(" + $("#hShadowDropShadowFilter").val() + "px " + $("#vShadowDropShadowFilter").val() + "px " + $("#blurDropShadowFilter").val() + "px" + " " + $("#dropShadowFilterColorPick").val() + ")";
//alert(dropShadowFilter1);            
                $("#" + selectedTextID).css('filter', dropShadowFilter1)
                        .css('-webkit-filter', dropShadowFilter1);
            }

            $("#dropShadowFilterColorPick").spectrum({
                change: dropShadowFilter,
                preferredFormat: "hex",
                showPalette: true,
                palette: [
                    ["#000", "#444", "#666", "#999", "#ccc", "#eee", "#f3f3f3", "#fff"],
                    ["#f00", "#f90", "#ff0", "#0f0", "#0ff", "#00f", "#90f", "#f0f"],
                    ["#f4cccc", "#fce5cd", "#fff2cc", "#d9ead3", "#d0e0e3", "#cfe2f3", "#d9d2e9", "#ead1dc"],
                    ["#ea9999", "#f9cb9c", "#ffe599", "#b6d7a8", "#a2c4c9", "#9fc5e8", "#b4a7d6", "#d5a6bd"],
                    ["#e06666", "#f6b26b", "#ffd966", "#93c47d", "#76a5af", "#6fa8dc", "#8e7cc3", "#c27ba0"],
                    ["#c00", "#e69138", "#f1c232", "#6aa84f", "#45818e", "#3d85c6", "#674ea7", "#a64d79"],
                    ["#900", "#b45f06", "#bf9000", "#38761d", "#134f5c", "#0b5394", "#351c75", "#741b47"],
                    ["#600", "#783f04", "#7f6000", "#274e13", "#0c343d", "#073763", "#20124d", "#4c1130"]
                ]
            });

        });

        $("#" + selectedTextID).resizable({
        });

        $(".draggableLogo").draggable({
            scroll: false,
            cursor: "move"
        });

        $(".draggableLogo").click(function (e) {
            //alert($("#"+selectedTextID).css("background-blend-mode"));
            var id = $(this).children("div").attr("id");

            selectedTextID = id;
            //alert(selectedTextID);
            hideMapper();
            var childPos = $("#" + id).offset();
            var parentPos = $(this).parent().offset();
//alert(Math.round(childPos.left) + ", "+ parentPos.left);
            var childOffset = {
                top: childPos.top - parentPos.top,
                left: childPos.left - parentPos.left
            }
            //alert($("#"+id).css("height"));
            x = Math.round(childOffset.left);
            y = Math.round(childOffset.top);
            $(".selectedElement").text("Selected item: " + $("#" + selectedTextID).attr("id"));
            $(".position").text("Co ordinates: X=" + x + ", Y=" + y);
            $(".size").text("Height=" + $("#" + id).css("height") + ", Width=" + $("#" + id).css("width"));
            reloadTabs(1);
        });


    });
    
});

//.............................................................................

var num1 = 1;
//adding defult value and apoch
function addDefault() {

    var num = 0;
    var fontnameis = [];
    var sub_category_type = $("#subcategories").val();
    var block_mindbody_query = $("#mindbodyquery").val();
//   alert($("#mindbodyquery").val());

    var mindbody_xml_url = "";
    if (block_mindbody_query !== "null") {
        if (block_mindbody_query.toLowerCase().contains("class")) {
            mindbody_xml_url = "images/layoutmodelxml/mindbodyclassdatapoints.xml";
        } else if (block_mindbody_query.toLowerCase().contains("work shop") || block_mindbody_query.contains("workshop")) {
            mindbody_xml_url = "images/layoutmodelxml/mindbodyenrollmentsdatapoints.xml";
        } else if (block_mindbody_query.toLowerCase().contains("staff")) {
            mindbody_xml_url = "images/layoutmodelxml/mindbodystaffdatapoints.xml";
        }
    } else {
        mindbody_xml_url = "images/layoutmodelxml/nodatapoints.xml";
        $(".form-control").prop("disabled", true);
    }

    if (parseInt(sub_category_type) === 1) {
        mindbody_xml_url = "images/layoutmodelxml/mindbodyenrollmentsdatapoints.xml";
    } else if (parseInt(sub_category_type) === 2) {
        mindbody_xml_url = "images/layoutmodelxml/mindbodyclassdatapoints.xml";
    } else if (parseInt(sub_category_type) === 3) {
        mindbody_xml_url = "images/layoutmodelxml/mindbodyclassdatapoints.xml";
    }

    if (addElementsArray.length === num1) {
        $.ajax({
            type: "GET",
            url: getHost() + mindbody_xml_url,
            dataType: "xml",
            success: function (xml) {
//            alert( addElementsArray[num1]);
                $("#lab").append('<div class="col-md-5 " id="appenddiv' + addElementsArray[num1 - 1] + '" style="display:none"><p id="' + num1 + '"> ' + addElementsArray[num1 - 1] + '</p><select id="Footer1dropdown-' + num1 + '" class="form-control"></select>\
                                          <p id="hidepara' + num1 + '" class="col-md-3 "> Default Value <input id="inputfield' + num1 + '" type="text" value="default"><br> \n\
                                            Epoch Formatter<input id="inputfield1' + num1 + '" type="text" value="default" ></p><div>');
                $(xml).find('optionelement').each(function () {
                    fontnameis[num] = $(this).find('element').text();

                    num++;

                });
                for (var i = 0; i <= fontnameis.length - 1; i++) {
                    $('#Footer1dropdown-' + num1).append(new Option(fontnameis[i]));
                }
                num1++;
            },
            error: function () {
                alert("The XML File could not be processed correctly.");
            }
        });
    }


}
var assa = [];
var counterformapper = 0;
function hideMapper() {

//    alert(selectedTextID);
    assa[counterformapper] = selectedTextID;
    counterformapper++;
//    alert("appenddiv"+selectedTextID);
//    alert(assa.length);
    $("#appenddiv" + selectedTextID).css("display", "block");

    for (var i = 0; i < assa.length - 1; i++)
    {
        if (assa[i] !== selectedTextID) {
            $("#appenddiv" + assa[i]).css("display", "none");
        }
    }
}
function componentToHex(c) {
    var hex = c.toString(16);
    return hex.length === 1 ? "0" + hex : hex;
}

function rgbToHex(r, g, b) {
    return componentToHex(r) + componentToHex(g) + componentToHex(b);
}

//................functoin to convert matrix into degree......
function getRotationDegrees(obj) {
    var matrix = obj.css("-webkit-transform") ||
            obj.css("-moz-transform") ||
            obj.css("-ms-transform") ||
            obj.css("-o-transform") ||
            obj.css("transform");
    if (matrix !== 'none') {
        var values = matrix.split('(')[1].split(')')[0].split(',');
        var a = values[0];
        var b = values[1];
        var angle = Math.round(Math.atan2(b, a) * (180 / Math.PI));
    } else {
        var angle = 0;
    }
    return (angle < 0) ? angle + 360 : angle;
}

function popupwindow() {
    $('#popup').show("slow");
    $("#tabs *").attr("disabled", "disabled");
    $("#main *").attr("disabled", "disabled");
    $("#content *").attr("disabled", false);
    $("#right *").attr("disabled", "disabled");

    $('#popupclose').click(function () {

        $("#mapper").val($("#mapperxml").val());
        $("#layout").val($("#layoutxml").val());
        $("#tabs *").attr("disabled", false);
        $("#main *").attr("disabled", false);
        $("#right *").attr("disabled", false);
        $('#popup').hide("slow");

    });

}

function passvaluetoinputfield() {


    popupwindow();

    var containerstyle = "Width!" + $("#containerWidth").val() +
            " Height!" + $("#containerHeight").val();

    $("#containerstyle").val(containerstyle);
    var mapperdata = [];
    for (var i = 0; i <= addElementsArray.length - 1; i++) {

        mapperdata[i] = " element!" + addElementsArray[i] + " option!" + $("#Footer1dropdown-" + (i + 1)).find('option:selected').text() + " default!" + $("#inputfield" + (i + 1)).val() + " epoch!" + $("#inputfield1" + (i + 1)).val();
//        alert(mapperdata);

        var style1;
        var angle = getRotationDegrees($("#" + addElementsArray[i]));
        var contenttype = $("#" + addElementsArray[i]).parent().attr("class");
//        alert(contenttype);

        var dropshadow = $("#" + addElementsArray[i]).css("text-shadow");
        var dropshadowarr = dropshadow.split(" ");
        var dropshadowdata = " H-shadow:" + dropshadowarr[3] + " V-shadow:" + dropshadowarr[4] + " blur:" + dropshadowarr[5];

        var dropshadow1 = $("#" + addElementsArray[i]).css("-webkit-filter").replace('drop-shadow(', '').replace('rgb(', '').replace(')', '').replace(')', '').replace('(', '');

        var data = $("#" + addElementsArray[i]).css("color").replace('rgb(', '').replace(')', '').replace(/\ /g, '').trim();
        var data1 = $("#" + addElementsArray[i]).css("text-shadow").replace('rgb(', '').replace(')', '').replace(/\ /g, '').trim();
        var data2 = $("#" + addElementsArray[i]).css("background-color").replace('rgb(', '').replace(')', '').replace(/\ /g, '').trim();
        var data3 = $("#" + addElementsArray[i]).css("-webkit-filter").replace('drop-shadow(', '').replace('rgb(', '').replace(')', ',').replace(/\ /g, '').replace(")", '').trim();

        var arr = data.split(',');
        var arr1 = data1.split(',');
        var arr2 = data2.split(',');
        var arr3 = data3.split(',');
        var color1 = rgbToHex(parseInt(arr[0]), parseInt(arr[1]), parseInt(arr[2]));
        var color2 = rgbToHex(parseInt(arr1[0]), parseInt(arr1[1]), parseInt(arr1[2]));
        var color3 = rgbToHex(parseInt(arr2[0]), parseInt(arr2[1]), parseInt(arr2[2]));
        var color4 = rgbToHex(parseInt(arr3[0]), parseInt(arr3[1]), parseInt(arr3[2]));

        var dropshadowdata1;
        var childPos = $("#" + addElementsArray[i]).offset();
        var parentPos = $("#" + addElementsArray[i]).parent().parent().offset();
//            alert($("#" + addElementsArray[i]).attr("class"));          
        var childOffset = {
            top: childPos.top - parentPos.top,
            left: childPos.left - parentPos.left
        };
        var x1 = Math.round(childOffset.left);
        var y1 = Math.round(childOffset.top);

        if (isNaN(dropshadow1[0])) {

            var filterdata = $("#" + addElementsArray[i]).css("-webkit-filter").replace('(', ' ').replace(')', '').replace('(', ' ').replace(')', '').replace('(', ' ').replace(')', '').replace('(', ' ').replace(')', '').replace('(', ' ').replace(')', '').replace('(', ' ').replace(')', '').replace('(', ' ').replace(')', '').replace('(', ' ').replace(')', '');
//          alert(filterdata);
            var filterdataarray = filterdata.split(' ');
            dropshadowdata1 = " blur!" + filterdataarray[1] + " grayscale!" + filterdataarray[3] + " sepia!" + filterdataarray[5] + " saturate!" + filterdataarray[7] +
                    " huerotate!" + filterdataarray[9] + " invert!" + filterdataarray[11] +
                    " brightness!" + filterdataarray[13] + " contrast!" + filterdataarray[15];
//           alert(dropshadowdata1);

        }
        else {
            var dropshadowarr1 = dropshadow1.split(" ");
            dropshadowdata1 = " Drop-shadow-color!" + "#" + color4 + " H-shadow!" + dropshadowarr1[3] + " V-shadow!" + dropshadowarr1[4] + " blur!" + dropshadowarr1[5];
        }



        if (contenttype.startsWith('draggableImage')) {
//            alert($("#" + addElementsArray[i]).css("background-image"));
            style1 = " tag!" + "image" +
                    " x-co-ordinates!" + x1 +
                    " y-co-ordinates!" + y1 +
                    " width!" + $("#" + addElementsArray[i]).css("width") +
                    " height!" + $("#" + addElementsArray[i]).css("height") +
                    " opacity!" + $("#" + addElementsArray[i]).css("opacity") +
                    " background-image!" + $("#" + addElementsArray[i]).css("background-image") +
                    dropshadowdata1 +
//                    " filter:" + $("#" + addElementsArray[i]).css("-webkit-filter") +
                    " Blend!" + $("#" + addElementsArray[i]).css("background-blend-mode") +
                    " blend-background-color!" + "#" + color3;

        }
                if (contenttype.startsWith('draggableLogo')) {
//            alert($("#" + addElementsArray[i]).css("background-image"));
            style1 = " tag!" + "logo" +
                    " x-co-ordinates!" + x1 +
                    " y-co-ordinates!" + y1 +
                    " width!" + $("#" + addElementsArray[i]).css("width") +
                    " height!" + $("#" + addElementsArray[i]).css("height") +
                    " opacity!" + $("#" + addElementsArray[i]).css("opacity") +
                    " background-image!" + $("#" + addElementsArray[i]).css("background-image") +
                    dropshadowdata1 +
//                    " filter:" + $("#" + addElementsArray[i]).css("-webkit-filter") +
                    " Blend!" + $("#" + addElementsArray[i]).css("background-blend-mode") +
                    " blend-background-color!" + "#" + color3;

        }

        if (contenttype.startsWith('draggableText')) {
            style1 = " tag!" + "text" +
                    " x-co-ordinates!" + x1 +
                    " y-co-ordinates!" + y1 +
                    " font-weight!" + $("#" + addElementsArray[i]).css("font-weight") +
                    " font-style!" + $("#" + addElementsArray[i]).css("font-style") +
                    " text-align!" + $("#" + addElementsArray[i]).css("text-align") +
                    " font-size!" + $("#" + addElementsArray[i]).css("font-size") +
                    " font-color!" + "#" + color1 +
                    " text-shadow!" + "#" + color2 +
                    dropshadowdata +
                    " line-height!" + $("#" + addElementsArray[i]).css("line-height") +
                    " letter-spacing!" + $("#" + addElementsArray[i]).css("letter-spacing") +
                    " opacity!" + $("#" + addElementsArray[i]).css("opacity") +
                    " webkit-transform!" + angle;

        }
        if (contenttype.startsWith('draggableButton')) {

            style1 = " tag!" + "button" +
                    " x-co-ordinates!" + x1 +
                    " y-co-ordinates!" + y1;

        }

        if (contenttype.startsWith('draggableBlock')) {

            style1 = " tag!" + "block" +
                    " x-co-ordinates!" + x1 +
                    " y-co-ordinates!" + y1 +
                    " width!" + $("#" + addElementsArray[i]).css("width") +
                    " height!" + $("#" + addElementsArray[i]).css("height") +
                    " opacity!" + $("#" + addElementsArray[i]).css("opacity") +
                    " background-color!" + "#" + color3 + dropshadowdata1;
        }
        textareadetails[i] = style1 + " type!" + addElementsArray[i];
//        alert(textareadetails); 



        $("#textstyle").val(textareadetails);

    }
//    alert(mapperdata);
    $("#element").val(mapperdata);

} 