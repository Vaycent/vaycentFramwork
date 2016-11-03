package Helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.download.ImageDownloader;

import vaycent.vaycentproject.R;

/**
 * Created by Vaycent on 2016/11/3.
 */

public class NineGridViewAdapter extends BaseAdapter {

    private LayoutInflater mInflater;

    private int[] nineGridPicSet;

    @Override
    public int getCount() {
        return nineGridPicSet.length;
    }

    public NineGridViewAdapter(Context context, int[] set) {
        mInflater = LayoutInflater.from(context);
        nineGridPicSet=set;
    }


    @Override
    public Object getItem(int arg0) {
        return arg0;
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.nine_grid_item, null);

            holder.imgShow = (ImageView)convertView.findViewById(R.id.image_show);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        String drawableUrl = ImageDownloader.Scheme.DRAWABLE.wrap(nineGridPicSet[position] + "");
        AdapterImageLoaderSet(drawableUrl,holder.imgShow);

        return convertView;
    }

    private void AdapterImageLoaderSet(String imageUrl, ImageView mImageView) {

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_replay_black_24dp)
                .showImageOnFail(R.drawable.ic_android_black_24dp)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        ImageLoader.getInstance().displayImage(imageUrl, mImageView, options);
    }
}

class ViewHolder {
    ImageView imgShow;
}



