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
    /*.......................................... facebook popup navbar ................*/
    $("#facebookpost").click(function(){
        $("#facebookpostsection").show();
        if($('#savedpostdiv').is(":visible")){
            $("#fbpostremove").show();
        }
        $("#facebookactionsection").hide();
        $("#facebooknotesection").hide();
        $("#fbactionsave").hide();
        $("#fbnotesave").hide();
        
        $("#facebookaction").removeClass("top-subnav-link-active-detail");
        $("#facebookaction a").removeAttr("class");
        $("#facebooknote").removeClass("top-subnav-link-active-detail");
        $("#facebooknote a").removeAttr("class");
        $("#facebookpost").removeClass("top-subnav-link-active-detail");
        $("#facebookpost a").removeAttr("class");
        
        $("#facebookpost").addClass("top-subnav-link-active-detail");
        $("#facebookpost a").addClass("h3-subnav-subnav-active");
        $("#facebookaction").addClass("top-subnav-links-detail");
        $("#facebookaction a").addClass("h3-subnav");
        $("#facebooknote").addClass("top-subnav-links-detail");
        $("#facebooknote a").addClass("h3-subnav");
        
    });
    $("#facebookaction").click(function(){
         var note=$("#fbnote").val();
            if(note === ''){
                
                $("#fbdescription").text(note);
                $("#notesavedheader").css("display","none");
                $("#emptynoteheader").css("display","block");
            }
            if(note !== '')
            {
                $("#fbdescription").text(note);
                $("#emptynoteheader").css("display","none");
                $("#notesavedheader").css("display","block");
                
            }
         var change=$("#change").val();
         $("#fbdescription").show();
//         var desc=$("#fbnote").val();
//         if(desc !== ''){alert(desc);$("#notesavedheader").css("display","block");$("#emptynoteheader").css("display","none");$("#fbdescription").show();}
//         if(desc === ''){alert(desc);$("#emptynoteheader").css("display","block");$("#notesavedheader").css("display","none");}
        if(change === "1")
        {
            var note=$("#fbnote").val();
            if(note === ''){
                
                $("#fbdescription").text(note);
                $("#notesavedheader").css("display","none");
                $("#emptynoteheader").css("display","block");
            }
            if(note !== '')
            {
                $("#fbdescription").text(note);
                $("#emptynoteheader").css("display","none");
                $("#notesavedheader").css("display","block");
                
            }
            $("#change").val("0");
        }
        $("#facebookactionsection").show();
        $("#fbactionsave").show();
        $("#facebookpostsection").hide();
        $("#facebooknotesection").hide();
        $("#fbpostremove").hide();
        $("#fbnotesave").hide();
        
        $("#facebookaction").removeClass("top-subnav-link-active-detail");
        $("#facebookaction a").removeAttr("class");
        $("#facebooknote").removeClass("top-subnav-link-active-detail");
        $("#facebooknote a").removeAttr("class");
        $("#facebookpost").removeClass("top-subnav-link-active-detail");
        $("#facebookpost a").removeAttr("class");
        
        $("#facebookaction").addClass("top-subnav-link-active-detail");
        $("#facebookaction a").addClass("h3-subnav-subnav-active");
        $("#facebookpost").addClass("top-subnav-links-detail");
        $("#facebookpost a").addClass("h3-subnav");
        $("#facebooknote").addClass("top-subnav-links-detail");
        $("#facebooknote a").addClass("h3-subnav");
        
    });
    $("#facebooknote").click(function(){
        $("#facebooknotesection").show();
        $("#fbnotesave").show();
        $("#facebookpostsection").hide();
        $("#facebookactionsection").hide();
        $("#fbpostremove").hide();
        $("#fbactionsave").hide();
        
        $("#facebookaction").removeClass("top-subnav-link-active-detail");
        $("#facebookaction a").removeAttr("class");
        $("#facebooknote").removeClass("top-subnav-link-active-detail");
        $("#facebooknote a").removeAttr("class");
        $("#facebookpost").removeClass("top-subnav-link-active-detail");
        $("#facebookpost a").removeAttr("class");
        
        $("#facebooknote").addClass("top-subnav-link-active-detail");
        $("#facebooknote a").addClass("h3-subnav-subnav-active");
        $("#facebookpost").addClass("top-subnav-links-detail");
        $("#facebookpost a").addClass("h3-subnav");
        $("#facebookaction").addClass("top-subnav-links-detail");
        $("#facebookaction a").addClass("h3-subnav");
        
    });
   
    /*..................................... twitter popup navbar .................... */ 
    $("#twitterpost").click(function(){

        $("#twitterpostsection").show();
        if( $('#twtsavedpostdiv').css('display') === 'block' ){
        $("#twtpostremove").show();
        }
        $("#twitteractionsection").hide();
        $("#twitternotesection").hide();
        $("#twactionsave").hide();
        $("#twnotesave").hide();
        
        $("#twitteraction").removeClass("top-subnav-link-active-detail");
        $("#twitteraction a").removeAttr("class");
        $("#twitternote").removeClass("top-subnav-link-active-detail");
        $("#twitternote a").removeAttr("class");
        $("#twitterpost").removeClass("top-subnav-link-active-detail");
        $("#twitterpost a").removeAttr("class");
        
        $("#twitterpost").addClass("top-subnav-link-active-detail");
        $("#twitterpost a").addClass("h3-subnav-subnav-active");
        $("#twitteraction").addClass("top-subnav-links-detail");
        $("#twitteraction a").addClass("h3-subnav");
        $("#twitternote").addClass("top-subnav-links-detail");
        $("#twitternote a").addClass("h3-subnav");
    });
    $("#twitternote").click(function(){
        $("#twitternotesection").show();
        $("#twnotesave").show();
        $("#twitteractionsection").hide();
        $("#twitterpostsection").hide();
        $("#twactionsave").hide();
        $("#twtpostremove").hide();
        
        $("#twitteraction").removeClass("top-subnav-link-active-detail");
        $("#twitteraction a").removeAttr("class");
        $("#twitternote").removeClass("top-subnav-link-active-detail");
        $("#twitternote a").removeAttr("class");
        $("#twitterpost").removeClass("top-subnav-link-active-detail");
        $("#twitterpost a").removeAttr("class");
        
        $("#twitternote").addClass("top-subnav-link-active-detail");
        $("#twitternote a").addClass("h3-subnav-subnav-active");
        $("#twitteraction").addClass("top-subnav-links-detail");
        $("#twitteraction a").addClass("h3-subnav");
        $("#twitterpost").addClass("top-subnav-links-detail");
        $("#twitterpost a").addClass("h3-subnav");
    });
    $("#twitteraction").click(function(){
        
//        if(change === "1")
//        {
            var note=$("#twtnote").val();
            if(note === ''){
                
                $("#twtnotetext").text(note);
                $("#twtnoteheader").css("display","none");
                $("#twtemptyheader").css("display","block");
            }
            if(note !== '')
            {
                $("#twtnotetext").text(note);
                $("#twtemptyheader").css("display","none");
                $("#twtnoteheader").css("display","block");
                
            }
//            $("#change").val("0");
//        }
        
        
        $("#twitteractionsection").show();
        $("#twactionsave").show();
        $("#twitternotesection").hide();
        $("#twitterpostsection").hide();
        $("#twnotesave").hide();
        $("#twtpostremove").hide();
        
        $("#twitteraction").removeClass("top-subnav-link-active-detail");
        $("#twitteraction a").removeAttr("class");
        $("#twitternote").removeClass("top-subnav-link-active-detail");
        $("#twitternote a").removeAttr("class");
        $("#twitterpost").removeClass("top-subnav-link-active-detail");
        $("#twitterpost a").removeAttr("class");
        
        $("#twitteraction").addClass("top-subnav-link-active-detail");
        $("#twitteraction a").addClass("h3-subnav-subnav-active");
        $("#twitternote").addClass("top-subnav-links-detail");
        $("#twitternote a").addClass("h3-subnav");
        $("#twitterpost").addClass("top-subnav-links-detail");
        $("#twitterpost a").addClass("h3-subnav"); 
    });
    
    /*..................................... email popup navbar .................... */ 
    $("#emailpost").click(function(){
        $("#emailpostsection").show();
        $("#emailpostremove").show();
        $("#emailactionsection").hide();
        $("#emailnotesection").hide();
        $("#emailactionsave").hide();
        $("#emailnotesave").hide();
        
        $("#emailaction").removeClass("top-subnav-link-active-detail");
        $("#emailaction a").removeAttr("class");
        $("#emailnote").removeClass("top-subnav-link-active-detail");
        $("#emailnote a").removeAttr("class");
        $("#emailpost").removeClass("top-subnav-link-active-detail");
        $("#emailpost a").removeAttr("class");
        
        $("#emailpost").addClass("top-subnav-link-active-detail");
        $("#emailpost a").addClass("h3-subnav-subnav-active");
        $("#emailaction").addClass("top-subnav-links-detail");
        $("#emailaction a").addClass("h3-subnav");
        $("#emailnote").addClass("top-subnav-links-detail");
        $("#emailnote a").addClass("h3-subnav");
    });
    $("#emailnote").click(function(){
            
        $("#emailnotesection").show();
        $("#emailnotesave").show();
        $("#emailactionsection").hide();
        $("#emailpostsection").hide();
        $("#emailactionsave").hide();
        $("#emailpostremove").hide();
        
        $("#emailaction").removeClass("top-subnav-link-active-detail");
        $("#emailaction a").removeAttr("class");
        $("#emailnote").removeClass("top-subnav-link-active-detail");
        $("#emailnote a").removeAttr("class");
        $("#emailpost").removeClass("top-subnav-link-active-detail");
        $("#emailpost a").removeAttr("class");
        
        $("#emailnote").addClass("top-subnav-link-active-detail");
        $("#emailnote a").addClass("h3-subnav-subnav-active");
        $("#emailaction").addClass("top-subnav-links-detail");
        $("#emailaction a").addClass("h3-subnav");
        $("#emailpost").addClass("top-subnav-links-detail");
        $("#emailpost a").addClass("h3-subnav");
    });
    $("#emailaction").click(function(){
        var change=$("#change").val();
        if(change === "1")
        {
            var note=$("#emailnotes").val();
            $("#emaildescription").html(note);
            $("#change").val("0");
        }
        $("#emailactionsection").show();
        $("#emailactionsave").show();
        $("#emailnotesection").hide();
        $("#emailpostsection").hide();
        $("#emailnotesave").hide();
        $("#emailpostremove").hide();
        
        $("#emailaction").removeClass("top-subnav-link-active-detail");
        $("#emailaction a").removeAttr("class");
        $("#emailnote").removeClass("top-subnav-link-active-detail");
        $("#emailnote a").removeAttr("class");
        $("#emailpost").removeClass("top-subnav-link-active-detail");
        $("#emailpost a").removeAttr("class");
        
        $("#emailaction").addClass("top-subnav-link-active-detail");
        $("#emailaction a").addClass("h3-subnav-subnav-active");
        $("#emailnote").addClass("top-subnav-links-detail");
        $("#emailnote a").addClass("h3-subnav");
        $("#emailpost").addClass("top-subnav-links-detail");
        $("#emailpost a").addClass("h3-subnav"); 
    });
    
    
    $(".close").click(function(){
        
        var change=$("#change").val();
        if( change === "0")
        {
//            alert("nochange");
            closeoverlay();
            $('#slider-button').click();
            $('.bottom-cta-bar').hide();
        }
        if( change !== "0")
        {
//            alert("change");
            setTimeout(function (){
            window.open(getHost() + 'marketing.jsp', "_self");
               },430);
//            document.location.reload();
            $("#change").val("0");
            closeoverlay();
            $('#slider-button').click();
            $('.bottom-cta-bar').hide();
        }
//        window.open(getHost() + 'marketing.jsp', "_self");
        //$("#fade").hide();
        //$("#facebooksection").hide();
    });
         
    
  //////////////////////////////////////////// emaillist popup ////////////////////////////////////////
  $("#close").click(function(){
       $("#fade").hide();
       $("#addContact").hide();
  });
  $("#closeimguploadpopup").click(function(){
       $("#fade").hide();
       $("#imagepopup").hide();
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