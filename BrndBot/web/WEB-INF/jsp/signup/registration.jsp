<%-- 
    Document   : signup
    Created on : Apr 5, 2016, 11:51:46 AM
    Author     : development
--%>
<div class="body-normal">
    <div class="Login_left-pane fleft">
        <div class="Login_left-pane_vert-split"></div>
        <div class="Login_left-pane_vert-split"></div>
    </div>
    <div class="Login_right-pane fright">
        <div class="login-container">
            <img src="images/BB-Hello.svg" class="signUp-logo" style="cursor:pointer;">
            <div class="input_Label pushUp_30">Email</div>
            <input class="input_Field login-fields" id="emailId" placeholder="Email"  ng-blur="isUserUnique()"/>
            <div class="input_Label">Password</div>
            <input class="input_Field login-fields" id="password" type="password" placeholder="Password"/>
            <div class="input_Label">Confirm Password</div>
            <input class="input_Field login-fields" id="rePassword" type="password" placeholder="Confirm Password"/>
            <a href="#/company"><div class="CTA_Button Button--Blue fleft pushUp_30">Sign Up</div></a>
            <div class="sign-up_container"></div>
        </div>
    </div>
    <form hidden class="form-horizontal" id="signform" action="${pageContext.request.contextPath}/login" method="POST">
        <div class="Login_left-pane fleft">
            <div class="Login_left-pane_vert-split"></div>
            <div class="Login_left-pane_vert-split"></div>
        </div>
        <div class="Login_right-pane fright">
            <div class="login-container">
                <img src="images/BB_regular.svg" class="login-logo" style="cursor:pointer;"> 
                <div class="input_Label pushUp_30">Email</div>
                <input name="username"  id="username" class="input_Field login-fields" type="text" required>
                <div class="input_Label">Password</div>
                <input  class="input_Field login-fields" id="userpassword" name="password" type="text" required>
                <div class="forgot_password" style="float:left">
                    <span>Forgot Password</span>
                </div>
                <div class="forgot_password" style="position: relative;left: 1vw;" >
                    <a href="signup/registration"> <span>Sign up</span></a>
                </div>

                <button style="left:-10px;" id="submitButton" type="submit"  class="CTA_Button Button--Blue fleft pushUp_30">Login</button>
                <div class="sign-up_container"></div>
            </div>
        </div>
        <input type="hidden" name="${_csrf.parameterName}"   value="${_csrf.token}" />
    </form>
</div>
