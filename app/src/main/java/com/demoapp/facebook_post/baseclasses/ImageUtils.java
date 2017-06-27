
package com.demoapp.facebook_post.baseclasses;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.FileDescriptorBitmapDecoder;
import com.bumptech.glide.load.resource.bitmap.VideoBitmapDecoder;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;


public class ImageUtils {

    public interface ImageLoadingListener {
        void onLoadingStart(ImageView imageView);

        void onLoadingSuccess(ImageView imageView);

        void onLoadingComplete(ImageView imageView);

        void onLoadingFailed(ImageView imageView);
    }

    public static void displayImage(Context activity, String imageUrl, ImageView imageView, Drawable placeHolder) {

        Glide.with(activity).load(imageUrl).placeholder(placeHolder).dontAnimate().into(imageView);
    }

    public static void displayImage(Context activity, String imageUrl, ImageView imageView) {
        displayImage(activity, imageUrl, imageView, null);

    }

    public static void displayImage(Context activity, String imageUrl, final ImageView imageView, Drawable placeHolder,
                                    final ImageLoadingListener imageLoadingListener) {

        onLoadingStart(imageView, imageLoadingListener);
        Glide.with(activity)
                .load(imageUrl)
                .placeholder(placeHolder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        ImageUtils.onLoadingFailed(imageView, imageLoadingListener);
                        ImageUtils.onLoadingComplete(imageView, imageLoadingListener);
                        return false;
                    }

                    @Override public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target,
                                                             boolean isFromMemoryCache, boolean isFirstResource) {
                        ImageUtils.onLoadingSuccess(imageView, imageLoadingListener);
                        ImageUtils.onLoadingComplete(imageView, imageLoadingListener);
                        return false;
                    }
                })
                .into(imageView);
    }

    public static void displayVideoThumbImage(Activity activity, String videoUrl, ImageView imageView) {
        BitmapPool bitmapPool = Glide.get(activity).getBitmapPool();
        FileDescriptorBitmapDecoder decoder =
                new FileDescriptorBitmapDecoder(new VideoBitmapDecoder(1), bitmapPool, DecodeFormat.PREFER_ARGB_8888);
        Glide.with(activity).load(videoUrl).asBitmap().videoDecoder(decoder).into(imageView);
    }

    public static void onLoadingStart(ImageView imageView, ImageLoadingListener imageLoadingListener) {
        if (imageLoadingListener != null) {
            imageLoadingListener.onLoadingStart(imageView);
        }
    }

    public static void onLoadingComplete(ImageView imageView, ImageLoadingListener imageLoadingListener) {
        if (imageLoadingListener != null) {
            imageLoadingListener.onLoadingComplete(imageView);
        }
    }

    public static void onLoadingSuccess(ImageView imageView, ImageLoadingListener imageLoadingListener) {
        if (imageLoadingListener != null) {
            imageLoadingListener.onLoadingSuccess(imageView);
        }
    }

    public static void onLoadingFailed(ImageView imageView, ImageLoadingListener imageLoadingListener) {
        if (imageLoadingListener != null) {
            imageLoadingListener.onLoadingFailed(imageView);
        }
    }
}

