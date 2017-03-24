package com.gigigo.ggglib.device.providers;

import android.bluetooth.BluetoothAdapter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class BluetoothMacProviderTest {

    @Mock
    BluetoothAdapter bluetoothAdapter;

    @Before
    public void setupBluetoothMacProviderTest(){
        MockitoAnnotations.initMocks(this);
    }

    /*
    @Test
    public void testProvideBluetoothMacOk() throws Exception {
        String mac = "0F:34:12:45:56:34";

        BluetoothMacProvider bluetoothMacProvider = new BluetoothMacProvider();

        BluetoothMacProvider spyBluetoothMacProvider = spy(bluetoothMacProvider);

        when(spyBluetoothMacProvider.provideBluetoothDefaultAdapter()).thenReturn(bluetoothAdapter);

        when(bluetoothAdapter.getAddress()).thenReturn(mac);

        String bluetoothMac = spyBluetoothMacProvider.provideBluetoothMac(bluetoothAdapter);

        assertEquals(mac, bluetoothMac);
    }
*/

    @Test
    public void testProvideBluetoothMacNotSupported() throws Exception {

        BluetoothMacProvider bluetoothMacProvider = new BluetoothMacProvider();

        String bluetoothMac = bluetoothMacProvider.provideBluetoothMac(null);

        assertNull(bluetoothMac);
    }
}
