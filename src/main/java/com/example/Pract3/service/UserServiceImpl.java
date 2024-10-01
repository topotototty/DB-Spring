package com.example.Pract3.service;

import com.example.Pract3.models.UserModel;
import com.example.Pract3.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserModel> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public UserModel createUser(UserModel user) {
        return userRepository.save(user);
    }

    @Override
    public UserModel updateUser(Long id, UserModel updatedUser) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(updatedUser.getName());

                    if (user.getProfile() != null && updatedUser.getProfile() != null) {
                        user.getProfile().setFirstName(updatedUser.getProfile().getFirstName());
                        user.getProfile().setSecondName(updatedUser.getProfile().getSecondName());
                        user.getProfile().setAddress(updatedUser.getProfile().getAddress());
                    } else if (updatedUser.getProfile() != null) {
                        user.setProfile(updatedUser.getProfile());
                    }

                    return userRepository.save(user);
                })
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + id + " not found"));
    }


    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
