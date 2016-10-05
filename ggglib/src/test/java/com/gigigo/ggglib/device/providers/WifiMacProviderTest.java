package com.gigigo.ggglib.device.providers;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WifiMacProviderTest {

    @Mock
    Context context;

    @Mock
    WifiManager wifiManager;

    @Mock
    WifiInfo wifiInfo;

    @Test
    public void testProvideWifiMacOk() throws Exception {
        String macWifi = "0F:34:12:33:43:54";

        WifiMacProvider wifiMacProvider = new WifiMacProvider();

        WifiMacProvider spyWifiMacProvider = spy(wifiMacProvider);

        when(spyWifiMacProvider.provideWifiManager(context)).thenReturn(wifiManager);

        when(wifiManager.getConnectionInfo()).thenReturn(wifiInfo);

        when(wifiInfo.getMacAddress()).thenReturn(macWifi);

        assertEquals(macWifi, spyWifiMacProvider.provideWifiMac(wifiManager));
    }

    @Test
    public void testProvideWifiMacNotActived() throws Exception {
        WifiMacProvider wifiMacProvider = new WifiMacProvider();

        WifiMacProvider spyWifiMacProvider = spy(wifiMacProvider);

        when(spyWifiMacProvider.provideWifiManager(context)).thenReturn(wifiManager);

        when(wifiManager.getConnectionInfo()).thenReturn(null);

        assertNull(spyWifiMacProvider.provideWifiMac(wifiManager));
    }
}
