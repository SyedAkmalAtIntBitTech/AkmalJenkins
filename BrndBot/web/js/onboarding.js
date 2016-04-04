/* 
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
function onboardingcontroller($scope,$http) {
           
    $scope.getOrganizations = function () {
        
                    $http({
                            method : 'GET',
                            url : getHost()+'/getAllOrganizations.do'
                        }).success(function(data, status, headers, config) {
                            $scope.organizations=data.d.details;
                        }).error(function(data, status, headers, config) {
                                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                        });       
    };
    
    
    $scope.saveOrganization = function (){
        
        var companyName= $("#companyName").val();
        var industryName= $("#industryDropDown").val();      
             
        if(companyName ===""){
            alert(companyNameEmpty);
            $("#companyName").focus();
        }
        else if(industryName ==="0"){
            alert(industryNameEmpty);
            $("#industryDropDown").focus();            
        }
        else
        {            
            localStorage.setItem("companyName",companyName);
            localStorage.setItem("industryName",industryName);
            window.open(getHost() + 'user/onboarding3.jsp', "_self");
//            $.ajax({
//                    method: 'POST',
//                    url: getHost() + '/saveUserOrganization.do',
//                    dataType: "json",
//                    contentType: "application/json",
//                    data: JSON.stringify(organization)
//                }).success(function (data, status, headers, config)
//                {  
//                    alert(eval(JSON.stringify(data.d.operationStatus.messages))); //eval() is to get string without "" quotes                            
//                    window.open(getHost() + 'adminv2/organization.jsp', "_self");
//                }).error(function(data, status, headers, config){
//                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
//                });                         
        }
    };
    
    $scope.getServices = function (){
        alert(localStorage.getItem("companyName")+"\n"+localStorage.getItem("industryName"));
    };
    
    $scope.saveServices = function (){
        var services= $("#services").val();
        localStorage.setItem("services",services);
        alert(localStorage.getItem("services"));
        window.open(getHost() + 'user/onboarding4.jsp', "_self");
        getAllLocalStorage(services);
    };
    $scope.getAllLocalStorage =function (key){
      localStorage.getItem("'"+key+"'");
      alert();
      return;
    };
};

