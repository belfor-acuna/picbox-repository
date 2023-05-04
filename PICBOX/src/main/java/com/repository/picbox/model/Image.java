package com.repository.picbox.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;

@Entity
@Table(name = "image")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_image")
    private Integer id;

    @Column (name = "title_image")
    @NotBlank
    @NotNull
    private String title;

    @Column (name = "description_image")
    private String description;

    @Lob
    @NotNull
    @Column (name = "image")
    private byte[] image;

    @NotEmpty
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "image_tag", joinColumns = @JoinColumn(name = "id_image"), inverseJoinColumns = @JoinColumn(name = "id_tag"))
    private HashSet<Tag> tags;


}
