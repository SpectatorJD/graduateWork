package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class ExtendedAd {

    private Integer pk;
//    pk	integer($int32)
//    id объявления

    private String authorFirstName;
//    authorFirstName	string
//    имя автора объявления

    private String authorLastName;
//    authorLastName	string
//    фамилия автора объявления

    private String description;
//    description	string
//    описание объявления

    private String email;
//    email	string
//    логин автора объявления

    private String image;
//    image	string
//    ссылка на картинку объявления

    private String phone;
//    phone	string
//    телефон автора объявления

    private Integer price;
//    price	integer($int32)
//    цена объявления

    private String title;
//    title	string
//    заголовок объявления
}
