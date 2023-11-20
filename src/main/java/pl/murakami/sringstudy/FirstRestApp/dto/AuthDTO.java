package pl.murakami.sringstudy.FirstRestApp.dto;

import jakarta.validation.constraints.NotEmpty;

public class AuthDTO {
    @NotEmpty(message = "username is empty")
    private String username;
    @NotEmpty(message = "password is empty")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
