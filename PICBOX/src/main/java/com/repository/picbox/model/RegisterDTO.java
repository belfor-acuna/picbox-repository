package com.repository.picbox.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
