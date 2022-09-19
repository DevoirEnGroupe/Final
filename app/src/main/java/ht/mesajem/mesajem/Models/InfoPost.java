package ht.mesajem.mesajem.Models;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

import org.parceler.Parcel;
import org.xml.sax.Parser;

@Parcel(analyze = InfoPost.class)
@ParseClassName("InfoPost")
public class InfoPost extends ParseObject {

    public static final String KEY_NOM = "lastname";
    public static final String KEY_PRENOM = "firstname";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_ADDRESSE = "addresse";
    public static final String KEY_USER ="user";


    public ParseUser getUser(){
        return getParseUser(KEY_USER);
    }
    public void setUser(ParseUser user){
        put(KEY_USER, user);
    }

    public InfoPost(){

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
