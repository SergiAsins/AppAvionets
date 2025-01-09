package AppAvionets.java.AppAvionets.entities;

import jakarta.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long idUser;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String name;
    private String password;
    private Integer age;
    private String address;
    private String mail;
    private String profilePicture;

    public User(Role role, String name, String password, Integer age, String address, String mail) {
        this.role = role;
        this.name = name;
        this.password = password;
        this.age = age;
        this.address = address;
        this.mail = mail;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}
