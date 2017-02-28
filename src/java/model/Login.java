package model;

/**
 *
 * @author suhag
 */
public class Login {
    private String id;
    private String salt;
    private String hash;

    public Login(String id, String salt, String hash) {
        this.id = id;
        this.salt = salt;
        this.hash = hash;
    }

    public Login() {
        this.id = null;
        this.salt = null;
        this.hash = null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
    
    
    
}
