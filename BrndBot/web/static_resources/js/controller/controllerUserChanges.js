settingFlowApp.controller("controllerUserChanges", ['$scope', '$window', '$location', 'signupFactory', 'settingsFactory', 'assetsFactory', 'onboardingFactory', 'appSessionFactory', function ($scope, $window, $location, signupFactory, settingsFactory, assetsFactory, onboardingFactory, appSessionFactory) {

        $scope.inputType = 'password';
        $scope.colorFrom = "custom";
        $scope.newPasswordValidation = newPasswordValidation;
        $scope.confirmPasswordValidation = confirmPasswordValidation;
        $scope.confirmPasswordMissmatch = confirmPasswordMissmatch;
        $scope.logoValidation = logoValidation;
        $scope.companyAddressValidation = companyAddressValidation;
        $scope.invalidCompanyAddressValidation = invalidCompanyAddressValidation;
        $scope.addressLine1Validation = addressLine1Validation;
        $scope.addressLine2Validation = addressLine2Validation;
        $scope.cityValidation = cityValidation;
        $scope.stateValidation = stateValidation;
        $scope.zipcodeValidation = zipcodeValidation;
        $scope.countryValidation = countryValidation;
        $scope.showPaletteChangePopUp="";
        $scope.addUserSettings = false;                   
        $scope.userRoleLookUpId = "";
        $scope.inviteId = "";
        $scope.passwordText = "";
        $scope.companyName = "";
        $scope.userFirstName = "";
        $scope.userLastName = "";
        $scope.userRole = "";
        $scope.logourl = "";
        $scope.showCustomColorPicker = false;
        $scope.companyAddressDetails = {};
        $scope.companyDetails = {};
        $scope.userDetails = {};

        $scope.getUserDetails = function(){
            
            appSessionFactory.getCompany().then(function(kGlobalCompanyObject){
                $scope.companyName = kGlobalCompanyObject.companyName;
                $scope.userFirstName = kGlobalCompanyObject.userFirstName;
                $scope.userLastName = kGlobalCompanyObject.userLastName;
                $scope.userRole = kGlobalCompanyObject.roleName; 
                $scope.logourl = kGlobalCompanyObject.logourl;
                $scope.userDetails.userFirstName=$scope.userFirstName;
                $scope.userDetails.userLastName=$scope.userLastName;                
            });
        };
        
        $scope.validateCompanyAddress = function (companyAddressData) {
            if (!companyAddressData.companyAddress) {
                $scope.companyDetails.companyAddress = "";
                $("#companyAddress").focus();
                return false;
            } 
            else if (!companyAddressData.addressline2 || !companyAddressData.city || !companyAddressData.state || !companyAddressData.zipcode || !companyAddressData.country) {
                $("#companyAddress").focus();
                return false;
            } 
//            if (!companyAddressData.addressLine1) {
//                $scope.companyAddressDetails.addressLine1 = "";
//                $("#addressLine1").focus();
//                return false;
//            }
//            else if (!companyAddressData.city) {
//                $scope.companyAddressDetails.city = "";
//                $("#city").focus();
//                return false;
//            }
//            else if (!companyAddressData.state) {
//                $scope.companyAddressDetails.state = "";
//                $("#state").focus();
//                return false;
//            } 
//            else if (!companyAddressData.zipCode) {
//                $scope.companyAddressDetails.zipCode = "";
//                $("#zipcode").focus();
//                return false;
//            }
//            else if (!companyAddressData.country) {
//                $scope.companyAddressDetails.country = "";
//                $("#country").focus();
//                return false;
//            }
            return true;
        };
        
        $scope.getCompanyAddress = function (){
            settingsFactory.getAllPreferencesGet().then(function (data) {
                $scope.companyAddressDetails=JSON.parse(data.d.details).companyAddress[0];
                $scope.companyDetails.companyAddress=$scope.companyAddressDetails.addressLine1+" "
                                        +$scope.companyAddressDetails.addressLine2+", "
                                        +$scope.companyAddressDetails.city+", "
                                        +$scope.companyAddressDetails.state+" "
                                        +$scope.companyAddressDetails.zipCode+", "
                                        +$scope.companyAddressDetails.country;
            });            
        };
        
        
        $scope.updateCompanyAddress = function (company){
            $scope.address1 = document.getElementById('street_number').value;
            $scope.address2 = document.getElementById('route').value;
            $scope.city = document.getElementById('locality').value;
            $scope.state = document.getElementById('administrative_area_level_1').value;
            $scope.zipcode = document.getElementById('postal_code').value;
            $scope.country = document.getElementById('country').value;
            if($scope.validateCompanyAddress(company)){
            var companyAddress = {"addressLine1":$scope.address1,"addressLine2":$scope.address2,"city":$scope.city,"state":$scope.state,"zipcode":$scope.zipcode,"country":$scope.country};
            onboardingFactory.saveCompanyAddress(companyAddress).then(function (data){
                growl(companyAddressSaved);
            });  
            }
        };
        
        // Hide & show password function
        $scope.hideShowPassword = function () {
            if ($scope.inputType == 'password')
                $scope.inputType = 'text';
            else
                $scope.inputType = 'password';
        };

        $scope.accountSettingsValidation = function (password, confirmPassword) {
            if (!password) {
                $scope.userDetails.password = "";
                $("#newpassword").focus();
                return false;
            }
            if (!confirmPassword) {
                $scope.userDetails.confirmPassword = "";
                $("#confirmpassword").focus();
                return false;
            }
            if($scope.isConfirmPasswordSame(confirmPassword))
            return true;
        };
        
        $scope.userAccountSettingsValidation = function (fname, lname) {
            if (!fname) {
                $scope.userDetails.userFirstName = "";
                $("#firstName").focus();
                return false;
            }
            if (!lname) {
                $scope.userDetails.userLastName = "";
                $("#lastName").focus();
                return false;
            }
            return true;
        };
        
        $scope.setPassword = function (password){
            $scope.passwordText = password;
        };
        
        $scope.isConfirmPasswordSame = function (cPassword){
            if(cPassword === ""){
                $scope.isConfirmPasswordSamePassword = true;
                return false;
            }else if($scope.passwordText === cPassword){
                $scope.isConfirmPasswordSamePassword = false;
                return true;
            }else{
                $scope.isConfirmPasswordSamePassword = true;
                return false;
            }
        };
        
        $scope.changePassword = function (userDetails) {
            if ($scope.accountSettingsValidation(userDetails.password, userDetails.confirmPassword))
            {
                var password_object = {"password": userDetails.password, "confirmpassword": userDetails.confirmPassword, "type": "update"};
                signupFactory.resetPasswordPost(password_object).then(function (data) {
                    growl("Password changed successfully");
                    $scope.status = data;
                });
            }
        };
        
        $scope.changeUserName = function (userDetails){
            if($scope.userAccountSettingsValidation(userDetails.userFirstName, userDetails.userLastName))
            {   
                var userName = {"firstName":userDetails.userFirstName,"lastName":userDetails.userLastName};
                signupFactory.updateUser(userName).then(function (data) { 
                    appSessionFactory.getCompany().then(function(kGlobalCompanyObject){
                    kGlobalCompanyObject.userFirstName = userDetails.userFirstName;
                    kGlobalCompanyObject.userLastName = userDetails.userLastName;                   
                    
                    appSessionFactory.setCompany(kGlobalCompanyObject).then(function(){
                        appSessionFactory.getCompany().then(function(kGlobalCompanyObject){
                            $scope.companyName = kGlobalCompanyObject.companyName;
                            $scope.userFirstName = kGlobalCompanyObject.userFirstName;
                            $scope.userLastName = kGlobalCompanyObject.userLastName;
                            $scope.userRole = kGlobalCompanyObject.roleName; 
                            $scope.logourl = kGlobalCompanyObject.logourl;
                            $scope.userDetails.userFirstName=$scope.userFirstName;
                            $scope.userDetails.userLastName=$scope.userLastName;    
                        });
                    });
                    });
                  growl(eval(JSON.stringify(data.d.operationStatus.messages)));  
                });
            }
        };
        
        $scope.emailAdressValidation = function (email) {
            var regex = /^(([^<>()\[\]\.,;:\s@\"]+(\.[^<>()\[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
                if (regex.test(email)) {
                    return false;
                }
            return true;
        };
        
        $scope.inviteUser = function (userDetails) {
            if (!userDetails){
                growl(noEmailAndRole);
            }else if (!userDetails.email) {
                growl(noEmail);
            }else if ($scope.emailAdressValidation(userDetails.email)) {
                growl(emailValidation);
            }else if (!userDetails.adminRadio){
                growl(noRole);
            }else {
                var roles = [];
                roles.push(userDetails.adminRadio);
                var invitation = {"userRoleLookUpId":"", "emailaddress": userDetails.email, "roles": roles, "task": 'invitation'};

                onboardingFactory.inviteUserPost(invitation).then(function (data) {
                    growl(data.d.message);
                    $scope.getInvitedUsers();
                    $scope.closeInviteUsersPopup();
    //                $location.path("/settings/useraccountsettings");
                });
            }
        };

        $scope.resendUserInvite = function (inviteId) {
           
            onboardingFactory.resendUserInvitePost(inviteId).then(function (data) {
                growl(data.d.message);
                $scope.closeOverlay();
                $location.path("/settings/useraccountsettings");
            });
        };

        $scope.editUser = function (userDetails) {
            var roles = [];
            roles.push(userDetails.editAdminRadio);
            //TODO change with the latest after merge Muzamil
            var invitation = {"inviteId":$scope.inviteId, "userRoleLookUpId":$scope.userRoleLookUpId.toString(), "emailaddress": $scope.userEmailId, "roles": roles, "task": 'invitation'};
            
            onboardingFactory.editUserRolePost(invitation).then(function (data) {
                growl(data.d.message);
                $scope.closeOverlay();
                $location.path("/settings/useraccountsettings");
            });
        };

        $scope.removeUser = function (inviteId) {
            
            onboardingFactory.removeUserPost(inviteId).then(function (data) {
                growl(data.d.message);
                $location.path("/settings/useraccountsettings");
            });
        };

        $scope.getInvitedUsers = function () {
            onboardingFactory.getInvitedUsersPost().then(function (data) {
                $scope.invitedUsers = data.d.details;
            });
        };
        
        $scope.isActive = function (viewLocation) {
            return viewLocation === $location.path();
        };

//        $scope.setTab = function (type, password, confirmPassword, logoImage) {
//            if (type === 'account') {
//                $scope.userLogoSetClass = '';
//                $scope.userUserSetClass = '';
//                $scope.userAccountSetClass = 'active';
//                $scope.password = password;
//                $scope.confirmPassword = confirmPassword;
//            }
//            if (type === 'logo') {
//                $scope.userAccountSetClass = '';
//                $scope.userUserSetClass = '';
//                $scope.userLogoSetClass = 'active';
//                $scope.logoImage = logoImage;
//            }
//            if (type === 'user') {
//                $scope.userAccountSetClass = '';
//                $scope.userLogoSetClass = '';
//                $scope.userUserSetClass = 'active';
//                $scope.logoImage = logoImage;
//            }
//        };

        $scope.showAddUser = function ()
        {
            $scope.fadeClasses = 'fadeClasses';
            $scope.addUserSettings = true;
            $scope.editUserSettings = false;
        };

        $scope.showEditUser = function (inviteId,userRoleLookUpId,userEmailId)
        {
            $scope.fadeClasses = 'fadeClasses';
            $scope.userRoleLookUpId = userRoleLookUpId;
            $scope.userEmailId = userEmailId;
            $scope.inviteId = inviteId;
            $("#editemail").val(userEmailId);
            $scope.addUserSettings = false;
            $scope.editUserSettings = true;
        };

        $scope.showResendEmailToUser = function (userRoleLookUpId,userEmailId)
        {
            $scope.fadeClasses = 'fadeClasses';
            $scope.userRoleLookUpId = userRoleLookUpId;
            $scope.userEmailId = userEmailId;
            $("#editemail").val(userEmailId);
            $scope.addUserSettings = false;
            $scope.editUserSettings = true;
        };

        $scope.closeInviteUsersPopup = function ()
        {
            $scope.fadeClasses = '';
            $scope.addUserSettings = false;
        };
        
        $scope.userLogoValidation = function (logoImage) {
            if (!logoImage) {
                $scope.logoImage = "";
                $("#uploadlogo").focus();
                return false;
            }
            return true;
        };

        $scope.changeLogo = function (logoImage) {
            var file = logoImage;
//            growl(file);
            if ($scope.userLogoValidation(logoImage))
            {
                settingsFactory.changeLogoPost(file).then(function (data) {
                    growl("Logo uploaded sucessfully");
                    $location.path("signup/choosepalette");
                    $scope.password = password;
                });
            }
        };
        $scope.getColorsFromLogo = function () {
            $scope.activeColorLogo = 'selected_Tab';
            $scope.activeColorPicker = '';
            $scope.activeColorTheme = '';
            $scope.colorFrom = "logo";
            onboardingFactory.colorsForLogoGet().then(function (data) {
                $scope.color = data.d.details;
            });
        };
        $scope.getAllThemes = function () {
            $scope.activeColorTheme = 'selected_Tab';
            $scope.activeColorPicker = '';
            $scope.activeColorLogo = '';
            $scope.colorFrom = "theme";
           
            assetsFactory.allColorThemesGet().then(function (data) {
                $scope.curPage = 0;
                $scope.pageSize = 10;
                $scope.themeColors = data.d.details;
            });
        };
        $scope.getColorFromPicker = function () {
            $scope.activeColorPicker = 'selected_Tab';
            $scope.activeColorTheme = '';
            $scope.activeColorLogo = '';
            $scope.colorFrom = "custom";
            $('#picker').colpick({
                flat: true,
                layout: 'hex',
                onSubmit: function (hsb, hex, rgb, el) {
                    //for haking hex value growl(hex);
                    $('.palette-colorswab-selected').css("background-color", "#" + hex);
                    $('.palette-colorswab-selected').val("#" + hex);

                }
            });
        };
        $scope.selectTheme = function (color1, color2, color3, color4) {
            $("#colorPalete1").css("background-color", color1);
            $("#colorPalete2").css("background-color", color2);
            $("#colorPalete3").css("background-color", color3);
            $("#colorPalete4").css("background-color", color4);
        };
        $scope.showColors = function () {
            $scope.setTab('logo');
            settingsFactory.getColorsURLGet().then(function (data) {
                $scope.user_preferences_colors = data.d.details;
            });
            assetsFactory.allColorThemesGet().then(function (data) {
                $scope.themes = data.d.details;
            });
        };
        $scope.getColorID = function (color) {
            $('.palette-colorswab-selected').css("background-color", color);

        };

        $scope.getLogoColors = function () {
            onboardingFactory.colorsForLogoGet().then(function (data) {
                $scope.color = data.d.details;
            });
        };
        $scope.saveColorPalette = function () {
            var color1 = $("#colorPalete1").css("backgroundColor");
            var color2 = $("#colorPalete2").css("backgroundColor");
            var color3 = $("#colorPalete3").css("backgroundColor");
            var color4 = $("#colorPalete4").css("backgroundColor");
            if ((color1 === "rgba(0, 0, 0, 0)") || (color2 === "rgba(0, 0, 0, 0)") || (color3 === "rgba(0, 0, 0, 0)") || (color4 === "rgba(0, 0, 0, 0)")) {
                growl("Please choose all 4 colors.");
            }
            $("#color1").css("backgroundColor", color1);
            $("#color2").css("backgroundColor", color2);
            $("#color3").css("backgroundColor", color3);
            $("#color4").css("backgroundColor", color4);
//            $scope.showPaletteChangePopUp = false;
            settingsFactory.setColorsPost(color1, color2, color3, color4).then(function (data) {
                growl("Color pallette changed successfully");
                $scope.paletteChangePopUp = false;
//                growl(JSON.stringify(data.d.operationStatus.messages));
            });
        };
        
        $scope.showPaletteChangePopUp = function() {
            $scope.paletteChangePopUp = true;
        };
        
        $scope.closePaletteChangePopUp = function () {
            $scope.paletteChangePopUp = false;
        };
        
        $scope.clearColorPalette = function () {
            var bgColor = "background-color";
            $("#color1").css(bgColor, "");
            $("#color2").css(bgColor, "");
            $("#color3").css(bgColor, "");
            $("#color4").css(bgColor, "");
        };


        $scope.createUserPreferences = function (themecolor1, themecolor2, themecolor3, themecolor4)
        {
            growl(themecolor1);
            growl(themecolor2);
            growl(themecolor3);
            growl(themecolor4);
//            color1 = "rgb(255, 0, 255)";
//            color2 = "rgb(255, 0, 255)";
//            color3 = "rgb(255, 0, 255)";
//            color4 = "rgb(255, 0, 255)";
            settingsFactory.setColorsPost(color1, color2, color3, color4).then(function (data) {
                $scope.status = data;
                growl(data.d.operationStatus.messages[0]);
                $scope.showColors();
            });
        };
        $('#picker').colpick({
            flat: true,
            layout: 'hex',
            onSubmit: function (hsb, hex, rgb, el) {
                //for haking hex value growl(hex);
                $('.palette-colorswab-selected').css("background-color", "#" + hex);
                $('.palette-colorswab-selected').val("#" + hex);

            }
        });

        $scope.color1 = function ()
        {
        };

        $scope.stepsModel = [];

        $scope.imageUpload = function (element) {
            var reader = new FileReader();
            reader.onload = $scope.imageIsLoaded;
            reader.readAsDataURL(element.files[0]);
        };

        $scope.imageIsLoaded = function (e) {
            $scope.$apply(function () {
                $scope.stepsModel = [];
                $scope.stepsModel.push(e.target.result);
            });
        };
        
    }]);

      // This example displays an address form, using the autocomplete feature
      // of the Google Places API to help users fill in the information.

      // This example requires the Places library. Include the libraries=places
      // parameter when you first load the API. For example:
      // <script src="https://maps.googleapis.com/maps/api/js?key=YOUR_API_KEY&libraries=places">
      var placeSearch, autocomplete;
      var componentForm = {
        street_number: 'long_name',
        route: 'long_name',
        locality: 'long_name',
        administrative_area_level_1: 'long_name',
        country: 'long_name',
        postal_code: 'long_name'
      };

      function initAutocomplete() {
        // Create the autocomplete object, restricting the search to geographical
        // location types.
        autocomplete = new google.maps.places.Autocomplete(
            /** @type {!HTMLInputElement} */(document.getElementById('companyAddress')),
            {types: ['geocode']});

        // When the user selects an address from the dropdown, populate the address
        // fields in the form.
        autocomplete.addListener('place_changed', fillInAddress);
      }

      function fillInAddress() {
        // Get the place details from the autocomplete object.
        var place = autocomplete.getPlace();
        for (var component in componentForm) {
            document.getElementById(component).value = '';
            document.getElementById(component).disabled = false;
        }
        // Get each component of the address from the place details
        // and fill the corresponding field on the form.
        for (var i = 0; i < place.address_components.length; i++) {
          var addressType = place.address_components[i].types[0];
          if (componentForm[addressType]) {
            var val = place.address_components[i][componentForm[addressType]];
            document.getElementById(addressType).value = val;
          }
        }
        var inputvalues = $('#country,#postal_code,#administrative_area_level_1,#locality,#route,#street_number');
        inputvalues.trigger('input');
      }

      // Bias the autocomplete object to the user's geographical location,
      // as supplied by the browser's 'navigator.geolocation' object.
      function geolocate() {
        if (navigator.geolocation) {
          navigator.geolocation.getCurrentPosition(function(position) {
            var geolocation = {
              lat: position.coords.latitude,
              lng: position.coords.longitude
            };
            var circle = new google.maps.Circle({
              center: geolocation,
              radius: position.coords.accuracy
            });
            autocomplete.setBounds(circle.getBounds());
          });
        }
      }
