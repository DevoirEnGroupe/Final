package ht.mesajem.mesajem.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import ht.mesajem.mesajem.Adapters.ReceiverAdapter;
import ht.mesajem.mesajem.Models.Post;
import ht.mesajem.mesajem.R;


public class ReceivedFragment extends Fragment {

    RecyclerView rvPosts;
    ReceiverAdapter adapter;
    List<Post> posts;
    Boolean mFirstLoad;
    TextView tvreceive;


    public ReceivedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_received, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvPosts = view.findViewById(R.id.rvPosts);
        tvreceive = view.findViewById(R.id.tvreceive);

        posts = new ArrayList<>();
        adapter = new ReceiverAdapter(posts,getContext());
        rvPosts.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
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

                for (Post post: posts){

                    Log.i("ok",  "keydate " +post.getCreatedAt()+", keyId " +post.getObjectId()+",username " +post.getUser().getUsername());
                    Toast.makeText(getContext(), "keydate " +post.getCreatedAt()+", keyId " +post.getObjectId()+",username " +post.getUser().getUsername(), Toast.LENGTH_SHORT).show();
                }

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