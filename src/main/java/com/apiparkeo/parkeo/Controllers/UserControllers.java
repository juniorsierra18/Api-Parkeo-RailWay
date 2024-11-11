package com.apiparkeo.parkeo.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apiparkeo.parkeo.Entities.Users;
import com.apiparkeo.parkeo.Repositories.UserRepository;

@RestController
@RequestMapping("/apiparkeo")
@CrossOrigin(origins = "http://localhost:5173/")
public class UserControllers {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<Users> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public Users getUserById(@PathVariable Long id){
        return userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No User found with ID: " + id));
    }

    @PostMapping("/users")
    public Users createUser(@RequestBody Users users){
        return userRepository.save(users);
    }

    @PutMapping("/users/{id}")
    public Users updateUser(@PathVariable Long id, @RequestBody Users detailUser){
        Users users = userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No User found with ID: " + id));

        users.setUser(detailUser.getUser());
        users.setContraseña(detailUser.getContraseña());

        return userRepository.save(users);
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id){
        Users users = userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No User found with ID: " + id));

        userRepository.delete(users);
        return "The User with ID " + id + " was removed.";
    }
}