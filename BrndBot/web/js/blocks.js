/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function blocksController($scope, $http) {

//    $scope.block = {};
    var name, mindbodyquery, brand_id, sub_category_id;
    function validate() {
        name = $("#name").val();
        sub_category_id = $("#sub_categories").val();
        brand_id = $("#brand").val();
        mindbodyquery = $("#mindbodyquery").val();
        
//        alert(sub_category_id);
//        var zz = document.getElementById("sub_categories").selectedIndex;
//        sub_category_id = document.getElementsByTagName("option")[zz].value;

//        var x = document.getElementById("brand").selectedIndex;
//        brand_id = document.getElementsByTagName("option")[x].value;
//
//        var y = document.getElementById("mindbodyquery").selectedIndex;
//        mindbodyquery = document.getElementsByTagName("option")[y].value;
        
        if (name === "") {
            alert("Enter the block name");
            $("#name").focus();
            return false;
        }
        
        if (brand_id == 0) {
            alert("No brand selected, please select the brand");
            document.getElementById("brand").focus();
            return false;
        }

        return true;
    }

    $scope.createNewblock = function ()
    {
        if (validate())
        {
            var block = {"name": name, "mindbodyquery": mindbodyquery, "brand_id": brand_id, "sub_category_id":sub_category_id, "type": "add"};

            $http({
                method: 'POST',
                url: getHost() + 'ServletBlock',
                headers: {'Content-Type': 'application/json'},
                data: block
            }).success(function (data)
            {
                $scope.status = data;
                if (data === "false") {
                    alert("block already exist");
                    $("#blockname").focus();
                } else if (data === "true") {
                    alert("block saved successfully");
                    window.open(getHost() + 'admin/blocks.jsp', "_self");
                } else if (data === error) {
                    alert(data);
                }
            })
                    .error(function (data, status) {
                        // called asynchronously if an error occurs
                        // or server returns response with an error status.
                        alert("Request not successful!");
                    });
        }
    };

//        $scope.editblock = function (block_id, name, mindbody_query, brand_id, sub_category_id) {
//            var configuration = global_host_address + "admin/editblocks.jsp" + "?font_id=" + font_id + "&font_name=" + font_name;
//            window.open(configuration, "_self");
//        };

//    $scope.changeblock = function (block_id)
//    {
//        var org_id = '#' + block_id;
//        var blockname = $(org_id).val();
//
//        function validate() {
//            if (blockname === "") {
//                alert("Enter the block");
//                $(org_id).focus();
//                return false;
//            }
//            return true;
//        }
//
//        if (validate())
//        {
//            var block = {"block_id": block_id.toString(), "block_name": blockname, "type": "edit"};
//            $http({
//                method: 'POST',
//                url: getHost() + 'ServletBlock',
//                headers: {'Content-Type': 'application/json'},
//                data: block
//            }).success(function (data)
//            {
//                $scope.status = data;
//                if (data === "true") {
//                    alert("block updated successfully");
//                    window.open(getHost() + 'admin/blocks.jsp', "_self");
//                } else if (data === error) {
//                    alert(data);
//                } else if (data === "false") {
//                    alert("block already exist");
//                    $("#blockname").focus();
//                }    
//            })
//                    .error(function (data, status) {
//                        // called asynchronously if an error occurs
//                        // or server returns response with an error status.
//                        alert("Request not successful!");
//                    });
//        }
//    };

    $scope.deleteblock = function (block_id) {
        var block = {"block_id": block_id.toString(), "type": "delete"};
        $http({
            method: 'POST',
            url: getHost() + 'ServletBlock',
            headers: {'Content-Type': 'application/json'},
            data: block
        }).success(function (data)
        {
            $scope.status = data;
            if (data === "true") {
                alert("block deleted successfully");
                window.open(getHost() + 'admin/blocks.jsp', "_self");
            } else if (data === error) {
                alert(data);
            }
        });
    };
};

function editBlock(block_id, name, mindbody_query, brand_id, sub_category_id){
    
            var configuration = global_host_address + "admin/editblocks.jsp" + "?block_id=" + block_id + "&name=" + name + "&mindbody_query=" + mindbody_query + "&brand_id=" + brand_id + "&sub_category_id="+ sub_category_id;
            window.open(configuration, "_self");
//                                    $.ajax({
//                                            url: 'editblocks.jsp',
//                                            method: 'post',
//                                            data: {
//                                                block_id: block_id,
//                                                name: name,
//                                                mindbody_query: mindbody_query,
//                                                brand_id: brand_id,
//                                                sub_category_id: sub_category_id
//                                            },
//                                            success: function (responseText) {
//                                            }
//                                        });

};





