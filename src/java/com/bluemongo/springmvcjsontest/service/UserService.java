package com.bluemongo.springmvcjsontest.service;

import com.bluemongo.springmvcjsontest.model.UserCredentials;

/**
 * Created by glenn on 31/08/15.
 */
public class UserService {
    public boolean validateCredentials(UserCredentials userCredentials) {
        if (userCredentials.getUsername().equals("bob")) {
            return true;
        }
        else{
            return false;
        }
    }
}
