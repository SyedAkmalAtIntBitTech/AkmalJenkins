
         var selected_schedules_to_delete = "";
         var count=0;
        function selcheckbox(id){
//            alert(id+"--selected");
            content='<input type="checkbox" id="'+'entityid'+id+'" hidden="">';
//            alert(content);
//            var a=$("#"+id).text();alert(a);
            var htm=$("#"+id).html();
            
            var selected_schedule_id=id;
            if(htm.contains('class="check-icon"')){
                selected_schedules_to_delete = selected_schedules_to_delete.replace(selected_schedule_id + ",", "");
                count-=1;
                $("#"+id).html(content);
            }
            else
            {
                selected_schedules_to_delete = selected_schedule_id + "," + selected_schedules_to_delete;
//                alert(selected_schedules_to_delete);
                count+=1;
                $("#"+id).html(content+'<img src="images/Icons/check.svg" class="check-icon" style="cursor:pointer;"/>');
            }
            $("#"+id).toggleClass('selection-icon');
            $("#"+id).toggleClass('selection-icon-selected');
            if(count > 0)
            {   
                $("#crtmarkprog").hide();
                $("#delmarkprog").show();
//                $(".add-action-button").hide();
//                $(".delete-button").show();
            }
            if(count==0)
            {
                $("#crtmarkprog").show();
                $("#delmarkprog").hide();
//                $(".add-action-button").show();
//                $(".delete-button").hide();
            }
        }
         function pastprograms(){
             document.getElementById("currprogs").style.display = "none";
             document.getElementById("pastprogs").style.display = "block";
             document.getElementById("pstmarprogli").classList.add("top-subnav-link-active");
             document.getElementById("pstmarprog").classList.add("h3-active-subnav");
             document.getElementById("curmarprogli").classList.remove("top-subnav-link-active");
             document.getElementById("curmarprog").classList.remove("h3-active-subnav");
             document.getElementById("curmarprogli").classList.add("top-subnav-links");     
             document.getElementById("curmarprog").classList.add("h3");    
         }
        function currprograms(){
             document.getElementById("pastprogs").style.display = "none";
             document.getElementById("currprogs").style.display = "block";
             document.getElementById("curmarprogli").classList.add("top-subnav-link-active");
             document.getElementById("curmarprog").classList.add("h3-active-subnav");
             document.getElementById("pstmarprogli").classList.remove("top-subnav-link-active");
             document.getElementById("pstmarprog").classList.remove("h3-active-subnav");
             document.getElementById("pstmarprogli").classList.add("top-subnav-links");     
             document.getElementById("pstmarprog").classList.add("h3");
             
         }
        function controllerUserMarketingProgamsByStatus($scope, $http){
          $("#delmarkprog").hide();
            $scope.getUserMarketingProgramsOpen = function(){
//                
//                $("#pastprogs").css("display","none");
//                $("#currprogs").show();
//                $("#email ").show();
//                $("#prog").hide();
                var programStatus = { "programType": "Open" };
                
                $http({
                    method: 'GET',
                    url: 'listAllMarketingProgram.do?programType=Open',
                    data: programStatus 
                }).success(function (data, status, headers, config) {
                    $scope.current_programs = data.programs;
//                    $(".row").css("display","block");
                }).error(function (data, status, headers, config) {
                    alert("No data available, problem fetching the data");
                    // called asynchronously if an error occurs
                    // or server returns response with an error status.
//                    $(".row").css("display","block");
                });                
            };
            $scope.sendProgramId = function(program_id,past){
                var program_end_date=$("#program_end_date").html();
                window.open(getHost() + 'marketingprogramactions.jsp?program_id='+program_id+'&past='+past+'&program_date='+program_end_date, "_self");
            };
//            $scope.endMarketingProgram = function(){
//                        var endprogid=selected_schedules_to_delete.replace(/[ ,]+/g, "");
////                        var endpid=endprogid.replace(/[ ,]+/g, "");
////                    alert(endprogid);
//                    var delconf=confirm("Do you really want to End this Program?");
//                    if(delconf===true){
//                  var program_id = {"program_id": endprogid};
//                $http({
//                    method: 'POST',
//                    url: 'endMarketingProgram.do',
//                    headers: {'Content-Type':'application/json'},
//                    data: JSON.stringify(program_id)
//                }).success(function (data, status, headers, config) {
//                    if (data == "true"){
//
//                          window.open(getHost() + 'marketingprogramlists.jsp', "_self");
//
//                    }else {
//                        alert("Problem deleting the record!");
//                    }
//                }).error(function (data, status, headers, config) {
//                    alert("No data available! Problem fetching the data.");
//                    // called asynchronously if an error occurs
//                    // or server returns response with an error status.
//                });      
//
//                }else{
//
//                }
//            };
            
            $scope.getUserMarketingProgramsClosed = function(){
                var programStatus = { "programType": "Closed" };
//                $("#pastprogs").show();
////                $("#prog").show();
//                $("#currprogs").hide();
//                $("#email").hide();
                $http({
                    method: 'GET',
                    url: 'listAllMarketingProgram.do?programType=Closed',
                    data: programStatus 
                }).success(function (data, status, headers, config) {
                    $scope.past_programs = data.programs;
                    $(".row").css("display","block");
                }).error(function (data, status, headers, config) {
                    alert("No data available, problem fetching the data");
                    // called asynchronously if an error occurs
                    // or server returns response with an error status.
                    $(".row").css("display","block");
                });                
            };
            
           
        }
        