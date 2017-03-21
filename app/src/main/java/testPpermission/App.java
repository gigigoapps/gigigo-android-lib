package testPpermission;

import android.annotation.TargetApi;
import android.app.Application;
import android.os.Build;

/**
 * Created by nubor on 13/02/2017.
 */
public class App extends Application {
  @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH) @Override public void onCreate() {
    super.onCreate();

this.registerActivityLifecycleCallbacks(new AppLifeCycle() );
  }
}
