package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    private Integer author;

    private String authorImage;

    private String authorFirstName;

    private Timestamp createAt;

    private Integer pk;

    private String text;

}