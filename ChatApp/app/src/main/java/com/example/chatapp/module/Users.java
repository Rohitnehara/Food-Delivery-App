package com.example.chatapp.module;

public class Users {
    public Users(String username, String email, String password) {
        Username = username;
        Email = email;
        this.password = password;
    }
    //empty constructor
    public Users(){}
    //our sign in  constructorr
    public Users( String email, String password) {
        Email = email;
        this.password = password;

    }
    String Username,Email,password,ProfilePic;

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePic() {
        return ProfilePic;
    }

    public void setProfilePic(String profilePic) {
        ProfilePic = profilePic;
    }


}
