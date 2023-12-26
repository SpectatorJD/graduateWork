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
    private Integer id;

    @OneToOne
    @Column(name = "author")
    private UserEntity author;

    @Column(name = "image")
    private String image;

    @OneToOne
    @Column(name = "pk")
    private AdEntity pk;

    @Column(name = "price")
    private Integer price;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdEntity adEntity = (AdEntity) o;
        return Objects.equals(id, adEntity.id) && Objects.equals(author, adEntity.author) && Objects.equals(image, adEntity.image) && Objects.equals(pk, adEntity.pk) && Objects.equals(price, adEntity.price) && Objects.equals(title, adEntity.title) && Objects.equals(description, adEntity.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, image, pk, price, title, description);
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
                ", description='" + description + '\'' +
                '}';
    }
}
