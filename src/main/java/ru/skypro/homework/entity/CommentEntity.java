package ru.skypro.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@AllArgsConstructor
@NoArgsConstructor

@Entity(name = "comments")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

//    @OneToOne
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "commentId")
//    private Integer commentId;

    @ManyToOne()
    @JoinColumn(name = "ads_id")
    private AdEntity adEntity;

    @ManyToOne
//    @Column(name = "author")
    @JoinColumn(name = "users_id")
    private UserEntity author;

//    @Column(name = "author_image")
//    private String authorImage;
//
//    @Column(name = "author_first_name")
//    private String authorFirstName;

    @Column(name = "create_at")
    private LocalDateTime createAt;

//    @ManyToOne
//    @Column(name = "pk")
////    @JoinColumn(name = "pk")
//    private Integer Id;

    @Column(name = "text")
    private String text;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAdEntity(AdEntity adEntity) {
        this.adEntity = adEntity;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public void setText(String text) {
//        if (text.length() <= 8 || text.length() >= 64) {
//            throw new IllegalArgumentException("Текст должен состоять из 8-64 символов");
//        }
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentEntity that = (CommentEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(adEntity, that.adEntity) && Objects.equals(author, that.author) && Objects.equals(createAt, that.createAt) && Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, adEntity, author, createAt, text);
    }

    @Override
    public String toString() {
        return "CommentEntity{" +
                "id=" + id +
                ", adEntity=" + adEntity +
                ", author=" + author +
                ", createAt=" + createAt +
                ", text='" + text + '\'' +
                '}';
    }
}
