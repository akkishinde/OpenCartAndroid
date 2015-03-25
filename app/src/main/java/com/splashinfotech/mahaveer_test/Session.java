package com.splashinfotech.mahaveer_test;

import android.app.Application;

/**
 * Created by Splash New on 3/25/2015.
 */
public class Session extends Application {
    private String session_id;
    private String username;
    private String firstname;


    public String getSession_id() {

        return session_id;
    }

    public void setSession_id(String sSession_id) {

        session_id = sSession_id;

    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String sUsername) {

        username = sUsername;
    }

    public String getFirstname() {

        return firstname;
    }

    public void setFirstname(String fn) {

        firstname = fn;

    }

}
