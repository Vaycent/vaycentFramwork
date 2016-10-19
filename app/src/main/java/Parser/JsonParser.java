package Parser;

import org.json.JSONArray;
import org.json.JSONObject;

import vaycent.magicLog.mlog;

/**
 * Created by Vaycent on 2016/10/18.
 */

public class JsonParser {

    public JsonParser(String jsonData){

        if(jsonData.startsWith("{")) {
            parseJsonObject(jsonData);
        }else{
            parseJsonArray(jsonData);
        }
    }

    private void parseJsonArray(String jsonData){
        try{
            JSONArray jsonArray = new JSONArray(jsonData);
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String lat = jsonObject.getString("lat");
                String lon = jsonObject.getString("lon");
                String cityName = jsonObject.getString("cityName");
                String level = jsonObject.getString("level");
                String alevel = jsonObject.getString("alevel");

                mlog.d("parseJsonArrayObject:"+lat+","+lon+","+cityName+","+level+","+alevel);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void parseJsonObject(String jsonData){
        try{
            JSONObject jsonObject = new JSONObject(jsonData);

            String lat = jsonObject.getString("lat");
            String lon = jsonObject.getString("lon");
            String cityName = jsonObject.getString("cityName");
            String level = jsonObject.getString("level");
            String alevel = jsonObject.getString("alevel");

            mlog.d("parseJsonObject:"+lat+","+lon+","+cityName+","+level+","+alevel);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
