package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Integer id;

    private String emile;

    private String firstName;

    private String lastName;

    private String phone;

    private Role role;

    private Byte[] image;


}
