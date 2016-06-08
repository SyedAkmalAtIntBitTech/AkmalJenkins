
marketingFlowApp.controller("marketingController", ['$scope', '$location', 'marketingFactory', 'companyMarketingProgramFactory', function ($scope, $location, marketingFactory, companyMarketingProgramFactory) {

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
        
        $scope.getProgramActions = function()
        {
           
               companyMarketingProgramFactory.alluserMarketingProgramGet($scope.programId).then(function (data) {
                 $scope.template_status=data.emailautomation;
                 $scope.programs = data;
                 $scope.actionType="Email";
                  $scope.program_id = program;
                  
                 
             });
            
        };

    }]);

       