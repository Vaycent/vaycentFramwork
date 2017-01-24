package Helper;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import DataBase.Bean.ItemArticle;
import HelpFulClass.RoundTransform;
import vaycent.magicLog.mlog;

/**
 * Created by Vaycent on 2017/1/24.
 */

public class HeaderAdapter extends PagerAdapter {
    private static final String LOG = "NEWS_LOG";

    private Activity context;
    private List<ItemArticle> articles;
    private List<ImageView> images = new ArrayList<ImageView>();


    public HeaderAdapter(Activity context, List<ItemArticle> articles) {
        this.context = context;
        if (articles == null || articles.size() == 0) {
            this.articles = new ArrayList<>();
        } else {
            this.articles = articles;
        }

        for (int i = 0; i < articles.size(); i++) {
            ImageView image = new ImageView(context);
            Glide.with(context).load(articles.get(i).getPicUrl())
                   .into(image);//.sizeMultiplier(0.1f)
            images.add(image);
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        mlog.w("images.size:"+images.size());

        container.addView(images.get(position));
        return images.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(images.get(position));
    }

    @Override
    public int getCount() {
        return articles.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        Log.i(LOG, "in isViewFromObject view: " + view + " object: "
                + object + " equal: " + (view == (View) object));
        return view == (View) object;
    }
}