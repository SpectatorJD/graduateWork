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

}