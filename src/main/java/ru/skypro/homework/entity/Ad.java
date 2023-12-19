package ru.skypro.homework.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity(name = "ads")
public class Ad {
    public Ad() {
    }
    @Id
    @GeneratedValue
    private Long id;
    private Integer author;
    //    author	integer($int32)
//    id автора объявления

    private String image;
    //    image	string
//    ссылка на картинку объявления

    private Integer pk;
    //    pk	integer($int32)
//    id объявления

    private Integer price;
    //    price	integer($int32)
//    цена объявления

    private String title;
//    title	string
//    заголовок объявления

    public Ad(Long id, Integer author, String image, Integer pk, Integer price, String title) {
        this.id = id;
        this.author = author;
        this.image = image;
        this.pk = pk;
        this.price = price;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ad ad = (Ad) o;
        return Objects.equals(id, ad.id) && Objects.equals(author, ad.author) && Objects.equals(image, ad.image) && Objects.equals(pk, ad.pk) && Objects.equals(price, ad.price) && Objects.equals(title, ad.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, image, pk, price, title);
    }

    @Override
    public String toString() {
        return "Ad{" +
                "id=" + id +
                ", author=" + author +
                ", image='" + image + '\'' +
                ", pk=" + pk +
                ", price=" + price +
                ", title='" + title + '\'' +
                '}';
    }
}
