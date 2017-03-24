package com.gigigo.ggglib.device.providers;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class WifiMacProviderTest {

    @Mock
    Context context;

    @Mock
    WifiManager wifiManager;

    @Mock
    WifiInfo wifiInfo;

    private WifiMacProvider wifiMacProvider;

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Before public void setUpWifiMacProviderTest() {
        MockitoAnnotations.initMocks(this);
        wifiMacProvider = new WifiMacProvider();
    }

    @Test
    public void testProvideWifiMacOk() {
        String macWifi = "0F:34:12:33:43:54";

        when(wifiManager.getConnectionInfo()).thenReturn(wifiInfo);
        when(wifiInfo.getMacAddress()).thenReturn(macWifi);

        assertEquals(macWifi, wifiMacProvider.provideWifiMacAddress(wifiManager));
    }


    @Test
    public void testProvideWifiMacNotActived() {
        when(wifiManager.getConnectionInfo()).thenReturn(null);

        assertNull(wifiMacProvider.provideWifiMacAddress(wifiManager));
    }
}
