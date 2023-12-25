package ru.skypro.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.homework.dto.Role;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;
@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
//    id	integer($int32)
//    id пользователя
@Column(name = "emile")
    private String emile;
//    email	string
//    логин пользователя
@Column(name = "first_name")
    private String firstName;
//    firstName	string
//    имя пользователя
@Column(name = "last_name")
    private String lastName;
//    lastName	string
//    фамилия пользователя
@Column(name = "phone")
    private String phone;
//    phone	string
//    телефон пользователя
@Column(name = "role")
    private Role role;
//    role	string
//    роль пользователя
//
//    Enum:
//            [ USER, ADMIN ]
@Column(name = "image")
    private String image;
//    image	string
//    ссылка на аватар пользователя

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity user = (UserEntity) o;
        return Objects.equals(id, user.id) && Objects.equals(emile, user.emile) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(phone, user.phone) && role == user.role && Objects.equals(image, user.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, emile, firstName, lastName, phone, role, image);
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
                ", image='" + image + '\'' +
                '}';
    }
}
