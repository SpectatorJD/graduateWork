package ru.skypro.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
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

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commentId")
    private Integer commentId;

    @OneToOne()
    @Column(name = "adId")
    private AdEntity adEntity;

    @OneToOne
    @Column(name = "author")
    private AdEntity author;

    @Column(name = "author_image")
    private String authorImage;

    @Column(name = "author_first_name")
    private String authorFirstName;

    @Column(name = "create_at")
    private Timestamp createAt;

    @OneToOne
    @Column(name = "pk")
    private AdEntity pk;

    @Column(name = "text")
    private String text;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public void setAdEntity(AdEntity adEntity) {
        this.adEntity = adEntity;
    }

    public void setAuthor(AdEntity author) {
        this.author = author;
    }

    public void setAuthorImage(String authorImage) {
        this.authorImage = authorImage;
    }

    public void setAuthorFirstName(String authorFirstName) {
        if (authorFirstName.length() <= 3 || authorFirstName.length() >= 10) {
            throw new IllegalArgumentException("Имя должен состоять от 3 до 10 символов");
        }
        this.authorFirstName = authorFirstName;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    public void setPk(AdEntity pk) {
        this.pk = pk;
    }

    public void setText(String text) {
        if (text.length() <= 8 || text.length() >= 64) {
            throw new IllegalArgumentException("Текст должен состоять из 8-64 символов");
        }
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentEntity that = (CommentEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(commentId, that.commentId) && Objects.equals(adEntity, that.adEntity) && Objects.equals(author, that.author) && Objects.equals(authorImage, that.authorImage) && Objects.equals(authorFirstName, that.authorFirstName) && Objects.equals(createAt, that.createAt) && Objects.equals(pk, that.pk) && Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, commentId, adEntity, author, authorImage, authorFirstName, createAt, pk, text);
    }

    @Override
    public String toString() {
        return "CommentEntity{" +
                "id=" + id +
                ", commentId=" + commentId +
                ", adEntity=" + adEntity +
                ", author=" + author +
                ", authorImage='" + authorImage + '\'' +
                ", authorFirstName='" + authorFirstName + '\'' +
                ", createAt=" + createAt +
                ", pk=" + pk +
                ", text='" + text + '\'' +
                '}';
    }
}
