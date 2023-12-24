package ru.skypro.homework.entity;

import ru.skypro.homework.dto.Role;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity(name = "users")
public class User {
    public User() {
    }
    @Id
    @GeneratedValue
    private Integer id;
//    id	integer($int32)
//    id пользователя

    private String emile;
//    email	string
//    логин пользователя

    private String firstName;
//    firstName	string
//    имя пользователя

    private String lastName;
//    lastName	string
//    фамилия пользователя

    private String phone;
//    phone	string
//    телефон пользователя

    private Role role;
//    role	string
//    роль пользователя
//
//    Enum:
//            [ USER, ADMIN ]

    private String image;
//    image	string
//    ссылка на аватар пользователя

    public User(Integer id, String emile, String firstName, String lastName, String phone, Role role, String image) {
        this.id = id;
        this.emile = emile;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.role = role;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmile() {
        return emile;
    }

    public void setEmile(String emile) {
        this.emile = emile;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(emile, user.emile) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(phone, user.phone) && role == user.role && Objects.equals(image, user.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, emile, firstName, lastName, phone, role, image);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", emile='" + emile + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", role=" + role +
                ", image='" + image + '\'' +
                '}';
    }
}
