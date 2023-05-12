package com.repository.picbox.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column( name = "username", nullable = false, length = 20, unique = true)
    private String username;

    @Column( name = "fullname", nullable = false, length = 80)
    private String fullname;

    @Column( name = "password", nullable = false, length = 20)
    private String password;

    @Column( name = "email", nullable = false, length = 80, unique = true)
    private String email;

    @Lob
    @Column( name = "profilePicture", nullable = true)
    private byte[] profilePicture;

    @Column( name = "aboutYou", length = 200, nullable = true)
    private String aboutYou;

    @Column( name = "profession", nullable = false)
    private String profession;

    @Column( name = "phoneNumber", length = 12, unique = true, nullable = true)
    private String phoneNumber;

    @Column( name = "boxName",length = 25, unique = true, nullable = true)
    private String boxName;

    @Column( name = "boxDescription",length = 200, nullable = true)
    private String boxDescription;

    @Column( name = "rol", nullable = false)
    private String rol;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images = new ArrayList<>();

}