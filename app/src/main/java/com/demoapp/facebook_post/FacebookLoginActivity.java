package com.demoapp.facebook_post;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.demoapp.R;
import com.demoapp.facebook_post.model.FacebookData;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.Arrays;


public class FacebookLoginActivity extends Activity implements View.OnClickListener {

    private LoginButton mLoginButton;
    private CallbackManager mCallbackManager;
    private Button btnFacebookLogin;
    public static final String FACEBOOK_DATA = "facebook_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_login);
        setBindViews();
        setListener();


    }

    private void setBindViews() {
        mCallbackManager = CallbackManager.Factory.create();
        mLoginButton = (LoginButton) findViewById(R.id.login_button);
        btnFacebookLogin = (Button) findViewById(R.id.btnFacebookLogin);
        mLoginButton.setReadPermissions("email", "public_profile ", "user_status", "user_posts");

    }

    private void setListener() {
        btnFacebookLogin.setOnClickListener(this);
        // Callback registration

        mLoginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                getPostDetail(loginResult.getAccessToken().getUserId());
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });


    }

    private void setRegisterCallback() {

    }


    private void getPostDetail(String userId) {
        Bundle params = new Bundle();
        params.putString("fields", "message,created_time,id,full_picture,status_type,source,comments.summary(true),likes.summary(true)");
        params.putString("limit", "15");

        /* make the API call */
        new GraphRequest(AccessToken.getCurrentAccessToken(), "/" + userId + "/posts", params, HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        /* handle the result */
                        System.out.println("Festival Page response::" + String.valueOf(response.getJSONObject()));
                        try {
                            JSONObject jObjResponse = new JSONObject(String.valueOf(response.getJSONObject()));

                            FacebookData facebookData = new Gson().fromJson(String.valueOf(jObjResponse), FacebookData.class);
                            Intent intent = new Intent(FacebookLoginActivity.this, FacebookPostActivity.class);
                            intent.putParcelableArrayListExtra(FACEBOOK_DATA, facebookData.getFacebookPostArrayList());
                            startActivity(intent);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).executeAsync();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
        LoginManager.getInstance().logInWithReadPermissions(FacebookLoginActivity.this, Arrays.asList("email", "public_profile ", "user_status", "user_posts"));
        mCallbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(mCallbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        getPostDetail(loginResult.getAccessToken().getUserId());
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });
    }
}
