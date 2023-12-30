package ru.skypro.homework.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity(name = "image")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {

    @Id
    @GeneratedValue
    @JsonIgnore
    @Column(name = "id")
    private Integer id;

    @JsonIgnore
    @Column(name = "path")
    private String filePath;

    @JsonIgnore
    @Column(name = "size")
    private long fileSize;

    @Column(name = "media_type")
    private String mediaType;

    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    @JsonIgnore
    @Column(name = "data")
    private byte[] data;

    @JsonIgnore
    @OneToOne
    @Column(name = "ad")
    private AdEntity ad;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return fileSize == image.fileSize && Objects.equals(id, image.id) && Objects.equals(filePath, image.filePath) && Objects.equals(mediaType, image.mediaType) && Arrays.equals(data, image.data) && Objects.equals(ad, image.ad) && Objects.equals(user, image.user);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, filePath, fileSize, mediaType, ad, user);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", filePath='" + filePath + '\'' +
                ", fileSize=" + fileSize +
                ", mediaType='" + mediaType + '\'' +
                ", data=" + Arrays.toString(data) +
                ", ad=" + ad +
                ", user=" + user +
                '}';
    }

    @JsonIgnore
    @OneToOne
    @Column(name = "user")
    private UserEntity user;
}
