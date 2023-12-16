package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class UpdateUser {

    private String firstName;
//    firstName	string
//    minLength: 3
//    maxLength: 10
//    имя пользователя

    private String lastName;
//    lastName	string
//    minLength: 3
//    maxLength: 10
//    фамилия пользователя

    private String phone;
//    phone	string
//    pattern: \+7\s?\(?\d{3}\)?\s?\d{3}-?\d{2}-?\d{2}
//    телефон пользователя
}
