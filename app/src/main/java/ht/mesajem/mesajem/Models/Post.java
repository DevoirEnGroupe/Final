package ht.mesajem.mesajem.Models;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import org.parceler.Parcel;

import java.util.Date;


@Parcel(analyze = Post.class)
@ParseClassName("Post")
public class Post extends ParseObject {

    public static final String KEY_IMAGE ="image";
    public static final String KEY_USER ="user";
    public static final String KEY_CREATED_AT ="createdAt";
   // public static final String KEY_OBJECT_ID = "objectId";
    public static final String KEY_STATUS = "status";

    public static final String KEY_NOM = "lastname";
    public static final String KEY_PRENOM = "firstname";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_ADDRESSE = "addresse";



    public ParseFile getKeyImage(){
        return getParseFile(KEY_IMAGE);
    }
    public void setKeyImage(ParseFile parseFile){
        put(KEY_IMAGE, parseFile);
    }

    public ParseUser getUser(){
        return getParseUser(KEY_USER);
    }
    public void setUser(ParseUser user){
        put(KEY_USER, user);
    }


    public String getKeyCreatedAt(){
        return  getString(KEY_CREATED_AT);
    }
    public void setKeyCreatedAt(String createdAt){
        put(KEY_CREATED_AT,createdAt);
    }

    public Boolean getStatus(){
        return getBoolean(KEY_STATUS);
    }
    public void setStatus(Boolean status){
        put(KEY_STATUS,status);
    }




    public String getNom(){
        return getString(KEY_NOM);
    }
    public String getPrenom(){
        return getString(KEY_PRENOM);
    }
    public String getEmail(){
        return getString(KEY_EMAIL);
    }
    public String getAddresse(){
        return getString(KEY_ADDRESSE);
    }

    public void setNom(String nom){
        put(KEY_NOM,nom);
    }

    public void setPrenom(String prenom){
        put(KEY_PRENOM,prenom);
    }


    public void setEmail(String email){
        put(KEY_EMAIL,email);
    }


    public void setAddresse(String addresse){
        put(KEY_ADDRESSE,addresse);
    }

}
