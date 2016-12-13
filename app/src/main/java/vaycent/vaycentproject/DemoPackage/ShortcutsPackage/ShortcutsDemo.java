package vaycent.vaycentproject.DemoPackage.ShortcutsPackage;

import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.Arrays;

import vaycent.vaycentproject.R;

/**
 * Created by Vaycent on 2016/12/13.
 */

public class ShortcutsDemo extends AppCompatActivity implements View.OnClickListener{
    private Button addShortcutBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shorcuts_demo);

        addShortcutBtn = (Button)findViewById(R.id.add_shortcut_btn);
        addShortcutBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add_shortcut_btn:
                AddShortcutEvent();
                break;
        }
    }

    private void AddShortcutEvent(){
        ShortcutManager shortcutManager = getSystemService(ShortcutManager.class);

        ShortcutInfo shortcut = new ShortcutInfo.Builder(this, "id1")
                .setShortLabel("Web site")
                .setLongLabel("Open the web site")
                .setIcon(Icon.createWithResource(this, R.drawable.ic_face_black_24dp))
                .setIntent(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.mysite.example.com/")))
                .build();

        shortcutManager.setDynamicShortcuts(Arrays.asList(shortcut));
    }
}
