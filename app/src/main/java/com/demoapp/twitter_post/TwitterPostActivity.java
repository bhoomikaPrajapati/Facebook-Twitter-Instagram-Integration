package com.demoapp.twitter_post;

import android.app.ListActivity;
import android.os.Bundle;

import com.demoapp.R;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

/**
 * Created by vikas on 26/6/17.
 */

public class TwitterPostActivity extends ListActivity {

 /*   private RecyclerView rvTwitterPost;
    private ArrayList<FacebookPost> facebookPostArrayList = new ArrayList<>();*/


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timeline);
        init();
    }


    private void init() {
        UserTimeline userTimeline = new UserTimeline.Builder().screenName("twitterdev").build();
        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(this)
                .setTimeline(userTimeline)
                .build();
        setListAdapter(adapter);


    }




}
