<%-- 
    Document   : newjsp
    Created on : 1 Jun, 2016, 6:02:21 PM
    Author     : sandeep
--%>
<form class="form-horizontal" id="signform"  method="POST">
    <div class="Login_left-pane fleft" >
        <div class="Login_left-pane_vert-split"></div>
        <div class="Login_left-pane_vert-split"></div>
    </div>
    <div class="Login_right-pane fright">
        <div class="login-container">
            <div class="alert-div">The email or password that you entered don't match.</div>
            <img src="images/BB_regular.svg" class="login-logo" style="cursor:pointer;"> 
            <div class="input_Label pushUp_30">Email</div>

            <input name="username"  class="input_Field login-fields" type="email" required>
            <div class="input_Label">Password</div>
            <input  class="input_Field login-fields" name="password" type="password" required>
            <div class="forgot_password" style="float:left">
                <a href="resetpassword"><span>Forgot Password</span></a>
            </div>
            <div class="forgot_password" style="" >
                <a href="#/register"> <span style="position: relative;left: 10px;">Sign up</span></a>
            </div>

            <button style="left:-10px;" type="submit"  class="CTA_Button Button--Blue fleft pushUp_30">Login</button>
            <div class="sign-up_container"></div>
        </div>
    </div>
    <input type="hidden" />
</form>
