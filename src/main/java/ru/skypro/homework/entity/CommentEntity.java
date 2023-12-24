package ru.skypro.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity(name = "comments")
public class CommentEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @OneToOne
    @Column(name = "author")
    private UserEntity author;
    //    author	integer($int32)
//    id автора комментария

    @Column(name = "author_image")
    private String authorImage;
//    authorImage	string
//    ссылка на аватар автора комментария

@Column(name = "author_first_name")
    private String authorFirstName;
    //    authorFirstName	string
//    имя создателя комментария

@Column(name = "create_at")
    private Timestamp createAt;
//    createdAt	integer($int64)
//    дата и время создания комментария в миллисекундах с 00:00:00 01.01.1970

    @OneToOne
@Column(name = "pk")
    private CommentEntity pk;
    //    pk	integer($int32)
//    id комментария

    @Column(name = "text")
    private String text;
    //    text	string
//    текст комментария

    @OneToOne()
    @Column(name = "adId")
    private AdEntity adEntity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentEntity comment = (CommentEntity) o;
        return Objects.equals(id, comment.id) && Objects.equals(author, comment.author) && Objects.equals(authorImage, comment.authorImage) && Objects.equals(authorFirstName, comment.authorFirstName) && Objects.equals(createAt, comment.createAt) && Objects.equals(pk, comment.pk) && Objects.equals(text, comment.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, authorImage, authorFirstName, createAt, pk, text);
    }

    @Override
    public String toString() {
        return "CommentEntity{" +
                "id=" + id +
                ", author=" + author +
                ", authorImage='" + authorImage + '\'' +
                ", authorFirstName='" + authorFirstName + '\'' +
                ", createAt=" + createAt +
                ", pk=" + pk +
                ", text='" + text + '\'' +
                '}';
    }
}
