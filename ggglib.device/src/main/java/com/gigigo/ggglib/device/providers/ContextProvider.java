package com.gigigo.ggglib.device.providers;

import android.app.Activity;
import android.content.Context;

/**
 * Created by rui.alonso on 22/5/17.
 */

public interface ContextProvider {
  Activity getCurrentActivity();

  boolean isActivityContextAvailable();

  Context getApplicationContext();

  boolean isApplicationContextAvailable();
}
