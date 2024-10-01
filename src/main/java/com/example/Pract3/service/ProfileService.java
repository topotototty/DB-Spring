package com.example.Pract3.service;

import com.example.Pract3.models.ProfileModel;
import java.util.List;
import java.util.Optional;

public interface ProfileService {
    List<ProfileModel> getAllProfiles();
    Optional<ProfileModel> getProfileById(Long id);
    ProfileModel createProfile(ProfileModel profile);
    ProfileModel updateProfile(Long id, ProfileModel profile);
    void deleteProfile(Long id);
}
