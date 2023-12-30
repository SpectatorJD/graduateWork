package ru.skypro.homework.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {

    @Id
    @GeneratedValue
    private Long id;

    @JsonIgnore
    private String filePath;

    private long fileSize;

    private String mediaType;

    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    @JsonIgnore
    private byte[] data;

    @OneToOne
    private AdEntity adEntity;

    private String image;
}
