package VanquishP2.DTOs;

public class LoginCredentialsDTO {

    private String username;
    private String password;

    public LoginCredentialsDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginCredentialsDTO() {
    }

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

    @Override
    public String toString() {
        return "LoginCredentialsDTO {\n" +
                "username: " + username + ",\n" +
                "password: " + password + ",\n" +
                '}';
    }
}