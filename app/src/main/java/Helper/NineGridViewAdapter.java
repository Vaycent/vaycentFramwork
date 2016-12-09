package Helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import HelpFulClass.CircleTransform;
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

        Glide.with(mInflater.getContext()).load(nineGridPicSet[position]).crossFade(R.anim.test_ani2, 5000)
                .transform(new CircleTransform(mInflater.getContext())).into(holder.imgShow);



        return convertView;
    }
}

class ViewHolder {
    ImageView imgShow;
}



