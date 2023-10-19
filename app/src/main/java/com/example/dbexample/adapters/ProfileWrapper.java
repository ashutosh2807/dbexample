package com.example.dbexample.adapters;

import com.example.dbexample.Data.profileData;

public class ProfileWrapper {
    profileData data;

    public void setProfileData(profileData profile) {
        data = profile;
    }

    public profileData getProfileData() {
        return data;
    }
}
