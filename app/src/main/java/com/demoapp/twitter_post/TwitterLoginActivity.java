package com.demoapp.twitter_post;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.demoapp.R;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.tweetui.TweetUi;

/**
 * Created by vikas on 26/6/17.
 */

public class TwitterLoginActivity extends Activity implements View.OnClickListener {


    private Button mLoginButton;
    private TwitterAuthClient client;
    private TwitterLoginButton mTwitterLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter_login);
        init();

    }

    private void init() {
        TwitterConfig config = new TwitterConfig.Builder(this)
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(new TwitterAuthConfig(getString(R.string.consumer_key), getString(R.string.consumer_secret)))
                .debug(true)
                .build();
        Twitter.initialize(config);
        TwitterCore.getInstance();
        TweetUi.getInstance();
        setBindViews();
    }

    private void setBindViews() {
        mLoginButton = (Button) findViewById(R.id.login_button);
        mTwitterLogin = (TwitterLoginButton) findViewById(R.id.login_button_twitter);
        setListener();
    }

    private void setListener() {
        mLoginButton.setOnClickListener(this);
        mTwitterLogin.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                // Do something with result, which provides a TwitterSession for making API calls
            }

            @Override
            public void failure(TwitterException exception) {
                // Do something on failure
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        client.onActivityResult(requestCode, resultCode, data);
        mTwitterLogin.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login_button) {
            client = new TwitterAuthClient();
            client.authorize(TwitterLoginActivity.this, new Callback<TwitterSession>() {
                @Override
                public void success(Result<TwitterSession> twitterSessionResult) {
                    Toast.makeText(TwitterLoginActivity.this, "success", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(TwitterLoginActivity.this, TwitterPostActivity.class));
                }

                @Override
                public void failure(TwitterException e) {
                    Toast.makeText(TwitterLoginActivity.this, "failure", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}

