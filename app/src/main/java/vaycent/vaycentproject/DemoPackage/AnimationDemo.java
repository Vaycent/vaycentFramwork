package vaycent.vaycentproject.DemoPackage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import vaycent.vaycentproject.R;

/**
 * Created by Vaycent on 16/9/9.
 */
public class AnimationDemo extends AppCompatActivity {
    private Button rotateButton = null;
    private Button scaleButton = null;
    private Button alphaButton = null;
    private Button translateButton = null;
    private ImageView image = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation_demo);

        rotateButton = (Button) findViewById(R.id.rotateButton);
        scaleButton = (Button) findViewById(R.id.scaleButton);
        alphaButton = (Button) findViewById(R.id.alphaButton);
        translateButton = (Button) findViewById(R.id.translateButton);
        image = (ImageView) findViewById(R.id.image);

        rotateButton.setOnClickListener(new RotateButtonListener());
        scaleButton.setOnClickListener(new ScaleButtonListener());
        alphaButton.setOnClickListener(new AlphaButtonListener());
        translateButton.setOnClickListener(new TranslateButtonListener());
    }

    class AlphaButtonListener implements View.OnClickListener {
        public void onClick(View v) {
//            // 使用AnimationUtils装载动画配置文件
//            Animation animation = AnimationUtils.loadAnimation(
//                    MainActivity.this, R.anim.alpha);
//            // 启动动画
//            image.startAnimation(animation);
            Animation an = AnimationUtils.loadAnimation(AnimationDemo.this, R.anim.alpha);
            image.startAnimation(an);
        }
    }

    class RotateButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            Animation animation = AnimationUtils.loadAnimation(
                    AnimationDemo.this, R.anim.rotate);
            image.startAnimation(animation);
        }
    }

    class ScaleButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            Animation animation = AnimationUtils.loadAnimation(
                    AnimationDemo.this, R.anim.scale);
            image.startAnimation(animation);
        }
    }

    class TranslateButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            Animation animation = AnimationUtils.loadAnimation(AnimationDemo.this, R.anim.translate);
            image.startAnimation(animation);
        }
    }
}
