package com.realworld.feature;

import lombok.Data;

@Data
public class TestToken {
    private String userId;
    private String grantType;
    private String accessToken;
    private String refreshToken;
    private String userEmail;
    private String nickname;

    // Getters and setters

    public static TestToken fromString(String tokenString) {
        TestToken token = new TestToken();
        tokenString = tokenString.replace("Token(", "").replace(")", "");
        String[] fields = tokenString.split(", ");
        for (String field : fields) {
            String[] keyValue = field.split("=");
            switch (keyValue[0]) {
                case "userId":
                    token.setUserId(keyValue[1]);
                    break;
                case "grantType":
                    token.setGrantType(keyValue[1]);
                    break;
                case "accessToken":
                    token.setAccessToken(keyValue[1]);
                    break;
                case "refreshToken":
                    token.setRefreshToken(keyValue[1]);
                    break;
                case "userEmail":
                    token.setUserEmail(keyValue[1]);
                    break;
                case "nickname":
                    token.setNickname(keyValue[1]);
                    break;
            }
        }
        return token;
    }
}
