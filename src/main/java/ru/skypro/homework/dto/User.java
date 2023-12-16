package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class User {

    private Integer id;
//    id	integer($int32)
//    id пользователя

    private String emile;
//    email	string
//    логин пользователя

    private String firstName;
//    firstName	string
//    имя пользователя

    private String lastName;
//    lastName	string
//    фамилия пользователя

    private String phone;
//    phone	string
//    телефон пользователя

    private Role role;
//    role	string
//    роль пользователя
//
//    Enum:
//            [ USER, ADMIN ]

    private String image;
//    image	string
//    ссылка на аватар пользователя


}
