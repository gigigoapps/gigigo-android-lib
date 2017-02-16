package testPpermission;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.gigigo.ggglib.permissions.AndroidPermissionCheckerImpl;
import com.gigigo.ggglib.permissions.PermissionChecker;
import com.gigigo.ggglib.permissions.UserPermissionRequestResponseListener;
import dondeestamicarro.asvfactory.asv.testapplication.R;

public class MainActivity extends AppCompatActivity {
  private PermissionChecker permissionChecker;
  private PermissionLocationImp accessFineLocationPermissionImp;
  private myContextProvider myContextProvider;

  private UserPermissionRequestResponseListener userPermissionResponseListener =
      new UserPermissionRequestResponseListener() {
        @Override public void onPermissionAllowed(boolean permissionAllowed) {
          if (permissionAllowed) {
            // Toast.makeText(MainActivity.this, "MENSAJE DE TEST Permiso recien concedido", Toast.LENGTH_LONG).show();
          } else {
            //  Toast.makeText(MainActivity.this, "MENSAJE DE TEST Permiso NO concedido", Toast.LENGTH_LONG).show();
          }
        }
      };

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Button btn= (Button)findViewById(R.id.btnpermission);
    btn.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        askPermissions();
      }
    });



  }

  @Override protected void onResume() {
    super.onResume();
     //if we ask here for permissions dexteractivity onresume on pause raise this onResumeEvent and cause loop

}

  private void askPermissions() {
    myContextProvider = new myContextProvider(this, this.getApplicationContext());

    permissionChecker =
        new AndroidPermissionCheckerImpl(this.getApplicationContext(), myContextProvider);
    accessFineLocationPermissionImp = new PermissionLocationImp();

    boolean isGranted = permissionChecker.isGranted(accessFineLocationPermissionImp);
    if (isGranted) {
      //  Toast.makeText(this, "Permiso concedido, con anterioridad", Toast.LENGTH_LONG).show();
    } else {
      permissionChecker.askForPermission(accessFineLocationPermissionImp,
          userPermissionResponseListener, this);
    }
  }
  }

