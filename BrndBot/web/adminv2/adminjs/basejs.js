/* 
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
       $(document).ready(function () {
           $("#addorg").click(function (){
              $("#addorganizationpopup").show();
              $("#addorgpopupdiv").show();
           });
           
            $("#createorg").click(function (){
              $("#addorganizationpopup").hide();
           });
           $("#addorgpopupdiv").click(function (){
               $("#addorganizationpopup").hide();
           });
       });