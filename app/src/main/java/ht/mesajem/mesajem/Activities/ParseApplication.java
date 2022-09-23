package ht.mesajem.mesajem.Activities;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

import ht.mesajem.mesajem.Models.Delivery;
import ht.mesajem.mesajem.Models.Post;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Use for monitoring Parse network traffic
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        // Can be Level.BASIC, Level.HEADERS, or Level.BODY
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        // any network interceptors must be added with the Configuration Builder given this syntax
        builder.networkInterceptors().add(httpLoggingInterceptor);

        ParseObject.registerSubclass(Post.class);
        ParseObject.registerSubclass(Delivery.class);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("XgpEGn5vBntafy0fVxJVFuGrcnQku6ZdsWZ1DSQg")
                .clientKey("vpUnrU5VALPPRwvsHm2qvOnEuTooibAbZvnV4Nog")
                .server("https://parseapi.back4app.com")
                .build()
        );

    }
}
