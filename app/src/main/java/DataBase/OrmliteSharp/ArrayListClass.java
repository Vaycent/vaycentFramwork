package DataBase.OrmliteSharp;

import java.util.ArrayList;

/**
 * Created by Vaycent on 16/9/12.
 */
public class ArrayListClass<T extends Class> {

    private T var;
    private ArrayList<T> alist = new ArrayList();

    public void setVar(T data){
        var=data;
        alist.add(var);
    }

    public T getVar(int i){
        return alist.get(i);
    }

    public int getSize(){
        return alist.size();
    }


}
