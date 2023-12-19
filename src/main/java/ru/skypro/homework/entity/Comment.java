package ru.skypro.homework.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Entity(name = "comments")
public class Comment {
    public Comment() {
    }
    @Id
    @GeneratedValue
    private Long id;
    private Integer author;
    //    author	integer($int32)
//    id автора комментария

    private String authorImage;
//    authorImage	string
//    ссылка на аватар автора комментария

    private String authorFirstName;
    //    authorFirstName	string
//    имя создателя комментария

    private Timestamp createAt;
//    createdAt	integer($int64)
//    дата и время создания комментария в миллисекундах с 00:00:00 01.01.1970

    private Integer pk;
    //    pk	integer($int32)
//    id комментария
    private String text;
//    text	string
//    текст комментария

    public Comment(Long id, Integer author, String authorImage, String authorFirstName, Timestamp createAt, Integer pk, String text) {
        this.id = id;
        this.author = author;
        this.authorImage = authorImage;
        this.authorFirstName = authorFirstName;
        this.createAt = createAt;
        this.pk = pk;
        this.text = text;
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

    public String getAuthorImage() {
        return authorImage;
    }

    public void setAuthorImage(String authorImage) {
        this.authorImage = authorImage;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id) && Objects.equals(author, comment.author) && Objects.equals(authorImage, comment.authorImage) && Objects.equals(authorFirstName, comment.authorFirstName) && Objects.equals(createAt, comment.createAt) && Objects.equals(pk, comment.pk) && Objects.equals(text, comment.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, authorImage, authorFirstName, createAt, pk, text);
    }

    @Override
    public String toString() {
        return "Comment{" +
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
