package se.lexicon.g40_jpa_booking.model.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static se.lexicon.g40_jpa_booking.model.constants.EntityConstants.GENERATOR;
import static se.lexicon.g40_jpa_booking.model.constants.EntityConstants.UUID_GENERATOR;

@Entity
public class AppRole {

    @Id
    @GeneratedValue(generator = GENERATOR)
    @GenericGenerator(name = GENERATOR, strategy = UUID_GENERATOR)
    private String id;

    private String Role;

    @ManyToMany(
            cascade = {CascadeType.REFRESH,CascadeType.DETACH},
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "role_app_user",
            joinColumns = @JoinColumn(name = "fk_app_role_id", table ="role_app_user"),
            inverseJoinColumns = @JoinColumn(name = "fk_app_user_id", table = "role_app_user")
    )
    private Set<AppUser> appUsers;

    public AppRole(String id, String role) {
        this.id = id;
        Role = role;
    }

    public AppRole() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public Set<AppUser> getAppUsers() {
        if (appUsers == null) appUsers = new HashSet<>();
        return appUsers;
    }

    public void setAppUsers(Set<AppUser> appUsers) {
        if (appUsers == null) appUsers = new HashSet<>();

        if (appUsers.isEmpty()){
            if (this.appUsers != null){
                this.appUsers.forEach(appUser -> appUser.getRoles().remove(this));
            }
        }else{
            appUsers.forEach(appUser -> appUser.getRoles().add(this));
        }
        this.appUsers = appUsers;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppRole appRole = (AppRole) o;
        return Objects.equals(getId(), appRole.getId()) && Objects.equals(getRole(), appRole.getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRole());
    }

    @Override
    public String toString() {
        return "AppRole{" +
                "id='" + id + '\'' +
                ", Role='" + Role + '\'' +
                '}';
    }
}
