package ec.edu.espe.ivstudio.unihelper.model;
/**
 *
 * @author Tomas Tapia, IV Studio, @ESPE
 */
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.util.List;

public class AuthService {
    private final List<UserAccount> users;

    public AuthService(List<UserAccount> users) {
        this.users = users;
    }

    public boolean register(String username, String rawPassword) {
        if (users.stream().anyMatch(u -> u.getUsername().equalsIgnoreCase(username))) {
            return false; // User already exists
        }
        String hashed = hashPassword(rawPassword);
        users.add(new UserAccount(username, hashed));
        return true;
    }

    public boolean authenticate(String username, String rawPassword) {
        String hashed = hashPassword(rawPassword);
        return users.stream()
                .anyMatch(u -> u.getUsername().equalsIgnoreCase(username) && u.getPasswordHash().equals(hashed));
    }

    private String hashPassword(String rawPassword) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(rawPassword.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 not available", e);
        }
    }

    public List<UserAccount> getUsers() {
        return users;
    }
}

