<%-- 
    Document   : froalaeditor_styles
    Created on : Mar 15, 2016, 7:30:00 PM
    Author     : Rasim Parvez at IntBit Technologies.
--%>

 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="../css/froala_editor.css">
        <link rel="stylesheet" href="../css/froala_style.css">
        <link rel="stylesheet" href="../css/plugins/code_view.css">
        <link rel="stylesheet" href="../css/plugins/colors.css">
        <link rel="stylesheet" href="../css/plugins/emoticons.css">
        <link rel="stylesheet" href="../css/plugins/image_manager.css">
        <link rel="stylesheet" href="../css/plugins/image.css">
        <link rel="stylesheet" href="../css/plugins/line_breaker.css">
        <link rel="stylesheet" href="../css/plugins/table.css">
        <link rel="stylesheet" href="../css/plugins/char_counter.css">
        <link rel="stylesheet" href="../css/plugins/video.css">
        <link rel="stylesheet" href="../css/plugins/fullscreen.css">
        <link rel="stylesheet" href="../css/plugins/file.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.3.0/codemirror.min.css">
          

            <script type="text/javascript" src="../js/froala_editor.min.js" ></script>
            <script type="text/javascript" src="../js/plugins/align.min.js"></script>
            <script type="text/javascript" src="../js/plugins/code_view.min.js"></script>
            <script type="text/javascript" src="../js/plugins/colors.min.js" ></script>
            <script type="text/javascript" src="../js/plugins/emoticons.min.js"></script>
            <script type="text/javascript" src="../js/plugins/font_size.min.js"></script>
            <script type="text/javascript" src="../js/plugins/font_family.min.js"></script>
            <script type="text/javascript" src="../js/plugins/image.min.js"></script>
            <script type="text/javascript" src="../js/plugins/file.min.js"></script>
            <script type="text/javascript" src="../js/plugins/image_manager.min.js"></script>
            <script type="text/javascript" src="../js/plugins/line_breaker.min.js"></script>
            <script type="text/javascript" src="../js/plugins/link.min.js"></script>
            <script type="text/javascript" src="../js/plugins/lists.min.js"></script>
                <input type="hidden" name="mapper" id="mapper">
            <script type="text/javascript" src="../js/plugins/paragraph_format.min.js"></script>
            <script type="text/javascript" src="../js/plugins/paragraph_style.min.js"></script>
            <script type="text/javascript" src="../js/plugins/video.min.js"></script>
            <script type="text/javascript" src="../js/plugins/table.min.js"></script>
            <script type="text/javascript" src="../js/plugins/url.min.js"></script>
            <script type="text/javascript" src="../js/plugins/entities.min.js"></script>
            <script type="text/javascript" src="../js/plugins/char_counter.min.js"></script>
            <script type="text/javascript" src="../js/plugins/inline_style.min.js"></script>
            <script type="text/javascript" src="../js/plugins/save.min.js"></script>
            <script type="text/javascript" src="../js/plugins/fullscreen.min.js"></script>
            <script type="text/javascript" src="../js/plugins/quote.min.js"></script>
              <script> 
                $(function () {
                       $('#edit').froalaEditor({key: FroalaLicenseKey});                    
                    });

                function HidePopUp(){
                      $('#popup').hide("slow");
                 }
 
            </script>
        
          <script>
       $("#Servicecontinue").hide();
        var rootApp = angular.module('rootApp', ['uploadModule','imagegallery']);
        var uploadModule = angular.module('uploadModule', []);
        
            uploadModule.directive('fileModel', ['$parse', function ($parse) {
                    return {
                        restrict: 'A',
                        link: function (scope, element, attrs) {
                            var model = $parse(attrs.fileModel);
                            var modelSetter = model.assign;

                            element.bind('change', function () {
                                scope.$apply(function () {
                                    modelSetter(scope, element[0].files[0]);
                                });
                            });
                        }
                    };
                }]);

            uploadModule.service('fileUpload', ['$http', function ($http) {
                    this.uploadFileToUrl = function (file, uploadUrl) {
                        var fd = new FormData();
                        fd.append('file', file);
                        $http.post(uploadUrl, fd, {
                            transformRequest: angular.identity,
                            headers: {'Content-Type': undefined}
                        })
                            .success(function (data) {
                                validate(data.link);
                            })
                            .error(function () {
                                alert("Request unsuccessful!");
                            });
                    };
                }]);

            uploadModule.controller('myCtrl', ['$scope', 'fileUpload', function ($scope, fileUpload) {

                    $scope.uploadFile = function () {                        
                        var file = $scope.myFile;
                        console.log('file is ' + JSON.stringify(file));
                        var uploadUrl = global_host_address + 'UploadImages';
                        fileUpload.uploadFileToUrl(file, uploadUrl);
                    };

              }]);
          
           var uploadModule = document.getElementById('uploadModule');

            angular.element(document).ready(function() {
                   angular.bootstrap(uploadModule, ['uploadModule']);
            });

        </script>
         <script>
            
            var imagegallery = angular.module('imagegallery', []);

                imagegallery.controller('samplecontoller', function ($scope,$http) {


                   

                });
                
                angular.module('imagegallery').filter('pagination', function()
                {
                 return function(input, start)
                 {
                  start = +start;
                  return input.slice(start);
                 };
                });
            
        </script>
        <script language="javascript" type="text/javascript">
