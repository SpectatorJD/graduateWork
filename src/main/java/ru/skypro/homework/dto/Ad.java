package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class Ad {

    private Integer author;
    //    author	integer($int32)
//    id автора объявления

    private String image;
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
