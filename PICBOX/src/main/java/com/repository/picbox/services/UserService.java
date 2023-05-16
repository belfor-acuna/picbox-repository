package com.repository.picbox.services;

import com.repository.picbox.model.LoginDTO;
import com.repository.picbox.model.RegisterDTO;
import com.repository.picbox.model.User;
import com.repository.picbox.repositories.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private userRepository userRepository;

    public List<User> listUsers() {
        return userRepository.findAll();
    }

    public void registerUser(RegisterDTO registerDTO) throws Exception {
        verifyData(registerDTO);
        verifyUniqueEmail(registerDTO.getEmail());
        User user = new User();
        user.setEmail(registerDTO.getEmail());
        user.setFullname(registerDTO.getFullname());
        user.setPassword(registerDTO.getPassword());
        user.setUsername(registerDTO.getUsername());
        user.setProfession(registerDTO.getProfession());
        user.setAboutYou("Hola! soy "+registerDTO.getFullname()+ ". Si quieres contactarte conmigo enviame un correo a "+registerDTO.getEmail());
        user.setBoxDescription("Aqui verás mis imagenes :)");
        user.setBoxName("Mi primer box");
        user.setPhoneNumber(null);
        user.setProfilePicture(null);
        user.setRol(registerDTO.getRol());
        userRepository.save(user);
    }

    public String loginUser(LoginDTO loginDTO) throws Exception{
        User user = userRepository.findByEmail(loginDTO.getEmail()).orElseThrow(()-> new Exception("Usuario no existe"));
        if(!user.getPassword().equals(loginDTO.getPassword())) throw new Exception("Contraseña Incorrecta");
        return user.getRol();
    }

    public Long getIdByEmail(LoginDTO loginDTO) throws Exception {

        User user = userRepository.findByEmail(loginDTO.getEmail()).orElseThrow(()-> new Exception("Usuario no existe"));
        return user.getId();

    }

    public User getUserById(Long id){
        return userRepository.findById(id).get();
    }

    public User updateUser(User user) throws Exception{
        Optional<User> verifyEmail = userRepository.findByEmail(user.getEmail());
        Optional<User> verifyUsername = userRepository.findByUsername(user.getUsername());
        if(verifyEmail.isPresent() && verifyEmail.get().getId()!=user.getId()) throw new Exception("Ya existe una cuenta vinculada a ese correo");
        if (verifyUsername.isPresent() && verifyUsername.get().getId()!=user.getId()) throw new Exception("Ya existe una cuenta vinculada a ese username");
        return userRepository.save(user);
    }

    public void deleteUser(Long id){
        userRepository.delete(getUserById(id));
    }

    private void verifyData(RegisterDTO registerDTO) throws Exception{
        if(registerDTO.getUsername()==null) throw new Exception("Nombre de usuario no ingresado");
        if(registerDTO.getEmail()==null) throw new Exception("Email no ingresado");
        if(registerDTO.getFullname()==null) throw new Exception("Nombre no ingresado");
        if(registerDTO.getPassword()==null) throw new Exception("Constraseña no ingresada");

    }

    private void verifyUniqueEmail(String email) throws Exception{
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()) throw new Exception("Ya existe una cuenta vinculada a ese correo");
    }
}
