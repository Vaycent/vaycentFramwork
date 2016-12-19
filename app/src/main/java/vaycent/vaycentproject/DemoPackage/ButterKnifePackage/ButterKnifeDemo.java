package vaycent.vaycentproject.DemoPackage.ButterKnifePackage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vaycent.vaycentproject.R;

/**
 * Created by Vaycent on 2016/12/19.
 */

public class ButterKnifeDemo extends AppCompatActivity {

    @Bind(R.id.rotateButton)
    Button mRotateButton;
    @Bind(R.id.scaleButton)
    Button mScaleButton;
    @Bind(R.id.alphaButton)
    Button mAlphaButton;
    @Bind(R.id.translateButton)
    Button mTranslateButton;
    @Bind(R.id.image)
    ImageView mImage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation_demo);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.rotateButton, R.id.scaleButton, R.id.alphaButton, R.id.translateButton})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rotateButton:
                Toast.makeText(this,"Rotate",Toast.LENGTH_SHORT).show();
                break;
            case R.id.scaleButton:
                Toast.makeText(this,"Scale",Toast.LENGTH_SHORT).show();

                break;
            case R.id.alphaButton:
                Toast.makeText(this,"Alph",Toast.LENGTH_SHORT).show();
                break;
            case R.id.translateButton:
                Toast.makeText(this,"Translate",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
