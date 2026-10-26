package ec.edu.espe.ivstudio.unihelper.model;
/**
 *
 * @author Tomas Tapia, IV Studio, @ESPE
 */
public class UserAccount {
    private String username;
    private String passwordHash;

    public UserAccount() {}

    public UserAccount(String username, String passwordHash) {
        this.username = username;
        this.passwordHash = passwordHash;
    }

    public String getUsername() { return username; }
    public String getPasswordHash() { return passwordHash; }
}

