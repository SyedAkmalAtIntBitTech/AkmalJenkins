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

$(document).ready(function ()
{
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