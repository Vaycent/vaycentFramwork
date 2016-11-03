package ImageLoader;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.View;

import vaycent.magicLog.mlog;

/**
 * Created by Vaycent on 2016/11/2.
 */

public class ImageLoaderSharp {

    public ImageLoaderSharp(Context context, View view, int picResource){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getResources(), picResource, options);
        int imageHeight = options.outHeight;
        int imageWidth = options.outWidth;
        String imageType = options.outMimeType;
        mlog.w("imageHeight:"+imageHeight);
        mlog.w("imageWidth:"+imageWidth);
        mlog.w("imageType:"+imageType);


        mlog.w("view.getHeight():"+view.getMeasuredHeight());
        mlog.w("view.getWidth():"+view.getMeasuredWidth());


    }
}
