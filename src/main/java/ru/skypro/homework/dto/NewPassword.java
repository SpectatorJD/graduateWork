package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
