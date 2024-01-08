package ru.skypro.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "comments")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "ads_id")
    private AdEntity ads;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private UserEntity author;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Column(name = "text")
    private String text;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentEntity that = (CommentEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(ads, that.ads) && Objects.equals(author, that.author) && Objects.equals(createAt, that.createAt) && Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ads, author, createAt, text);
    }

    @Override
    public String toString() {
        return "CommentEntity{" +
                "id=" + id +
                ", ads=" + ads +
                ", author=" + author +
                ", createAt=" + createAt +
                ", text='" + text + '\'' +
                '}';
    }
}
