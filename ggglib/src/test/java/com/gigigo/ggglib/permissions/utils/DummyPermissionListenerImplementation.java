package com.gigigo.ggglib.permissions.utils;

import com.gigigo.ggglib.R;
import com.gigigo.ggglib.permissions.AbstractPermissionListener;
import com.gigigo.ggglib.ContextProvider;
import com.gigigo.ggglib.permissions.UserPermissionRequestResponseListener;


public class DummyPermissionListenerImplementation extends AbstractPermissionListener {

  private final StubString stubString;

  public DummyPermissionListenerImplementation(ContextProvider contextProvider,
      StubString stubString) {
    super(contextProvider);
    this.stubString = stubString;
  }

  public DummyPermissionListenerImplementation(UserPermissionRequestResponseListener
      userPermissionRequestResponseListener, ContextProvider contextProvider,
      StubString stubString) {
    super(userPermissionRequestResponseListener, contextProvider);
    this.stubString = stubString;
  }

  @Override public int getPermissionDeniedFeedback() {
    return stubString.getPermissionDeniedFeedback();
  }

  @Override public int getPermissionRationaleMessage() {
    return stubString.getPermissionRationaleMessage();
  }

  @Override public int getPermissionRationaleTitle() {
    return stubString.getPermissionRationaleTitle();
  }

  @Override public int getNumRetry() {
    return 0;
  }

  @Override public int getPermissionSettingsDeniedFeedback() {
    return R.string.continueRequestPermissionSettingsDeniedFeedback;
  }
}
