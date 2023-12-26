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
    private Integer id;

    @GeneratedValue
    @Column(name = "commentId")
    private Integer commentId;

    @OneToOne
    @Column(name = "author")
    private UserEntity author;

    @Column(name = "author_image")
    private String authorImage;

@Column(name = "author_first_name")
    private String authorFirstName;

@Column(name = "create_at")
    private Timestamp createAt;

    @OneToOne
@Column(name = "pk")
    private CommentEntity pk;


    @Column(name = "text")
    private String text;


    @OneToOne()
    @Column(name = "adId")
    private AdEntity adEntity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentEntity that = (CommentEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(commentId, that.commentId) && Objects.equals(author, that.author) && Objects.equals(authorImage, that.authorImage) && Objects.equals(authorFirstName, that.authorFirstName) && Objects.equals(createAt, that.createAt) && Objects.equals(pk, that.pk) && Objects.equals(text, that.text) && Objects.equals(adEntity, that.adEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, commentId, author, authorImage, authorFirstName, createAt, pk, text, adEntity);
    }

    @Override
    public String toString() {
        return "CommentEntity{" +
                "id=" + id +
                ", commentId=" + commentId +
                ", author=" + author +
                ", authorImage='" + authorImage + '\'' +
                ", authorFirstName='" + authorFirstName + '\'' +
                ", createAt=" + createAt +
                ", pk=" + pk +
                ", text='" + text + '\'' +
                ", adEntity=" + adEntity +
                '}';
    }
}
