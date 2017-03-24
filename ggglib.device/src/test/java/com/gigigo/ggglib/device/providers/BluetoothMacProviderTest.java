package com.gigigo.ggglib.device.providers;

import android.bluetooth.BluetoothAdapter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

public class BluetoothMacProviderTest {

  @Mock BluetoothAdapter bluetoothAdapter;

  private BluetoothMacProvider bluetoothMacProvider;

  @Before public void setupBluetoothMacProviderTest() {
    MockitoAnnotations.initMocks(this);

    bluetoothMacProvider = new BluetoothMacProvider();
  }

  @Test public void testProvideBluetoothMacOk() {
    String mac = "0F:34:12:45:56:34";

    when(bluetoothAdapter.getAddress()).thenReturn(mac);

    assertEquals(mac, bluetoothMacProvider.provideBluetoothMacAddress(bluetoothAdapter));
  }

  @Test public void testProvideBluetoothMacNotSupported() {
    assertNull(bluetoothMacProvider.provideBluetoothMacAddress(null));
  }
}
