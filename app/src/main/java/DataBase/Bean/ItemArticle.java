package DataBase.Bean;

/**
 * Created by Vaycent on 2017/1/24.
 */

public class ItemArticle {
    private int index;
    private String picUrl;

    public ItemArticle(int index,  String picUrl) {
        this.index = index;
        this.picUrl = picUrl;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

//    public int getImageResource() {
//        return picResuource;
//    }
//
//    public void setImageResource(int picResuource) {
//        this.picResuource = picResuource;
//    }

      public String getPicUrl(){
          return picUrl;
      }
      public void setPicUrl(String picUrl){
          this.picUrl = picUrl;
      }
}