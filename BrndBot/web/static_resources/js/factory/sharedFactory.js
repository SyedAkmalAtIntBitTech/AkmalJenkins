/* 
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */

sharedServiceApp.factory('SharedService', function ($window) {

    var data="hi..";

    if (!!$window.sharedService){
      return $window.sharedService;
    }

    $window.sharedService = {
      change: function(newdata){
        data = newdata;
      },
      get: function(){
        return data;
      }
    };
    return $window.sharedService;
});