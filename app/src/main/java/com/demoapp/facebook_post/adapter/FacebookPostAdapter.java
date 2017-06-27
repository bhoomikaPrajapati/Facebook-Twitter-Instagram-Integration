package com.demoapp.facebook_post.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.demoapp.R;
import com.demoapp.facebook_post.baseclasses.BaseRecyclerAdapter;
import com.demoapp.facebook_post.baseclasses.ImageUtils;
import com.demoapp.facebook_post.model.FacebookPost;

import java.util.ArrayList;


/**
 * Created by vikas on 29/3/17.
 */

public class FacebookPostAdapter extends BaseRecyclerAdapter<FacebookPostAdapter.DataViewHolder, FacebookPost> {


    private ArrayList<FacebookPost> facebookPostArrayList;
    private Context context;


    public FacebookPostAdapter(ArrayList<FacebookPost> facebookPostArrayList, Context context) {
        super(facebookPostArrayList);
        this.facebookPostArrayList = facebookPostArrayList;
        this.context = context;

    }

    @Override
    public FacebookPostAdapter.DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FacebookPostAdapter.DataViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_post, parent, false));
    }

    @Override
    public void onBindViewHolder(FacebookPostAdapter.DataViewHolder holder, int position) {
        FacebookPost facebookPost = facebookPostArrayList.get(position);
        if (facebookPost.getFull_picture() != null) {
            holder.ivUserPost.setVisibility(View.VISIBLE);
            ImageUtils.displayImage(context, facebookPost.getFull_picture(), holder.ivUserPost);
        } else {
            holder.ivUserPost.setVisibility(View.GONE);
        }

        if (facebookPost.getMessage() != null) {
            holder.tvUserPostMesaage.setVisibility(View.VISIBLE);
            holder.tvUserPostMesaage.setText(facebookPost.getMessage());
        }
        else {
            holder.tvUserPostMesaage.setVisibility(View.GONE);
        }




    }

    class DataViewHolder extends BaseRecyclerAdapter.ViewHolder {
        ImageView ivUserPost;
        TextView tvUserPostMesaage;


        public DataViewHolder(View itemView) {
            super(itemView);
            ivUserPost = (ImageView) itemView.findViewById(R.id.ivUserPost);
            tvUserPostMesaage = (TextView) itemView.findViewById(R.id.tvPostMessage);
            clickableViews(itemView);


        }
    }
}


