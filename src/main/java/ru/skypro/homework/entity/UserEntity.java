package ru.skypro.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Getter
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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setEmile(String emile) {
        this.emile = emile;
    }

    public void setFirstName(String firstName) {
        if (firstName.length() <= 3 || firstName.length() >= 10) {
            throw new IllegalArgumentException("Имя должен состоять от 3 до 10 символов");
        }
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        if (lastName.length() <= 3 || lastName.length() >= 10) {
            throw new IllegalArgumentException("Фамилия должен состоять от 3 до 10 символов");
        }
        this.lastName = lastName;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setCurrentPassword(Role currentPassword) {
        if (lastName.length() <= 8 || lastName.length() >= 16) {
            throw new IllegalArgumentException("Текущий пароль должен состоять от 8 до 16 символов");
        }
        this.currentPassword = currentPassword;
    }

    public void setNewPassword(Role newPassword) {
        if (lastName.length() <= 8 || lastName.length() >= 16) {
            throw new IllegalArgumentException("Новый пароль должен состоять от 8 до 16 символов");
        }
        this.newPassword = newPassword;
    }

    public void setUsername(Role username) {
        if (lastName.length() <= 4 || lastName.length() >= 32) {
            throw new IllegalArgumentException("Имя пользователя должен состоять от 4 до 32 символов");
        }
        this.username = username;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    public void setPhone(String phone) {
        if (!phone.matches("\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")) {
            throw new IllegalArgumentException("Неверно набран номер");
        }
        this.phone = phone;
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
