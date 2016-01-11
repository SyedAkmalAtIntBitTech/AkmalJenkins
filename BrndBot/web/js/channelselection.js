var minbodyid=$("#minbodyid").val();
var category_id=$("#category_id").val();
var sub_category_id=$("#sub_category_id").val();
$(document).ready(function (){
                $("#soc,#eml,#prnt,#dwnld").hide();
            });
            var print = "print";
            var download = "image";
            function selected_media(selectedmedia) {
                
                if (selectedmedia == print){
                    var configuration = global_host_address + "socialeditor.jsp" + "?id=mindbodyid" + "&mediatype=print";
                    window.open(configuration, "_self");
                }else if (selectedmedia == download){
                    var configuration = global_host_address + "socialeditor.jsp" + "?id=mindbodyid" + "&mediatype=image";
                    window.open(configuration, "_self");
                }else if (selectedmedia == 'social'){
                    var configuration = global_host_address + "selectpromotesocialmedia.jsp" + "?id=mindbodyid" + "&mediatype=social";
                    window.open(configuration, "_self");
                }else if (selectedmedia == 'emailsubject'){
                    var configuration = global_host_address + "emailsubjects.jsp" + "?id=mindbodyid" + "&mediatype=email";
                    window.open(configuration, "_self");
                }
            }
            
            function selectPromoteMediaController($scope, $http){
                
                $scope.checkTemplateAvailability = function(){
                    
                    var category = {"category_id": category_id, "sub_category_id": sub_category_id};
                    
                    $http({
                        method: 'POST',
                        url: 'GetTemplates',
                        headers: {'Content-Type': 'application/json'},
                        data: category
                    }).success(function (data, status, headers, config) {
                        $scope.email_templates = data.email_template_availability;
                        $scope.social_templates = data.social_template_availability;
                        $scope.social_templates_print=data.social_template_print;
                        $scope.social_templates_download=data.social_template_download;
                        var email=JSON.stringify($scope.email_templates);
                        var social=JSON.stringify($scope.social_templates);
                        var print=JSON.stringify($scope.social_templates_print);
                        var download=JSON.stringify($scope.social_templates_download);
                        if((email==="0")&&(social==="0")&&(print==="0")&&(download==="0")){
                            $("#channelhead").empty().append('Oops! No Channels to Select. Please wait, its redirecting to Dashboard...');
                            $(".h1").hide();
                            setTimeout(function (){
                            window.location="dashboard.jsp";},4000);
                        }
                        
                        if($scope.email_templates !== 0){$("#eml").show();$("")}
                        if($scope.social_templates !== 0){$("#soc").show();}
                        if($scope.social_templates_print !== 0){$("#prnt").show();}
                        if($scope.social_templates_download !== 0){$("#dwnld").show();}
                                     
                        if (data === error) {
                            alert(data);
                        }
                    }).error(function (data, status, headers, config) {
                        alert("No data available, problem fetching the data");
                        // called asynchronously if an error occurs
                        // or server returns response with an error status.
                    });
                };    
            };