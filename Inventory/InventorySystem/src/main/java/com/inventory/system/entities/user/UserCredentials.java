package com.inventory.system.entities.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserCredentials {
    @JsonProperty
    private String username;
    @JsonProperty
    private String password;

    public UserCredentials(String username, String password) {
        this.username = username;
        this.password = hashPassword(password);
    }

    public UserCredentials(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    //method that simulates hash function
    public static String hashPassword(String password){
        int hash = 0;
        for (char c : password.toCharArray()) {
            hash = (hash * 31) + c;
        }
        return Integer.toString(hash);
    }


}
