package ru.skypro.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "emile")
    private String emile;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone")
    private String phone;

@Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Column(name = "password")
    private String password;

    @Column(name = "username")
    private String username;

    @Column(name = "image")
    private String image;

    @OneToMany(mappedBy = "users")
    private List<AdEntity> adEntityList;

//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public void setEmile(String emile) {
//        this.emile = emile;
//    }
//
//    public void setFirstName(String firstName) {
//        if (firstName.length() <= 3 || firstName.length() >= 10) {
//            throw new IllegalArgumentException("Имя должен состоять от 3 до 10 символов");
//        }
//        this.firstName = firstName;
//    }
//
//    public void setLastName(String lastName) {
//        if (lastName.length() <= 3 || lastName.length() >= 10) {
//            throw new IllegalArgumentException("Фамилия должен состоять от 3 до 10 символов");
//        }
//        this.lastName = lastName;
//    }
//
//    public void setRole(Role role) {
//        this.role = role;
//    }
//
//    public void setCurrentPassword(Role currentPassword) {
//        if (lastName.length() <= 8 || lastName.length() >= 16) {
//            throw new IllegalArgumentException("Текущий пароль должен состоять от 8 до 16 символов");
//        }
//        this.currentPassword = currentPassword;
//    }
//
//    public void setNewPassword(Role newPassword) {
//        if (lastName.length() <= 8 || lastName.length() >= 16) {
//            throw new IllegalArgumentException("Новый пароль должен состоять от 8 до 16 символов");
//        }
//        this.newPassword = newPassword;
//    }
//
//    public void setUsername(Role username) {
//        if (lastName.length() <= 4 || lastName.length() >= 32) {
//            throw new IllegalArgumentException("Имя пользователя должен состоять от 4 до 32 символов");
//        }
//        this.username = username;
//    }
//
//    public void setImage(String image) {
//        this.image = image;
//    }
//
//    public void setPhone(String phone) {
//        if (!phone.matches("\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")) {
//            throw new IllegalArgumentException("Неверно набран номер");
//        }
//        this.phone = phone;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(emile, that.emile) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(phone, that.phone) && role == that.role && Objects.equals(password, that.password) && Objects.equals(username, that.username) && Objects.equals(image, that.image) && Objects.equals(adEntityList, that.adEntityList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, emile, firstName, lastName, phone, role, password, username, image, adEntityList);
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", emile='" + emile + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", role=" + role +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", image='" + image + '\'' +
                ", adEntityList=" + adEntityList +
                '}';
    }
}
