package vaycent.service.router.interfaces;

import android.content.Context;

import java.io.Serializable;

public interface IntentRunnable extends Serializable {
    void run(Context context);
}
