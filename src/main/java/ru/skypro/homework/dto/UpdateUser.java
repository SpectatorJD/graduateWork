package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUser {

    private String firstName;

    private String lastName;

    private String phone;

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
}
