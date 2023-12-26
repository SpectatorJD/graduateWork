package ru.skypro.homework.dto;

import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Collection;

@Data
public class Comments {

  private Integer count;
//    count	integer($int32)
//    общее количество комментариев

  private Collection<Comment> results;
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
