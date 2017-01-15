package vaycent.vaycentproject.DemoPackage.CsvPackage;

/**
 * Created by Vaycent on 2017/1/4.
 */

public class Food {
        private int refCode;
        private String enName;
        private String zhName;
        private String enQuantity;
        private String zhQuantity;
        private String type;
        private int cals;
        private String imgName;

    public Food(int refCode,String enName,String zhName,String enQuantity,String zhQuantity,String type,int cals,String imgName){
            this.refCode=refCode;
            this.enName=enName;
            this.zhName=zhName;
            this.enQuantity=enQuantity;
            this.zhQuantity=zhQuantity;
            this.type=type;
            this.cals=cals;
            this.imgName=imgName;
    }

    public int getRefCode(){
        return refCode;
    }

    public String getEnName(){
        return enName;
    }

    public String getZhName(){
        return zhName;
    }

    public String getEnQuantity(){
        return enQuantity;
    }

    public String getZhQuantity(){
        return zhQuantity;
    }

    public String getType(){
        return type;
    }

    public int getCals(){
        return cals;
    }

    public String getImgName(){
        return imgName;
    }

}
