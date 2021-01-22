package data;

public class ACUser {
    private final String username;
    private final String password;

    public ACUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
