var mindbodyid=$("#minbodyid").val();
var category_id=$("#category_id").val();
var sub_category_id=$("#sub_category_id").val();
var sub_category_name=$("#sub_category_name").val();

$(document).ready(function (){
                $("#soc,#eml,#prnt,#dwnld").hide();
            });
            var print = "print";
            var download = "image";
            function selected_media(selectedmedia,mindbodyid) {
                mindbodyid=mindbodyid;
                category_id=$("#category_id").val();
                sub_category_id=$("#sub_category_id").val();
                sub_category_name=$("#sub_category_name").val();
                if (selectedmedia == print){
                    var configuration = global_host_address + "socialeditor.jsp" + "?id="+ mindbodyid + "&mediatype=print";
                    window.open(configuration, "_self");
                }else if (selectedmedia == download){
                    var configuration = global_host_address + "socialeditor.jsp" + "?id="+ mindbodyid + "&mediatype=image";
                    window.open(configuration, "_self");
                }else if (selectedmedia == 'social'){
                    var configuration = global_host_address + "selectpromotesocialmedia.jsp" + "?id="+ mindbodyid + "&mediatype=social" + "&category_id=" +category_id+ "&sub_category_id="+sub_category_id+"&sub_category_name="+sub_category_name;
                    window.open(configuration, "_self");
                }else if (selectedmedia == 'emailsubject'){
                    var configuration = global_host_address + "emailsubjects.jsp" + "?id="+ mindbodyid + "&mediatype=email";
                    window.open(configuration, "_self");
                }
            }
function selectPromoteMediaController($scope, $http) {

    $scope.checkTemplateAvailability = function () {
        var category = {"category_id": parseInt($("#category_id").val()), "sub_category_id": parseInt($("#sub_category_id").val())};

        $http({
            method: 'POST',
            url: 'GetTemplates',
            headers: {'Content-Type': 'application/json'},
            data: category
        }).success(function (data, status, headers, config) {
            $scope.email_templates = data.email_template_availability;
            $scope.social_templates = data.social_template_availability;
            $scope.social_templates_print = data.social_template_print;
            $scope.social_templates_download = data.social_template_download;
            var email = JSON.stringify($scope.email_templates);
            var social = JSON.stringify($scope.social_templates);
            var print = JSON.stringify($scope.social_templates_print);
            var download = JSON.stringify($scope.social_templates_download);
            if ((email === "0") && (social === "0") && (print === "0") && (download === "0")) {
                $("#channelhead").empty().append('Oops! No Channels to Select. Please wait, its redirecting to Dashboard...');
                $(".h1").hide();
                setTimeout(function () {
                    window.location = "dashboard.jsp";
                }, 4500);
            }

            if ($scope.email_templates !== 0) {
                $("#email").show();
            }
            if ($scope.social_templates !== 0) {
                $("#social").show();
            }
            if ($scope.social_templates_print !== 0) {
                $("#print").show();
            }
            if ($scope.social_templates_download !== 0) {
                $("#download").show();
            }

            if (data === error) {
                alert(data);
            }
        }).error(function (data, status, headers, config) {
            alert(nodataerror);
        });
    };
};
