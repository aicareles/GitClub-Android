package com.i502tech.gitclub.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.util.Util;
import com.i502tech.gitclub.R;
import com.i502tech.gitclub.widget.GlideCircleTransform;

import java.io.File;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by Tony on 2018/1/16.
 */

public class GlideUtil {

    public static void display(Context context, ImageView imageView, String url, int placeholder, int error) {
        if (imageView == null || null == context) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url).placeholder(placeholder)
                .error(error).into(imageView);
    }

    public static void display(Context context, ImageView imageView, String url) {
        if (imageView == null || null == context) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .dontAnimate()
                .error(R.drawable.ic_empty_picture)
                .placeholder(R.drawable.ic_image_loading)
                .into(imageView);
    }

    public static void displayBitmap(Context context, ImageView imageView, Bitmap bitmap) {
        if (imageView == null || null == context) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(bitmap)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .dontAnimate()
                .error(R.drawable.ic_empty_picture)
                .placeholder(R.drawable.ic_image_loading)
                .into(imageView);
    }

    public static void displayAlbum(Context context, ImageView imageView, String url) {
        if (imageView == null || null == context) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .centerCrop()
//                .placeholder(R.drawable.ic_image_loading)
                .dontAnimate()
                .error(R.drawable.ic_empty_picture)
                .into(imageView);
    }

    public static void displayFit(Context context, ImageView imageView, String url) {
        if (imageView == null || null == context) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
//                .placeholder(R.drawable.ic_image_loading)
                .dontAnimate()
                .error(R.drawable.ic_empty_picture)
                .into(imageView);
    }

    public static void displayFitCenter(Context context, ImageView imageView, String url) {
        if (imageView == null || null == context) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
//                .placeholder(R.drawable.ic_image_loading)
                .error(R.drawable.ic_empty_picture)
                .into(imageView);
    }

    public static void display(Context context, ImageView imageView, Drawable url) {
        if (imageView == null || null == context) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
//                .placeholder(R.drawable.ic_image_loading)
                .error(R.drawable.ic_empty_picture)
                .into(imageView);
    }

    public static void displaySmallPhoto(Context context, ImageView imageView, String url) {
        if (imageView == null || null == context) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .placeholder(R.drawable.ic_image_loading)
                .error(R.drawable.ic_empty_picture)
                .thumbnail(0.5f)
                .into(imageView);
    }

    public static void displayBigPhoto(Context context, ImageView imageView, String url) {
        if (imageView == null || null == context) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url)
                .format(DecodeFormat.PREFER_ARGB_8888)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_image_loading)
                .error(R.drawable.ic_empty_picture)
                .into(imageView);
    }

    public static void display(Context context, ImageView imageView, int res) {
        if (imageView == null || null == context) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(res)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
//                .placeholder(R.drawable.ic_image_loading)
                .error(R.drawable.ic_empty_picture)
                .into(imageView);
    }

    public static void displayRound(Context context, ImageView imageView, String url, int radius) {
        if (imageView == null || null == context) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context)
                .load(url)
                //设置等待时的图片【这个时候需要注释，否则这个会作为背景图】
                //.placeholder(R.drawable.img_loading)
                //设置加载失败后的图片显示
                .error(R.drawable.ic_empty_picture)
                .centerCrop()
                //默认淡入淡出动画
                .transition(withCrossFade())
                //缓存策略,跳过内存缓存【此处应该设置为false，否则列表刷新时会闪一下】
                .skipMemoryCache(false)
                //缓存策略,硬盘缓存-仅仅缓存最终的图像，即降低分辨率后的（或者是转换后的）
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                //设置图片加载的优先级
                .priority(Priority.HIGH)
                .transform(new RoundedCorners(DensityUtil.dip2px(context, radius)))
                .into(imageView);
    }

    //自定义图片形状
    public static void displayTransform(Context context, ImageView imageView, String url, Transformation<Bitmap> transformation) {
        if (imageView == null || null == context) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context)
                .load(url)
                //设置等待时的图片【这个时候需要注释，否则这个会作为背景图】
                //.placeholder(R.drawable.img_loading)
                //设置加载失败后的图片显示
                .error(R.drawable.ic_empty_picture)
                .centerCrop()
                //默认淡入淡出动画
                .transition(withCrossFade())
                //缓存策略,跳过内存缓存【此处应该设置为false，否则列表刷新时会闪一下】
                .skipMemoryCache(false)
                //缓存策略,硬盘缓存-仅仅缓存最终的图像，即降低分辨率后的（或者是转换后的）
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                //设置图片加载的优先级
                .priority(Priority.HIGH)
                .transform(transformation)
                .into(imageView);
    }

    public static void displayCircle(Context context, ImageView imageView, String url) {
        if (imageView == null || null == context) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context)
                .load(url)
                //设置等待时的图片【这个时候需要注释，否则这个会作为背景图】
                //.placeholder(R.drawable.img_loading)
                //设置加载失败后的图片显示
                .error(R.drawable.ic_empty_picture)
                .centerCrop()
                //默认淡入淡出动画
                .transition(withCrossFade())
                //缓存策略,跳过内存缓存【此处应该设置为false，否则列表刷新时会闪一下】
                .skipMemoryCache(false)
                //缓存策略,硬盘缓存-仅仅缓存最终的图像，即降低分辨率后的（或者是转换后的）
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                //设置图片加载的优先级
                .priority(Priority.HIGH)
                //圆形
                .circleCrop()
                .into(imageView);
    }

    public static void displayFile(Context context, ImageView imageView, String filePath) {
        if (imageView == null || null == context) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(new File(filePath))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
//                .placeholder(R.drawable.ic_image_loading)
                .error(R.drawable.ic_empty_picture)
                .into(imageView);
    }
    public static void displayCircleWithBorder(Context context, ImageView imageView, String url, int borderWidth, int borderColor) {
        if (imageView == null || null == context) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context)
                .load(url)
                //设置等待时的图片【这个时候需要注释，否则这个会作为背景图】
                //.placeholder(R.drawable.img_loading)
                //设置加载失败后的图片显示
                .error(R.drawable.ic_empty_picture)
                .centerCrop()
                //默认淡入淡出动画
                .transition(withCrossFade())
                //缓存策略,跳过内存缓存【此处应该设置为false，否则列表刷新时会闪一下】
                .skipMemoryCache(false)
                //缓存策略,硬盘缓存-仅仅缓存最终的图像，即降低分辨率后的（或者是转换后的）
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                //设置图片加载的优先级
                .priority(Priority.HIGH)
                //圆形
//                .circleCrop()
                .transform(new GlideCircleTransform(context, borderWidth, borderColor))
                .into(imageView);
    }


    /**
     * 暂停所有的加载图片任务
     */
    public static void pauseRequestsRecursive(Activity activity) {
        Glide.with(activity).pauseRequestsRecursive();
    }


    /**
     * 恢复所有的加载图片任务
     */
    public static void resumeRequestsRecursive(Activity activity) {
        Glide.with(activity).resumeRequestsRecursive();
    }

    /**
     * 在触发内存警告使用调用
     */
    public static void onLowMemory(Context context) {
    }

    /**
     * 根据不同的内存警告级别进行不同的处理
     */
    public static void onTrimMemory(Context context, int level) {
    }

    /**
     * 相应的销毁方法
     */
    public static void onDestory(Context context) {
        if (Util.isOnMainThread()) {
            Glide.with(context).onDestroy();
        }
    }

    public interface loadDownListener {
        void loadSuc();

        void loadFail();
    }
}
