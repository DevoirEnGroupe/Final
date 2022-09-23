package ht.mesajem.mesajem.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import ht.mesajem.mesajem.Adapters.SenderAdapter;
import ht.mesajem.mesajem.Models.Post;
import ht.mesajem.mesajem.R;

public class TrackActivity extends AppCompatActivity {

    RecyclerView rvPosts;
    SenderAdapter adapter;
    List<Post> posts;
    Boolean mFirstLoad;
    TextView tvsend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);
        rvPosts = findViewById(R.id.rvPosts);
        tvsend = findViewById(R.id.tvsend);

        posts = new ArrayList<>();
        adapter = new SenderAdapter(posts,this);
        rvPosts.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvPosts.setLayoutManager(layoutManager);
        layoutManager.setReverseLayout(true);
        mFirstLoad=true;
        queryposts();



    }

    protected void queryposts() {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);
        query.setLimit(20);
        query.addDescendingOrder(Post.KEY_CREATED_AT);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {


                if(e == null){
                    adapter.clear();
                    adapter.addAll(posts);

                }
                if(mFirstLoad){
                    rvPosts.scrollToPosition(0);
                    mFirstLoad= false;
                }
                else {
                    Log.e("post", "Error Loading post" + e);
                }

            }
        });
    }
}