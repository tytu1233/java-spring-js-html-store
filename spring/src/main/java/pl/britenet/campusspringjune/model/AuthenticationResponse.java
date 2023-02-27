package pl.britenet.campusspringjune.model;

public class AuthenticationResponse {

    private String token;
    private int userId;

    public AuthenticationResponse() {
    }

    public AuthenticationResponse(String token, int userId) {
        this.token = token;
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public int getUserId() {
        return userId;
    }
}