package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
