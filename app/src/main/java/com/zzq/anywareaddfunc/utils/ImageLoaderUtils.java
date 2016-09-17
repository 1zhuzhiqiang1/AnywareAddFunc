package com.zzq.anywareaddfunc.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.zzq.anywareaddfunc.R;

/**
 * Created by Administrator on 2016/8/15 0015.
 */
public class ImageLoaderUtils {

    private static ImageLoader imageLoader = null;
    private DisplayImageOptions options = null;
    private static ImageLoaderUtils imageLoaderUtils = null;

    public static ImageLoaderUtils getInstance(Context mContext) {
        if (imageLoader == null) {
            imageLoader = ImageLoader.getInstance();
        }
        imageLoader.init(ImageLoaderConfiguration.createDefault(mContext));
        if (imageLoaderUtils == null) {
            imageLoaderUtils = new ImageLoaderUtils();
        }
        return imageLoaderUtils;
    }

    public void display(String path, ImageView imageView) {
        imageLoader.displayImage(path, imageView, getOption());
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    private DisplayImageOptions getOption() {
        if (options == null) {
            options = new DisplayImageOptions.Builder()
                    .showImageForEmptyUri(R.mipmap.ic_launcher)
                    .showImageOnFail(R.mipmap.ic_launcher)
                    .resetViewBeforeLoading(false)
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .considerExifParams(true)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .build();
        }
        return options;
    }
}
