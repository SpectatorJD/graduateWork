package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class NewPassword {

    private String currentPassword;
//    currentPassword	string
//    minLength: 8
//    maxLength: 16
//    текущий пароль

    private String newPassword;
//    newPassword	string
//    minLength: 8
//    maxLength: 16
//    новый пароль
}
