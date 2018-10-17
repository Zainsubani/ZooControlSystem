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
    @Column(name = "LOGIN")
    private String login;

    @Column(name = "HASH")
    private byte[] hash;

    @Column(name = "SALT")
    private byte[] salt;

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

    public ZooControlSystemUser(String login, byte[] salt, byte[] hash){
        this.hash = hash;
        this.login = login;
        this.salt = salt;
    }

    public ZooControlSystemUser(String login, String password){
        this.login = login;
        this.salt = Passwords.getNextSalt();
        this.hash = Passwords.hash(password.toCharArray(), this.salt);
    }
}