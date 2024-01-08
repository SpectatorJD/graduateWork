package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrUpdateAd {

    private String title;

    private Integer price;

    private String description;

    public void setTitle(String title) {
        if (title.length() < 4 || title.length() > 32) {
            throw new IllegalArgumentException("Тема должен состоять от 8 до 64 символов");
        }
        this.title = title;
    }

    public void setPrice(Integer price) {
        if (price.longValue() < 0 || price.longValue() > 10000000) {
            throw new IllegalArgumentException("Сумма должен состоять от 0 до 10_000_000 цифр");
        }
        this.price = price;
    }

    public void setDescription(String description) {
        if (description.length() < 8 || description.length() > 64) {
            throw new IllegalArgumentException("Описание должен состоять от 8 до 64 символов");
        }
        this.description = description;
    }
}
