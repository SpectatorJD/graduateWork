package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    private Integer author;

    private String authorImage;

    private String authorFirstName;

    private LocalDateTime createdAt;

    private Integer pk;

    private String text;

}