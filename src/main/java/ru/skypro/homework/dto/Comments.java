package ru.skypro.homework.dto;

import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
public class Comments {

  private Integer count;
//    count	integer($int32)
//    общее количество комментариев

  private Comment[] results;
//    results	[Comment{Jump to definition
//        author	[...]
//        authorImage	[...]
//        authorFirstName	[...]
//        createdAt	[...]
//        pk	[...]
//        text	[...]
//
//    }]
}
