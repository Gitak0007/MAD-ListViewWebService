package com.example.geeta.madpractise;

/**
 * Created by Geeta on 07-Jan-18.
 */

public class User {
    String _name;
    String _email;

    public User( String name, String email){

        this._name = name;
        this._email = email;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }
}
