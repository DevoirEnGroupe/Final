package ht.mesajem.mesajem.Models;


import com.parse.ParseClassName;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseUser;

import org.parceler.Parcel;

@Parcel(analyze = Delivery.class)
@ParseClassName("Delivery")
public class Delivery extends ParseObject {

    public static final String KEY_USER_NAME = "username";
    public static final String KEY_LOCATION = "location";
    public static final String KEY_POST_ACC = "postaccept";
    public static final String KEY_USER ="userd";
    public static final String KEY_STATUS ="status";
    public static final String KEY_NOM = "lastname";
    public static final String KEY_PRENOM = "firstname";





    public Delivery(){

    }

    public String getNom(){
        return getString(KEY_NOM);
    }
    public String getPrenom(){
        return getString(KEY_PRENOM);
    }
    public void setNom(String nom){
        put(KEY_NOM,nom);
    }

    public void setPrenom(String prenom){
        put(KEY_PRENOM,prenom);
    }

    public ParseUser getUserd(){
        return getParseUser(KEY_USER);
    }
    public void setUserd(ParseUser user){
        put(KEY_USER, user);
    }

    public Boolean getStatus(){return  getBoolean(KEY_STATUS);}
    public void setStatus(Boolean satus){put(KEY_STATUS,satus);}

    public ParseObject getPostaccept(){
        return getParseObject(KEY_POST_ACC);
    }

    public void setPostaccept(ParseObject postaccept){
        put(KEY_POST_ACC,postaccept);
    }
}
