package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrUpdateComment {

 private String Text;
//    text*	string
//    minLength: 8
//    maxLength: 64
//    текст комментария


}
