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
    overlay();
}
$("#gotoimageeditor").click(function(){
    $("#gotoimageeditor").css("background-color", "yellow");
    $("#uploadimage").css("background-color", "#E3E3E3");
});
$("#uploadimage").click(function(){
    $("#uploadimage").css("background-color", "yellow");
    $("#gotoimageeditor").css("background-color", "#E3E3E3");
    
});

$(document).ready(function ()
{
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