package com.sp17mcsw0007.midpapaer.Helpers;

import com.sp17mcsw0007.midpapaer.MainActivity;
import com.sp17mcsw0007.midpapaer.Models.Contact;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Geeta on 14-Jan-18.
 */

public class WebService {

    public static List<Contact> GetData(String url) throws IOException, JSONException {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        JSONObject object = new JSONObject(response.body().string());
        JSONArray contactArray = object.getJSONArray("contacts");

        List<Contact> contacts = new ArrayList<>();

        for (int i =0 ; i<contactArray.length();i++){

        JSONObject objectContact = contactArray.getJSONObject(i);
        Contact c = new Contact(objectContact.getString("id"),objectContact.getString("name"),objectContact.getString("email"),objectContact.getString("address"));
        contacts.add(c);
        }
        return contacts;
    }
}
