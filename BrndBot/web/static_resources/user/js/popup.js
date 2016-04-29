
var maxLength = 140;
var end_date="";
var script = document.createElement('script');
script.src = "js/alertmessage.js";
document.getElementsByTagName('script')[0].parentNode.appendChild(script);

function overlay(){
    $("#fbpopupfooter").show();
            document.getElementById('fade').style.display = 'block';
            document.getElementById('blk').style.display = 'block';
            document.body.style.overflow = 'hidden';
        }
function closeoverlay(){
             $(".timepicker_wrap").css("width","56%");
        document.getElementById('fade').style.display = 'none';
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
       
        var res = idname.split("--------");
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
    $('#chars').text(length);
    $("#emailpreview").click(function(){
        $("#deskpreview").css('background-image', 'url("' + global_host_address +'images/imac27.png'+ '")')
        $("#mobpreview").css('background-image', 'url("' + global_host_address +'images/Phone.svg'+ '")')
        $("#fade").show();
        $("#email_previewdiv").show();
    });
    $("#closePreview").click(function(){
        $("#fade").hide();
        $("#email_previewdiv").hide();
    });
    
    $("#accountsettingtab").click(function(){
        $("#logosettingdiv").hide();
        $("#palettesettingdiv").hide();
        $("#accountsettingdiv").show();
        
        $("#logosettingtab").removeAttr("class");
        $("#logosettingtab a").removeAttr("class");
        $("#palettesettingtab").removeAttr("class");
        $("#palettesettingtab a").removeAttr("class");
        $("#accountsettingtab").removeAttr("class");
        $("#accountsettingtab a").removeAttr("class");
                
        $("#logosettingtab").addClass("top-subnav-links");
        $("#logosettingtab a").addClass("h3");
        $("#palettesettingtab").addClass("top-subnav-links");
        $("#palettesettingtab a").addClass("h3");
        $("#accountsettingtab").addClass("top-subnav-link-active"); 
        $("#accountsettingtab a").addClass("h3-active-subnav"); 
    });
    
    $("#logosettingtab").click(function(){
        $("#accountsettingdiv").hide();
        $("#palettesettingdiv").hide();
        $("#logosettingdiv").show();
        
        $("#logosettingtab").removeAttr("class");
        $("#logosettingtab a").removeAttr("class");
        $("#palettesettingtab").removeAttr("class");
        $("#palettesettingtab a").removeAttr("class");
        $("#accountsettingtab").removeAttr("class");
        $("#accountsettingtab a").removeAttr("class");
        
        $("#logosettingtab").addClass("top-subnav-link-active");
        $("#logosettingtab a").addClass("h3-active-subnav");
        $("#palettesettingtab").addClass("top-subnav-links");
        $("#palettesettingtab a").addClass("h3");
        $("#accountsettingtab").addClass("top-subnav-links"); 
        $("#accountsettingtab a").addClass("h3"); 
    });
    $("#palettesettingtab").click(function(){
        $("#accountsettingdiv").hide();
        $("#logosettingdiv").hide();
        $("#palettesettingdiv").show();
        
        $("#logosettingtab").removeAttr("class");
        $("#logosettingtab a").removeAttr("class");
        $("#palettesettingtab").removeAttr("class");
        $("#palettesettingtab a").removeAttr("class");
        $("#accountsettingtab").removeAttr("class");
        $("#accountsettingtab a").removeAttr("class");
        
        $("#palettesettingtab").addClass("top-subnav-link-active");
        $("#palettesettingtab a").addClass("h3-active-subnav");
        $("#logosettingtab").addClass("top-subnav-links");
        $("#logosettingtab a").addClass("h3");
        $("#accountsettingtab").addClass("top-subnav-links"); 
        $("#accountsettingtab a").addClass("h3"); 
    });
    
        
    $("#schedule_social_time").click(function(){
        $(".timepicker_wrap").css("margin-top","-207px");
        $(".timepicker_wrap").css("width","195px");
    });
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
    });
    $("#closepostpopup").click(function(){
        $("#postpopup").hide();
        $("#fade").hide();
    });
    $("#changeLink").click(function(){       
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
    
      
//    $('#twittertext').keyup(function() {
//        var length = $(this).val().length;
//        var length = maxLength-length;
//        $("#chars").removeClass("red");
//        $("#chars").addClass("gray");
//        if(length === 0){
//            $("#chars").removeClass("gray");
//            $("#chars").addClass("red");
//        }
//        $('#chars').text(length);
//    });
//    
//    $('#twittertext').mouseover(function() {
//        var length = $(this).val().length;
//        var length = maxLength-length;
//        $("#chars").removeClass("red");
//        $("#chars").addClass("gray");
//        if(length === 0){
//            $("#chars").removeClass("gray");
//            $("#chars").addClass("red");
//        }
//        $('#chars').text(length);
//    });
    
//    $('#twittertext').mouseout(function() {
//        var length = $(this).val().length;
//        var length = maxLength-length;
//        $("#chars").removeClass("red");
//        $("#chars").addClass("gray");
//        if(length === 0){
//            $("#chars").removeClass("gray");
//            $("#chars").addClass("red");
//        }
//        $('#chars').text(length);
//    });
//   
    
    var sortlink="bit.ly/1XOkJo";
    $("#submitLink").click(function(){
        $("#editLink").show();
        $("#removeLink").show();
        $("#changeLink").hide();
        var textval=$("#url").val();
        var link=textval;
        var urlregex = new RegExp("^(http:\/\/www.|https:\/\/www.|ftp:\/\/www.|www.){1}([0-9A-Za-z]+\.)");
        if(urlregex.test(textval) === false)
        {
            alert(linkerror);
            return false; 
        }
        $("#link").val(textval);
        $("#Linkurl").val(textval);
        $("#link_title").show();
        $("#link_description").show();
        $("#Linkurl").show();
//        $("#link").show();
       
        $("#fade").hide();
        $("#linkpopup").hide();
        var value=$("#twittertext").val();
        var valuelenght=$("#twittertext").val().length;
        var max=0;
        if(valuelenght >117){
            max=1;
            $("#twittertext").val(value); 
            alert("Link can't be added! Because Twitter can't accept More than 140 characters.");
        }
        if($("#twittertext").val().trim().contains("bit.ly/1XOkJo")){
             var twtext = $("#twittertext").val();
                var res = twtext.split(" ");
                var text = "";
                var i = "";
                var flag1=0;
                 var space=0;
                $.each(res, function (i, value) {
                    if (value === " ")
                    {
                          space=1;
                    }
                    if (value === "bit.ly/1XOkJo") {
                        flag1=1;
                        space=0;
                    }
                    else if(space===0)
                    {
                        flag1=0;
                        text +=value+" ";
                    }
                    else
                    {
                        flag1=0;
                        text +=value;
                    }

                });
                latesttwtext = text;
                if(flag1 === "1")
                {        
                    $("#twittertext").val(latesttwtext); 
                }
                else
                {
                    $("#twittertext").val(latesttwtext+" "+sortlink);   
                }                      
        }
        else{
            if(max!==1){
                $("#twittertext").val(value+" "+sortlink);        
            }
        }        
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
        var twitterlink=$("#link").val();
        var facebookpreviewimage=$("#facebookpreviewimage").val();
        var twittertext=$("#twittertext").val();
        var mindbodyid = $("#mindbodyid").val();
        var twitterpreviewimage=$("#twitterpreviewimage").val();
        data.push(fbposttext);
        data.push(fblink_title);
        data.push(fblink_description);
        data.push(fbLinkurl);
        data.push(twittertext);
        data.push(twitterlink);
        data.push(facebookpreviewimage);        
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
        var twitterlink=$("#link").val();
        var twitterpreviewimage=$("#twitterpreviewimage").val();
        data.push(fbposttext);
        data.push(fblink_title);
        data.push(fblink_description);
        data.push(fbLinkurl);
        data.push(twittertext);
        data.push(twitterlink);
        data.push(facebookpreviewimage);
        data.push(twitterpreviewimage);  
        var selectedtype=$("#selectedtype").val();
        var id=$("#selectedimagename").val();
        var social=$("#social").val();
        var isFacebook=$("#isFacebook").val();
        var isTwitter=$("#isTwitter").val();
        window.open('socialimageselection.jsp?image='+id+'&isFacebook='+isFacebook+'&isTwitter='+isTwitter+'&mediatype='+social+'&selectedtype='+selectedtype+'&data='+data+'&gallery=gallery', "_self");
    });
    /*.......................................... recurring popup navbar ................*/
    $("#recurringtemplate").click(function(){        
        $("#recurringactiondiv").hide();
        $("#recurringnotediv").hide();
        $("#recurringtemplatediv").show();
        $("#recurringaction").removeClass("top-subnav-link-active-detail");
        $("#recurringaction a").removeAttr("class");
        $("#recurringnote").removeClass("top-subnav-link-active-detail");
        $("#recurringnote a").removeAttr("class");
        $("#recurringtemplate").removeClass("top-subnav-link-active-detail");
        $("#recurringtemplate a").removeAttr("class");
        
        $("#recurringtemplate").addClass("top-subnav-link-active-detail");
        $("#recurringtemplate a").addClass("h3-subnav-subnav-active");
        $("#recurringaction").addClass("top-subnav-links-detail");
        $("#recurringaction a").addClass("h3-subnav");
        $("#recurringnote").addClass("top-subnav-links-detail");
        $("#recurringnote a").addClass("h3-subnav");
    });
    $("#recurringaction").click(function(){        
        $("#recurringactiondiv").show();
        $("#recurringnotediv").hide();
        $("#recurringtemplatediv").hide();
        
        $("#recurringaction").removeClass("top-subnav-link-active-detail");
        $("#recurringaction a").removeAttr("class");
        $("#recurringnote").removeClass("top-subnav-link-active-detail");
        $("#recurringnote a").removeAttr("class");
        $("#recurringtemplate").removeClass("top-subnav-link-active-detail");
        $("#recurringtemplate a").removeAttr("class");
        
        $("#recurringaction").addClass("top-subnav-link-active-detail");
        $("#recurringaction a").addClass("h3-subnav-subnav-active");
        $("#recurringtemplate").addClass("top-subnav-links-detail");
        $("#recurringtemplate a").addClass("h3-subnav");
        $("#recurringnote").addClass("top-subnav-links-detail");
        $("#recurringnote a").addClass("h3-subnav");
        
    });
    $("#recurringnote").click(function(){
        $("#recurringactiondiv").hide();
        $("#recurringnotediv").show();
        $("#recurringtemplatediv").hide();
        
        $("#recurringaction").removeClass("top-subnav-link-active-detail");
        $("#recurringaction a").removeAttr("class");
        $("#recurringnote").removeClass("top-subnav-link-active-detail");
        $("#recurringnote a").removeAttr("class");
        $("#recurringtemplate").removeClass("top-subnav-link-active-detail");
        $("#recurringtemplate a").removeAttr("class");
        
        $("#recurringnote").addClass("top-subnav-link-active-detail");
        $("#recurringnote a").addClass("h3-subnav-subnav-active");
        $("#recurringtemplate").addClass("top-subnav-links-detail");
        $("#recurringtemplate a").addClass("h3-subnav");
        $("#recurringaction").addClass("top-subnav-links-detail");
        $("#recurringaction a").addClass("h3-subnav");
        
    });
    /*.......................................... reminder popup navbar ................*/
    
    $("#reminderdetailstab").click(function(){
        $("#timepickernote").css("width",'40%');
        $(".timepicker_wrap").css("margin-top","-200px").css("width",'28%');
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
        }
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
        if(change === "1")
        {
            var note=$("#email_description"+id).val();
            $("#emaildescription"+id).html(note);
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
        if(change === "1")
        {
            var note=$("#emailnotes"+id).val();
            $("#emaildescription"+id).html(note);
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
    
  /////////////////////////////////////////// overview and action tab /////////////////////////////////
  $("#overview").click(function(){
        $("#actionstab").hide();
        $("#overviewtab").show();
        $("#saveprogram").show();
        
        $("#actionsli").removeClass("top-subnav-link-active");
        $("#actionsli a").removeClass("h3-active-subnav");
        $("#ovrviewli").removeClass("top-subnav-links");
        $("#ovrviewli a").removeClass("h3");

        $("#ovrviewli").addClass("top-subnav-link-active");
        $("#ovrviewli a").addClass("h3-active-subnav");
        $("#actionsli").addClass("top-subnav-links");
        $("#actionsli a").addClass("h3");
  });
  $("#actions").click(function(){
        $("#overviewtab").hide();
        $("#saveprogram").hide();
        $("#actionstab").show();

        $("#ovrviewli").removeClass("top-subnav-link-active");
        $("#ovrviewli a").removeClass("h3-active-subnav");
        
        $("#ovrviewli").addClass("top-subnav-links");
        $("#ovrviewli a").addClass("h3");
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
    });
////// social flow  ///
   $("#addImageToPostButton").click(function(){
    $("#addImageDiv").show();
    $("#addImageToPostButton").hide();
    });
   $("#changeImage").click(function(){
     $("#imagePopUp").show();
    });
   $("#hidepopup").click(function(){
     $("#imagePopUp").hide();
    });
    
});
function changePostType(){
   var postType = $("#linkPostFields").css("display");
    if(postType === "none"){
    $("#linkPostFields").show();
    $("#postType").text("Change To Normal Post");
    }
  if(postType === "inline"){
    $("#linkPostFields").hide();
    $("#postType").text("Change To Link Post");
    }
}

function fbPost(){
    var shareText = $("#shareText").val();
    var linkTitle = $("#linkTitle").val();
    var linkDescription = $("#linkDescription").val();
    var linkUrl = $("#linkUrl").val();
    var dataObject="";
    if(linkTitle === ""){
       
    }
   $.ajax({
            url: "",
           method: 'post',
            data: dataObject,
          success: function (responseText) {
                alert(JSON.stringify(responseText));
                        },
          error: function (jqXHR, textStatus, errorThrown) {
              alert(JSON.stringify(jqXHR));
        }
    });
}
function twitterPost(){
    var shareText = $("#twitterShareText").val();
    var dataObject="";
   $.ajax({
            url: "",
           method: 'post',
            data: dataObject,
          success: function (responseText) {
                alert(JSON.stringify(responseText));
                        },
          error: function (jqXHR, textStatus, errorThrown) {
              alert(JSON.stringify(jqXHR));
        }
    });
}

function displayUserImages($scope,$http){
    $scope.getUserImaages = function(){
        $http({
                url:"",
                method:"GET"      
        }).success(function(data){
            
            
        });
    }; 
}