package DatabaseModel;

public class testBCrypt {
    public static void main(String[] args) {
        String password = "test";
        String candidate = "test";
        // Hash a password for the first time
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());

        // Check that an unencrypted password matches one that has
        // previously been hashed
        if (BCrypt.checkpw(candidate, hashed)) {
            System.out.println("It matches");
        }
        else {
            System.out.println("It does not match");
        }
    }
}