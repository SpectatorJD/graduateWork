package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class CreateOrUpdateAd {

   private String title;
   //    title	string
//    minLength: 4
//    maxLength: 32
//    заголовок объявления

   private Integer price;
//    price	integer($int32)
//    minimum: 0
//    maximum: 10000000
//    цена объявления

   private String description;
//    description	string
//    minLength: 8
//    maxLength: 64
//    описание объявления
}
