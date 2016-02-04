
         var selected_schedules_to_delete = "";
         var count=0;
        function selcheckbox(id){
            content='<input type="checkbox" id="'+'entityid'+id+'" hidden="">';
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
                count+=1;
                $("#"+id).html(content+'<img src="images/Icons/check.svg" class="check-icon" style="cursor:pointer;"/>');
            }
            $("#"+id).toggleClass('selection-icon');
            $("#"+id).toggleClass('selection-icon-selected');
            if(count > 0)
            {   
                $("#crtmarkprog").hide();
                $("#delmarkprog").show();
            }
            if(count==0)
            {
                $("#crtmarkprog").show();
                $("#delmarkprog").hide();
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
                var programStatus = { "programType": "Open" };                
                $http({
                    method: 'GET',
                    url: 'listAllMarketingProgram.do?programType=Open',
                    data: programStatus 
                }).success(function (data, status, headers, config) {
                    $scope.current_programs = data.programs;
                }).error(function (data, status, headers, config) {
                    alert(nodataerror);
                });                
            };
            $scope.sendProgramId = function(program_id,past){
                var program_end_date=$("#program_end_date"+program_id).html();
                window.open(getHost() + 'marketingprogramactions.jsp?program_id='+program_id+'&past='+past+'&program_date='+program_end_date, "_self");
            };
            
            $scope.getUserMarketingProgramsClosed = function(){
                var programStatus = { "programType": "Closed" };
                $http({
                    method: 'GET',
                    url: 'listAllMarketingProgram.do?programType=Closed',
                    data: programStatus 
                }).success(function (data, status, headers, config) {
                    $scope.past_programs = data.programs;
                    $(".row").css("display","block");
                }).error(function (data, status, headers, config) {
                    alert(nodataerror);
                    $(".row").css("display","block");
                });                
            };          
        }