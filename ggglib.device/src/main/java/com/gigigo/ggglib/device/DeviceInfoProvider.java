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
import com.gigigo.ggglib.device.providers.AndrodSerialNumberProvider;
import com.gigigo.ggglib.device.providers.AndroidSecureIdProvider;
import com.gigigo.ggglib.device.providers.BluetoothMacProvider;
import com.gigigo.ggglib.device.providers.HandsetProvider;
import com.gigigo.ggglib.device.providers.WifiMacProvider;

public class DeviceInfoProvider {

    public static String getHandset() {
        HandsetProvider handset = new HandsetProvider();
        return handset.provideHandset();
    }

    public static String getAndroidSecureId(Context context) {
        AndroidSecureIdProvider androidSecureId = new AndroidSecureIdProvider();
        return androidSecureId.provideAndroidSecureId(context);
    }

    public static String getAndroidSerialNumber() {
        AndrodSerialNumberProvider androdSerialNumber = new AndrodSerialNumberProvider();
        return androdSerialNumber.provideAndroidSerialNumber();
    }

    public static String getWifiMac(Context context) {
        WifiMacProvider provider = new WifiMacProvider();
        WifiManager wifiManager = provider.provideWifiManager(context);
        return provider.provideWifiMac(wifiManager);

    }

    public static String getBluetoothMac() {
        BluetoothMacProvider provider = new BluetoothMacProvider();
        BluetoothAdapter adapter = provider.provideBluetoothDefaultAdapter();
        return provider.provideBluetoothMac(adapter);
    }

    public static String getOsVersion() {
        return String.valueOf(AndroidSdkVersion.getAndroidSdkVersion());
    }
}
