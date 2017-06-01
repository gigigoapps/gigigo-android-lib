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

package com.gigigo.ggglib.device;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.wifi.WifiManager;
import com.gigigo.ggglib.device.providers.AndroidSecureIdProvider;
import com.gigigo.ggglib.device.providers.AndroidSerialNumberProvider;
import com.gigigo.ggglib.device.providers.BluetoothMacProvider;
import com.gigigo.ggglib.device.providers.HandsetProvider;
import com.gigigo.ggglib.device.providers.WifiMacProvider;

public final class DeviceInfoProvider {

  private DeviceInfoProvider() {

  }

  public static String getHandset() {
    HandsetProvider handset = new HandsetProvider();
    return handset.provideHandset();
  }

  public static String getAndroidSecureId(Context context) {
    AndroidSecureIdProvider androidSecureIdProvider = new AndroidSecureIdProvider();
    return androidSecureIdProvider.provideAndroidSecureId(context);
  }

  public static String getAndroidSerialNumber() {
    AndroidSerialNumberProvider androidSerialNumberProvider = new AndroidSerialNumberProvider();
    return androidSerialNumberProvider.provideAndroidSerialNumber();
  }

  public static String getWifiMac(Context context) {
    WifiMacProvider wifiMacProvider = new WifiMacProvider();
    WifiManager wifiManager = wifiMacProvider.provideWifiManager(context);
    return wifiMacProvider.provideWifiMacAddress(wifiManager);
  }

  public static String getBluetoothMac() {
    BluetoothMacProvider bluetoothMacProvider = new BluetoothMacProvider();
    BluetoothAdapter adapter = bluetoothMacProvider.provideBluetoothDefaultAdapter();
    return bluetoothMacProvider.provideBluetoothMacAddress(adapter);
  }

  public static String getOsVersion() {
    return String.valueOf(AndroidSdkVersion.getAndroidSdkVersion());
  }
}
