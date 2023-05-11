package com.repository.picbox.model;

import lombok.*;

import java.util.HashSet;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ImageDTO {

    private String title;
    private String description;
    public HashSet<Tag> tags;

}
