package com.example.watervan.Login;

public class LoginResponse {
    public int Status;
    public String StatusMessage;
    public String responseToken;



public LoginResponse(int statuss, String message, String token){
    this.Status=statuss;
    this.StatusMessage=message;
    this.responseToken=token;
}

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        this.Status = status;
    }

    public String getStatusMessage() {
        return StatusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.StatusMessage = statusMessage;
    }

    public String getResponseToken() {
        return responseToken;
    }

    public void setResponseToken(String responseToken) {
        this.responseToken = responseToken;
    }
}
