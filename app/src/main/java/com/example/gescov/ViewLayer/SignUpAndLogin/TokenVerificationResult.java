package com.example.gescov.ViewLayer.SignUpAndLogin;

public class TokenVerificationResult {
    private boolean error;
    private String userId;
    private String token;

    public TokenVerificationResult(boolean result, String userId) {
        error = result;
        this.userId = userId;
    }

    public TokenVerificationResult(String t) {
        token = t;
    }

    public Boolean isError() {
        return error;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserToken() {
        return token;
    }
}
