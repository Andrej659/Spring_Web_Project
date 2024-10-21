package hr.unizd.web.springbootAMapp.passwordCheck;

import jakarta.validation.constraints.NotEmpty;

public class PasswordCheckForm {

    @NotEmpty(message = "Password is not allowed to be empty")
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
