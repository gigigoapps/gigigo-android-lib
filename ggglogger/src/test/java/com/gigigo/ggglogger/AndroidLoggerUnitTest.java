package com.gigigo.ggglogger;

import android.util.Log;
import org.junit.Test;

import static org.junit.Assert.*;

public class AndroidLoggerUnitTest {

  /**
   * Test switch statement in deliverToAndroidLog method
   * @throws Exception
   */
  @Test public void loggingSwitch() throws Exception {
    assertEquals(Log.DEBUG,
        AndroidLogger.deliverToAndroidLog("TEST : LogLevel.ASSERT", LogLevel.ASSERT, "TEST"));
    assertEquals(Log.DEBUG,
        AndroidLogger.deliverToAndroidLog("TEST : LogLevel.DEBUG", LogLevel.DEBUG, "TEST"));
    assertEquals(Log.ERROR,
        AndroidLogger.deliverToAndroidLog("TEST : LogLevel.ERROR", LogLevel.ERROR, "TEST"));
    assertEquals(Log.INFO,
        AndroidLogger.deliverToAndroidLog("TEST : LogLevel.INFO", LogLevel.INFO, "TEST"));
    assertEquals(Log.VERBOSE,
        AndroidLogger.deliverToAndroidLog("TEST : LogLevel.VERBOSE", LogLevel.VERBOSE, "TEST"));
    assertEquals(Log.WARN,
        AndroidLogger.deliverToAndroidLog("TEST : LogLevel.WARN", LogLevel.WARN, "TEST"));
  }
}