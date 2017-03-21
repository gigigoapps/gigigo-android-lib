package testPpermission;

import android.app.Activity;
import android.content.Context;
import com.gigigo.ggglib.ContextProvider;

/**
 * Created by nubor on 13/02/2017.
 */
public class myContextProvider implements ContextProvider {

  Activity mActivity;
  Context mAppContext;

  public myContextProvider(Activity mActivity, Context mAppContext) {
    this.mActivity = mActivity;
    this.mAppContext = mAppContext;
  }

  @Override public Activity getCurrentActivity() {
    return mActivity;
  }

  @Override public boolean isActivityContextAvailable() {
    return true;
  }

  @Override public Context getApplicationContext() {
    return mAppContext;
  }

  @Override public boolean isApplicationContextAvailable() {
    return true;
  }
}
