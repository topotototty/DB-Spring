package com.example.Pract3.repositories;

import com.example.Pract3.models.ProfileModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ProfileRepository extends JpaRepository<ProfileModel, Long> {

}
