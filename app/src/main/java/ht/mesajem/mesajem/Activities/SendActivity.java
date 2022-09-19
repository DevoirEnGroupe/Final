package ht.mesajem.mesajem.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.File;

import ht.mesajem.mesajem.Fragments.MapsFragment;
import ht.mesajem.mesajem.Models.Post;
import ht.mesajem.mesajem.R;

public class SendActivity extends AppCompatActivity {

    public static final String TAG ="SendFragment";
    public final static int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1034;
    FragmentManager fragmentManager = getSupportFragmentManager();
    Button ButtonTakePic;
    TextView tvDocSend;
    ImageView PostImage;
    ImageView send_id;
    public String photoFileName = "photo.jpg";
    File photoFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        ButtonTakePic = findViewById(R.id.ButtonTakePic);
        PostImage = findViewById(R.id.PostImage);
        tvDocSend = findViewById(R.id.tvDocSend);
        send_id = findViewById(R.id.send_id);

        ButtonTakePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LaunchCamera();
                if(PostImage!=null){
                    ButtonTakePic.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if(photoFile== null||PostImage.getDrawable()== null){
                                Toast.makeText(SendActivity.this,"ERROR PICTURE", Toast.LENGTH_LONG).show();
                                return;
                            }
                            ParseUser currentUser = ParseUser.getCurrentUser();
                            savePost(currentUser,photoFile);
                            //Intent i = new Intent(getActivity(), MapsActivity.class);
                            //startActivity(i);
                            ButtonTakePic.setVisibility(View.GONE);
                            PostImage.setVisibility(View.GONE);
                            tvDocSend.setVisibility(View.GONE);
                            send_id.setVisibility(View.GONE);
                            Fragment fragment= new MapsFragment();
                            fragmentManager.beginTransaction().replace(R.id.relativ,fragment).commit();
                        }
                    });
                }
            }
        });
    }

    private void LaunchCamera() {

        // create Intent to take a picture and return control to the calling application
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Create a File reference for future access
        photoFile = getPhotoFileUri(photoFileName);

        // wrap File object into a content provider
        // required for API >= 24
        // See https://guides.codepath.com/android/Sharing-Content-with-Intents#sharing-files-with-api-24-or-higher
        Uri fileProvider = FileProvider.getUriForFile(this, "com.codepath.fileprovider", photoFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider);

        // If you call startActivityForResult() using an intent that no app can handle, your app will crash.
        // So as long as the result is not null, it's safe to use the intent.
        if (intent.resolveActivity(this.getPackageManager()) != null) {
            // Start the image capture intent to take photo
            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE){
            if(resultCode == RESULT_OK){
                Bitmap Takemap = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                PostImage.setImageBitmap(Takemap);
            }
        }
    }

    // Returns the File for a photo stored on disk given the fileName
    public File getPhotoFileUri(String fileName) {
        // Get safe storage directory for photos
        // Use `getExternalFilesDir` on Context to access package-specific directories.
        // This way, we don't need to request external read/write runtime permissions.
        File mediaStorageDir = new File(this.getExternalFilesDir(Environment.DIRECTORY_PICTURES), TAG);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()){
            Log.d(TAG, "failed to create directory");
        }

        // Return the file target for the photo based on filename
        File file = new File(mediaStorageDir.getPath() + File.separator + fileName);

        return file;
    }



    private void savePost(ParseUser currentUser, File photoFile) {
        Post post = new Post();
        post.setKeyImage(new ParseFile(photoFile));
        post.setUser(currentUser);
        post.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e!= null){
                    Log.e(TAG, "error while save", e);
                    Toast.makeText(SendActivity.this,"error save", Toast.LENGTH_LONG).show();
                }
                Log.i(TAG,"Save post");
                PostImage.setImageResource(0);
            }
        });
    }

}