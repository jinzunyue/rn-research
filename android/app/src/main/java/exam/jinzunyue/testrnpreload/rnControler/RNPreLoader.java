package exam.jinzunyue.testrnpreload.rnControler;

import android.app.Activity;
import android.util.Log;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.facebook.react.ReactApplication;
import com.facebook.react.ReactRootView;

import java.util.HashMap;
import java.util.Map;

public class RNPreLoader {
    public static Map<String, ReactRootView> CACHE = new HashMap<>();

    public static void preLoader(Activity activity, String componentName) {
        if (CACHE.get(componentName) != null) {
            return;
        }

        // 1.创建ReactRootView
        ReactRootView rootView = new ReactRootView(activity);
        rootView.startReactApplication(
                ((ReactApplication) activity.getApplication()).getReactNativeHost().getReactInstanceManager(),
                componentName,
                null);

        // 2.添加到缓存
        CACHE.put(componentName, rootView);
    }

    public static ReactRootView getReactRootView(String componentName) {
        return CACHE.get(componentName);
    }

    public static void deatchView(String componentName) {
        try {
            ReactRootView reactRootView = CACHE.get(componentName);
            if (reactRootView != null) {
                ViewGroup parent = (ViewGroup) reactRootView.getParent();
                if (parent != null) {
                    parent.removeView(reactRootView);
                }
            }
        } catch (Throwable throwable) {
            Log.e("ReactNativePreLoader", throwable.getMessage());
        }
    }
}
