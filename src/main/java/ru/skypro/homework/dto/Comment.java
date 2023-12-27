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
    //    author	integer($int32)
//    id автора комментария

    private String authorImage;
//    authorImage	string
//    ссылка на аватар автора комментария

    private String authorFirstName;
    //    authorFirstName	string
//    имя создателя комментария

    private Timestamp createAt;
//    createdAt	integer($int64)
//    дата и время создания комментария в миллисекундах с 00:00:00 01.01.1970

    private Integer pk;
    //    pk	integer($int32)
//    id комментария
    private String text;
//    text	string
//    текст комментария
}
