package se.lexicon.g40_jpa_booking.model.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static se.lexicon.g40_jpa_booking.model.constants.EntityConstants.GENERATOR;
import static se.lexicon.g40_jpa_booking.model.constants.EntityConstants.UUID_GENERATOR;

@Entity
public class AppUser {

    @Id
    @GeneratedValue(generator = GENERATOR)
    @GenericGenerator(name = GENERATOR, strategy = UUID_GENERATOR)
    private String id;

    private String username;
    private String password;

    @ManyToMany(
            cascade = {CascadeType.REFRESH,CascadeType.DETACH},
            fetch = FetchType.LAZY,
            mappedBy = "appUsers"
    )
    private Set<AppRole> roles;

    public AppUser(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public AppUser() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Set<AppRole> getRoles() {
        if (roles == null) roles = new HashSet<>();
        return roles;
    }

    public void setRoles(Set<AppRole> roles) {
        if (roles == null) roles = new HashSet<>();

        if (roles.isEmpty()){
            if (this.roles != null){
                this.roles.forEach( appRole -> appRole.getAppUsers().remove(this));
            }
        }else {
            roles.forEach(appRole -> appRole.getAppUsers().add(this));
        }

        this.roles = roles;
    }

    public void addRole(AppRole appRole){
        if (appRole == null) throw new IllegalArgumentException("appRole was null");
        if (this.roles == null) this.roles = new HashSet<>();

        this.roles.add(appRole);
        appRole.getAppUsers().add(this);
    }

    public void remove(AppRole appRole){
        if (appRole == null) throw new IllegalArgumentException("appRole was null");
        if (this.roles == null) this.roles = new HashSet<>();

        this.roles.remove(appRole);
        appRole.getAppUsers().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return Objects.equals(getId(), appUser.getId()) && Objects.equals(getUsername(), appUser.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername());
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
