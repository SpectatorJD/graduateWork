package ru.skypro.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;
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

    @OneToMany
    private List<Image> images;

    @OneToMany(mappedBy = "users")
    private List<AdEntity> adEntityList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(emile, that.emile) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(phone, that.phone) && role == that.role && Objects.equals(password, that.password) && Objects.equals(username, that.username) && Objects.equals(images, that.images) && Objects.equals(adEntityList, that.adEntityList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, emile, firstName, lastName, phone, role, password, username, images, adEntityList);
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
                ", images=" + images +
                ", adEntityList=" + adEntityList +
                '}';
    }
}
