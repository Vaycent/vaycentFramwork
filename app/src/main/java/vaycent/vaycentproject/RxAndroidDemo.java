package vaycent.vaycentproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RxAndroidDemo extends AppCompatActivity {

    private static final String TAG = "RxAndroidSamples";

//    private final CompositeDisposable disposables = new CompositeDisposable();

    @BindView(R.id.button_run_scheduler)
    Button mButtonRunScheduler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_android_demo);
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick(R.id.button_run_scheduler)
    public void onClick() {
        {
            Observable.create(new ObservableOnSubscribe<Integer>() {
                @Override
                public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                    emitter.onNext(1);
                    emitter.onNext(2);
                    emitter.onNext(3);
                    emitter.onComplete();
                }
            }).subscribe(new Observer<Integer>() {
                @Override
                public void onSubscribe(Disposable d) {
                    Log.d(TAG, "subscribe");
                }

                @Override
                public void onNext(Integer value) {
                    Log.d(TAG, "" + value);
                }

                @Override
                public void onError(Throwable e) {
                    Log.d(TAG, "error");
                }

                @Override
                public void onComplete() {
                    Log.d(TAG, "complete");
                }
            });
        }
    }
}
