emailFlowApp.controller("emailController", ['$scope', '$window', '$location', 'blockModelFactory', 'companyFactory','categoryFactory', 'emailDraftFactory', 'subCategoryFactory', 'externalContentFactory', 'redirectFactory', 'SharedService', 'settingsFactory', function ($scope, $window, $location, blockModelFactory, companyFactory, categoryFactory, emailDraftFactory, subCategoryFactory, externalContentFactory, redirectFactory, SharedService, settingsFactory) {
        $scope.emailChannelId = 3;
        $scope.printChannelId = 2;
        $scope.imageChannelId = 1;
        $scope.forward = "";
        $scope.categoryId = "";
        $scope.lookupId = "";
        $scope.externalSourceName = "";
        $scope.subCategoryId = "";
        $scope.mindbodyid = "";
        $scope.emailsubject = "";
        $scope.emailSubjectError = "";
        $scope.sharedData = "";
        $scope.selectedBlockId="";
        $scope.isBlockClicked="false";
        $scope.htmlBlockId="defaultblock1";
        $scope.firstTemplateForBlock="";
        $scope.addBlockCount=0;
        $scope.draftId = 0;
        $scope.redirect = function (redirect, categoryId, mindbody, lookupId, mindbodyid, draftId)
        {
            $scope.lookupId = lookupId;
            if (mindbody === '')
            {
                $scope.categoryId = categoryId;
            }
            if (mindbody === 'Mindbody')
            {
                $scope.externalSourceName = 'mindbody';
                redirect = $scope.forwardone;
                $scope.lookupId = lookupId;
                $scope.subCategoryId = categoryId;
            }
            if (mindbody === 'nonmindbody')
            {
                if (redirect === 'emailexternalsource')
                {
                    redirect = $scope.forwardtwo;
                    $scope.subCategoryId = categoryId;
                }
                $scope.categoryId = categoryId;
            }
            if (mindbodyid !== '')
            {
                $scope.mindbodyid = mindbodyid;
            }
            if (draftId !== '')
            {
                $scope.draftId = mindbodyid;
            }
            $location.path("/" + redirect);
        };
        $scope.redirectToEmailFlow = function (forwardone)
        {
            redirectFactory.redirectFlowTo(forwardone);
            $window.location = getHost() + "user/" + forwardone;
            var emailsubject = $scope.emailsubject;
            if (emailsubject === '')
            {
                $scope.emailSubjectError = "Email Subject Required!";
            }
        };
        $scope.getCategories = function (forwardone)
        {
            categoryFactory.allCompanyCategoriesGet(emailChannelId).then(function (data) {
                $scope.pageName = "emailcategory";
                $scope.header = "Select Category";
                $scope.forwardone = forwardone;
                $scope.displayAllCategories = data.d.details;
            });
        };
        $scope.getSubCategories = function (forwardone, forwardtwo)
        {
            $scope.pageName = "emailsubcategory";
            subCategoryFactory.allSubCategoriesGet($scope.categoryId).then(function (data) {
                $scope.pageName = "emailsubcategory";
                $scope.header = "Select Subcategory";
                $scope.forwardone = forwardone;
                $scope.forwardtwo = forwardtwo;
                $scope.displayAllCategories = data.d.details;
            });
        };
        $scope.getActive = function (lookupId)
        {
            externalContentFactory.activatedGet(lookupId).then(function (data) {
                var minddata = JSON.stringify(data.d.details);
                if (minddata === "[true]") {
                    externalContentFactory.listDataGet(lookupId).then(function (data) {
                        var parseData = JSON.parse(data.d.details);
                        $scope.mindbodylist = parseData.mindbody_data;
                    });
                }
                else
                {

                }
            });
        };
        $scope.redirectFlow = function (pageName)
        {
            redirectFactory.redirectFlowTo(pageName);
        };

        $scope.redirectToMarketingProgram = function (pageName)
        {
            redirectFactory.redirectFlowTo(pageName);
        };
        
        $scope.getEmailDrafts = function (emailDrafts) {
            if (!$scope.draftId) {
                emailDraftFactory.getEmailDraftGet(emailDrafts).then(function(data){
                     if (data == "") {
                        $scope.emaildraftsstatus = "No email drafts present";
                    } else {

                        $scope.htmlbody = data.htmlbody;
                        $('#edit').fraoalaEditor('html.set', '' + data.htmlbody);
                    }
                });
        };
           
        $scope.showStyles = function () {
            if (isBlockClicked == "true" || htmlBlockId != "defaultblock1")
            {                
                blockModelFactory.allEmailBlockModelGet(selectedBlockId).then(function (data) {
                    $scope.datalistsstyles = data.d.details;
                });
            }
            else
            {
                blockModelFactory.EmailModelsIdGet($scope.subCategoryId).then(function (data) {
                    $scope.datalistsstyles = data.d.details;
                });
            }         
        };
        
        $scope.showBlocks = function () {
            companyFactory.allBlocksForCompanyGet().then(function (data) {
                $scope.blockLists = data.d.details;                
            });
        };

        $scope.didChooseBlock = function (selectedBlockId, externalSourceKeywordLookupId) {

            blockModelFactory.allEmailBlockModelGet().then(function (data) {
                $scope.firstTemplateForBlock = data.d.details[0].emailBlockModelLookupId;

                $scope.isBlockClicked = "true";
                $scope.htmlBlockId = "";
                $scope.selectedBlockId = selectedBlockId;

                if (externalSourceKeywordLookupId == 0)
                {
                    $scope.mindbodyid = "0";
                    $scope.addHTMLInEmailEditor(selectedBlockId);
                } else
                {
                    externalContentFactory.activatedGet(externalSourceKeywordLookupId).then(function (data) {
                        var externalData = JSON.stringify(data.d.details);
                        if (externalData === "[true]") {

                            externalContentFactory.listDataGet(externalSourceKeywordLookupId).then(function (listData) {
                                var parseData = JSON.parse(listData.d.details);
                                $scope.mindbodyDataList = parseData;
                                $scope.showStyles();
                            });
                        }
                    });
                }
            });
        };

        
        $scope.didSelectMindbodyDataId = function (id) {
            $scope.mindbodyid = id;
            $scope.showStyles();
            $scope.addHTMLInEmailEditor($scope.firstTemplateForBlock);
        };
        
        $scope.addHTMLInEmailEditor = function (templateId) {
            var mindbodyId = 0;

            //TODO Ilyas to check while testing
            if (!$scope.mindbodyid) {
            } else {
                mindbodyId = $scope.mindbodyid;
            }            

            externalContentFactory.layoutEmailModelGet(templateId, $scope.isBlockClicked, mindbodyId).then(function (data) {
                var emailData = JSON.parse(data.d.details);
                
                if ($scope.isBlockClicked === "false") {
                    var editorHtml = $('#edit').froalaEditor('html.get');
                    if (editorHtml.contains('id="defaultblock1"')) {
                        var jHtmlObject = jQuery(editorHtml);
                        var editor = jQuery("<p>").append(jHtmlObject);
                        editor.find("#defaultblock1").remove();
                        editorHtml = editor.html();
                    }
                    var styleHtml = '<div id=defaultblock1 ng-click="selecterBlockId(defaultblock1,0)">' + emailData.htmldata + '</div>';
                    $('#edit').froalaEditor('html.set', '' + styleHtml + '' + editorHtml + '');
                } else {
                    ++$scope.addBlockCount;
                    var htmlTagId = "block"+$scope.addBlockCount;
                    
                    var editorHtml = $('#edit').froalaEditor('html.get');
                    if (editorHtml.contains('id="' + htmlTagId + '"')) {
                        var jHtmlObject = jQuery(editorHtml);
                        var editor = jQuery("<p>").append(jHtmlObject);
                        var BlockHtml = '<div id=' + htmlTagId + ' ng-click=selecterBlockId(' + htmlTagId + ',' + $scope.selectedBlockId + ')>' + emailData.htmldata + '</div>';
                        editor.find("#" + htmlTagId).replaceWith(BlockHtml);
                        editorHtml = editor.html();
                        $('#edit').froalaEditor('html.set', '' + editorHtml + '');
                    } else
                    {
                        BlockHtml = '<div id=' + htmlTagId + ' ng-click=selecterBlockId(' + htmlTagId + ',' + $scope.selectedBlockId + ')>' + emailData.htmldata + '</div>';
                        $('#edit').froalaEditor('html.set', '' + editorHtml + '' + BlockHtml + '');
                    }
                }
            });

        };
        
        $scope.getFooterDetails = function () {
            settingsFactory.getAllPreferencesGet().then(function(data){
                                $scope.footerDetails = JSON.parse(data.d.details).userProfile;
            });
        };
        $scope.changeFooterDetails = function () {
            var address = $("#footerAddress").val();
            var websiteurl = $("#footerWebsiteUrl").val();
            ;
            var facebookurl = $("#footerFacebookUrl").val();
            ;
            var twitterUrl = $("#footerTwitterUrl").val();
            ;
            var instagramUrl = $("#footerInstagramUrl").val();
            var footerData = '{"facebookUrl":"' + facebookurl + '","twitterUrl":"' + twitterUrl + '","instagramUrl":"' + instagramUrl + '","websiteUrl":"' + websiteurl + '","address":"' + address + '"}';
            if (address) {
                $http({
                    method: 'POST',
                    url: getHost() + 'settings/setFooter',
                    data: footerData
                }).success(function (data) {
                    alert(detailssaved);
                    $("#emailFooterPopup").hide();

                }).error(function (data, status) {
                    alert(requesterror);
                });
            }
            else {
                alert("please enter the Address");
                $("#footerAddress").focus();
            }
        };

  }]);
