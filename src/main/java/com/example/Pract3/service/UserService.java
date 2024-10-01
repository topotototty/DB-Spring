package com.example.Pract3.service;

import com.example.Pract3.models.UserModel;
import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserModel> getAllUsers();
    Optional<UserModel> getUserById(Long id);
    UserModel createUser(UserModel user);
    UserModel updateUser(Long id, UserModel user);
    void deleteUser(Long id);
}
