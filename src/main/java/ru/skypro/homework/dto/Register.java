package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Register {

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String phone;

    private Role role;

    public void setUsername(String username) {
        if (lastName.length() <= 4 || lastName.length() >= 32) {
            throw new IllegalArgumentException("Имя пользователя должен состоять от 4 до 32 символов");
        }
        this.username = username;
    }

    public void setPassword(String password) {
        if (password.length() <= 8 || password.length() >= 16) {
            throw new IllegalArgumentException("Текущий пароль должен состоять от 8 до 16 символов");
        }
        this.password = password;
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

    public void setPhone(String phone) {
        if (!phone.matches("\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")) {
            throw new IllegalArgumentException("Неверно набран номер");
        }
        this.phone = phone;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

