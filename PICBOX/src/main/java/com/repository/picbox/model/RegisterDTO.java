package com.repository.picbox.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {

    private String username;
    private String fullname;
    private String email;
    private String password;
    private String profession;
    private String rol;
}
