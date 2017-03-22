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

package com.gigigo.ggglib.logger;

import android.util.Log;


public class AndroidLogger {
  public static int deliverToAndroidLog(String text, LogLevel logLevel, String tag) {
    switch (logLevel.getLogLevelValue()){
      case Log.WARN:
        Log.w(tag, text);
        return Log.WARN;
      case Log.VERBOSE:
        Log.v(tag, text);
        return Log.VERBOSE;
      case Log.ERROR:
        Log.e(tag, text);
        return Log.ERROR;
      case Log.INFO:
        Log.i(tag, text);
        return Log.INFO;
      case Log.ASSERT:
      case Log.DEBUG:
        Log.d(tag, text);
      return Log.DEBUG;
      default:
        Log.wtf(tag, text);
        return -1;
    }
  }
}
