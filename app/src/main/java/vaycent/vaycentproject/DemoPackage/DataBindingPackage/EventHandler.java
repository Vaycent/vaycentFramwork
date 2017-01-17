package vaycent.vaycentproject.DemoPackage.DataBindingPackage;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Vaycent on 2017/1/17.
 */

public class EventHandler {
    private Context mContext;
    public EventHandler(Context context) {
        mContext = context;
    }

    public void onClickFriend(View view) {
        Toast.makeText(mContext, "onClickFriend", Toast.LENGTH_LONG).show();
    }

    public void onClickFriend2(View view) {
        Toast.makeText(mContext, "onClickFriend2", Toast.LENGTH_LONG).show();
    }
}
