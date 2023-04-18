package com.techelevator.dao;

import com.techelevator.model.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcProfileDao implements ProfileDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcProfileDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }


    @Override
    public Profile getProfile(int userId) {
        String sql="SELECT * FROM profile WHERE user_id=?";
        SqlRowSet results= jdbcTemplate.queryForRowSet(sql,userId);
        Profile profile=new Profile();
        if(results.next()){
            profile=mapProfileToRowSet(results);
        }
        return profile;
    }

    @Override
    public void createProfile(Profile profile) {
      String sql="INSERT INTO profile (user_id, display_name, profile_picture) VALUES (?,?,?)";
      jdbcTemplate.update(sql,profile.getUserId(),profile.getDisplayName(),profile.getProfilePicture());
    }

    @Override
    public boolean updateProfile(Profile profile) {
        boolean success=false;
        String sql="UPDATE profile SET display_name=?, profile_picture=? WHERE profile_id=?";
        int linesUpdated=jdbcTemplate.update(sql,profile.getDisplayName(),profile.getProfilePicture(),profile.getProfileId());
        if(linesUpdated==1){
            success=true;
        }
        return success;
    }

    @Override
    public boolean deleteProfile(int profileId) {
        boolean success=false;
        String sql="DELETE FROM profile WHERE profile_id=?";
        int linesUpdated= jdbcTemplate.update(sql,profileId);
        if(linesUpdated==1){
            success=true;
        }
        return success;
    }

    @Override
    public int getProfileIdByUsername(String username) {
        String sql="SELECT p.profile_id FROM profile p "+
                "JOIN users u ON u.user_id=p.user_id "+
                "WHERE u.username=?";
        int profileId=jdbcTemplate.queryForObject(sql, Integer.class,username);
        return profileId;
    }

    @Override
    public List<Profile> getAllProfiles() {
        String sql="SELECT * FROM profile";
        SqlRowSet results= jdbcTemplate.queryForRowSet(sql);
        List<Profile> allProfiles=new ArrayList<>();
        while(results.next()){
            allProfiles.add(mapProfileToRowSet(results));
        }
        return allProfiles;
    }

    private Profile mapProfileToRowSet(SqlRowSet results){
        Profile profile=new Profile();
        profile.setProfileId(results.getInt("profile_id"));
        profile.setUserId(results.getInt("user_id"));
        profile.setDisplayName(results.getString("display_name"));
        profile.setProfilePicture(results.getString("profile_picture"));
        return profile;
    }
}
