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


public class LogProcessor {

  private InvokerInspector invokerInspector = null;

  public LogProcessor(boolean showTrace) {
    if (showTrace){
      invokerInspector = new InvokerInspector();
    }
  }

  public String processTextToShow(String text, int stackLevels) {
    if (invokerInspector!=null){
      invokerInspector.calculateInvoker(stackLevels+1, LogProcessor.class);
      return invokerInspector.obtainInvokerLine() + "::" + text;
    }else{
      return text;
    }
  }

  public String processTagToShow(String tag) {
    if (tag == null){
      if (invokerInspector!=null){
        return invokerInspector.obtainInvokerClassName();
      }else{
        return GGGLogImpl.class.getSimpleName();
      }
    }else{
      return tag;
    }
  }

}
