package com.demoapp.instagram;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.demoapp.R;
import com.demoapp.facebook_post.baseclasses.ImageUtils;

import java.util.ArrayList;


public class MyGridListAdapter extends BaseAdapter {
	 private Context context;
	private ArrayList<String> imageThumbList;
	private LayoutInflater inflater;



	public MyGridListAdapter(Context context, ArrayList<String> imageThumbList) {
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.imageThumbList = imageThumbList;
		this.context=context;

	}

	@Override
	public int getCount() {
		return imageThumbList.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = inflater.inflate(R.layout.media_list_inflater, null);
		Holder holder = new Holder();
		holder.ivPhoto = (ImageView) view.findViewById(R.id.ivImage);
		ImageUtils.displayImage(context,imageThumbList.get(position), holder.ivPhoto);
		return view;
	}

	private class Holder {
		private ImageView ivPhoto;
	}

}
