package com.demoapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.demoapp.facebook_post.FacebookLoginActivity;
import com.demoapp.instagram.InstagramLoginActivity;
import com.demoapp.twitter_post.TwitterLoginActivity;

/**
 * Created by vikas on 27/6/17.
 */

public class HomeActivity extends Activity implements View.OnClickListener {

    private TextView tvFacebook;
    private TextView tvTwitter;
    private TextView tvInstagram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setBindViews();
        setListener();
    }

    private void setListener() {
        tvFacebook.setOnClickListener(this);
        tvTwitter.setOnClickListener(this);
        tvInstagram.setOnClickListener(this);
    }

    private void setBindViews() {
        tvFacebook = (TextView) findViewById(R.id.tvFacebook);
        tvTwitter = (TextView) findViewById(R.id.tvTwitter);
        tvInstagram = (TextView) findViewById(R.id.tvInstagram);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvFacebook:
                startActivity(new Intent(HomeActivity.this, FacebookLoginActivity.class));
                break;
            case R.id.tvTwitter:
                startActivity(new Intent(HomeActivity.this, TwitterLoginActivity.class));
                break;
            case R.id.tvInstagram:
                startActivity(new Intent(HomeActivity.this, InstagramLoginActivity.class));
                break;
        }
    }
}
