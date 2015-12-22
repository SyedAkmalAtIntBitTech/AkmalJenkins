    function overlay(){
                //$(".black_overlay").fade(1000);
                //document.getElementById('light').style.display = 'block';
                document.getElementById('fade').style.display = 'block';
                document.getElementById('blk').style.display = 'block';
                //document.getElementById('slider-button').style.display = 'block';
                document.body.style.overflow = 'hidden';
                // $("#calendar").css("pointer-events","none");
                //$("#slider-button").hide();
            }
    function closeoverlay(){
            //document.getElementById('light').style.display = 'none';
            //$("#calendar").css("pointer-events","auto");
            document.getElementById('fade').style.display = 'none';
            document.body.style.overflow = 'scroll';
            //document.getElementById('blk').style.display = 'none';
            //document.getElementById('edtfbimg').style.display = 'none';
            //document.getElementById('prevtwtimg').style.display = 'none';
            //document.getElementById('edttwtimg').style.display = 'none';
            //document.getElementById('prevfbimg').style.display = 'none';
    }

function fun(type, emailAddress, UUID, firstName, lastName)
{
    
    $("#fade").show();
    $("#addContact").show();
    $("#emailId").val(emailAddress);
    $("#uuid").val(UUID);
    $("#firstName").val(firstName);
    $("#lastName").val(lastName);
    $("#type").val(type);
    $("#selectedtype").val(type);
    $("#selectedid").val(emailAddress);
    overlay();
}


$(document).ready(function ()
{
    $("#gotoimageeditor").click(function(){
        
        var selectedtype=$("#selectedtype").val();
        var id=$("#selectedid").val();
        var social=$("#social").val();
        window.open('socialeditor.jsp?id='+id+'&mediatype='+social+'&selectedtype='+selectedtype, "_self");
        
//        $("#gotoimageeditor").css("background-color", "#5CC1A4");
//        $("#uploadimage").css("background-color", "#E3E3E3");
    });
    $("#uploadimage").click(function(){
        var selectedtype=$("#selectedtype").val();
        var id=$("#selectedid").val();
        var social=$("#social").val();
        window.open('http://www.google.com?id='+id+'&mediatype='+social+'&selectedtype='+selectedtype, "_self");
//        $("#uploadimage").css("background-color", "#5CC1A4");
//        $("#gotoimageeditor").css("background-color", "#E3E3E3");
//
    });
    
  //////////////////////////////////////////// emaillist popup ////////////////////////////////////////
  $(".email").click(function(){
      alert();
  });
  $("#close").click(function(){
      $("#addContact").hide();
      $("#fade").hide();
      
  });
  
  /////////////////////////////////////////////////////////////////////////////////////////////////////
    
    $("#addactionClose").click(function(){
    $("#fade").hide();
    $("#addAction").hide();
    document.getElementById('light').style.display = 'none';
    $("#calendar").css("pointer-events","auto");
    document.getElementById('fade').style.display = 'none';
    document.body.style.overflow = 'scroll';
    document.getElementById('blk').style.display = 'none';
    document.getElementById('edtfbimg').style.display = 'none';
    document.getElementById('prevtwtimg').style.display = 'none';
    document.getElementById('edttwtimg').style.display = 'none';
    document.getElementById('prevfbimg').style.display = 'none';
    });
//    $("#fade").click(function(){
//    $("#fade").hide();
//    $("#addAction").hide();
//    document.getElementById('light').style.display = 'none';
//    $("#calendar").css("pointer-events","auto");
//    document.getElementById('fade').style.display = 'none';
//    document.body.style.overflow = 'scroll';
//    document.getElementById('blk').style.display = 'none';
//    document.getElementById('edtfbimg').style.display = 'none';
//    document.getElementById('prevtwtimg').style.display = 'none';
//    document.getElementById('edttwtimg').style.display = 'none';
//    document.getElementById('prevfbimg').style.display = 'none';
//  });
    
  
});