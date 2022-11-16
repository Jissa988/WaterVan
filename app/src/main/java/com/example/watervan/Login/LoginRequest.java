package com.example.watervan.Login;

public class LoginRequest {
    String UserName;
    String Password;
    String DeviceType;

    public String getDeviceType() {
        return DeviceType;
    }

    public void setDeviceType(String deviceType) {
        DeviceType = deviceType;
    }



    public LoginResponse getLoginResponse() {
        return loginResponse;
    }

    public void setLoginResponse(LoginResponse loginResponse) {
        this.loginResponse = loginResponse;
    }

    LoginResponse loginResponse;


//    public LoginRequest(String username,String pass) {
//        this.UserName = username;
//        this.Password = pass;
//    }

    public String getUsername() {
        return UserName;
    }
//
    public void setUsername(String username) {
        this.UserName = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }
}
