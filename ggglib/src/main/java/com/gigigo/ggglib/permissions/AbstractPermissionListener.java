/*
 * Created by Gigigo Android Team
 *
 * Copyright (C) 2016 Gigigo Mobile Services SL
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gigigo.ggglib.permissions;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;
import com.gigigo.ggglib.ContextProvider;
import com.karumi.dexterox.PermissionToken;
import com.karumi.dexterox.listener.PermissionDeniedResponse;
import com.karumi.dexterox.listener.PermissionGrantedResponse;
import com.karumi.dexterox.listener.PermissionRequest;
import com.karumi.dexterox.listener.single.PermissionListener;

public abstract class AbstractPermissionListener implements PermissionListener {

  private UserPermissionRequestResponseListener userPermissionRequestResponseListener;
  private ContextProvider contextProvider;

  public AbstractPermissionListener(ContextProvider contextProvider) {
    this.contextProvider = contextProvider;
  }

  public AbstractPermissionListener(
      UserPermissionRequestResponseListener userPermissionRequestResponseListener,
      ContextProvider contextProvider) {
    this.userPermissionRequestResponseListener = userPermissionRequestResponseListener;
    this.contextProvider = contextProvider;
  }

  @Override public void onPermissionGranted(PermissionGrantedResponse response) {
    if (userPermissionRequestResponseListener != null) {
      userPermissionRequestResponseListener.onPermissionAllowed(true);
    }
  }

  @Override public void onPermissionDenied(PermissionDeniedResponse response) {
    //

    if (userPermissionRequestResponseListener != null) {
      userPermissionRequestResponseListener.onPermissionAllowed(false);
    }
  }

  @Override public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest,
      PermissionToken token) {

    RationaleResponse rationaleResponse = createRationaleResponseInstance(token);//

    Context context = contextProvider.getCurrentActivity();
    if (showSettingsDialog()) {
      PermissionsUIViews.showRationaleView(rationaleResponse, context,
          getPermissionRationaleTitle(), getPermissionRationaleMessage());
    }

    if (getNumRetry() > 0 && getNumRetry() == readNumRetryDone()) {
      PermissionsUIViews.showSettingsView(context,getPermissionRationaleTitle(), getPermissionSettingsDeniedFeedback(),
          getPermissionDeniedFeedback());
        //  only show this one time, the next writeNewretry
    }

    if (getNumRetry() > 0) writeNewRetry();
  }

  private RationaleResponse createRationaleResponseInstance(final PermissionToken token) {
    return new RationaleResponse() {
      @Override public void cancelPermissionRequest() {
        token.cancelPermissionRequest();
      }

      @Override public void continuePermissionRequest() {
        token.continuePermissionRequest();
      }
    };
  }

  public abstract int getPermissionDeniedFeedback();

  public abstract int getPermissionRationaleMessage();

  public abstract int getPermissionRationaleTitle();

  public abstract int getNumRetry() ;

  public abstract  int getPermissionSettingsDeniedFeedback();
  /*NEW retrys*/
  public String calculateHashCodeKey() {
    String hashcode = this.hashCode() + "";
    return hashcode;
  }

  private void writeNewRetry() {
    if (contextProvider.getCurrentActivity() != null) {

      String prefKey = calculateHashCodeKey();
      SharedPreferences sharedPref =
          contextProvider.getCurrentActivity().getPreferences(Context.MODE_PRIVATE);
      int defaultValue = 0;
      int numRetrys = sharedPref.getInt(prefKey, defaultValue);

      numRetrys = numRetrys + 1;
      SharedPreferences.Editor editor = sharedPref.edit();
      editor.putInt(prefKey, numRetrys);
      editor.commit();
    }
  }

  private int readNumRetryDone() {
    if (contextProvider.getCurrentActivity() != null) {

      String prefKey = calculateHashCodeKey();
      SharedPreferences sharedPref =
          contextProvider.getCurrentActivity().getPreferences(Context.MODE_PRIVATE);
      int defaultValue = 0;
      int numRetrys = sharedPref.getInt(prefKey, defaultValue);

      return numRetrys;
    }
    return 0;
  }

  private boolean showSettingsDialog() {

    if (getNumRetry() == -1) return true; //infinite retries

    if (getNumRetry() == 0) return false; //no one retries
    if (getNumRetry() > readNumRetryDone()) { //check retries
      return true;
    } else {
      return false;
    }
  }



  /****
   * calculate hashcode from permission for create key in preferences and counts retries
   */
  public int hashCode() {
    int megaHash = 0;
    try {
      megaHash = hashCodeObject(this.getPermissionDeniedFeedback());
      megaHash = megaHash + hashCodeObject(this.getPermissionRationaleMessage());
      megaHash = megaHash + hashCodeObject(this.getPermissionRationaleTitle());
    } catch (Exception e) {
      Log.i("ERROR ", e.getMessage());
    } catch (Throwable throwable) {
      Log.i("ERROR ", throwable.getMessage());
    }
    return megaHash;
  }

  public static int hashCodeObject(Object o) {
    return (o == null) ? 0 : o.hashCode();
  }
}
