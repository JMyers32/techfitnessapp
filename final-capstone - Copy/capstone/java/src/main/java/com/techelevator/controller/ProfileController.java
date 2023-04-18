package com.techelevator.controller;

import com.techelevator.dao.ProfileDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@CrossOrigin
@RequestMapping(path="/profile")
@RestController
@PreAuthorize("isAuthenticated()")
public class ProfileController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ProfileDao profileDao;

    @GetMapping()
    public Profile getProfile(Principal principal){
        int userId= userDao.findIdByUsername(principal.getName());
        return profileDao.getProfile(userId);
    }
    @GetMapping(path = "/all")
    public List<Profile> getAllProfiles(){
        return profileDao.getAllProfiles();
    }
    @PostMapping()
    public void createProfile(Principal principal,@RequestBody Profile profile){
        int userId= userDao.findIdByUsername(principal.getName());
        profile.setUserId(userId);
        profileDao.createProfile(profile);
    }

    @PutMapping()
    public void updatedProfile(Principal principal, @RequestBody Profile profile){
        int userId= userDao.findIdByUsername(principal.getName());
        int profileId=profileDao.getProfileIdByUsername(principal.getName());
        profile.setUserId(userId);
        profile.setProfileId(profileId);
        boolean updated=profileDao.updateProfile(profile);
        if(!updated){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"profile not updated");
        }
    }

    @DeleteMapping()
    public void deleteProfile(Principal principal){
        int profileId= profileDao.getProfileIdByUsername(principal.getName());
        boolean updated=profileDao.deleteProfile(profileId);
        if(!updated){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"profile not updated");
        }
    }
}
