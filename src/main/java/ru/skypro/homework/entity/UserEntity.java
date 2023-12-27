package ru.skypro.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
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

    @Column(name = "emile")
    private String emile;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "role")
    private Role role;

    @Type(type = "org.hibernate.type.BinaryType")
    @Column(name = "image")
    //    private String image;
        private Byte[] image;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(emile, that.emile) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(phone, that.phone) && role == that.role && Objects.equals(image, that.image);
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
