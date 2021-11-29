package com.example.project.Domain;

public class UserInfo {
    public UserInfo(String profilePic, String username) {
        this.profilePic = profilePic;
        this.username = username;
    }

    public UserInfo() {
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    String  profilePic,username;
}
