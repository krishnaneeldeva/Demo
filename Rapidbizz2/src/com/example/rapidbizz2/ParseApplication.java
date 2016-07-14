package com.example.rapidbizz2;


import com.parse.Parse;
import com.parse.ParseACL;
 
import com.parse.ParseUser;
 
import android.app.Application;
 
public class ParseApplication extends Application {
 
    @Override
    public void onCreate() {
        super.onCreate();
 
        // Add your initialization code here
		Parse.initialize(this,"gmjW7tNFB5x0hUW3T48QWpcDTVNE22UZRBH5r3ql", "lrs9AYSqCQqoudQ4fOilTjvwKxHTd2To1jOaJeTa");
 
        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
 
        // If you would like all objects to be private by default, remove this
        // line.
        defaultACL.setPublicReadAccess(true);
 
        ParseACL.setDefaultACL(defaultACL, true);
    }
 
}