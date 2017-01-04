package vaycent.vaycentproject.DemoPackage.CsvPackage;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.SparseArray;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import vaycent.magicLog.mlog;

/**
 * Created by Vaycent on 2017/1/4.
 */

public class MyCSV {

    static class ViewHolder {
        public static <T extends View> T get(View view, int id) {
            SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
            if (viewHolder == null) {
                viewHolder = new SparseArray<View>();
                view.setTag(viewHolder);
            }

            View childView = viewHolder.get(id);
            if (childView == null) {
                childView = view.findViewById(id);
                viewHolder.put(id, childView);
            }

            return (T) childView;
        }
    }


    public static FoodList getFoodListFromCSV(Context context){
        FoodList mFoodList = new FoodList();

        List<String[]> mCsvResult = readCsv(context);

        for (int i=0;i<mCsvResult.size();i++){
            String[] mCsvLine = mCsvResult.get(i);
            Food food = new Food(Integer.parseInt(mCsvLine[0]),mCsvLine[1],mCsvLine[2],mCsvLine[3],mCsvLine[4],mCsvLine[5],Integer.parseInt(mCsvLine[6]),mCsvLine[7]);
            mFoodList.add(food);
        }
        return mFoodList;
    }


    public static List<String[]> readCsv(Context context) {
        List<String[]> questionList = new ArrayList<String[]>();
        AssetManager assetManager = context.getAssets();

        try {
//            InputStream csvStream = assetManager.open(CSV_PATH);
            InputStream csvStream = context.getAssets().open("food_list_upload_20140630d.csv");

            InputStreamReader csvStreamReader = new InputStreamReader(csvStream);
            CSVReader csvReader = new CSVReader(csvStreamReader);
            String[] line;


            // throw away the header
            csvReader.readNext();

            while ((line = csvReader.readNext()) != null) {
                questionList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return questionList;
    }


    public void csvLog(Context context){
        List<String[]> csvResult = readCsv(context);
        for(int i=0;i<csvResult.size();i++){
            String[] csvLine = csvResult.get(i);
            for(int j=0;j<csvLine.length;j++){
                mlog.d("csvLine:"+csvLine[j]);
            }
            mlog.d("************************");
        }
    }

}
