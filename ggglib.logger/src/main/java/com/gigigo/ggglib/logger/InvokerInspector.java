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

public class InvokerInspector {

  private CallerInfo callerInfo;

  /**
   * @param stepsBack meaning how many stacktraces whe want to go back, if you want the call to
   * this callingmethod put 0 if you want the previous line that call to this callingmethod,
   * for example if you put the caller of getinvoker in a catch and you want to retrive the
   * line fires the catch put 1
   * @param gggLogClass to-do
   */
  public void calculateInvoker(int stepsBack, Class gggLogClass) {

    try {
      String whoAmI = gggLogClass.getName();
      Thread thread = Thread.currentThread();
      StackTraceElement[] tracesArray = thread.getStackTrace();

      int lastAppearOfWhoIam = 0;
      StackTraceElement stackTrace;

      for (int j = 0; j < tracesArray.length; j++) {
        stackTrace = tracesArray[j];
        if (stackTrace.getClassName().equals(whoAmI)) {
          lastAppearOfWhoIam = j;
          break;
        }
      }

      if (lastAppearOfWhoIam != 0) {
        StackTraceElement trace4Response =
            tracesArray[lastAppearOfWhoIam + stepsBack]; //+1 for this kind of implementation
        this.callerInfo =
            new CallerInfo(trace4Response.getFileName(), trace4Response.getLineNumber());
      } else {
        this.callerInfo = new CallerInfo();
      }
    } catch (Exception e) {
      this.callerInfo = new CallerInfo();
    }
  }

  public String obtainInvokerClassName() {
    if (callerInfo == null) {
      callerInfo = new CallerInfo();
    }
    return callerInfo.getClassName();
  }

  public String obtainInvokerLine() {
    if (callerInfo == null) {
      callerInfo = new CallerInfo();
    }
    return callerInfo.obtainlink();
  }
}
