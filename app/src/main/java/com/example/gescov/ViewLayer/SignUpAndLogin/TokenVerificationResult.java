package com.example.gescov.ViewLayer.SignUpAndLogin;

public class TokenVerificationResult {
    private boolean success;
    private String userId;
    private String token;

    public TokenVerificationResult(boolean result, String userId) {
        success = result;
        this.userId = userId;
    }

    public TokenVerificationResult(String t) {
        token = t;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserToken() {
        return token;
    }
}
