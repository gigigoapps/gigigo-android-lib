package testPpermission;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by nubor on 14/02/2017.
 */
@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH) public class AppLifeCycle
    implements Application.ActivityLifecycleCallbacks {

  @Override public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

  }

  @Override public void onActivityStarted(Activity activity) {
  //  Toast.makeText(activity, "onActivityStarted" + activity.toString(), Toast.LENGTH_LONG).show();
  }

  @Override public void onActivityResumed(Activity activity) {

  }

  @Override public void onActivityPaused(Activity activity) {

  }

  @Override public void onActivityStopped(Activity activity) {
  //  Toast.makeText(activity, "onActivityStopped" + activity.toString(), Toast.LENGTH_LONG).show();
  }

  @Override public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

  }

  @Override public void onActivityDestroyed(Activity activity) {

  }
}
