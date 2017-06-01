package com.gigigo.ggglib.device.providers;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

public class WifiMacProviderTest {

  @Rule public ExpectedException thrown = ExpectedException.none();
  @Mock Context context;
  @Mock WifiManager wifiManager;
  @Mock WifiInfo wifiInfo;
  private WifiMacProvider wifiMacProvider;

  @Before public void setUpWifiMacProviderTest() {
    MockitoAnnotations.initMocks(this);
    wifiMacProvider = new WifiMacProvider();
  }

  @Test public void testProvideWifiMacOk() {
    String macWifi = "0F:34:12:33:43:54";

    when(wifiManager.getConnectionInfo()).thenReturn(wifiInfo);
    when(wifiInfo.getMacAddress()).thenReturn(macWifi);

    assertEquals(macWifi, wifiMacProvider.provideWifiMacAddress(wifiManager));
  }

  @Test public void testProvideWifiMacNotActived() {
    when(wifiManager.getConnectionInfo()).thenReturn(null);

    assertNull(wifiMacProvider.provideWifiMacAddress(wifiManager));
  }
}
