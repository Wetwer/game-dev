package ch.lebois.troyserver.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Project: Hermann
 * Package: ch.lebois.troyserver.data
 **/

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String passwordSha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswordSha() {
        return passwordSha;
    }

    public void setPasswordSha(String passwordSha) {
        this.passwordSha = passwordSha;
    }
}