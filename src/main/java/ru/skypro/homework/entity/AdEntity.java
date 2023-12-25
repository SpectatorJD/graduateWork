package ru.skypro.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;
@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity(name = "ads")
public class AdEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @OneToOne
    @Column(name = "author")
    private UserEntity author;
    //    author	integer($int32)
//    id автора объявления

    @Column(name = "image")
    private String image;
    //    image	string
//    ссылка на картинку объявления

    @OneToOne
    @Column(name = "pk")
    private AdEntity pk;
    //    pk	integer($int32)
//    id объявления

    @Column(name = "price")
    private Integer price;
    //    price	integer($int32)
//    цена объявления

    @Column(name = "title")
    private String title;
//    title	string
//    заголовок объявления
//
    @Column(name = "description")
    private String description;
//    title	string
//    заголовок объявления

//@Column(name = "auther_first_Name") private String authorFirstName;
////    authorFirstName	string
////    имя автора объявления
//
//    private String authorLastName;
////    authorLastName	string
////    фамилия автора объявления
//
//    private String description;
////    description	string
////    описание объявления
//
//    private String email;
////    email	string
////    логин автора объявления

//    private String phone;
////    phone	string
////    телефон автора объявления

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdEntity adEntity = (AdEntity) o;
        return Objects.equals(id, adEntity.id) && Objects.equals(author, adEntity.author) && Objects.equals(image, adEntity.image) && Objects.equals(pk, adEntity.pk) && Objects.equals(price, adEntity.price) && Objects.equals(title, adEntity.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, image, pk, price, title);
    }

    @Override
    public String toString() {
        return "AdEntity{" +
                "id=" + id +
                ", author=" + author +
                ", image='" + image + '\'' +
                ", pk=" + pk +
                ", price=" + price +
                ", title='" + title + '\'' +
                '}';
    }
}
