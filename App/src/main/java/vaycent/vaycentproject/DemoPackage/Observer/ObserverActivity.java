package vaycent.vaycentproject.DemoPackage.Observer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Observable;
import java.util.Observer;

import vaycent.vaycentproject.R;

public class ObserverActivity extends AppCompatActivity implements Observer {
    ConcreteObservable observable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observer);
        observable = ConcreteObservable.getInstance();
        observable.addObserver(this);
        observable.setData(1);
        observable.post();
        observable.setData(1);
        observable.post();
        observable.post(2);
    }

    @Override
    public void update(Observable o, Object arg) {
        ConcreteObservable concreteObservable = (ConcreteObservable) o;
        Log.d("wxl", "ObserverActivity=" + concreteObservable.getData() + ",arg=" + arg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //删除观察者
        observable.deleteObserver(this);
    }}