
marketingFlowApp.controller("marketingController", ['$scope', '$location', 'marketingFactory', 'companyMarketingProgramFactory', function ($scope, $location, marketingFactory, companyMarketingProgramFactory) {
alert();
        $scope.marketingCategoryId = "";
        $scope.marketingProgramId = "";
        $scope.past = "";
        $scope.endDate ="";
        $scope.programId="";
        $scope.redirect = function (pageName, marketingCategoryId)
        {

            $scope.marketingCategoryId = marketingCategoryId;
            $location.path("/" + pageName);


        };
        $scope.redirectProgram = function (pageName, marketingCategoryId, marketingProgramId, htmlData)
        {

            $scope.marketingCategoryId = marketingCategoryId;
            $scope.marketingProgramId = marketingProgramId;
            $scope.htmlData = htmlData;
            $location.path("/" + pageName);


        };
         $scope.redirectToActions = function (pageName, programId,past,endData)
        {
            
            $scope.past = past;
            $scope.endDate = endData;
            $scope.programId = programId;
            $location.path("/"+pageName);


        };
        
         $scope.redirectToEmailAutomation = function(pageName, add, programId, zero)
         {
              
               $scope.programId = programId;
               $scope.add = add;
               $scope.zero = zero;
               $location.path("/"+pageName);
             
         };
         
        $scope.getAllMarketingPrograms = function (forward) {
            marketingFactory.companyMarketingCategoriesGet().then(function (data) {
                $scope.header = "Please choose a marketing program type.";
                $scope.pageName = "marketing";
                $scope.forward = forward;
                $scope.marketingCategories = data.d.details;
            });

        };

        $scope.displayMarketingProgramByCategoryId = function (forward) {
           
            marketingFactory.marketingProgramsGet($scope.marketingCategoryId).then(function (data) {
                $scope.pageName = "marketingPrograms";
                $scope.forward = forward;
                $scope.displayAllMarketingPrograms = data.d.details;
                $scope.header = "Select a Category";

            });

        };

        $scope.saveMarketingProgram = function (programName, programUrl, programUrlName, programDateTime) {
            var data = {"program_name": programName,
                "program_date_time": programDateTime,
                "program_url": programUrl,
                "program_url_name": programUrlName,
                "marketing_category_id": $scope.marketingCategoryId.toString(),
                "marketing_program_id": $scope.marketingProgramId.toString()
            };

            companyMarketingProgramFactory.setMarketingProgramPost(data).then(function (data) {

            });
        };

        $scope.getUserMarketingProgramsOpen = function (forward) {
            companyMarketingProgramFactory.listAllMarketingProgramGet("Open").then(function (data) {
                $scope.currentPrograms = data.programs;
                $scope.currProgramsDiv = true;
                $scope.pastProgramsDiv = false;
                $scope.forward = forward;
            });

        };

        $scope.getUserMarketingProgramsClosed = function (forward) {
            companyMarketingProgramFactory.listAllMarketingProgramGet("Closed").then(function (data) {
                $scope.pastPrograms = data.programs;
                $scope.currProgramsDiv = false;
                $scope.pastProgramsDiv = true;
                $scope.forward = forward;
            });

        };
        $scope.showPastPrograms = function () {
            $scope.currProgramsDiv = false;
            $scope.pastProgramsDiv = true;

        };
        $scope.showCurrentPrograms = function () {
            $scope.currProgramsDiv = true;
            $scope.pastProgramsDiv = false;

        };
        
        $scope.getProgramActions = function(forward)
        {
           
               companyMarketingProgramFactory.alluserMarketingProgramGet($scope.programId).then(function (data) {
                   
                 $scope.template_status=data.emailautomation;
                 $scope.programs = data;
                 $scope.actionType="Email";
                  $scope.forward = forward;
                  
                 
             });
            
        };
        
        
         $scope.ShowAddAction = function()
        { 
       $scope.fadeClass='fadeClass';
        $scope.fade = true;
        $scope.addAction = true;
        
        }
    
    $scope.closeOverlay = function()
    {
       $scope.fadeClass='';
        $scope.addAction = false;
        
    }
    
    $scope.AddAction = function(addTitle,datePicker,timePicker,actionType)
    {
        var actiondate = datePicker;
        var actionDateTime=$("#timepicker1").val().replace(/ /g,'');
        var l=actiondate.toLocaleString() +" "+actionDateTime.toLocaleString();
        var myDate = new Date(l);
        var days=0;
        var schedule_time = Date.parse(l);
        var myEpoch = schedule_time;
        var days=0;
        var action = {"title": addTitle, "actiontype": actionType,"type": "save","description":"","marketingType":$scope.programId,"action_date": myEpoch,"days":days};
        companyMarketingProgramFactory.addActionPost(action).then(function (data) {
            
      
    });
    };
    
    $scope.addUpdateRecuringAction = function()
    {
         marketingRecurringEmailControllerFactory.recurringEmailTemplatePost().then(function (data) {
             
             
         });
        
    };
    
   

    }]);

       