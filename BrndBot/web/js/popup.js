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
function getImageId(idname)
    {
       
        var res = idname.split("-");
        var id=res[0];
        var imagename=res[1];
        var userid=res[2];
        var imageId=id+userid+"images";
        imagename=imagename+"&user_id="+userid;
        $("#addimage").show();
        $(".imageGallery-card >div >div").css("color", "#5F6775");
        $(".imageGallery-card").removeClass("blueborder");
        $(".imageGallery-card").addClass("bottom-margin");
         $("#div"+id).removeClass("bottom-margin");
        $("#div"+id).addClass("blueborder");
        $("#selectedimagename").val(imagename);
        $("#selectedimageid").val(id);
    }
$(document).ready(function ()
{
     $("#timepicker5").click(function(){
       $(".timepicker_wrap").css("width","27%"); 
    });
     $("#timepicker1").click(function(){
       $(".timepicker_wrap").css("width","56%"); 
    });
    /*------------------------------------------------ social image popup----------------*/
    
    $("#schedule").click(function(){
        $("#postpopup").hide();        
        $("#schedulepopup").show();
        $("#fade").show();
    });
    
    $("#closeschedulepopup").click(function(){
        $("#postpopup").show();        
        $("#schedulepopup").hide();
        $("#fade").show();
    });
    $("#postorschedule").click(function(){
        if($("#isFacebook").val()!=="true")
        {
            $("#facebookselection").hide();
        }
        if($("#isTwitter").val()!=="true")
        {
            $("#twitterselection").hide();
        }
        $("#postpopup").show();
        $("#fade").show();
    });
    $("#closepostpopup").click(function(){
        $("#postpopup").hide();
        $("#fade").hide();
    });
    $("#changeLink").click(function(){
        $("#editLink").show();
        $("#removeLink").show();
        $("#changeLink").hide();
        $("#linkpopup").show();
        $("#fade").show();
    });
    $("#editLink").click(function(){
        $("#editLink").show();
        $("#removeLink").show();
        $("#changeLink").hide();
        $("#linkpopup").show();
        $("#fade").show();
    });
    $("#closeLinkPopup").click(function(){
        $("#linkpopup").hide();
        $("#fade").hide();
    });
     $("#submitLink").click(function(){
       var textval=$("#url").val();
       var urlregex = new RegExp("^(http:\/\/www.|https:\/\/www.|ftp:\/\/www.|www.){1}([0-9A-Za-z]+\.)");
       if(urlregex.test(textval) === false)
       {
           alert("Please Enter Valid Link");
           return false; 
       }
       $("#link").val(textval);
       $("#Linkurl").val(textval);
       $("#link_title").show();
       $("#link_description").show();
       $("#Linkurl").show();
       $("#link").show();
       $("#fade").hide();
       $("#linkpopup").hide();
    });
     $("#dropdownurl").change(function(){
        var choosenlink=$("#dropdownurl").val();
        var link=choosenlink.split("--");
        $("#url").val(link[0]);
       if(choosenlink === "0")
       {
           $("#url").val("http://");
       }
    });
    $("#url").keyup(function(){
        var link=$("#url").val();
        if(link.contains("http://") === false)
        {
            if(link.contains("http:/") === true)
                $("#url").val("http://");
            if(link.contains("http:") === true)
                $("#url").val("http://");
            if(link.contains("http") === true)
                $("#url").val("http://");
            if(link.contains("htt") === true)
                $("#url").val("http://");
            if(link.contains("ht") === true)
                $("#url").val("http://");
            if(link.contains("h") === true)
                $("#url").val("http://");
            else
            {
                $("#url").val("http://"+link);
            }
        }        
    });
    $("#closeimagegallerydiv").click(function(){
      $("#imageGalleryDiv").hide();      
      $("#addContact").show();
  });
  
  $("#galleryupload").click(function(){
      $("#fileuploaddiv").show();
      $("#imageGalleryDiv").hide();
  });
  $("#closefileupload").click(function(){
      $("#fileuploaddiv").hide();
      $("#imageGalleryDiv").show();
  });
   $("#gotoimageeditor").click(function(){
        var data=[];
        var fbposttext=$("#posttext").val();
        var fblink_title=$("#link_title").val();
        var fblink_description=$("#link_description").val();
        var fbLinkurl=$("#Linkurl").val();
        var facebookpreviewimage=$("#facebookpreviewimage").val();
        var twittertext=$("#link").val();
        var mindbodyid = $("#mindbodyid").val();
        var twitterpreviewimage=$("#twitterpreviewimage").val();
        data.push(fbposttext);
        data.push(fblink_title);
        data.push(fblink_description);
        data.push(fbLinkurl);
        data.push(facebookpreviewimage);
        data.push(twittertext);
        data.push(twitterpreviewimage);        

        var selectedtype=$("#selectedtype").val();
        var id=$("#selectedid").val();
        var social=$("#social").val();
        var isFacebook=$("#isFacebook").val();
        var isTwitter=$("#isTwitter").val();
        
        window.open('socialeditor.jsp?id='+id+'&isFacebook='+isFacebook+'&isTwitter='+isTwitter+'&mediatype='+social+'&selectedtype='+selectedtype+'&data='+data, "_self");
    });
        $("#addimage").click(function(){
        
        var data=[];
        var fbposttext=$("#posttext").val();
        var fblink_title=$("#link_title").val();
        var fblink_description=$("#link_description").val();
        var fbLinkurl=$("#Linkurl").val();
        var facebookpreviewimage=$("#facebookpreviewimage").val();
        var twittertext=$("#twittertext").val();
        var twitterpreviewimage=$("#twitterpreviewimage").val();
        data.push(fbposttext);
        data.push(fblink_title);
        data.push(fblink_description);
        data.push(fbLinkurl);
        data.push(facebookpreviewimage);
        data.push(twittertext);
        data.push(twitterpreviewimage);   
        var selectedtype=$("#selectedtype").val();
        var id=$("#selectedimagename").val();
        var social=$("#social").val();
        var isFacebook=$("#isFacebook").val();
        var isTwitter=$("#isTwitter").val();
        //document.location.href = "socialimageselection.jsp?image="+image+"&isTwitter="+isTwitter+"&isFacebook="+isFacebook+"&mediaType="+mediaType+"&selectedType="+selectedType+"&data="+data;
        window.open('socialimageselection.jsp?image='+id+'&isFacebook='+isFacebook+'&isTwitter='+isTwitter+'&mediatype='+social+'&selectedtype='+selectedtype+'&data='+data+'&gallery=gallery', "_self");
        
//        $("#gotoimageeditor").css("background-color", "#5CC1A4");
//        $("#uploadimage").css("background-color", "#E3E3E3");
    });
    /*.......................................... recuring popup navbar ................*/
    $("#recuringtemplate").click(function(){
        
        $("#recuringactiondiv").hide();
        $("#recuringnotediv").hide();
        $("#recuringtemplatediv").show();
        $("#recuringaction").removeClass("top-subnav-link-active-detail");
        $("#recuringaction a").removeAttr("class");
        $("#recuringnote").removeClass("top-subnav-link-active-detail");
        $("#recuringnote a").removeAttr("class");
        $("#recuringtemplate").removeClass("top-subnav-link-active-detail");
        $("#recuringtemplate a").removeAttr("class");
        
        $("#recuringtemplate").addClass("top-subnav-link-active-detail");
        $("#recuringtemplate a").addClass("h3-subnav-subnav-active");
        $("#recuringaction").addClass("top-subnav-links-detail");
        $("#recuringaction a").addClass("h3-subnav");
        $("#recuringnote").addClass("top-subnav-links-detail");
        $("#recuringnote a").addClass("h3-subnav");
    });
    $("#recuringaction").click(function(){        
//        var fb_scheduleid=$("#fb_scheduleid").val();
//        var change=$("#change").val();
//        if(change === "1")
//        {
//            var note=$("#fbnote"+fb_scheduleid).val();
//            $("#fbdescription").text(note);
//            $("#emptynoteheader").css("display","none");
//            $("#notesavedheader").css("display","block");
//      
//            $("#change").val("0");
//        }
        $("#recuringactiondiv").show();
        $("#recuringnotediv").hide();
        $("#recuringtemplatediv").hide();
        
        $("#recuringaction").removeClass("top-subnav-link-active-detail");
        $("#recuringaction a").removeAttr("class");
        $("#recuringnote").removeClass("top-subnav-link-active-detail");
        $("#recuringnote a").removeAttr("class");
        $("#recuringtemplate").removeClass("top-subnav-link-active-detail");
        $("#recuringtemplate a").removeAttr("class");
        
        $("#recuringaction").addClass("top-subnav-link-active-detail");
        $("#recuringaction a").addClass("h3-subnav-subnav-active");
        $("#recuringtemplate").addClass("top-subnav-links-detail");
        $("#recuringtemplate a").addClass("h3-subnav");
        $("#recuringnote").addClass("top-subnav-links-detail");
        $("#recuringnote a").addClass("h3-subnav");
        
    });
    $("#recuringnote").click(function(){
        $("#recuringactiondiv").hide();
        $("#recuringnotediv").show();
        $("#recuringtemplatediv").hide();
        
        $("#recuringaction").removeClass("top-subnav-link-active-detail");
        $("#recuringaction a").removeAttr("class");
        $("#recuringnote").removeClass("top-subnav-link-active-detail");
        $("#recuringnote a").removeAttr("class");
        $("#recuringtemplate").removeClass("top-subnav-link-active-detail");
        $("#recuringtemplate a").removeAttr("class");
        
        $("#recuringnote").addClass("top-subnav-link-active-detail");
        $("#recuringnote a").addClass("h3-subnav-subnav-active");
        $("#recuringtemplate").addClass("top-subnav-links-detail");
        $("#recuringtemplate a").addClass("h3-subnav");
        $("#recuringaction").addClass("top-subnav-links-detail");
        $("#recuringaction a").addClass("h3-subnav");
        
    });
    /*.......................................... reminder popup navbar ................*/
    
    $("#reminderdetailstab").click(function(){
        var change=$("#change").val();
        var scheduleID=$("#remainder_id").val();        
        var dateid=$("#notedateid").val();
        $("#datepickernote").val(dateid);
        if(change === "1")
        {
            var note=$("#reminderdesc"+scheduleID).val();
            $("#note_desc").html(note);            
        }
        
        $("#reminderdetailsdiv").show();
        $("#savedreminderdiv").hide();
        $("#reminderactionsave").show();
        $("#remindernotesave").hide();
        
        //$("#reminderdetailstab").removeClass("top-subnav-links-detail");
        $("#reminderdetailstab").addClass("top-subnav-link-active-detail");
        $("#reminderdetailstab a").removeClass("h3-subnav");
        $("#reminderdetailstab a").addClass("h3-subnav-active");
        
        $("#savedremindertab").removeClass("top-subnav-link-active-detail");
        $("#savedremindertab a").removeClass("h3-subnav-active");
        $("#savedremindertab").addClass("top-subnav-links-detail");
        $("#savedremindertab a").addClass("h3-subnav");
        
    });
    $("#savedremindertab").click(function(){
        $("#reminderdetailsdiv").hide();
        $("#savedreminderdiv").show();
        $("#reminderactionsave").hide();
        $("#remindernotesave").show();
        
        $("#reminderdetailstab").removeClass("top-subnav-link-active-detail");
        $("#reminderdetailstab a").removeClass("h3-subnav-active");
        $("#reminderdetailstab a").addClass("h3-subnav");
        
        //$("#savedremindertab").removeClass("top-subnav-link-active-detail");
        $("#savedremindertab a").removeClass("h3-subnav");
        $("#savedremindertab").addClass("top-subnav-link-active-detail");
        $("#savedremindertab a").addClass("h3-subnav-active");
        
    });
    
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
        var fb_scheduleid=$("#fb_scheduleid").val();
        var change=$("#change").val();
        if(change === "1")
        {
            var note=$("#fb_description"+fb_scheduleid).val();
            $("#fbdescription").text(note);
            $("#emptynoteheader").css("display","none");
            $("#notesavedheader").css("display","block");
            //$("#change").val("0");
        }
//        $("#fbdescription").show();
////         var desc=$("#fbnote").val();
////         if(desc !== ''){alert(desc);$("#notesavedheader").css("display","block");$("#emptynoteheader").css("display","none");$("#fbdescription").show();}
////         if(desc === ''){alert(desc);$("#emptynoteheader").css("display","block");$("#notesavedheader").css("display","none");}
//        if(change === "1")
//        {
//            var note=$("#fbnote").val();
//            if(note === ''){
//                
//                $("#fbdescription").text(note);
//                $("#notesavedheader").css("display","none");
//                $("#emptynoteheader").css("display","block");
//            }
//            if(note !== '')
//            {
//                $("#fbdescription").text(note);
//                $("#emptynoteheader").css("display","none");
//                $("#notesavedheader").css("display","block");
//                
//            }
//            $("#change").val("0");
//        }
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
    
    $("#facebookpost1").click(function(){
        $("#facebookpostsection").show();
        if($('#savedpostdiv').is(":visible")){
            $("#fbpostremove").show();
        }
        $("#facebookactionsection").hide();
        $("#facebooknotesection").hide();
        $("#fbactionsave").hide();
        $("#fbnotesave").hide();
        
        $("#facebookaction1").removeClass("top-subnav-link-active-detail");
        $("#facebookaction1 a").removeAttr("class");
        $("#facebooknote1").removeClass("top-subnav-link-active-detail");
        $("#facebooknote1 a").removeAttr("class");
        $("#facebookpost1").removeClass("top-subnav-link-active-detail");
        $("#facebookpost1 a").removeAttr("class");
        
        $("#facebookpost1").addClass("top-subnav-link-active-detail");
        $("#facebookpost1 a").addClass("h3-subnav-subnav-active");
        $("#facebookaction1").addClass("top-subnav-links-detail");
        $("#facebookaction1 a").addClass("h3-subnav");
        $("#facebooknote1").addClass("top-subnav-links-detail");
        $("#facebooknote1 a").addClass("h3-subnav");
        
    });
    $("#facebookaction1").click(function(){        
        var fb_scheduleid=$("#fb_scheduleid").val();
        var change=$("#change").val();
        var dateid=$("#fbdateid").val();
        $("#datepickerfb").val(dateid);
        if(change === "1")
        {
            var note=$("#fbnote"+fb_scheduleid).val();
            $("#fbdescription").text(note);
            $("#emptynoteheader").css("display","none");
            $("#notesavedheader").css("display","block");
            //$("#change").val("0");
        }
//        $("#fbdescription").show();
////         var desc=$("#fbnote").val();
////         if(desc !== ''){alert(desc);$("#notesavedheader").css("display","block");$("#emptynoteheader").css("display","none");$("#fbdescription").show();}
////         if(desc === ''){alert(desc);$("#emptynoteheader").css("display","block");$("#notesavedheader").css("display","none");}
//        if(change === "1")
//        {
//            var note=$("#fbnote").val();
//            if(note === ''){
//                
//                $("#fbdescription").text(note);
//                $("#notesavedheader").css("display","none");
//                $("#emptynoteheader").css("display","block");
//            }
//            if(note !== '')
//            {
//                $("#fbdescription").text(note);
//                $("#emptynoteheader").css("display","none");
//                $("#notesavedheader").css("display","block");
//                
//            }
//            $("#change").val("0");
//        }
        $("#facebookactionsection").show();
        $("#fbactionsave").show();
        $("#facebookpostsection").hide();
        $("#facebooknotesection").hide();
        $("#fbpostremove").hide();
        $("#fbnotesave").hide();
        
        $("#facebookaction1").removeClass("top-subnav-link-active-detail");
        $("#facebookaction1 a").removeAttr("class");
        $("#facebooknote1").removeClass("top-subnav-link-active-detail");
        $("#facebooknote1 a").removeAttr("class");
        $("#facebookpost1").removeClass("top-subnav-link-active-detail");
        $("#facebookpost1 a").removeAttr("class");
        
        $("#facebookaction1").addClass("top-subnav-link-active-detail");
        $("#facebookaction1 a").addClass("h3-subnav-subnav-active");
        $("#facebookpost1").addClass("top-subnav-links-detail");
        $("#facebookpost1 a").addClass("h3-subnav");
        $("#facebooknote1").addClass("top-subnav-links-detail");
        $("#facebooknote1 a").addClass("h3-subnav");
        
    });
    $("#facebooknote1").click(function(){
        $("#facebooknotesection").show();
        $("#fbnotesave").show();
        $("#facebookpostsection").hide();
        $("#facebookactionsection").hide();
        $("#fbpostremove").hide();
        $("#fbactionsave").hide();
        
        $("#facebookaction1").removeClass("top-subnav-link-active-detail");
        $("#facebookaction1 a").removeAttr("class");
        $("#facebooknote1").removeClass("top-subnav-link-active-detail");
        $("#facebooknote1 a").removeAttr("class");
        $("#facebookpost1").removeClass("top-subnav-link-active-detail");
        $("#facebookpost1 a").removeAttr("class");
        
        $("#facebooknote1").addClass("top-subnav-link-active-detail");
        $("#facebooknote1 a").addClass("h3-subnav-subnav-active");
        $("#facebookpost1").addClass("top-subnav-links-detail");
        $("#facebookpost1 a").addClass("h3-subnav");
        $("#facebookaction1").addClass("top-subnav-links-detail");
        $("#facebookaction1 a").addClass("h3-subnav");
        
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
        var change=$("#change").val();
        var schedule_id=$("#twitter_scheduleid").val();
        if(change === "1")
        {
            var note=$("#twitternote"+schedule_id).val();
            $("#twtnotetext").text(note);
            $("#twtnoteheader").css("display","none");
            $("#twtemptyheader").css("display","block");
            //$("#change").val("0");
        }
          
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
    
    $("#twitteraction1").click(function(){
        var change=$("#change").val();
        var schedule_id=$("#twitter_scheduleid").val();
        var dateid=$("#twdateid").val();
        $("#datepickertwitter").val(dateid);
        if(change === "1")
        {
            var note=$("#twtnote").val();
            $("#twtnotetext").text(note);
            $("#twtnoteheader").css("display","none");
            $("#twtemptyheader").css("display","block");
            //$("#change").val("0");
        }
          
        $("#twitteractionsection").show();
        $("#twactionsave").show();
        $("#twitternotesection").hide();
        $("#twitterpostsection").hide();
        $("#twnotesave").hide();
        $("#twtpostremove").hide();
        
        $("#twitteraction1").removeClass("top-subnav-link-active-detail");
        $("#twitteraction1 a").removeAttr("class");
        $("#twitternote1").removeClass("top-subnav-link-active-detail");
        $("#twitternote1 a").removeAttr("class");
        $("#twitterpost1").removeClass("top-subnav-link-active-detail");
        $("#twitterpost1 a").removeAttr("class");
        
        $("#twitteraction1").addClass("top-subnav-link-active-detail");
        $("#twitteraction1 a").addClass("h3-subnav-subnav-active");
        $("#twitternote1").addClass("top-subnav-links-detail");
        $("#twitternote1 a").addClass("h3-subnav");
        $("#twitterpost1").addClass("top-subnav-links-detail");
        $("#twitterpost1 a").addClass("h3-subnav"); 
    });
    $("#twitterpost1").click(function(){

        $("#twitterpostsection").show();
        if( $('#twtsavedpostdiv').css('display') === 'block' ){
        $("#twtpostremove").show();
        }
        $("#twitteractionsection").hide();
        $("#twitternotesection").hide();
        $("#twactionsave").hide();
        $("#twnotesave").hide();
        
        $("#twitteraction1").removeClass("top-subnav-link-active-detail");
        $("#twitteraction1 a").removeAttr("class");
        $("#twitternote1").removeClass("top-subnav-link-active-detail");
        $("#twitternote1 a").removeAttr("class");
        $("#twitterpost1").removeClass("top-subnav-link-active-detail");
        $("#twitterpost1 a").removeAttr("class");
        
        $("#twitterpost1").addClass("top-subnav-link-active-detail");
        $("#twitterpost1 a").addClass("h3-subnav-subnav-active");
        $("#twitteraction1").addClass("top-subnav-links-detail");
        $("#twitteraction1 a").addClass("h3-subnav");
        $("#twitternote1").addClass("top-subnav-links-detail");
        $("#twitternote1 a").addClass("h3-subnav");
    });
    $("#twitternote1").click(function(){
        $("#twitternotesection").show();
        $("#twnotesave").show();
        $("#twitteractionsection").hide();
        $("#twitterpostsection").hide();
        $("#twactionsave").hide();
        $("#twtpostremove").hide();
        
        $("#twitteraction1").removeClass("top-subnav-link-active-detail");
        $("#twitteraction1 a").removeAttr("class");
        $("#twitternote1").removeClass("top-subnav-link-active-detail");
        $("#twitternote1 a").removeAttr("class");
        $("#twitterpost1").removeClass("top-subnav-link-active-detail");
        $("#twitterpost1 a").removeAttr("class");
        
        $("#twitternote1").addClass("top-subnav-link-active-detail");
        $("#twitternote1 a").addClass("h3-subnav-subnav-active");
        $("#twitteraction1").addClass("top-subnav-links-detail");
        $("#twitteraction1 a").addClass("h3-subnav");
        $("#twitterpost1").addClass("top-subnav-links-detail");
        $("#twitterpost1 a").addClass("h3-subnav");
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
        var id=$("#emailaction_id").val();
        var note=$("#emailnotes"+id).val();
        $("#emaildescription"+id).html(note);
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
        var id=$("#scheduleId").val();
        var dateid=$("#emaildateid").val();
        $("#emaildatetime").val(dateid);
        //alert(id);
        if(change === "1")
        {
            var note=$("#email_description"+id).val();
            $("#emaildescription"+id).html(note);
            //$("#change").val("0");
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
    
    $("#emailpost1").click(function(){
        $("#emailpostsection").show();
        $("#emailpostremove").show();
        $("#emailactionsection").hide();
        $("#emailnotesection").hide();
        $("#emailactionsave").hide();
        $("#emailnotesave").hide();
        
        $("#emailaction1").removeClass("top-subnav-link-active-detail");
        $("#emailaction1 a").removeAttr("class");
        $("#emailnote1").removeClass("top-subnav-link-active-detail");
        $("#emailnote1 a").removeAttr("class");
        $("#emailpost1").removeClass("top-subnav-link-active-detail");
        $("#emailpost1 a").removeAttr("class");
        
        $("#emailpost1").addClass("top-subnav-link-active-detail");
        $("#emailpost1 a").addClass("h3-subnav-subnav-active");
        $("#emailaction1").addClass("top-subnav-links-detail");
        $("#emailaction1 a").addClass("h3-subnav");
        $("#emailnote1").addClass("top-subnav-links-detail");
        $("#emailnote1 a").addClass("h3-subnav");
    });
    $("#emailnote1").click(function(){
        var id=$("#emailaction_id").val();
        var note=$("#emailnotes"+id).val();
        $("#emaildescription"+id).html(note);
        $("#emailnotesection").show();
        $("#emailnotesave").show();
        $("#emailactionsection").hide();
        $("#emailpostsection").hide();
        $("#emailactionsave").hide();
        $("#emailpostremove").hide();
        
        $("#emailaction1").removeClass("top-subnav-link-active-detail");
        $("#emailaction1 a").removeAttr("class");
        $("#emailnote1").removeClass("top-subnav-link-active-detail");
        $("#emailnote1 a").removeAttr("class");
        $("#emailpost1").removeClass("top-subnav-link-active-detail");
        $("#emailpost1 a").removeAttr("class");
        
        $("#emailnote1").addClass("top-subnav-link-active-detail");
        $("#emailnote1 a").addClass("h3-subnav-subnav-active");
        $("#emailaction1").addClass("top-subnav-links-detail");
        $("#emailaction1 a").addClass("h3-subnav");
        $("#emailpost1").addClass("top-subnav-links-detail");
        $("#emailpost1 a").addClass("h3-subnav");
    });
    $("#emailaction1").click(function(){
        var change=$("#change").val();
        var id=$("#email_scheduleid").val();
        var dateid=$("#emaildateid").val();
        $("#emaildatetime").val(dateid);
        //alert(id);
        if(change === "1")
        {
            var note=$("#emailnotes"+id).val();
            $("#emaildescription"+id).html(note);
            //$("#change").val("0");
        }
        $("#emailactionsection").show();
        $("#emailactionsave").show();
        $("#emailnotesection").hide();
        $("#emailpostsection").hide();
        $("#emailnotesave").hide();
        $("#emailpostremove").hide();
        
        $("#emailaction1").removeClass("top-subnav-link-active-detail");
        $("#emailaction1 a").removeAttr("class");
        $("#emailnote1").removeClass("top-subnav-link-active-detail");
        $("#emailnote1 a").removeAttr("class");
        $("#emailpost1").removeClass("top-subnav-link-active-detail");
        $("#emailpost1 a").removeAttr("class");
        
        $("#emailaction1").addClass("top-subnav-link-active-detail");
        $("#emailaction1 a").addClass("h3-subnav-subnav-active");
        $("#emailnote1").addClass("top-subnav-links-detail");
        $("#emailnote1 a").addClass("h3-subnav");
        $("#emailpost1").addClass("top-subnav-links-detail");
        $("#emailpost1 a").addClass("h3-subnav"); 
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
    });
    
    $("#addactionlistClose").click(function(){
    $("#fade").hide();
    $("#addActionemllist").hide();
//    document.getElementById('light').style.display = 'none';
//    $("#calendar").css("pointer-events","auto");
//    document.getElementById('fade').style.display = 'none';
//    document.body.style.overflow = 'scroll';
//    document.getElementById('blk').style.display = 'none';
//    document.getElementById('edtfbimg').style.display = 'none';
//    document.getElementById('prevtwtimg').style.display = 'none';
//    document.getElementById('edttwtimg').style.display = 'none';
//    document.getElementById('prevfbimg').style.display = 'none';
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