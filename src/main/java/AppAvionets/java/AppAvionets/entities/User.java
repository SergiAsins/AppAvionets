package AppAvionets.java.AppAvionets.entities;

import jakarta.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long idUser;

    //role enum
    private String name;
    private String password;
    private Integer age;
    private String address;
    private String mail;
    private String profilePicture;

    public User(String name, String password, Integer age, String address, String mail) {
        this.name = name;
        this.password = password;
        this.age = age;
        this.address = address;
        this.mail = mail;
    }

    //Getters and setters
    public Long getIdUser() {
        return idUser;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public Integer getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public String getMail() {
        return mail;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}
