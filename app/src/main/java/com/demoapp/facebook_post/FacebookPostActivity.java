package com.demoapp.facebook_post;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.demoapp.R;
import com.demoapp.facebook_post.adapter.FacebookPostAdapter;
import com.demoapp.facebook_post.model.FacebookPost;

import java.util.ArrayList;

/**
 * Created by vikas on 26/6/17.
 */

public class FacebookPostActivity extends Activity {
    private RecyclerView rvFacebookPost;
    private ArrayList<FacebookPost> facebookPostArrayList = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        init();
    }


    private void init() {
        initializeList();

    }

    private void getIntentData() {
        if (getIntent().hasExtra(FacebookLoginActivity.FACEBOOK_DATA) && getIntent().getParcelableArrayListExtra(FacebookLoginActivity.FACEBOOK_DATA)!=null)
        {
            facebookPostArrayList.addAll(getIntent().<FacebookPost>getParcelableArrayListExtra(FacebookLoginActivity.FACEBOOK_DATA));
            rvFacebookPost.getAdapter().notifyDataSetChanged();

        }


    }

    private void initializeList() {
        rvFacebookPost = (RecyclerView) findViewById(R.id.rvPost);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvFacebookPost.setLayoutManager(layoutManager);
        rvFacebookPost.setAdapter(new FacebookPostAdapter(facebookPostArrayList, this));
        getIntentData();
    }
}
