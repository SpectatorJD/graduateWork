package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewPassword {

    private String currentPassword;

    private String newPassword;

    public void setCurrentPassword(String currentPassword) {
        if (currentPassword.length() <= 8 || currentPassword.length() >= 16) {
            throw new IllegalArgumentException("Новый пароль должен состоять от 8 до 16 символов");
        }
        this.currentPassword = currentPassword;
    }

    public void setNewPassword(String newPassword) {
        if (newPassword.length() <= 8 || newPassword.length() >= 16) {
            throw new IllegalArgumentException("Новый пароль должен состоять от 8 до 16 символов");
        }
        this.newPassword = newPassword;
    }
}
