function overlay(){
    $("#fbpopupfooter").show();
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
//                    document.body.style.overflow = 'scroll';
                    //document.getElementById('blk').style.display = 'none';
                    //document.getElementById('edtfbimg').style.display = 'none';
                    //document.getElementById('prevtwtimg').style.display = 'none';
                    //document.getElementById('edttwtimg').style.display = 'none';
                    //document.getElementById('prevfbimg').style.display = 'none';
            }


function fun(type,email,id,fname,lname)
{
    $("#fade").show();
    $("#addContact").show();
    $("#uuid").val(id);
    if(type==="update")
    {
        $("#emailId").val(email);
    }
    else
    {
        $("#emailId").val("");
    }
    if(fname !=="")
    {
        $("#firstName").val(fname);
    }
    else
    {
        $("#firstName").val("");
    }
    if(lname !=="")
    {
        $("#lastName").val(lname);
    }
    else
    {
        $("#lastName").val("");
    }
    
    $("#type").val(type);
    overlay();  
}
$(document).ready(function ()
{
    $(".close").click(function(){
        closeoverlay();
        $('#slider-button').click();
        $('.bottom-cta-bar').hide();
        //$("#fade").hide();
        //$("#facebooksection").hide();
    });
    
  //////////////////////////////////////////// emaillist popup ////////////////////////////////////////
  $("#close").click(function(){
       $("#fade").hide();
       $("#addContact").hide();
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