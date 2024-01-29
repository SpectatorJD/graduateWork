package ru.skypro.homework.dto;

import antlr.collections.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Collection;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ads {

    private Integer count;

    //    count	integer($int32)
//    общее количество объявлений

    private Collection<Ad> results;

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
