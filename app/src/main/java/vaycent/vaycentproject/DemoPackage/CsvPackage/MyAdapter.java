package vaycent.vaycentproject.DemoPackage.CsvPackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by Vaycent on 2017/1/4.
 */

public class MyAdapter extends BaseAdapter {

    private LayoutInflater mInflater;

    private FoodList mFoodList;

    private Context mContext;

    public MyAdapter(Context context,FoodList mFoodList) {
        mInflater = LayoutInflater.from(context);
        this.mFoodList=mFoodList;
        this.mContext=context;
    }

    public int getCount() {
        return mFoodList.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView,
                        ViewGroup parent) {
//        if (convertView == null) {
//            convertView = mInflater.inflate(R.layout.app_choose_food_list_item, parent, false);
//        }
//
//        TextView food_name = MyCSV.ViewHolder.get(convertView, R.id.food_name);
//        TextView food_quantity = MyCSV.ViewHolder.get(convertView, R.id.food_quantity);
//        TextView food_count = MyCSV.ViewHolder.get(convertView, R.id.food_count);
//        TextView food_cals = MyCSV.ViewHolder.get(convertView, R.id.food_cals);
//        ImageView food_image = MyCSV.ViewHolder.get(convertView, R.id.food_image);
//        Button add_food = MyCSV.ViewHolder.get(convertView, R.id.add_food);
//        Button reduce_food = MyCSV.ViewHolder.get(convertView, R.id.reduce_food);
//
//        Food mFood = (Food)mFoodList.get(position);
//
//        food_name.setText(mFood.getEnName());

        return convertView;
    }


}