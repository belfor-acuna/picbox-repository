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
import java.util.Set;

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
    private String title;

    @Column (name = "description_image")
    private String description;

    @Lob
    @Column (name = "image")
    private byte[] image;


    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinTable(name = "image_tag",
            joinColumns = @JoinColumn(name = "id_image"),
            inverseJoinColumns = @JoinColumn(name = "id_tag"))
    private Set<Tag> tags = new HashSet<>();


}