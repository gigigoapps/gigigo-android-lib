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

public final class GGGLogImpl {

  private static final LogLevel DEFAULT_LOGLEVEL = LogLevel.DEBUG;

  private GGGLogImpl() {
  }

  public static void log(String text) {
    logImpl(text, 1);
  }

  public static void log(String text, boolean showTrace) {
    logImpl(text, showTrace, 1);
  }

  public static void log(String text, LogLevel logLevel) {
    logImpl(text, logLevel, true, 1);
  }

  public static void log(String text, LogLevel logLevel, boolean showTrace) {
    logImpl(text, logLevel, false, 1);
  }

  public static void log(String text, LogLevel logLevel, String tag) {
    logImpl(text, logLevel, tag, false, 1);
  }

  public static void log(String text, LogLevel logLevel, String tag, int traceExtraLevels) {
    logImpl(text, logLevel, tag, true, traceExtraLevels + 1);
  }

  public static void log(String text, LogLevel logLevel, String tag, boolean showTrace) {
    logImpl(text, logLevel, tag, showTrace, 1);
  }

  private static void logImpl(String text, int stackLevels) {
    logImpl(text, DEFAULT_LOGLEVEL, null, stackLevels + 1);
  }

  private static void logImpl(String text, boolean showTrace, int stackLevels) {
    logImpl(text, DEFAULT_LOGLEVEL, null, showTrace, stackLevels + 1);
  }

  private static void logImpl(String text, LogLevel logLevel, boolean showTrace, int stackLevels) {
    logImpl(text, logLevel, null, showTrace, stackLevels + 1);
  }

  private static void logImpl(String text, LogLevel logLevel, String tag, int stackLevels) {
    logImpl(text, logLevel, tag, false, stackLevels + 1);
  }

  private static void logImpl(String text, LogLevel logLevel, String tag, boolean showTrace,
      int stackLevels) {

    LogProcessor logProcessor = new LogProcessor(showTrace);

    String processedText = logProcessor.processTextToShow(text, stackLevels + 1);
    String processedTag = logProcessor.processTagToShow(tag);

    AndroidLogger.deliverToAndroidLog(processedText, logLevel, processedTag);
  }
}
