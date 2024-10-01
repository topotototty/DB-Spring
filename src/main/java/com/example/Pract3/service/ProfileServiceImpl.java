package com.example.Pract3.service;

import com.example.Pract3.models.ProfileModel;
import com.example.Pract3.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public List<ProfileModel> getAllProfiles() {
        return profileRepository.findAll();
    }

    @Override
    public Optional<ProfileModel> getProfileById(Long id) {
        return profileRepository.findById(id);
    }

    @Override
    public ProfileModel createProfile(ProfileModel profile) {
        return profileRepository.save(profile);
    }

    @Override
    public ProfileModel updateProfile(Long id, ProfileModel updatedProfile) {
        return profileRepository.findById(id)
                .map(profile -> {
                    profile.setFirstName(updatedProfile.getFirstName());
                    profile.setSecondName(updatedProfile.getSecondName());
                    profile.setAddress(updatedProfile.getAddress());
                    return profileRepository.save(profile);
                })
                .orElseThrow(() -> new IllegalArgumentException("Profile with ID " + id + " not found"));
    }

    @Override
    public void deleteProfile(Long id) {
        profileRepository.deleteById(id);
    }
}
