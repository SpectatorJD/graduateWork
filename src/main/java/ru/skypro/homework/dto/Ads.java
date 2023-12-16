package ru.skypro.homework.dto;

import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
public class Ads {

    private Integer count;
    //    count	integer($int32)
//    общее количество объявлений

    @ManyToOne
    @JoinColumn(name = "ad_id")
    private Ad results;
//    results	[Ad{Jump to definition
//        author	integer($int32)
//                id автора объявления

//        image	string
//        ссылка на картинку объявления

//        pk	integer($int32)
//                id объявления

//        price	integer($int32)
//                цена объявления

//        title	string
//        заголовок объявления
//    }]
}
