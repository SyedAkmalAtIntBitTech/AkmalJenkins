<%-- 
    Document   : selectimagetype
    Created on : 21 Dec, 2015, 5:14:48 PM
    Author     : satyajit-roy
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <%! 
        String mindbodydata="";
    %>    
    <% 
        mindbodydata = request.getParameter("mindbodydata");
    %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
    <link rel="shortcut icon" href="favicon.png"/>
    <link rel="stylesheet" href="css/pikaday.css"/>
    <link rel="stylesheet" href="css/datepickerpikaday.css"/>
    <link href="css/timepicki.css" rel="stylesheet" type="text/css"/>
    <script src="js/pikaday.js"></script>
<!--    <script src="js/socialimage.js" type="text/javascript"></script>-->
    <script src="js/socialeditor.js" type="text/javascript"></script>
    <script src="js/ajaxfileupload.js" type="text/javascript"></script>
    <script src="js/selectimagetype.js" type="text/javascript"></script>
     
</head>    
    <body>
        
            <div id="fade" class="black_overlay"></div>
            <input type="hidden" name="selectedtype" id="selectedtype" value=""></input>
            <input type="hidden" name="selectedid" id="selectedid" value="<%=mindbodydata%>"></input>
            <input type="hidden" name="social" id="social" value="social"></input>
            
            <div id="addContact">
                <div class="pop-up-background">
                    <div class="pop-up-exit-container">
                        <div class="pop-up-exit-icon" id="close"> 
                            <img type="image/svg+xml" src="images/Icons/Close.svg" class="exit-button-icon" style="cursor:pointer;"/>
                        </div>
                    </div> 
                    <div class="pop-up-container pop-up-container-imageSelection"> 
                <div class="pop-up-title pop-up-title-h1"> Select an Image Type</div>
                <div class="pop-up-inner-imageSelection">
                    <div class="col-8of10  center">
                        <div class="col-4of10 pushright fleft">
                            <div class="image-selection-button" id="uploadimage" ng-click="showImages()"><p class="button-description algn">Upload an Image or from gallery</p></div>
                        </div>
                        <div class="col-4of10 fright">
                            <div class="image-selection-button" id="gotoimageeditor"><p class="button-description algn">Image Editor</p></div>
                        </div>
                    </div>
                </div>

            </div>
            </div>
            </div>
            
            <div id="imageGalleryDiv">
                <div class="pop-up-background">
                    <div class="pop-up-container-galleryselect"> 
                        <div class="pop-up-header">
                            <div class="exit-button-detail" id="closeimagegallerydiv">
                                <img type="image/svg+xml" src="images/Icons/backbutton.svg" class="exit-button-icon" style="cursor:pointer;"> </img>
                            </div>
                            <div class="pop-up-title-gallery fleft col-9of10"> 
                                <div class="pop-up-title-h1-gallery fleft"> Select an image to use:</div>
                                <div class="space-right">
                                    <a href="" class=" fleft padhlf">
                                        <div class="add-button22 md-button" id="addimage"> Add Image</div>    
                                    </a>
                                    <a href="" class="gray-button fright ">
                                        <div class=" md-button" id="galleryupload">Upload an Image</div>    
                                    </a>
                                </div>
                            </div>
                        </div>
                        <div class="pop-up-inner-gallery " ng-init="getLinks()">
                            <div class="imageGallery-inner-popup">
                                <div class="imageGallery-popup fleft" ng-repeat="images in datalistimages" style="cursor:pointer;">
                                    <div class="imageGallery-card" id="div{{images.id}}">

                                        <div id="{{images.id}}--------{{images.image_name}}--------{{images.user_id}}" onclick="getImageId(this.id)">
                                            <img value="{{images.image_name}}" id="{{images.id}}{{images.user_id}}images" class="galCard-image col-1of1" ng-src="/BrndBot/DownloadImage?image_type=GALLERY&image_name={{images.image_name}}&user_id={{images.user_id}}"/>                                                            
                                            <!--<img class="galCard-image col-1of1" alt="image" src="http://wp.nootheme.com/yogi/wp-content/uploads/2015/04/blog_10.jpg"  vspace="0" hspace="0" border="0" style="display:block;" />-->
                                            <div class="galCard-content-popup col-1of1"> 
                                                <div class="galImage-name">Image Name</div>
                                                <div class="galImage-description">Added on Nov 26 2015</div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
            <div id="imageUploadDiv2">
                
                <div class="pop-up-background">
                    <div class="pop-up-exit-container">
                        <div class="pop-up-exit-icon" id="close"> 
                            <img type="image/svg+xml" src="images/Icons/Close.svg" class="exit-button-icon" style="cursor:pointer;"/>
                        </div>
                    </div> 
                <div class="pop-up-container pop-up-container-newaction"> 
                    <div class="pop-up-title pop-up-title-h1">Upload an Image</div>
                     <div class="pop-up-exit-container">
                        <a href="/Newest_Files/YourPlan.html" class="pop-up-exit-icon">
                            <object type="image/svg+xml" data="/Icons/Close.svg" class="exit-button-icon" style="cursor:pointer;"> </object>
                        </a>
                    </div> 
                    <div class="pop-up-inner">
                        <div class="input-field-container ">
                            <div class="input-header "> Click to browse your photos:</div>
                            <div class="input-field-textfield input-placeholder">Dropdown of Links</div>
                        </div>
                    </div>
                </div>
            </div>
            
                <div class="pop-up-cta-container pop-up-cta-container-newaction">
                <a href="/Newest_Files/MarketingProgram_Actions.html">
                    <div class="pop-up-cta-button-full"> Add Link</div>
                </a>
            </div> 
                
            </div>
                             
            <div id="fileuploaddiv">
                <div class="pop-up-background">
                    <div class="pop-up-container-galleryselect"> 
                        <div class="pop-up-header">
                             <div class="exit-button-detail" id="closefileupload">
                               <a class=" exit-button-icon" href="">
                                <img type="image/svg+xml" src="images/Icons/backbutton.svg" class="exit-button-icon" style="cursor:pointer;"> </img>
                            </a>
                        </div>
                            <div class="pop-up-title-gallery fleft col-9of10"> 
                                <div class="pop-up-title-h1-gallery fleft"> Upload an Image:</div>
                            </div>
                        </div>
                        <div class="pop-up-inner-gallery ">
                            <div class="imageGallery-inner-popup">
                                <div class="imageUpload-container">
                                   <div class="browse-button">
                                        <div><input id="myFile" type="file" name="myFile" class="md-button gray-button" value="Browse your Images"></div>
                                    </div>
                                    <div class="browse-button top18">
                                        <div class="md-button gray-button" id="upload" ng-click="uploadFile()">Upload</div>
                                    </div>
                                    <input type="button" id="image1" ng-click="showImages()" value="image1" style="display: none;"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div> 
            
            <div id="linkpopup">
             <div class="pop-up-background">
                <div class="pop-up-container pop-up-container-newaction"> 
                    <div class="pop-up-title pop-up-title-h1">Add a Link</div>
                     <div class="pop-up-exit-container" id="closeLinkPopup">
                            <img type="image/svg+xml" src="images/Icons/Close.svg" class="exit-button-icon" style="cursor:pointer;"> </img>
                        </a>
                    </div> 
                    <div class="pop-up-inner">
                        <div class="popup-section-header">Choose a Link to Add</div>
                         <div class="line-divider-notop"></div>
                        <div class="input-field-container ">
                            <div class="input-header "> Choose from your Existing Links:</div>
                            <div class="">
                                <select id="dropdownurl" name="marketing_program_type" class="input-field-textfield input-placeholder full">
                                    <option value="0" class="caret">LINK URL<lable></lable></option>
                                    <option ng-repeat="url in urls" value="{{url.url}}--{{url.link_name}}">{{url.prigram_name}} - {{url.link_name}} - {{url.url}}</option>
                                </select>
                            </div>
                            <div class="popup-section-header pushUp" style="font-variant:small-caps;">-or-</div>
                            <div class="input-header pushUp"> Enter a new url:</div>
                            <div class=""><input type="text" id="url" class="input-field-textfield input-placeholder full" placeholder="EX. www.brndbot.com/usethislink"></input></div>
                        </div>
                    </div>

                </div>
            </div>
            <div id="submitLink" class="pop-up-cta-container-1 pop-up-cta-container-newaction">
                    <div class="pop-up-cta-button-full" > Add Link</div>
                </a>
            </div> 
            </div>
            
            <div id="postpopup">
                <div class="pop-up-background">
                    <div class="pop-up-exit-container">
                       <div class="pop-up-exit-icon" id="closepostpopup">
                           <img type="image/svg+xml" src="images/Icons/Close.svg" class="exit-button-icon" style="cursor:pointer;"></img>
                       </div>
                    </div> 
                    <div class="pop-up-container1 pop-up-container-Schedule"> 
                        <div class="pop-up-title pop-up-title-h1"> Would you like to post now or schedule for later?</div>
                        <div class="pop-up-inner-imageSelection">
                            <div class="col-8of10  center">
                                <div class="col-4of10 pushright fleft">
                                    <div class="image-selection-button" id="posttofb"> 
                                        <img type="image/svg+xml" src="images/Icons/postNow.svg" class="post-button-icon" style="cursor:pointer;"> </img>
                                    </div>
                                    <div class="button-description">Post Now</div>
                                </div>
                                <div class="col-4of10 fright">
                                    <div class="image-selection-button" id="schedule">
                                        <img type="image/svg+xml" src="images/Icons/schedulePost.svg" class="schedule-button-icon" style="cursor:pointer;"> </img>
                                    </div>
                                    <div class="button-description">Schedule for Later</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- ng-controller="socialmediapreview" id="socialmediapreview" -->
            <div id="schedulepopup">
                <div class="pop-up-exit-container">
                    <div class="pop-up-exit-icon" id="closeschedulepopup">
                        <img type="image/svg+xml" src="images/Icons/Close.svg" class="exit-button-icon"></img>
                    </div>
                </div>
                <div class="pop-up-background">
                    <div class="pop-up-container pop-up-container-newaction1"> 
                        <div class="pop-up-title pop-up-title-h1"> Schedule this Action</div>
                        <div class="pop-up-exit-container">
                            <a href="/Newest_Files/YourPlan.html" class="pop-up-exit-icon">
                                <object type="image/svg+xml" data="/Icons/Close.svg" class="exit-button-icon"> </object>
                            </a>
                        </div> 
                        <div class="pop-up-inner1">
                        <div class="popup-section-header">Save to Existing Action</div>
                        <div class="line-divider-notop"></div>
                        <div class="input-field-container">
                            <div class="input-header"> Choose Marketing Program</div>
                            <div ng-init="getProgramNames();">
                                <select name="programs" id="programs" class="full input-field-textfield2 input-placeholder" onchange="validateact();">
                                    <option value="0">--General--</option>
                                    <option ng-repeat="programs in marketing_programs" value="{{programs.program_id}}">{{programs.name}}</option>
                                </select>
                            </div>
                            <div id="facebookselection">
                                <div class="input-header"> Choose Facebook Action</div>
                                <div ng-init="getSocialFacebookActions();">
                                    <select name="facebookactions" id="facebookactions" class="input-field-textfield input-placeholder full" onchange="validateact();">
                                        <option value="0" >CUSTOM FACEBOOK</option>
                                        <option ng-repeat="fbactions in facebook_actions" value="{{fbactions.id}}">{{fbactions.schedule_title}}</option>
                                    </select>
                                </div>
                            </div>
                            <div id="twitterselection">
                                <div class="input-header"> Choose Twitter Action</div>
                                <div ng-init="getSocialTwitterActions();">
                                    <select name="twitteractions" id="twitteractions" class="input-field-textfield input-placeholder full" onchange="validateact();">
                                        <option value="0">CUSTOM TWITTER</option>
                                        <option ng-repeat="twitteractions in twitter_actions" value="{{twitteractions.id}}">{{twitteractions.schedule_title}}</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="popup-section-header pushUp-45">-Or- Create a New Action</div>
                        <div class="line-divider-notop "></div>
                        <div class="input-header">Name this Action</div>
                        <div class="">
                            <input type="text" class="input-field-textfield input-placeholder full" id="schedule_title" name="schedule_title" placeholder="TITLE" ></input>
                        </div>
                        <div class="input-header">Description</div>
                        <div class="">
                            <textarea class="input-field-textfield input-placeholder fulltextarea" name="schedule_desc" id="schedule_desc" placeholder="Description" value=""></textarea>
                        </div>
                        <div class="cols-2">
                             <div class="input-field-container col-4of10 fleft pushright">
                                <div class="input-header"> Action Date </div>
                                <input type="text" readonly id="schedule_social_date" name="schedule_social_date" class="input-field-textfield input-placeholder"  placeholder="Enter Action Date"></input>
                                <script>
                                    var picker = new Pikaday(
                                    {
                                        field: document.getElementById('schedule_social_date'),
                                        firstDay: 1,
                                        minDate: new Date(2000, 0, 1),
                                        maxDate: new Date(2050, 12, 31),
                                        yearRange: [2000,2050]
                                    });
                                </script>
                            </div>
                             <div class="input-field-container col-4of10 fleft">
                                <div class="input-header"> Action Time </div>
                                <div class="">
                                    <input id="schedule_social_time" type="text" name="schedule_social_time" class="input-field-textfield input-placeholder" placeholder="Enter Action Time" readonly/><br>
                                    <script src="js/timepicki.js" type="text/javascript"></script>
                                    <script>
                                        $('#schedule_social_time').timepicki({
                                                    show_meridian:true,
                                                    min_hour_value:0,
                                                    max_hour_value:12,
                                                    step_size_minutes:01,
                                                    overflow_minutes:true,
                                                    increase_direction:'up',
                                                    disable_keyboard_mobile: true
                                                });
                                    </script>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="pop-up-cta-container pop-up-cta-container-newaction1"  id="socialscheduleid">
                        <input type="hidden" value="socialmedia"/>
                        <div class="algnmnt" name="socialscheduleid" id="schedulethepost">SCHEDULE</div>
                    </div>
                    </div>
                </div>
            </div>
            
    </body>
</html>
