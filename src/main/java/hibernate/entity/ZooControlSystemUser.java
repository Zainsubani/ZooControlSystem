package hibernate.entity;

import security.Passwords;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ZOO_CONTROL_SYSTEM_USER")
public class ZooControlSystemUser {

    @Id
    @Column(name = "LOGIN", nullable = false)
    private String login;

    @Column(name = "HASH", nullable = false)
    private byte[] hash;

    @Column(name = "SALT", nullable = false)
    private byte[] salt;

    @Column(name = "IS_ADMIN", nullable = false)
    private boolean isAdmin;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public byte[] getHash() {
        return hash;
    }

    public void setHash(byte[] hash) {
        this.hash = hash;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public ZooControlSystemUser(String login, byte[] salt, byte[] hash, boolean isAdmin){
        this.hash = hash;
        this.login = login;
        this.salt = salt;
        this.isAdmin = isAdmin;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public ZooControlSystemUser(String login, String password, boolean isAdmin){
        this.login = login;
        this.salt = Passwords.getNextSalt();
        this.hash = Passwords.hash(password.toCharArray(), this.salt);
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return "{ \"login\" : \"" + login + "\", \"isAdmin\" : " + isAdmin + " }";
    }
}