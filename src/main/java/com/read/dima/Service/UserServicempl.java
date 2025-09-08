package com.read.dima.Service;

import com.read.dima.LibDTO.UserDTO;
import com.read.dima.Mappers.UserMapper;
import com.read.dima.Models.User;
import com.read.dima.Repo.UserRepo;
import com.read.dima.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServicempl implements UserService{

    private final UserRepo userRepo;
    private final UserMapper userMapper;

    @Autowired
    public UserServicempl(UserRepo userRepo, UserMapper userMapper) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepo.findAll().stream().map(userMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO getById(Long id) {
        User user = userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Cannot find id " + id));
        return userMapper.toDTO(user);
    }

    @Override
    public void addUser(UserDTO dto) {
        User user = userMapper.toEntity(dto);
        userRepo.save(user);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO dto) {
        User updatesUser = userRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Cannot find user by id" + id));

        updatesUser.setUsername(dto.getUsername());
        updatesUser.setPassword(dto.getPassword());
        updatesUser.setDate(dto.getDate());
        updatesUser.setCountReadBooks(dto.getCountReadBooks());
        userRepo.save(updatesUser);
        return userMapper.toDTO(updatesUser);
    }

    @Override
    public void deleteUser(Long Id) {
        User user = userRepo.findById(Id).orElseThrow(()->new ResourceNotFoundException("Cannot find user by id" + Id));
        userRepo.deleteById(Id);
    }

    @Override
    public List<UserDTO> findAllByUsername(String username) {
        return userRepo.findAllByUsername(username).stream().map(userMapper::toDTO).toList();
    }

    @Override
    public UserDTO findByName(String username) {
        User user = userRepo.findByUsername(username).orElseThrow(()-> new ResourceNotFoundException("nima"));
        return userMapper.toDTO(user);
    }

    @Override
    public List<UserDTO> SortByReadBooks() {
        return userRepo.SortByReadBooks().stream().map(userMapper::toDTO).toList();
    }
}
