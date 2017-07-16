package vaycent.vaycentproject.DemoPackage.Observer;

import java.util.Observable;

/**
 * Created by vaycent on 2017/6/16.
 */

public class ConcreteObservable extends Observable {

    private static ConcreteObservable observable;
    public static synchronized ConcreteObservable getInstance() {
        if (observable == null) {
            observable = new ConcreteObservable();
        }
        return observable;
    }




    private int data = 0;

    public int getData() {
        return data;
    }

    public void setData(int i) {
        //具体逻辑按需
        if (data != i) {
            this.data = i;
            setChanged();
        }
    }



    public ConcreteObservable post() {
        //只有在setChange()被调用后，notifyObservers()才会去调用update()，否则什么都不干。
        notifyObservers();
        return observable;
    }

    public ConcreteObservable post(Object arg) {
        setChanged();
        //只有在setChange()被调用后，notifyObservers()才会去调用update()，否则什么都不干。
        notifyObservers(arg);
        return observable;
    }}