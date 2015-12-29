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
    $("#imageGalleryDiv").hide();
    $("#emailId").val(emailAddress);
    $("#uuid").val(UUID);
    $("#firstName").val(firstName);
    $("#lastName").val(lastName);
    $("#type").val(type);
    $("#selectedtype").val(type);
    $("#selectedid").val(emailAddress);
    overlay();
}
function getImageId(idname)
{
    var res = idname.split("-");
    var id=res[0];
    var imagename=res[1];
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
    $("#galleryupload").click(function(){
        $("#fileuploaddiv").show();
        $("#imageGalleryDiv").hide();
    });
    $("#closefileupload").click(function(){
        $("#fileuploaddiv").hide();
        $("#imageGalleryDiv").show();
    });
    $("#changeLink").click(function(){
        $("#linkpopup").show();
        $("#fade").show();
    });
    $("#closeLinkPopup").click(function(){
        $("#linkpopup").hide();
        $("#fade").hide();
    });
    var data1=$("#data").val();
    var data = data1.split(',');
    var imageurl="/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&image_name=";
    //var imagen=$("#imagen").val();
    //imageurl
    $("#posttext").val(data[0]);
    $("#link_title").val(data[1]);
    $("#link_description").val(data[2]);
    $("#Linkurl").val(data[3]);
    $("#facebookpreviewimage").val(data[4]);
    $("#facebookpreviewimage").attr('src', data[4]);
    if(data[4]==="")
    {
        $("#facebookimage").hide();
        $("#fbChangeImage").show();
    }
    else
    {
        $("#facebookimage").show();
        $("#fbChangeImage").hide();
    }
    
    $("#twittertext").val(data[5]);
    $("#twitterpreviewimage").val(data[6]);
    $("#twitterimage").val("1");
    if(data[6]==="")
    {
        $("#twitterimage").hide();
        $("#twChangeImage").show();
    }
    else
    {
        $("#twitterimage").show();
        $("#twChangeImage").hide();
    }
    $("#twitterpreviewimage").attr('src', data[6]);
    
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
        window.open('socialimageselection.jsp?image='+id+'&isFacebook='+isFacebook+'&isTwitter='+isTwitter+'&mediatype='+social+'&selectedtype='+selectedtype+'&data='+data, "_self");
        
//        $("#gotoimageeditor").css("background-color", "#5CC1A4");
//        $("#uploadimage").css("background-color", "#E3E3E3");
    });    
    
    $("#gotoimageeditor").click(function(){
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
        var id=$("#selectedid").val();
        var social=$("#social").val();
        var isFacebook=$("#isFacebook").val();
        var isTwitter=$("#isTwitter").val();
        window.open('socialeditor.jsp?id='+id+'&isFacebook='+isFacebook+'&isTwitter='+isTwitter+'&mediatype='+social+'&selectedtype='+selectedtype+'&data='+data, "_self");
        
//        $("#gotoimageeditor").css("background-color", "#5CC1A4");
//        $("#uploadimage").css("background-color", "#E3E3E3");
    });
//    $("#uploadimage").click(function(){
//        alert("hi..");
//        $("#imageGalleryDiv").show();  
//        var selectedtype=$("#selectedtype").val();
//        var id=$("#selectedid").val();
//        var social=$("#social").val();
//        window.open('http://www.google.com?id='+id+'&mediatype='+social+'&selectedtype='+selectedtype, "_self");
//        $("#uploadimage").css("background-color", "#5CC1A4");
//        $("#gotoimageeditor").css("background-color", "#E3E3E3");
//
//      });
    
  //////////////////////////////////////////// emaillist popup ////////////////////////////////////////
  $(".email").click(function(){
      alert();
  });
  $("#close").click(function(){
      $("#addContact").hide();     
      $("#fade").hide();
  });
  $("#closeimagegallerydiv").click(function(){
      $("#imageGalleryDiv").hide();      
      $("#addContact").show();
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