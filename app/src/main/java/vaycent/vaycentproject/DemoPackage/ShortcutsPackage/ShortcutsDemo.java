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
import java.util.List;

import vaycent.magicLog.mlog;
import vaycent.vaycentproject.R;

/**
 * Created by Vaycent on 2016/12/13.
 */

public class ShortcutsDemo extends AppCompatActivity implements View.OnClickListener{

    private Button addShortcutBtn,updateShortcutBtn,removeId1ShortcutBtn,removeAllShortcutBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shorcuts_demo);

        addShortcutBtn = (Button)findViewById(R.id.add_shortcut_btn);
        updateShortcutBtn = (Button)findViewById(R.id.update_shortcut_btn);
        removeId1ShortcutBtn = (Button)findViewById(R.id.remove_id1_shortcut_btn);
        removeAllShortcutBtn = (Button)findViewById(R.id.remove_all_shortcut_btn);
        addShortcutBtn.setOnClickListener(this);
        updateShortcutBtn.setOnClickListener(this);
        removeId1ShortcutBtn.setOnClickListener(this);
        removeAllShortcutBtn.setOnClickListener(this);



        ShortcutManager shortcutManager = getSystemService(ShortcutManager.class);
        List<ShortcutInfo> staticInfos=shortcutManager.getManifestShortcuts();
        List<ShortcutInfo> dynamicInfos=shortcutManager.getDynamicShortcuts();

        for(ShortcutInfo info : staticInfos){
            mlog.d("Get info:"+info.getId());
            mlog.d("Get info:"+info.getLongLabel());
        }
        for(ShortcutInfo info : dynamicInfos){
            mlog.d("Get info:"+info.getId());
            mlog.d("Get info:"+info.getLongLabel());
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add_shortcut_btn:
                AddShortcutEvent();
                break;
            case R.id.update_shortcut_btn:
                UpdateShortcutEvent();
                break;
            case R.id.remove_id1_shortcut_btn:
                RemoveId1ShortcutEvent();
                break;
            case R.id.remove_all_shortcut_btn:
                RemoveAllShortcutEvent();
                break;
        }
    }

    private void AddShortcutEvent(){
        ShortcutManager shortcutManager = getSystemService(ShortcutManager.class);

        ShortcutInfo shortcut = new ShortcutInfo.Builder(this, "id1")
                .setShortLabel("Web site")
                .setLongLabel("Open My Web Site")
                .setIcon(Icon.createWithResource(this, R.drawable.ic_face_black_24dp))
                .setIntent(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://vaycent.github.io")))
//                .setRank(1) //This can change the shortcuts sorting
                .build();

        ShortcutInfo dynamicShortcut2 = new ShortcutInfo.Builder(this, "id2")
                .setShortLabel("Dynamic Shortcut")
                .setLongLabel("Open Dynamic shortcut")
                .setIcon(Icon.createWithResource(this, R.drawable.ic_face_black_24dp))
                .setIntents(
                        new Intent[]{
                                new Intent(Intent.ACTION_MAIN, Uri.EMPTY, this, ShortcutsDemo.class)
                                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        })
                .build();
        shortcutManager.setDynamicShortcuts(Arrays.asList(shortcut,dynamicShortcut2));

    }

    private void UpdateShortcutEvent(){
        ShortcutManager shortcutManager = getSystemService(ShortcutManager.class);

        ShortcutInfo shortcut = new ShortcutInfo.Builder(this, "id1")
                .setShortLabel("Baidu")
                .setLongLabel("Open Baidu Web")
                .setIcon(Icon.createWithResource(this, R.drawable.ic_face_black_24dp))
                .setIntent(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.baidu.com")))
                .build();

        shortcutManager.updateShortcuts(Arrays.asList(shortcut));
    }

    private void RemoveId1ShortcutEvent(){
        ShortcutManager mShortcutManager = getSystemService(ShortcutManager.class);
        List<ShortcutInfo> infos = mShortcutManager.getPinnedShortcuts();
        mlog.d("infos.size:"+infos.size());
        for (ShortcutInfo info : infos) {
            mlog.d("info.getId():"+info.getId());
            if (info.getId().equals("id" + 1)) {
                mShortcutManager.disableShortcuts(Arrays.asList(info.getId()), "暂无该联系人");
            }
        }
        mShortcutManager.removeDynamicShortcuts(Arrays.asList("id" + 1));
    }

    private void RemoveAllShortcutEvent(){
        ShortcutManager shortcutManager = getSystemService(ShortcutManager.class);
        shortcutManager.removeAllDynamicShortcuts();
    }

}
