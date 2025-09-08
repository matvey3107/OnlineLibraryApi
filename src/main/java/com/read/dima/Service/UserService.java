package com.read.dima.Service;

import com.read.dima.LibDTO.UserDTO;
import com.read.dima.Models.User;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();
    UserDTO getById(Long id);
    void addUser(UserDTO dto);
    UserDTO updateUser(Long id, UserDTO dto);
    void deleteUser(Long Id);
    List<UserDTO> findAllByUsername(String username);
    UserDTO findByName(String username);
    List<UserDTO> SortByReadBooks();

}
