package testPpermission;

import android.Manifest;
import com.gigigo.ggglib.permissions.Permission;
import dondeestamicarro.asvfactory.asv.testapplication.R;

/**
 * Created by nubor on 13/02/2017.
 */
public class PermissionLocationImp implements Permission {

  @Override
  public String getAndroidPermissionStringType() {
    return Manifest.permission.ACCESS_FINE_LOCATION;
  }

  @Override
  public int getPermissionSettingsDeniedFeedback() {
    return R.string.ox_permission_settings;
  }

  @Override
  public int getPermissionDeniedFeedback() {
  // return -1;//asv if you dont want show the message all times the app is started
    return R.string.ox_permission_denied_geolocation;
  }

  @Override
  public int getPermissionRationaleTitle() {
    return R.string.ox_permission_rationale_title_location;
  }

  @Override
  public int getPermissionRationaleMessage() {
    return R.string.ox_permission_rationale_message_location;
  }

  @Override public int getNumRetry() {
    return 0;
  }
}
