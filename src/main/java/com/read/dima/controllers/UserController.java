package com.read.dima.controllers;


import com.read.dima.LibDTO.BookDTO;
import com.read.dima.LibDTO.UserDTO;
import com.read.dima.Service.UserServicempl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserServicempl userService;
    @Autowired
    public UserController(UserServicempl userService) {
        this.userService = userService;
    }
    @GetMapping("/getAll")
    public List<UserDTO> getAllUsers(){return userService.getAllUsers();}

    @GetMapping("/getById/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable Long id){return ResponseEntity.ok(userService.getById(id));}

    @GetMapping("/getAllByUsername/{name}")
    public List<UserDTO> getAllByUsername(@PathVariable String name){return userService.findAllByUsername(name);}

    @GetMapping("/getByUsername/{username}")
    public ResponseEntity<UserDTO> getByUsername(@PathVariable String username){
        return ResponseEntity.ok(userService.findByName(username));
    }

    @GetMapping("/sortByReadBooks")
    public List<UserDTO> sortByReadBooks(){
        return userService.SortByReadBooks();
    }
    @PostMapping("/add")
    public ResponseEntity<String> addUser(@Valid @RequestBody UserDTO dto){
        userService.addUser(dto);
        return ResponseEntity.ok("saved");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id,@Valid @RequestBody UserDTO dto){
        return ResponseEntity.ok(userService.updateUser(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok("deleted");
    }


}
