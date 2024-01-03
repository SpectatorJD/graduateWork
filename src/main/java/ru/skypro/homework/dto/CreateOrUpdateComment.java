package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrUpdateComment {

    private String Text;

    public void setText(String text) {
        if (text.length() <= 8 || text.length() >= 64) {
            throw new IllegalArgumentException("Текст должен состоять из 8-64 символов");
        }
        Text = text;
    }
}
