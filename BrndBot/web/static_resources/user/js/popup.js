
var program_id="0";
var maxLength = 140;
var end_date = "";
var selecImageName = "";
var selecImageType = "";
var selecCompanyId = "";
var bit_url = "";
var lonkopen=0;
var script = document.createElement('script');
script.src = "js/alertmessage.js";
document.getElementsByTagName('script')[0].parentNode.appendChild(script);

function overlay() {
    $("#fbpopupfooter").show();
    document.getElementById('fade').style.display = 'block';
    document.getElementById('blk').style.display = 'block';
    document.body.style.overflow = 'hidden';
}
function closeoverlay() {
    $(".timepicker_wrap").css("width", "56%");
    document.getElementById('fade').style.display = 'none';
}

function fun(type, email, id, fname, lname)
{

    $("#fade").show();
    $("#addContact").show();
    $("#uuid").val(id);
    if (type === "update")
    {
        $("#emailId").val(email);
    } else
    {
        $("#emailId").val("");
    }
    if (fname !== "")
    {
        $("#firstName").val(fname);
    } else
    {
        $("#firstName").val("");
    }
    if (lname !== "")
    {
        $("#lastName").val(lname);
    } else
    {
        $("#lastName").val("");
    }

    $("#type").val(type);
    overlay();
}
function getImageId(idname)
{

    var res = idname.split("--------");
    var id = res[0];
    var imagename = res[1];
    var userid = res[2];
    var imageId = id + userid + "images";
    imagename = imagename + "&user_id=" + userid;
    $("#addimage").show();
    $(".imageGallery-card >div >div").css("color", "#5F6775");
    $(".imageGallery-card").removeClass("blueborder");
    $(".imageGallery-card").addClass("bottom-margin");
    $("#div" + id).removeClass("bottom-margin");
    $("#div" + id).addClass("blueborder");
    $("#selectedimagename").val(imagename);
    $("#selectedimageid").val(id);
}
$(document).ready(function ()
{
  $(".imageSelect").click(function () {
    $('.selected').removeClass('selected');
    $(this).addClass('selected');
});
    $('#chars').text(length);
    $("#emailpreview").click(function () {
        $("#deskpreview").css('background-image', 'url("' + global_host_address + 'images/imac27.png' + '")')
        $("#mobpreview").css('background-image', 'url("' + global_host_address + 'images/Phone.svg' + '")')
        $("#fade").show();
        $("#email_previewdiv").show();
    });
    $("#closePreview").click(function () {
        $("#fade").hide();
        $("#email_previewdiv").hide();
    });

    $("#accountsettingtab").click(function () {
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

    $("#logosettingtab").click(function () {
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
    $("#palettesettingtab").click(function () {
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


    $("#schedule_social_time").click(function () {
        $(".timepicker_wrap").css("margin-top", "-207px");
        $(".timepicker_wrap").css("width", "195px");
    });
    $("#timepicker5").click(function () {
        $(".timepicker_wrap").css("width", "27%");
    });
    $("#timepicker1").click(function () {
        $(".timepicker_wrap").css("width", "56%");
    });
    /*------------------------------------------------ social image popup----------------*/

    $("#schedule").click(function () {
        $("#postpopup").hide();
        $("#schedulepopup").show();
        $("#fade").show();
    });

    $("#closeschedulepopup").click(function () {
        $("#postpopup").show();
        $("#schedulepopup").hide();
        $("#fade").show();
    });
    $("#postorschedule").click(function () {
        if ($("#isFacebook").val() !== "true")
        {
            $("#facebookselection").hide();
        }
        if ($("#isTwitter").val() !== "true")
        {
            $("#twitterselection").hide();
        }
    });
    $("#closepostpopup").click(function () {
        $("#postpopup").hide();
        $("#fade").hide();
    });
    $("#changeLink").click(function () {
        $("#linkpopup").show();
        $("#fade").show();
    });
    $("#editLink").click(function () {
        $("#editLink").show();
        $("#removeLink").show();
        $("#changeLink").hide();
        $("#linkpopup").show();
        $("#fade").show();

    });
    $("#closeLinkPopup").click(function () {
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

    var sortlink = "bit.ly/1XOkJo";
    $("#submitLink").click(function () {
        $("#editLink").show();
        $("#removeLink").show();
        $("#changeLink").hide();
        var textval = $("#url").val();
        var link = textval;
        var urlregex = new RegExp("^(http:\/\/www.|https:\/\/www.|ftp:\/\/www.|www.){1}([0-9A-Za-z]+\.)");
        if (urlregex.test(textval) === false)
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
        var value = $("#twittertext").val();
        var valuelenght = $("#twittertext").val().length;
        var max = 0;
        if (valuelenght > 117) {
            max = 1;
            $("#twittertext").val(value);
            alert("Link can't be added! Because Twitter can't accept More than 140 characters.");
        }
        if ($("#twittertext").val().trim().contains("bit.ly/1XOkJo")) {
            var twtext = $("#twittertext").val();
            var res = twtext.split(" ");
            var text = "";
            var i = "";
            var flag1 = 0;
            var space = 0;
            $.each(res, function (i, value) {
                if (value === " ")
                {
                    space = 1;
                }
                if (value === "bit.ly/1XOkJo") {
                    flag1 = 1;
                    space = 0;
                } else if (space === 0)
                {
                    flag1 = 0;
                    text += value + " ";
                } else
                {
                    flag1 = 0;
                    text += value;
                }

            });
            latesttwtext = text;
            if (flag1 === "1")
            {
                $("#twittertext").val(latesttwtext);
            } else
            {
                $("#twittertext").val(latesttwtext + " " + sortlink);
            }
        } else {
            if (max !== 1) {
                $("#twittertext").val(value + " " + sortlink);
            }
        }
    });


    $("#dropdownurl").change(function () {
        var choosenlink = $("#dropdownurl").val();
        var link = choosenlink.split("--");
        $("#linkUrl").val(link[0]);
        if (choosenlink === "0")
        {
            $("#linkUrl").val("http://");
            $("#linkTitle").val("");
            $("#linkUrl").val(""); 
            $("#linkDescription").val(""); 
        }
    });
    $("#url").keyup(function () {
        var link = $("#url").val();
        if (link.contains("http://") === false)
        {
            if (link.contains("http:/") === true)
                $("#url").val("http://");
            if (link.contains("http:") === true)
                $("#url").val("http://");
            if (link.contains("http") === true)
                $("#url").val("http://");
            if (link.contains("htt") === true)
                $("#url").val("http://");
            if (link.contains("ht") === true)
                $("#url").val("http://");
            if (link.contains("h") === true)
                $("#url").val("http://");
            else
            {
                $("#url").val("http://" + link);
            }
        }
    });
    $("#closeimagegallerydiv").click(function () {
        $("#imageGalleryDiv").hide();
        $("#addContact").show();
    });

    $("#galleryupload").click(function () {
        $("#fileuploaddiv").show();
        $("#imageGalleryDiv").hide();
    });
    $("#closefileupload").click(function () {
        $("#fileuploaddiv").hide();
        $("#imageGalleryDiv").show();
    });
    $("#gotoimageeditor").click(function () {
        var data = [];
        var fbposttext = $("#posttext").val();
        var fblink_title = $("#link_title").val();
        var fblink_description = $("#link_description").val();
        var fbLinkurl = $("#Linkurl").val();
        var twitterlink = $("#link").val();
        var facebookpreviewimage = $("#facebookpreviewimage").val();
        var twittertext = $("#twittertext").val();
        var mindbodyid = $("#mindbodyid").val();
        var twitterpreviewimage = $("#twitterpreviewimage").val();
        data.push(fbposttext);
        data.push(fblink_title);
        data.push(fblink_description);
        data.push(fbLinkurl);
        data.push(twittertext);
        data.push(twitterlink);
        data.push(facebookpreviewimage);
        data.push(twitterpreviewimage);

        var selectedtype = $("#selectedtype").val();
        var id = $("#selectedid").val();
        var social = $("#social").val();
        var isFacebook = $("#isFacebook").val();
        var isTwitter = $("#isTwitter").val();

        window.open('socialeditor.jsp?id=' + id + '&isFacebook=' + isFacebook + '&isTwitter=' + isTwitter + '&mediatype=' + social + '&selectedtype=' + selectedtype + '&data=' + data, "_self");
    });
    $("#addimage").click(function () {

        var data = [];
        var fbposttext = $("#posttext").val();
        var fblink_title = $("#link_title").val();
        var fblink_description = $("#link_description").val();
        var fbLinkurl = $("#Linkurl").val();
        var facebookpreviewimage = $("#facebookpreviewimage").val();
        var twittertext = $("#twittertext").val();
        var twitterlink = $("#link").val();
        var twitterpreviewimage = $("#twitterpreviewimage").val();
        data.push(fbposttext);
        data.push(fblink_title);
        data.push(fblink_description);
        data.push(fbLinkurl);
        data.push(twittertext);
        data.push(twitterlink);
        data.push(facebookpreviewimage);
        data.push(twitterpreviewimage);
        var selectedtype = $("#selectedtype").val();
        var id = $("#selectedimagename").val();
        var social = $("#social").val();
        var isFacebook = $("#isFacebook").val();
        var isTwitter = $("#isTwitter").val();
        window.open('socialimageselection.jsp?image=' + id + '&isFacebook=' + isFacebook + '&isTwitter=' + isTwitter + '&mediatype=' + social + '&selectedtype=' + selectedtype + '&data=' + data + '&gallery=gallery', "_self");
    });
    /*.......................................... recurring popup navbar ................*/
    $("#recurringtemplate").click(function () {
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
    $("#recurringaction").click(function () {
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
    $("#recurringnote").click(function () {
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

    $("#reminderdetailstab").click(function () {
        $("#timepickernote").css("width", '40%');
        $(".timepicker_wrap").css("margin-top", "-200px").css("width", '28%');
        var change = $("#change").val();
        var scheduleID = $("#remainder_id").val();
        var dateid = $("#notedateid").val();
        $("#datepickernote").val(dateid);
        if (change === "1")
        {
            var note = $("#reminderdesc" + scheduleID).val();
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
    $("#savedremindertab").click(function () {
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
    $("#facebookpost").click(function () {
        $("#facebookpostsection").show();
        if ($('#savedpostdiv').is(":visible")) {
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
    $("#facebookaction").click(function () {
        var fb_scheduleid = $("#fb_scheduleid").val();
        var change = $("#change").val();
        if (change === "1")
        {
            var note = $("#fb_description" + fb_scheduleid).val();
            $("#fbdescription").text(note);
            $("#emptynoteheader").css("display", "none");
            $("#notesavedheader").css("display", "block");
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
    $("#facebooknote").click(function () {
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

    $("#facebookpost1").click(function () {
        $("#facebookpostsection").show();
        if ($('#savedpostdiv').is(":visible")) {
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
    $("#facebookaction1").click(function () {
        var fb_scheduleid = $("#fb_scheduleid").val();
        var change = $("#change").val();
        var dateid = $("#fbdateid").val();
        $("#datepickerfb").val(dateid);
        if (change === "1")
        {
            var note = $("#fbnote" + fb_scheduleid).val();
            $("#fbdescription").text(note);
            $("#emptynoteheader").css("display", "none");
            $("#notesavedheader").css("display", "block");
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
    $("#facebooknote1").click(function () {
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
    $("#twitterpost").click(function () {

        $("#twitterpostsection").show();
        if ($('#twtsavedpostdiv').css('display') === 'block') {
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
    $("#twitternote").click(function () {
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
    $("#twitteraction").click(function () {
        var change = $("#change").val();
        var schedule_id = $("#twitter_scheduleid").val();
        if (change === "1")
        {
            var note = $("#twitternote" + schedule_id).val();
            $("#twtnotetext").text(note);
            $("#twtnoteheader").css("display", "none");
            $("#twtemptyheader").css("display", "block");
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

    $("#twitteraction1").click(function () {
        var change = $("#change").val();
        var schedule_id = $("#twitter_scheduleid").val();
        var dateid = $("#twdateid").val();
        $("#datepickertwitter").val(dateid);
        if (change === "1")
        {
            var note = $("#twtnote").val();
            $("#twtnotetext").text(note);
            $("#twtnoteheader").css("display", "none");
            $("#twtemptyheader").css("display", "block");
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
    $("#twitterpost1").click(function () {

        $("#twitterpostsection").show();
        if ($('#twtsavedpostdiv').css('display') === 'block') {
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
    $("#twitternote1").click(function () {
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
    $("#emailpost").click(function () {
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
    $("#emailnote").click(function () {
        var id = $("#emailaction_id").val();
        var note = $("#emailnotes" + id).val();
        $("#emaildescription" + id).html(note);
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
    $("#emailaction").click(function () {
        var change = $("#change").val();
        var id = $("#scheduleId").val();
        var dateid = $("#emaildateid").val();
        $("#emaildatetime").val(dateid);
        if (change === "1")
        {
            var note = $("#email_description" + id).val();
            $("#emaildescription" + id).html(note);
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

    $("#emailpost1").click(function () {
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
    $("#emailnote1").click(function () {
        var id = $("#emailaction_id").val();
        var note = $("#emailnotes" + id).val();
        $("#emaildescription" + id).html(note);
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
    $("#emailaction1").click(function () {
        var change = $("#change").val();
        var id = $("#email_scheduleid").val();
        var dateid = $("#emaildateid").val();
        $("#emaildatetime").val(dateid);
        if (change === "1")
        {
            var note = $("#emailnotes" + id).val();
            $("#emaildescription" + id).html(note);
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
    $("#overview").click(function () {
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
    $("#actions").click(function () {
        $("#overviewtab").hide();
        $("#saveprogram").hide();
        $("#actionstab").show();

        $("#ovrviewli").removeClass("top-subnav-link-active");
        $("#ovrviewli a").removeClass("h3-active-subnav");

        $("#ovrviewli").addClass("top-subnav-links");
        $("#ovrviewli a").addClass("h3");
    });


    //////////////////////////////////////////// emaillist popup ////////////////////////////////////////
    $("#close").click(function () {
        $("#fade").hide();
        $("#addContact").hide();
    });
    $("#closeimguploadpopup").click(function () {
        $("#fade").hide();
        $("#imagepopup").hide();
    });


    /////////////////////////////////////////////////////////////////////////////////////////////////////

    $("#addactionClose").click(function () {
        $("#fade").hide();
        $("#addAction").hide();
    });

    $("#addactionlistClose").click(function () {
        $("#fade").hide();
        $("#addActionemllist").hide();
    });
////// social flow  ///
    $("#addImageToPostButton").click(function () {
         $("#addImageDiv").show();
        $("#imagePopUp").show();
        $("#addImageToPostButton").hide();
    });
    $("#changeImage").click(function () {
        $("#imagePopUp").show();
    });
    
    $("#hidepopup").click(function () {
        $("#addImageDiv").hide();
        $("#addImageToPostButton").show();
        $("#imagePopUp").hide();
        $("#twitterSetPinPopUp").hide();
        $("#fbmanagePagePopUp").hide();
        $("#addImageToTwitterPost").show();
        $("#addImageDivToTwitterPost").hide();
    });
    $("#shoeUploadimage").click(function () {
        $("#gallerySpan").hide();
        $("#addImageButton").hide();
        $("#uploadImageButton").show();
        $("#showGallery").removeClass("popUp_subheader-tabs-active fleft").addClass("popUp_subheader-tabs fleft");
        $("#shoeUploadimage").removeClass("popUp_subheader-tabs fleft").addClass("popUp_subheader-tabs-active fleft");
        $("#uploadImageSpan").show();
        
    });
    $("#showGallery").click(function () {
        $("#gallerySpan").show();
        $("#uploadImageButton").hide();
        $("#addImageButton").show();
        $("#showGallery").removeClass("popUp_subheader-tabs fleft").addClass("popUp_subheader-tabs-active fleft");
        $("#shoeUploadimage").removeClass("popUp_subheader-tabs-active fleft").addClass("popUp_subheader-tabs fleft");
        $("#uploadImageSpan").hide();
    });
    $("#addImageToTwitterPost").click(function () {
         $("#imagePopUp").show();
        $("#addImageDivToTwitterPost").show();
        $("#addImageToTwitterPost").hide();
    });
    
$("#closesendpopup").click(function () {
    $("#sendpopup").hide();
    $("#fade").hide();
});
});
function changePostType() {
    var postType = $("#linkPostFields").css("display");
    if (postType === "none") {
        $("#linkPostFields").show();
        $("#urlDropDownSpan").show();        
        lonkopen=1;
        $("#postType").text("Change To Normal Post");
    }
    if (postType === "inline") {
        $("#linkPostFields").hide();
        $("#urlDropDownSpan").hide();
        $("#linkTitle").val("");
        $("#linkDescription").val("");
        $("#linkUrl").val("");
        lonkopen=0;
        $("#postType").text("Change To Link Post");
    }
}
function postToFacebook() {
    var shareText = $("#shareText").val();
    var linkTitle = $("#linkTitle").val();
    var linkDescription = $("#linkDescription").val();
    var linkUrl = $("#linkUrl").val();
    var image_name = selecImageName;
    var image_type = selecImageType;
    $.ajax({
        url: getHost() + "socialPost/postToFacebook",
        method: 'post',
        data: JSON.stringify({
            imageToPost: image_name,
            accessToken: localStorage.getItem("CurrentFbAccessToken"),
            postText: shareText,
            title: linkTitle,
            url: linkUrl,
            description: linkDescription,
            imageType: image_type
        }),
        success: function (responseText) {
            var isSuccess = responseText.d.message;
//            alert(JSON.stringify(responseText));
            if (isSuccess === "success") {
                $("#closesendpopup").trigger('click');
                $("#fbSuccessPostPopup").show();
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(JSON.stringify(jqXHR));
        }
    });
}

function scheduleTwitter() {
    var twitterac = $("#twitteractions").val();
    var program_id=$("#programs").val();
    var shareText = $("#shareText").val();
    var linkTitle = $("#linkTitle").val();
    var linkDescription = $("#linkDescription").val();
    var linkUrl = $("#linkUrl").val();
    var image_name = selecImageName;
    var image_type = selecImageType;
    var schedule_date = $("#schedule_social_date").val();
    var schedule_time = $("#schedule_social_time").val().replace(/ /g, '');
    var schedule_title = $("#schedule_title").val();
    var schedule_desc = $("#schedule_desc").val();
    var l = schedule_date.toLocaleString() + " " + schedule_time.toLocaleString();
    var schedule_time = Date.parse(l);
    var myEpoch = schedule_time;
    var schedule_id_twitter="";
    var sendData="";
    if(program_id !=="0" || twitterac !== "0")
    {    
        if (twitterac === "0")
        {
            alert(twitteractionchoose);
            $("#twitteractions").focus();
            return false;
        }
        schedule_id_twitter = $("#twitteractions").val();
        $.ajax({
            url: getHost() + '/settings/twitterDetails.do',
            method: "POST",
            data: JSON.stringify({
                access_token_method: "getAccessToken"
            })
        }).success(function (data, status, headers, config) {
            var twitterData = data.d.message.split(",");
            var accessToken =twitterData[0];
            var tokenSecret =twitterData[1];
            var shareText = $("#twitterShareText").val();
            var url = $("#linkUrl").val();
            if(url !=="")
            {
                var username = "sandeep264328";
                var key = "R_63e2f83120b743bc9d9534b841d41be6";
                $.ajax({
                    url: "http://api.bit.ly/v3/shorten",
                    async: false,
                    data: {longUrl: url, apiKey: key, login: username},
                    dataType: "jsonp",
                    success: function (v)
                    {
                        var bitUrl = v.data.url;                        
                        sendData = [
                        {
                            type: gettwitter(),
                            image_name: image_name,
                            program_id: program_id,
                            schedule_id: schedule_id_twitter,
                            image_type: image_type,
                            token_data: {
                                "access_token": '"' + accessToken + '"',
                                "token_secret": '"' + tokenSecret + '"'
                            },
                            metadata: {
                                text: '"' + shareText + '"',
                                shorturl: '"' + bitUrl + '"'
                            }
                        }
                        ];
                        $.ajax({
                            url: getHost() + "actions/scheduleSocialPostActions",
                            method: 'post',
                            data: JSON.stringify(sendData),
                            success: function (responseText) {
                //                    alert(JSON.stringify(responseText));
                                var isSuccess = JSON.stringify(responseText.d.operationStatus.messages);
                                var parseData=JSON.parse(isSuccess);
                                if (parseData == "Success") {
                                    alert(postsuccess);
                                    location.href=getHost()+"user/dashboard";
                                }
                            },
                            error: function (jqXHR, textStatus, errorThrown) {
                                alert(JSON.stringify(jqXHR));
                            }
                        });
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        alert(JSON.stringify(jqXHR));
                    }
                });
            }
            else{
                var bitUrl = "";
                sendData = [{
                    type: gettwitter(),
                    image_name: image_name,
                    schedule_time: myEpoch,
                    schedule_title: schedule_title,
                    program_id: program_id,
                    schedule_desc: schedule_desc,
                    image_type: image_type,
                    token_data: {
                        "access_token": '"' + accessToken + '"',
                        "token_secret": '"' + tokenSecret + '"'
                    },
                    metadata: {
                        text: '"' + shareText + '"',
                        shorturl: '"' + bitUrl + '"'
                    }      
                }];
                $.ajax({
                    url: getHost() + "actions/scheduleSocialPost",
                    method: 'post',
                    data: JSON.stringify(sendData),
                    success: function (responseText) {
//                        alert(JSON.stringify(responseText));
                        var isSuccess = JSON.stringify(responseText.d.operationStatus.messages);
                        var parseData=JSON.parse(isSuccess);
                        if (parseData == "Success") {
                            alert(postsuccess);
                            location.href=getHost()+"user/dashboard";
                        }
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        alert(JSON.stringify(jqXHR));
                    }
                });
            }
        }).error(function (error) {
            alert(nodataerror);            
        });
    }
    else{
        if (schedule_title === "")
        {
            alert(titleerror);
            $("#schedule_title").focus();
            return false;
        }
        if (schedule_desc === "")
        {
            alert(descriptionerror);
            $("#schedule_desc").focus();
            return false;
        }
        if (schedule_date === "")
        {
            alert(dateerror);
            $("#schedule_social_date").focus();
            return false;
        }
        if ($("#schedule_social_time").val() === "")
        {
            alert(timeerror);
            $("#schedule_social_time").focus();
            return false;
        }       
        
        
        
        if ($("#shareText").val() === "")
        {
            alert(facebooktitle);
            flag = 1;
            $("#shareText").focus();
            return false;
        }
        if (image_name === "")
        {
            alert(chooseimage);
            flag = 1;
            $("#fade").show();
            $("#addContact").show();
            return false;
        }
        if ($("#linkUrl").val() !== "")
        {
            if ($("#linkTitle").val() === "")
            {
                alert(linktitleerror);
                flag = 1;
                $("#linkTitle").focus();
                return false;
            }
            if ($("#linkDescription").val() === "")
            {
                alert(descriptionerror);
                flag = 1;
                $("#linkDescription").focus();
                return false;
            }
        }
        $.ajax({
            url: getHost() + '/settings/twitterDetails.do',
            method: "POST",
            data: JSON.stringify({
                access_token_method: "getAccessToken"
            })
        }).success(function (data, status, headers, config) {
//            alert(JSON.stringify(data));
            
            var twitterData = data.d.message.split(",");
            var accessToken =twitterData[0];
            var tokenSecret =twitterData[1];
            var shareText = $("#twitterShareText").val();
            var url = $("#linkUrl").val();
            if(url !=="")
            {
                var username = "sandeep264328";
                var key = "R_63e2f83120b743bc9d9534b841d41be6";
                $.ajax({
                    url: "http://api.bit.ly/v3/shorten",
                    async: false,
                    data: {longUrl: url, apiKey: key, login: username},
                    dataType: "jsonp",
                    success: function (v)
                    {
                        var bitUrl = v.data.url;
                        sendData = [{
                                type: gettwitter(),
                                image_name: image_name,
                                schedule_time: myEpoch,
                                schedule_title: schedule_title,
                                program_id: program_id,
                                schedule_desc: schedule_desc,
                                image_type: image_type,
                                token_data: {
                                    "access_token": '"' + accessToken + '"',
                                    "token_secret": '"' + tokenSecret + '"'
                                },
                                metadata: {
                                    text: '"' + shareText + '"',
                                    shorturl: '"' + bitUrl + '"'
                                }      
                            }];
                        $.ajax({
                            url: getHost() + "actions/scheduleSocialPost",
                            method: 'post',
                            data: JSON.stringify(sendData),
                            success: function (responseText) {
//                                alert(JSON.stringify(responseText));
                                var isSuccess = JSON.stringify(responseText.d.operationStatus.messages);
                                var parseData=JSON.parse(isSuccess);
                                if (parseData == "Success") {
                                    alert(postsuccess);
                                    location.href=getHost()+"user/dashboard";
                                }
                            },
                            error: function (jqXHR, textStatus, errorThrown) {
                                alert(JSON.stringify(jqXHR));
                            }
                        });
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        alert(JSON.stringify(jqXHR));
                    }
                });
            }
            else{
                var bitUrl = "";
                sendData = [{
                    type: gettwitter(),
                    image_name: image_name,
                    schedule_time: myEpoch,
                    schedule_title: schedule_title,
                    program_id: program_id,
                    schedule_desc: schedule_desc,
                    image_type: image_type,
                    token_data: {
                        "access_token": '"' + accessToken + '"',
                        "token_secret": '"' + tokenSecret + '"'
                    },
                    metadata: {
                        text: '"' + shareText + '"',
                        shorturl: '"' + bitUrl + '"'
                    }      
                }];
                $.ajax({
                    url: getHost() + "actions/scheduleSocialPost",
                    method: 'post',
                    data: JSON.stringify(sendData),
                    success: function (responseText) {
//                        alert(JSON.stringify(responseText));
                        var isSuccess = JSON.stringify(responseText.d.operationStatus.messages);
                        var parseData=JSON.parse(isSuccess);
                        if (parseData == "Success") {
                            alert(postsuccess);
                            location.href=getHost()+"user/dashboard";
                        }
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        alert(JSON.stringify(jqXHR));
                    }
                });
            }
        }).error(function (data, status, headers, config) {
            alert(nodataerror);
        });
        
    }
//        alert(JSON.stringify(JSON.parse(sendData))); 
    
}

function scheduleFacebook() {
    var facebookac = $("#facebookactions").val();
    var twitterac = $("#twitteractions").val();
    var program_id=$("#programs").val();
    var shareText = $("#shareText").val();
    var linkTitle = $("#linkTitle").val();
    var linkDescription = $("#linkDescription").val();
    var linkUrl = $("#linkUrl").val();
    var image_name = selecImageName;
    var image_type = selecImageType;
    var schedule_date = $("#schedule_social_date").val();
    var schedule_time = $("#schedule_social_time").val().replace(/ /g, '');
    var schedule_title = $("#schedule_title").val();
    var schedule_desc = $("#schedule_desc").val();
    var l = schedule_date.toLocaleString() + " " + schedule_time.toLocaleString();
    var schedule_time = Date.parse(l);
    var myEpoch = schedule_time;
    var schedule_id_facebook="";
    var sendData="";
    if(program_id !=="0" || facebookac !== "0")
    {    
        if (facebookac === "0")
        {
            alert(facebookactionchoose);
            $("#facebookactions").focus();
            return false;
        }
        schedule_id_facebook = $("#facebookactions").val();
        sendData = [
                    {
                        type: getfacebook(),
                        image_name: image_name,
                        program_id: program_id,
                        schedule_id: schedule_id_facebook,
                        image_type: image_type,
                        token_data: {
                            "access_token": localStorage.getItem("CurrentFbAccessToken")
                        },
                        metadata: {
                            description: '"' + $("#linkDescription").val() + '"',
                            post_text: '"' + $("#shareText").val() + '"',
                            url: '"' + $("#linkUrl").val() + '"',
                            ManagedPage: '"' + localStorage.getItem("CurrentFbPageName") + '"',
                            title: '"' + $("#linkTitle").val() + '"'
                        }
                    }
                ];
                $.ajax({
                    url: getHost() + "actions/scheduleSocialPostActions",
                    method: 'post',
                    data: JSON.stringify(sendData),
                    success: function (responseText) {
    //                    alert(JSON.stringify(responseText));
                        var isSuccess = JSON.stringify(responseText.d.operationStatus.messages);
                        var parseData=JSON.parse(isSuccess);
                        if (parseData == "Success") {
                            alert(postsuccess);
                            location.href=getHost()+"user/dashboard";
                        }
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        alert(JSON.stringify(jqXHR));
                    }
                });
    }
    else{
        if (schedule_title === "")
        {
            alert(titleerror);
            $("#schedule_title").focus();
            return false;
        }
        if (schedule_desc === "")
        {
            alert(descriptionerror);
            $("#schedule_desc").focus();
            return false;
        }
        if (schedule_date === "")
        {
            alert(dateerror);
            $("#schedule_social_date").focus();
            return false;
        }
        if ($("#schedule_social_time").val() === "")
        {
            alert(timeerror);
            $("#schedule_social_time").focus();
            return false;
        }
        
        
        
        if ($("#shareText").val() === "")
        {
            alert(facebooktitle);
            flag = 1;
            $("#shareText").focus();
            return false;
        }
        if (image_name === "")
        {
            alert(chooseimage);
            flag = 1;
            $("#fade").show();
            $("#addContact").show();
            return false;
        }
        if ($("#linkUrl").val() !== "")
        {
            if ($("#linkTitle").val() === "")
            {
                alert(linktitleerror);
                flag = 1;
                $("#linkTitle").focus();
                return false;
            }
            if ($("#linkDescription").val() === "")
            {
                alert(descriptionerror);
                flag = 1;
                $("#linkDescription").focus();
                return false;
            }
        }
        sendData = [{
            "schedule_time": myEpoch,
            "schedule_title": schedule_title,
            "program_id": program_id,
            "schedule_desc": schedule_desc,
            "type":getfacebook(),
            "image_name": image_name,
            "accessToken": localStorage.getItem("CurrentFbAccessToken"),
            "postText": shareText,
            "title": linkTitle,
            "url": linkUrl,
            "description": linkDescription,
            "image_type": image_type,
            token_data: {
                            "access_token": localStorage.getItem("CurrentFbAccessToken")
                        },
            metadata: {
                description: '"' + $("#linkDescription").val() + '"',
                post_text: '"' + $("#shareText").val() + '"',
                url: '"' + $("#linkUrl").val() + '"',
                ManagedPage: '"' + localStorage.getItem("CurrentFbPageName") + '"',
                title: '"' + $("#linkTitle").val() + '"'
            }            
        }];
        $.ajax({
            url: getHost() + "actions/scheduleSocialPost",
            method: 'post',
            data: JSON.stringify(sendData),
            success: function (responseText) {
//                alert(JSON.stringify(responseText));
                var isSuccess = JSON.stringify(responseText.d.operationStatus.messages);
                var parseData=JSON.parse(isSuccess);
                if (parseData == "Success") {
                    alert(postsuccess);
                    location.href=getHost()+"user/dashboard";
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(JSON.stringify(jqXHR));
            }
        });
    }
//        alert(JSON.stringify(JSON.parse(sendData))); 
    
}

function validateTwitter(){
    var twitterText=$("#twitterShareText").val();
    var selectedImage=$("#selectedImage").attr('src').contains('companyId');
    if(twitterText == ""){
        alert("Please enter the text to share.");
        $("#twitterShareText").focus();
    }
    else if(!selectedImage){
        alert("Please add an Image to post.");
        $("#addImageToTwitterPost").trigger('click');
        $("#imagePopUp").show();
    }
    else if((twitterText != "")&&(selectedImage)){
    $("#sendpopup").show();
    $("#fade").show();        
    }
};
function validateFacebook(){
    var facebookText=$("#shareText").val();
    var selectedImage=$("#selectedImage").attr('src').contains('companyId');
    if(facebookText == ""){
        alert("Please enter the text to share.");
        $("#shareText").focus();
        return false;
    }    
    if(!selectedImage){
        alert("Please add an Image to post.");
        $("#addImageToPostButton").trigger('click');
        $("#changeImage").trigger('click');
        return false;
    }
    if ($("#dropdownurl").val() !== "0")
    {
        if ($("#linkTitle").val() === "")
        {
            alert(linktitleerror);
            flag = 1;
            $("#linkTitle").focus();
            return false;
        }
        if ($("#linkDescription").val() === "")
        {
            alert(descriptionerror);
            flag = 1;
            $("#linkDescription").focus();
            return false;
        }
    }
    if ($("#dropdownurl").val() === "0")
    {
        $("#linkTitle").val("");
        $("#linkUrl").val(""); 
        $("#linkDescription").val(""); 
    }
    if((facebookText != "")&&(selectedImage)){
        $("#sendpopup").show();
        $("#fade").show(); 
        return false;
    }
};
function postTwitter(){
    validateTwitter();
};

function postFacebook(){
    validateFacebook();
};


function postToTwitter() {
    showOverlay();
    var shareText = $("#twitterShareText").val();
    shareText + "bit.ly/1XOkJo";
    var url = $("#linkUrl").val();
    var image_name = selecImageName;
    var image_type = selecImageType;

    var username = "sandeep264328"; // bit.ly username
    var key = "R_63e2f83120b743bc9d9534b841d41be6";
    $.ajax({
        url: "http://api.bit.ly/v3/shorten",
        async: false,
        data: {longUrl: url, apiKey: key, login: username},
        dataType: "jsonp",
        success: function (v)
        {
            bit_url = v.data.url;
            $.ajax({
                url: getHost() + "socialPost/postToTwitter",
                method: 'post',
                data: JSON.stringify({
                    imageToPost: image_name,
                    twittweraccestoken: $("#twittweraccestoken").val(),
                    twitterTokenSecret: $("#twitterTokenSecret").val(),
                    text: shareText,
                    imageType: image_type,
                    shorturl: bit_url
                }),
                success: function (responseText) {
                    hideOverlay();
                    $("#sendpopup").hide();
                    $("#fade").hide();
//                    alert(JSON.stringify(responseText));
                    var isSuccess = responseText.d.message;
                    if (isSuccess === "success") {
                        alert("Successfully posted to Twitter.");
                        $("#twitterSuccessPostPopup").show();
                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(JSON.stringify(jqXHR));
                }
            });
        }
    });
}
function socialScheduleController($scope, $http) {
     $("#programs").change(function () {

        program_id = $("#programs").val();
        if (location.href.contains('twitterpost')) {
            $scope.getSocialTwitterActions();
        }
        if (location.href.contains('facebookpost')) {
            $scope.getSocialFacebookActions();
        }

        if (parseInt(program_id) == 0) {
            $("#facebookactions").attr("disabled", false);
            $("#twitteractions").attr("disabled", false);

            document.getElementById('schedule_desc').disabled = false;
            document.getElementById('schedule_title').disabled = false;
            document.getElementById('schedule_social_date').disabled = false;
            document.getElementById('schedule_social_time').disabled = false;
        } else {
            $("#facebookactions").attr("disabled", false);
            $("#twitteractions").attr("disabled", false);

            document.getElementById('schedule_desc').disabled = true;
            document.getElementById('schedule_title').disabled = true;
            document.getElementById('schedule_social_date').disabled = true;
            document.getElementById('schedule_social_time').disabled = true;

        }
    });
    
        $scope.schedulePostToTwitter = function(){
            var marketingPrograms=[];
    //                    showOverlay();                     
                        $.ajax({
                           method: 'GET',
                           url:getHost() + 'getAllUserMarketingPrograms'
                        }).success(function (data){
                            if(location.href.contains('twitterpost')){$("#facebookselection").hide();}
                            if(location.href.contains('facebookpost')){$("#twitterselection").hide();}
//                            alert(JSON.stringify(JSON.parse(data)));
                            marketingPrograms=JSON.stringify(JSON.parse(data));
                            $scope.marketing_programs1=eval(marketingPrograms);
                            $.ajax({
                            method: 'POST',
                            url: getHost() + 'actions/getActions',
                            data: {
                                programid: "0",
                                type: gettwitter()
                            }
                            }).success(function (data1) {
//                                alert(JSON.stringify(data1.d.details));                            
                                hideOverlay(); 
                                var parseData=JSON.parse(JSON.parse(data1.d.details));
    //                            alert(JSON.stringify(parseData));
                                $scope.twitter_actions = parseData;
                            }).error(function (data1) {
                                hideOverlay(); 
                                alert("Request not successful!");
                            });
    //                        alert(JSON.stringify(data));
                            $scope.marketing_programs = data;
                        }).error(function (data){
                            hideOverlay(); 
                            alert("Request not successful!");
                        });
    };
    $scope.getSocialTwitterActions = function () {
        $http({
            method: 'POST',
            url: getHost() + 'actions/getActions',
            data:{programid:program_id,type:gettwitter()}
        }).success(function (data) {
//            alert("twitter.... "+JSON.stringify(data));
            var parseData=JSON.parse(data.d.details)
            $scope.twitter_actions = eval(parseData);
        }).error(function (data) {
            alert(requesterror);
        });
    };
    $scope.getSocialFacebookActions = function () {
        $http({
            method: 'POST',
            url: getHost() + 'actions/getActions',
            data:{programid:program_id,type:getfacebook()}
        }).success(function (data) {
//            alert("facebook.... "+JSON.stringify(data));
            var parseData=JSON.parse(data.d.details)
            $scope.facebook_actions = eval(parseData);
        }).error(function (data) {
            alert(requesterror);
        });
    };
    
}

function validateact(){
               if(document.getElementById('programs').value === "0")
                {
                document.getElementById('schedule_title').disabled=false;
                document.getElementById('schedule_desc').disabled=false;
                document.getElementById('schedule_social_date').disabled=false;
                document.getElementById('schedule_social_time').disabled=false;
                 }
            else{
                document.getElementById('schedule_title').disabled=true;
                document.getElementById('schedule_desc').disabled=true;
                document.getElementById('schedule_social_date').disabled=true;
                document.getElementById('schedule_social_time').disabled=true;
                document.getElementById('schedule_title').value="";
                document.getElementById('schedule_desc').value="";
                document.getElementById('schedule_social_date').value="";
                }                 
            }




 function setScheduling(isFacebook,isTwitter) {
//        var isFacebook = $("#isFacebook").val();
//        var isTwitter = $("#isTwitter").val();
        var schedule_date = $("#schedule_social_date").val();
        var schedule_time = $("#schedule_social_time").val().replace(/ /g, '');
        var schedule_title = $("#schedule_title").val();
        var schedule_desc = $("#schedule_desc").val();
        var programs = $("#programs").val();
        var facebookac = $("#facebookactions").val();
        var twitterac = $("#twitteractions").val();
        var imagetype = "gallery";
//        var imagetype = $("#gallery").val();
        var link_description = $("#link_description").val();
        var bit_url = "";
        var link = $("#Linkurl").val();

        if (link != undefined) {
            var f = link.startsWith("http");

            if (!f)
            {
                link = "http://" + $("#Linkurl").val();
            }
            var url = link;
            var username = "sandeep264328"; // bit.ly username
            var key = "R_63e2f83120b743bc9d9534b841d41be6";
        }

        $.ajax({
            url: "http://api.bit.ly/v3/shorten",
            data: {longUrl: url, apiKey: key, login: username},
            dataType: "jsonp",
            success: function (v)
            {
                bit_url = v.data.url;
                $("#sortLengthurl").val(bit_url);
            }
        });
        if (programs === "0") {
            if (schedule_date === "" && schedule_time === "" && schedule_title === "" && schedule_desc === "")
            {
                if (number === 2)
                {
                    if (facebookac === "0")
                    {
                        alert(facebookactionchoose);
                        $("#facebookactions").focus();
                        return false;
                    }
                    if (twitterac === "0")
                    {
                        alert(twitteractionchoose);
                        $("#twitteractions").focus();
                        return false;
                    }
                }
                if (number === 1)
                {
                    if (facebookac === "0" || twitterac === "0")
                    {
                        if (fb === 1 && facebookac === "0")
                        {
                            alert(facebookactionchoose);
                            $("#facebookactions").focus();
                            return false;
                        }
                        else if (tw === 1 && twitterac === "0")
                        {
                            alert(twitteractionchoose);
                            $("#twitteractions").focus();
                            return false;
                        }
                    }
                }
            } else {
                if (schedule_title === "")
                {
                    alert(titleerror);
                    $("#schedule_title").focus();
                    return false;
                }
                if (schedule_desc === "")
                {
                    alert(descriptionerror);
                    $("#schedule_desc").focus();
                    return false;
                }
                if (schedule_time === "")
                {
                    alert(timeerror);
                    $("#schedule_social_time").focus();
                    return false;
                }
            }
        } else {
            if (number === 2)
            {
                if (facebookac === "0")
                {
                    alert(facebookactionchoose);
                    $("#facebookactions").focus();
                    return false;
                }
                if (twitterac === "0")
                {
                    alert(twitteractionchoose);
                    $("#twitteractions").focus();
                    return false;
                }
            }
            if (number === 1)
            {
                if (facebookac === "0" || twitterac === "0")
                {
                    if (fb === 1 && facebookac === "0")
                    {
                        alert(facebookactionchoose);
                        $("#facebookactions").focus();
                        return false;
                    }
                    else if (tw === 1 && twitterac === "0")
                    {
                        alert(twitteractionchoose);
                        $("#twitteractions").focus();
                        return false;
                    }
                }
            }
        }

        var image_name = $("#imageToPost").val();
        var program_id = $("#programs").val();
        var schedule_id_facebook = "0";
        var schedule_id_twitter = "0";
        var ManagedPage = "";

        if ((isFacebook == "true") && (isTwitter == "false")) {
            schedule_id_facebook = $("#facebookactions").val();
            ManagedPage = $("#pagenameSend").val();
        } else if ((isFacebook == "false") && (isTwitter == "true")) {
            schedule_id_twitter = $("#twitteractions").val();
        } else if ((isFacebook == "true") && (isTwitter == "true")) {
            schedule_id_facebook = $("#facebookactions").val();
            schedule_id_twitter = $("#twitteractions").val();
            ManagedPage = $("#pagenameSend").val();
        }

        if ((schedule_id_facebook == "0") && (schedule_id_twitter == "0")) {
            var schedule_date = $("#schedule_social_date").val();
            var schedule_time = $("#schedule_social_time").val().replace(/ /g, '');
            var schedule_title = $("#schedule_title").val();
            var schedule_desc = $("#schedule_desc").val();
            var l = schedule_date.toLocaleString() + " " + schedule_time.toLocaleString();
            var schedule_time = Date.parse(l);
            console.log("Epoch: " + schedule_time);
            var myEpoch = schedule_time;
            console.log("New Epoch: " + myEpoch);
            var social_schedule = "";
            if (isFacebook == "true" && isTwitter == "false") {

                social_schedule = [
                    {
                        type: getfacebook(),
                        image_name: image_name,
                        schedule_time: myEpoch,
                        schedule_title: schedule_title,
                        program_id: program_id,
                        schedule_desc: schedule_desc,
                        image_type: imagetype,
                        token_data: {
                            "access_token": '"' + $("#accesstoken").val() + '"'
                        },
                        metadata: {
                            description: '"' + $("#link_description").val() + '"',
                            post_text: '"' + $("#posttext").val() + '"',
                            url: '"' + $("#Linkurl").val() + '"',
                            ManagedPage: '"' + ManagedPage + '"',
                            title: '"' + $("#link_title").val() + '"'
                        }
                    }
                ];

            }
            else if (isFacebook == "false" && isTwitter == "true") {
                social_schedule = [
                    {
                        type: gettwitter(),
                        image_name: image_name,
                        schedule_time: myEpoch,
                        schedule_title: schedule_title,
                        program_id: program_id,
                        schedule_desc: schedule_desc,
                        image_type: imagetype,
                        token_data: {
                            "access_token": '"' + $("#twittweraccestoken").val() + '"',
                            "token_secret": '"' + $("#twitterTokenSecret").val() + '"'
                        },
                        metadata: {
                            text: '"' + latesttwtext + '"',
                            shorturl: '"' + $("#sortLengthurl").val() + '"'
                        }
                    }
                ];

            }
            else if (isFacebook == "true" && isTwitter == "true") {
                social_schedule =
                        [
                            {
                                type: getfacebook(),
                                image_name: image_name,
                                schedule_time: myEpoch,
                                schedule_title: schedule_title,
                                program_id: program_id,
                                schedule_desc: schedule_desc,
                                image_type: imagetype,
                                token_data: {
                                    "access_token": '"' + $("#accesstoken").val() + '"'
                                },
                                metadata: {
                                    description: '"' + $("#link_description").val() + '"',
                                    post_text: '"' + $("#posttext").val() + '"',
                                    url: '"' + $("#Linkurl").val() + '"',
                                    ManagedPage: '"' + ManagedPage + '"',
                                    title: '"' + $("#link_title").val() + '"'
                                }
                            },
                            {
                                type: gettwitter(),
                                image_name: image_name,
                                schedule_time: myEpoch,
                                schedule_title: schedule_title,
                                program_id: program_id,
                                schedule_desc: schedule_desc,
                                image_type: imagetype,
                                token_data: {
                                    "access_token": '"' + $("#twittweraccestoken").val() + '"',
                                    "token_secret": '"' + $("#twitterTokenSecret").val() + '"'
                                },
                                metadata: {
                                    text: '"' + latesttwtext + '"',
                                    shorturl: '"' + $("#sortLengthurl").val() + '"'
                                }
                            }
                        ];
            }
            alert(JSON.stringify(social_schedule));
            $.ajax({
                url: getHost() + 'actions/scheduleSocialPost',
                method: 'post',
                dataType: 'json',
                contentType: 'application/json',
                mimeType: 'application/json',
                data: JSON.stringify(social_schedule),
                success: function (responseText) {alert(JSON.stringify(responseText));
                    alert(postsuccess);
                    document.location.href = "dashboard";
                },
                error: function (errorThrown) {
                    alert(JSON.stringify(errorThrown));
            }
            });
        } else {
            if (isFacebook == "true" && isTwitter == "false") {
                social_schedule = [
                    {
                        type: getfacebook(),
                        image_name: image_name,
                        program_id: program_id,
                        schedule_id: schedule_id_facebook,
                        image_type: imagetype,
                        token_data: {
                            "access_token": '"' + $("#accesstoken").val() + '"'
                        },
                        metadata: {
                            description: '"' + $("#link_description").val() + '"',
                            post_text: '"' + $("#posttext").val() + '"',
                            url: '"' + $("#Linkurl").val() + '"',
                            ManagedPage: '"' + ManagedPage + '"',
                            title: '"' + $("#link_title").val() + '"'
                        }
                    }
                ];

            } else if (isFacebook == "false" && isTwitter == "true") {
                social_schedule = [
                    {
                        type: gettwitter(),
                        image_name: image_name,
                        program_id: program_id,
                        schedule_id: schedule_id_twitter,
                        image_type: imagetype,
                        token_data: {
                            "access_token": '"' + $("#twittweraccestoken").val() + '"',
                            "token_secret": '"' + $("#twitterTokenSecret").val() + '"'
                        },
                        metadata: {
                            text: '"' + latesttwtext + '"',
                            shorturl: '"' + $("#sortLengthurl").val() + '"'
                        }
                    }
                ];

            } else if (isFacebook == "true" && isTwitter == "true") {

                social_schedule =
                        [
                            {
                                type: getfacebook(),
                                image_name: image_name,
                                program_id: program_id,
                                schedule_id: schedule_id_facebook,
                                image_type: imagetype,
                                token_data: {
                                    "access_token": '"' + $("#accesstoken").val() + '"'
                                },
                                metadata: {
                                    description: '"' + $("#link_description").val() + '"',
                                    post_text: '"' + $("#posttext").val() + '"',
                                    url: '"' + $("#Linkurl").val() + '"',
                                    ManagedPage: '"' + ManagedPage + '"',
                                    title: '"' + $("#sortLengthurl").val() + '"'
                                }
                            },
                            {
                                type: gettwitter(),
                                image_name: image_name,
                                program_id: program_id,
                                schedule_id: schedule_id_twitter,
                                image_type: imagetype,
                                token_data: {
                                    "access_token": '"' + $("#twittweraccestoken").val() + '"',
                                    "token_secret": '"' + $("#twitterTokenSecret").val() + '"'
                                },
                                metadata: {
                                    text: '"' + latesttwtext + '"',
                                    shorturl: '"' + $("#sortLengthurl").val() + '"'
                                }
                            }
                        ];
            }
            $.ajax({
                url: getHost() + 'actions/scheduleSocialPostActions',
                method: 'post',
                dataType: 'json',
                contentType: 'application/json',
                mimeType: 'application/json',
                data: JSON.stringify(social_schedule),
                success: function (responseText) {alert(responseText);
                    alert(postsuccess);
                    document.location.href = "dashboard";
                }
            });
        }
     
     
     
                };




    var imgApp = angular.module('imageGallery', []);

        imgApp.controller("displayImageFromGallery", ['$scope','$http', 'fileUpload', function($scope,$http,fileUpload) {
                
                $scope.getUserImaages = function () {
                showOverlay();
                $http({
                    method: 'GET',
                    url: getHost() + '/companyImages/get'
                }).success(function (data) {
                    $scope.datalists = data.d.details;
                    hideOverlay();
                    $scope.numberOfPages = function () {
                        return Math.ceil($scope.datalists.length / $scope.pageSize);
                    };
                    if (data === error) {
                        alert(data);
                    }
                }).error(function (data) {
                    hideOverlay();
                    alert(JSON.stringify(data));
                });
            };
            $scope.uploadLogo = function () {
                var imagetext = $("#uploadLogoID").val();
                if (imagetext === "")
                {
                    alert(chooseimage);
                } else
                {
                    var file = $scope.logo;
                    var uploadUrl = getHost() + '/images/save';
                    var fd = new FormData();
                    fd.append('file', file);
                    $http.post(uploadUrl, fd, {
                        transformRequest: angular.identity,
                        headers: {'Content-Type': undefined}
                    })
                            .success(function (data) {
                                $scope.getUserImaages();
                                        $("#uploadImageSpan").hide();
                                        $("#gallerySpan").show();
                                        $("#uploadImageButton").hide();
                                        $("#addImageButton").show();
                                        $("#showGallery").removeClass("popUp_subheader-tabs fleft").addClass("popUp_subheader-tabs-active fleft");
                                        $("#shoeUploadimage").removeClass("popUp_subheader-tabs-active fleft").addClass("popUp_subheader-tabs fleft");
                            })
                            .error(function () {

                                alert(requesterror);
                            });
                }
            };
            $scope.selectImageToPost = function (imageName, imageType, companyId) {
                selecImageName = imageName;
                selecImageType = imageType;
                selecImageType.toLowerCase();
                selecCompanyId = companyId;
            };
            $scope.addImage = function () {
                $("#selectedImage").attr("src", "/BrndBot/downloadImage?imageType=GALLERY&imageName=" + selecImageName + "&companyId=" + selecCompanyId + "");
                $("#imagePopUp").hide();
            };
            
            $scope.changeTwitterPostType = function (){
               var postType = $("#twitterLinkPost").css("display");
                if (postType === "none") {
                    $("#urlDropDownSpan").show();
                    $("#twitterLinkPost").show();
                    $("#postType").text("Change To Normal Post");
                }
                if (postType === "inline") {
                    $("#twitterLinkPost").hide();
                    $("#urlDropDownSpan").hide();
                    $("#linkUrl").val("");
                    $("#postType").text("Change To Link Post");
                }
            };
          $scope.getUrls = function (){
                $http({
                    method: 'GET',
                    url: getHost() + "/getAllUserMarketingProgramsByUserId"
                }).success(function (data) {
                     $scope.urls= data;
                });
            };
            $scope.getSelectedUrl = function (){
                alert();
            };
            $scope.selectImage = function (id){
                $('.gallery-item-wrap-selected-true').addClass('gallery-item-wrap-selected').removeClass('gallery-item-wrap-selected-true');
                $("."+id).removeClass('gallery-item-wrap-selected').addClass('gallery-item-wrap-selected-true');
            };

        }]);
           
 imgApp.directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;
            
            element.bind('change', function(){
                scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);       
 
 imgApp.service('fileUpload', ['$http', function ($http) {
    this.uploadFileToUrl = function(file, uploadUrl){
        var fd = new FormData();
        fd.append('file', file);
        $http.post(uploadUrl, fd, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        })
        .success(function(){
        })
        .error(function(){
        });
    }
}]);
            
function changeimagetext1() {
    var imagetext = $("#filesToUpload1").val();
    if (imagetext === "")
    {
        alert(chooseimage);
    } else
    {
        $("#imagetext").val(imagetext);
    }
    if (imagefilevalidation("imagetext")) {
    } else
    {
        $("#filesToUpload").val("");
    }
}
function isDefaultTwitterSet() {
    $.ajax({
        url: getHost() + 'settings/twitterDetails.do',
        method: 'post',
        async: true,
        data: JSON.stringify({
            access_token_method: "getAccessToken"
        }),
        success: function (responseText) {
            var twitterAccessToken = responseText.d.message;
            if ((twitterAccessToken === null) || (twitterAccessToken === ""))
            {
                getAuthURL();
            } else {
                $("#twitterSetPinPopUp").hide();
                window.location = getHost() + "user/twitterpost";
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {

        }
    });

}
function getAuthURL() {
    $.ajax({
        url: getHost() + 'settings/twitterAuthURL.do',
        method: 'GET',
        success: function (responseText) {
            $("#twitterSetPinPopUp").show();
            $("#twitterlink").html("<a href='" + responseText.d.details[0] + "' target='_blank'>get your pin</a>");
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert("error:" + JSON.stringify(jqXHR));
        }
    });
}

function setTwitterAccessToken() {
    var pin = $("#pinTextBox").val();
    if (pin.length > 0) {
        $.ajax({
            url: getHost() + 'settings/twitterGetToken/' + pin,
            method: 'GET',
            success: function (responseText) {
                $("#twitterSetPinPopUp").hide();
                window.location = getHost() + "user/twitterpost";

            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(JSON.stringify(jqXHR));
            }
        });

    } else {
        alert(pinerror);
        $("#pinTextBox").focus();
    }
}
angular.module("socialMedia", [])
        .controller("isDefaultFbPageSet", function ($scope, $http) {
            $scope.getmanage = function () {
                $http({
                    url: getHost() + 'settings/facebookDetails.do',
                    method: "POST",
                    data: JSON.stringify({
                        access_token_method: "getAccessToken"
                    })
                }).success(function (data) {
                    var defaultPage = data.d.message;
                    if (defaultPage === "null,null,null")
                    {
                        $http({
                            url: getHost() + 'settings/fbAuthURL',
                            method: "Post",
                            data: JSON.stringify({
                                redirectUrl: "user/socialsequence"
                            })
                        }).success(function (data) {
                            window.location = data.d.details[0];

                        });
                    } else {
                        window.location = getHost() + "user/facebookpost";
                    }

                });
            };
            $scope.checkForCode = function () {
                var code = getUrlParameter("code");
                if (typeof code !== "undefined") {
                    $http({
                        url: getHost() + 'settings/fbGetToken/' + code,
                        method: "GET"
                    }).success(function (data) {
                        $("#fbmanagePagePopUp").show();
                        $scope.fbPagesDetails = data.d.details[0].fbPages;
                    });
                }
            };
            $scope.setPageAccessToken = function (accessToken, pageName, profileName) {
                localStorage.setItem("CurrentFbAccessToken", accessToken);
                localStorage.setItem("CurrentFbPageName", pageName);
                localStorage.setItem("FbProfileName", profileName);
            };
            $scope.postToSelectedPage = function () {
                var addDafaultmanagePage = $("#setDefaultManagePage").prop('checked');
                if (addDafaultmanagePage) {
                    //Add default page call
                    $http({
                        url: getHost() + 'settings/facebookDetails.do',
                        method: "POST",
                        data: JSON.stringify({
                            access_token_method: "setAccessToken",
                            access_token: localStorage.getItem("CurrentFbAccessToken"),
                            default_page_name: localStorage.getItem("CurrentFbPageName"),
                            fb_user_profile_name: localStorage.getItem("FbProfileName")
                        })
                    }).success(function (data) {
//                        alert(JSON.stringify(data));
                    });
                }
                window.location = getHost() + "user/facebookpost";
            };
            $scope.selectImage = function (id){
                $('.gallery-item-wrap-selected-true').addClass('gallery-item-wrap-selected').removeClass('gallery-item-wrap-selected-true');
                $("."+id).removeClass('gallery-item-wrap-selected').addClass('gallery-item-wrap-selected-true');
//                alert(id);
            };
        });
var getUrlParameter = function getUrlParameter(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
            sURLVariables = sPageURL.split('&'),
            sParameterName,
            i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
    }
};

function hideFbPopup() {
    $("#fbmanagePagePopUp").hide();
}