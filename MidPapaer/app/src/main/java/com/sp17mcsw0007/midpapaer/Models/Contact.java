package com.sp17mcsw0007.midpapaer.Models;

/**
 * Created by Geeta on 14-Jan-18.
 */

public class Contact {

    String name, id, email, address;

    public Contact(String id,String name, String email, String address) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }
}
