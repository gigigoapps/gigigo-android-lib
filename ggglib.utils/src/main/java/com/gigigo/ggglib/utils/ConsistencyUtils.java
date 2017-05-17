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

package com.gigigo.ggglib.utils;

import java.util.Collection;
import java.util.Iterator;

public final class ConsistencyUtils {

  private ConsistencyUtils() {
  }

  public static boolean isObjectNull(Object object) {
    boolean isNull = false;
    if (object == null) {
      isNull = true;
    }
    return isNull;
  }

  public static boolean isStringEmpty(String string) {
    boolean isEmpty = false;
    if (!isObjectNull(string)) {
      if (string.trim().equals("")) {
        isEmpty = true;
      }
    }
    return isEmpty;
  }

  public static <T> boolean isInstanceOf(Object obj, Class<T> type) {
    boolean isInstance = false;
    if (type.isInstance(obj)) {
      isInstance = true;
    }
    return isInstance;
  }

  public static boolean hasCollectionNullItem(Collection collection) {
    boolean hasNull = false;
    Iterator iterator = collection.iterator();
    while (iterator.hasNext() && !hasNull) {
      Object object = iterator.next();
      if (object == null) {
        hasNull = true;
      }
    }
    return hasNull;
  }

  public static boolean isCollectionEmpty(Collection collection) {
    boolean isEmpty = true;
    Iterator iterator = collection.iterator();
    while (iterator.hasNext() && isEmpty) {
      Object object = iterator.next();
      if (object != null) {
        isEmpty = false;
      }
    }
    return isEmpty;
  }
}
