package com.repository.picbox;

import com.repository.picbox.model.User;
import com.repository.picbox.repositories.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PicboxApplication{

    public static void main(String[] args) {
        SpringApplication.run(PicboxApplication.class, args);
    }
}
