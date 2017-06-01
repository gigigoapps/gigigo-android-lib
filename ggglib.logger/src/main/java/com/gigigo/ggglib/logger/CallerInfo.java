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

public class CallerInfo {

  private static final String NOT_FOUND = "NotFound";

  private String className;

  private int classLineNumber;

  public CallerInfo() {
    className = NOT_FOUND;
    classLineNumber = 0;
  }

  public CallerInfo(String className, int classLineNumber) {
    this.className = className;
    this.classLineNumber = classLineNumber;
  }

  public String getClassName() {
    return className;
  }

  public String obtainlink() {
    return "(" + className + ":" + classLineNumber + ")";
  }
}
