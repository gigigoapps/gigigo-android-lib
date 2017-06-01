package com.gigigo.ggglib.logger;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CallerInfoTest {
  @Test public void createNotValidCallerTest() {
    CallerInfo callerInfo = new CallerInfo();
    assertEquals(callerInfo.obtainlink(), "(NotFound:0)");
  }

  @Test public void createValidCallerTest() {
    CallerInfo callerInfo = new CallerInfo(CallerInfoTest.class.getName(), 1);
    assertTrue(callerInfo.obtainlink().contains("(" + CallerInfoTest.class.getName() + ":"));
  }
}
