/* 
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */

angular.module('marketingprogramota',[]).controller('marketingProgramsController',function($scope,$http)
{
    $scope.oneTimeActionsData=[ {actionName: "Simple",noOfDays: "7",actionType: "Facebook",actionTime:"03:30"}, 
                                {actionName: "action ",noOfDays: "12",actionType: "Twitter",actionTime:""}, 
                                {actionName: "smart",noOfDays: "1",actionType: "Reminder",actionTime:""}
                              ];
             
    $scope.getlistnum = function (index) {
        $(".overflowhide").css("overflow","hidden").animate({scrollTop:0}, '10', 'swing');
              $("#addOneTimeActionsPopup").show();
              $("#addOrganizationPopupDiv").show();
              $("#actionIndex").val(index);
              $("#addota").empty().append('<% indval='+index+'; %>');
//              $("#oneTimeActionName").val(actionName);
        
    };
});