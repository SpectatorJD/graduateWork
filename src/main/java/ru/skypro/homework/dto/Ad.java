package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ad {

    private Integer author;
    //    author	integer($int32)
//    id автора объявления

//    private String image;
    private Byte[] image;
    //    image	string
//    ссылка на картинку объявления

    private Integer pk;
    //    pk	integer($int32)
//    id объявления

    private Integer price;
    //    price	integer($int32)
//    цена объявления

    private String title;
//    title	string
//    заголовок объявления
}
