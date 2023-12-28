package ru.skypro.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity(name = "ads")
public class AdEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "author")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UserEntity author;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk")
    private AdEntity pk;

    @Type(type = "org.hibernate.type.BinaryType")
    @Column(name = "image")
    private Byte[] image;

    @Column(name = "price")
    private Integer price;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "author_first_name")
    private String authorFirstName;

    @Column(name = "author_last_name")
    private String authorLastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    public void setPhone(String phone) {
        if (!phone.matches("\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")) {
            throw new IllegalArgumentException("Неверно набран номер");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdEntity adEntity = (AdEntity) o;
        return Objects.equals(id, adEntity.id) && Objects.equals(author, adEntity.author) && Objects.equals(pk, adEntity.pk) && Arrays.equals(image, adEntity.image) && Objects.equals(price, adEntity.price) && Objects.equals(title, adEntity.title) && Objects.equals(description, adEntity.description) && Objects.equals(authorFirstName, adEntity.authorFirstName) && Objects.equals(authorLastName, adEntity.authorLastName) && Objects.equals(email, adEntity.email) && Objects.equals(phone, adEntity.phone);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, author, pk, price, title, description, authorFirstName, authorLastName, email, phone);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }

    @Override
    public String toString() {
        return "AdEntity{" +
                "id=" + id +
                ", author=" + author +
                ", pk=" + pk +
                ", image=" + Arrays.toString(image) +
                ", price=" + price +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", authorFirstName='" + authorFirstName + '\'' +
                ", authorLastName='" + authorLastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
