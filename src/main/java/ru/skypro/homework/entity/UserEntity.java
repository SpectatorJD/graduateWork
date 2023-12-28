package ru.skypro.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;
import java.util.Arrays;
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

    @Column(name = "role")
    private Role role;

    @Column(name = "current_password")
    private Role currentPassword;

    @Column(name = "new_password")
    private Role newPassword;

    @Column(name = "username")
    private Role username;

    @Type(type = "org.hibernate.type.BinaryType")
    @Column(name = "image")
        private Byte[] image;

    public void setPhone(String phone) {
        if (!phone.matches("\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")) {
            throw new IllegalArgumentException("Неверно набран номер");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(emile, that.emile) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(phone, that.phone) && role == that.role && currentPassword == that.currentPassword && newPassword == that.newPassword && username == that.username && Arrays.equals(image, that.image);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, emile, firstName, lastName, phone, role, currentPassword, newPassword, username);
        result = 31 * result + Arrays.hashCode(image);
        return result;
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
                ", currentPassword=" + currentPassword +
                ", newPassword=" + newPassword +
                ", username=" + username +
                ", image=" + Arrays.toString(image) +
                '}';
    }
}