//            $(document).ready(function(){
//                var textSize = 5;
//                while (textSize < 155)
//                {
//                    $("#textSize").append(new Option(textSize + "px", textSize));
//                    textSize = textSize + 5;
//                }
//                
//
//            });
            
            var xmlHttp;

            function showSelected(str) {

                if (str == 0) {
                    $("#selectedtype").val("non");
                } else {
                    $("#selectedtype").val("selected");
                }

            }

            function brandChange() {

                if (xmlHttp.readyState === 4 || xmlHttp.readyState === "complete") {

                    var response = xmlHttp.responseText;
                    var len = response.length;
                    var no1 = response.indexOf(",");
                    response1 = response.substr(0, no1);
                    response2 = response.substr(no1 + 1, len);
                    document.getElementById("textFontFamily").innerHTML = response1;
                    document.getElementById("blocks").innerHTML = response2;

                }
            }

            function showblocks(str) {

                if (typeof XMLHttpRequest !== "undefined") {

                    xmlHttp = new XMLHttpRequest();

                }
                else if (window.ActiveXObject) {

                    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");

                }
                if (xmlHttp === null) {

                    alert("Browser does not support XMLHTTP Request!");

                    return;
                }

                var url = "getfonts.jsp";

                url += "?Brand_id=" + str;

                xmlHttp.onreadystatechange = brandChange;


                xmlHttp.open("GET", url, true);

                xmlHttp.send(null);

            }

            function usersChange() {

                if (xmlHttp.readyState === 4 || xmlHttp.readyState === "complete") {

                    var response = xmlHttp.responseText;
                    var response1, response2, response3, response4, response5, response6;
                    var len = response.length;
                    var no1 = response.indexOf(",");
                    response1 = response.substr(0, no1);
                    response2 = response.substr(no1 + 1, len);

                    document.getElementById("users").innerHTML = response1;
                    document.getElementById("categories").innerHTML = response2;
                }
            }

            function showUsers(str) {

                if (typeof XMLHttpRequest !== "undefined") {

                    xmlHttp = new XMLHttpRequest();

                }
                else if (window.ActiveXObject) {

                    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");

                }
                if (xmlHttp === null) {

                    alert("Browser does not support XMLHTTP Request!");

                    return;
                }

                var url = "users.jsp";

                url += "?org_id=" + str;

                xmlHttp.onreadystatechange = usersChange;

                xmlHttp.open("GET", url, true);

                xmlHttp.send(null);

            }

            function showSubCategories(str) {


                if (str == 0) {
                    $("#blocks").attr("disabled", false);
                } else {
                    $("#blocks").attr("disabled", true);
                }

                if (typeof XMLHttpRequest !== "undefined") {

                    xmlHttp = new XMLHttpRequest();

                }
                else if (window.ActiveXObject) {

                    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");

                }
                if (xmlHttp === null) {

                    alert("Browser does not support XMLHTTP Request!");

                    return;
                }

                var url = "displaysubcategories.jsp";

                url += "?category_id=" + str;

                xmlHttp.onreadystatechange = categoryChange;

                xmlHttp.open("GET", url, true);

                xmlHttp.send(null);

            }

            function showmindbodyquery(str) {


                if (str == 0) {
                    $("#categories").attr("disabled", false);
                    $("#subcategories").attr("disabled", false);
                    $("#selectedtype").val("non");
                } else {
                    $("#categories").attr("disabled", true);
                    $("#subcategories").attr("disabled", true);
                    $("#selectedtype").val("selected");
                }

                if (typeof XMLHttpRequest !== "undefined") {

                    xmlHttp = new XMLHttpRequest();

                }
                else if (window.ActiveXObject) {

                    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");

                }
                if (xmlHttp === null) {

                    alert("Browser does not support XMLHTTP Request!");

                    return;
                }

                var url = "showmindbodyquery.jsp";

                url += "?block_id=" + str;

                xmlHttp.onreadystatechange = blockChange;

                xmlHttp.open("GET", url, true);

                xmlHttp.send(null);

            }

            function blockChange() {

                if (xmlHttp.readyState === 4 || xmlHttp.readyState === "complete") {

                    var response = xmlHttp.responseText;
                    $("#mindbodyquery").val(response.trim());

                    //              document.getElementById("subcategories").innerHTML=response;
                }
            }

            function categoryChange() {

                if (xmlHttp.readyState === 4 || xmlHttp.readyState === "complete") {

                    var response = xmlHttp.responseText;
                    document.getElementById("subcategories").innerHTML = response;
                }
            }
            function showbrand(Brand) {
                if (typeof XMLHttpRequest !== "undefined") {

                    xmlHttp = new XMLHttpRequest();

                }
                else if (window.ActiveXObject) {

                    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");

                }
                if (xmlHttp === null) {

                    alert("Browser does not support XMLHTTP Request!");

                    return;
                }

                var url = "getfonts.jsp";

                url += "?Brand_id=" + Brand;

                xmlHttp.onreadystatechange = fontChange;

                xmlHttp.open("GET", url, true);

                xmlHttp.send(null);

            }
            function fontChange() {

                if (xmlHttp.readyState === 4 || xmlHttp.readyState === "complete") {

                    var response = xmlHttp.responseText;
                    document.getElementById("textFontFamily").innerHTML = response;
                }

            }



        </script>  
        <script>
           $(document).ready(function(){
                $("#textFontFamily").change(function () {
                    var text = $("#textFontFamily").find('option:selected').text();
                    var font_family_name = $("#textFontFamily").val();
                    var font = font_family_name.split("|");
                    var google_key_word = font[0].split(' ').join('+');
                    var ss = document.createElement("link");
                    ss.type = "text/css";
                    ss.rel = "stylesheet";
                    ss.href = "https://fonts.googleapis.com/css?family=" + google_key_word;
                    document.getElementsByTagName("head")[0].appendChild(ss);

                    var font_path = global_host_address + "DownloadFonts?file_name=" + font[1];
                    var styles = "@font-face {" +
                            "font-family:" + text + ";" +
                            "src: url(" + font_path + ");"
                    $('<style type="text/css">' + styles + '</style>').appendTo(document.head);

                    $("#" + selectedTextID).css("font-family", font[0]);

                });


            });


        </script>
        <script>
            function validate(imageUrl) {                
                var UploadImage=imageUrl.replace(""+global_host_address+"DownloadImage?image_type=ADMIN_LAYOUT_BACKGROUNDIMAGES&user_id=null&image_name=","");
                var model_name = $("#namexml").val();
                var htmldata=$('#edit').froalaEditor('html.get');//$(".fr-wrapper").children().html();

                if (model_name === ""){
                    alert("Model name not entered!");
                    $("#namexml").focus();
                    return false;
                }
                else if(UploadImage === "null")
                    {
                    alert("Please select a image!");
                    HidePopUp();
                    $("#filesToUpload").focus();
                    return false;
                } 
                else if(htmldata === "<p><br></p>"){
                    alert("Please add html data!");
                    HidePopUp();
                    return false;
                    
                }        
                else {
                    $.ajax({
                        url: global_host_address + 'ServletValidateModel',
                        method: 'post',
                        data: {
                            model_name: model_name
                        },
                        success: function (responseText) {
                            if (responseText == "yes") {
                                alert("Name already exist! Please give some other name.");
                                $("#namexml").focus();
                                return false;
                            } else if (responseText == "no") {
                                var file_name = $("#namexml").val();
                                var mapperxml = file_name + "_" + "mapper";
                                var layoutxml = file_name + "_" + "layout";

                                $("#mapper").val(mapperxml);
                                $("#layout").val(layoutxml);
                                $("#model_name").val($("#namexml").val());
                                //                    $("#tabs *").attr("disabled", false);
                                //                    $("#main *").attr("disabled", false);
                                //                    $("#right *").attr("disabled", false);
                                $('#popup').hide("slow");

                                var organization = $("#organization").val();
                                var brand = $("#brand").val();
                                var users = $("#users").val();
                                var categories = $("#categories").val();
                                var subcategories = $("#subcategories").val();
                                var mindbodyquery = $("#mindbodyquery").val();
                                var containerstyle = $("#containerstyle").val();
                                var textstyle = $("#textstyle").val();
                                var element = $("#element").val();
                                var blocks = $("#blocks").val();
                                var model_name = $("#namexml").val();
                                var mapperxml = model_name + "_" + "mapper";
                                var layoutxml = model_name + "_" + "layout";

                                var imagename = $("#imagename").val();
                                var mail = $("#mail").val();
                                $.ajax({
                                    url: global_host_address + 'Model',
                                    method: 'post',
                                    data: {
                                        organization: organization,
                                        brand: brand,
                                        users: users,
                                        categories: categories,
                                        subcategories: subcategories,
                                        mindbodyquery: mindbodyquery,
                                        containerstyle: containerstyle,
                                        textstyle: textstyle,
                                        element: element,
                                        mapper: mapperxml,
                                        layout: layoutxml,
                                        blocks: blocks,
                                        model_name: model_name,
                                        imagename: imagename,
                                        mail: mail,
                                        htmldata:htmldata,
                                        UploadImage:UploadImage
                                    },
                                    success: function (responseText) {
                                        alert("Model saved successfully.");
                                        window.open(getHost() + 'admin/emaillayout.jsp', "_self");
                                    }
                                });



                            }
                        }
                    });

                }
                //      return true;          
            }


        </script>
