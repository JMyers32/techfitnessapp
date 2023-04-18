package com.techelevator.dao;

import com.techelevator.model.Profile;

import java.util.List;

public interface ProfileDao {


    Profile getProfile(int userId);

    void createProfile(Profile profile);

    boolean updateProfile(Profile profile);

    boolean deleteProfile(int profileId);

    int getProfileIdByUsername(String username);

    List<Profile> getAllProfiles();



}
