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
           
           $("#addcatprint").click(function (){
              $("#addprintcatpopup").show();
              $("#addorgpopupdiv").show();
           });
           
           $("#addorgpopupdiv").click(function (){
               $("#addprintcatpopup").hide();
               $("#addorganizationpopup").hide();
                $("#addorgpopupdiv").hide();
           });
       });