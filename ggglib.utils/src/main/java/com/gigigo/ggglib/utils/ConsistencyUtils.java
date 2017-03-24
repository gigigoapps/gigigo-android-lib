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

final public class ConsistencyUtils {

  public static <T> T checkNotNull(T object) {
    return checkNotNull(object, "The object is null");
  }

  public static <T> T checkNotNull(T object, String message) {
    if (object == null) {
      throw new NullPointerException(message);
    }
    return object;
  }

  public static String checkEmptyString(String object) {
    return checkEmptyString(object, "The string is null");
  }

  public static String checkEmptyString(String object, String message) {
    object = checkNotNull(object, message);
    if (object.trim() == "") {
      throw new NullPointerException(message);
    }
    return object;
  }

  public static <T> T checkInstance(Object obj, Class<T> type) {
    if (type.isInstance(obj)) {
      T t = type.cast(obj);
      return t;
    } else {
      throw new ClassCastException(obj.getClass() + " incompatible type with " + type.getName());
    }
  }

  public static <T> Collection<T> checkNotEmpty(Collection<T> container) {
    if (checkNotNull(container).isEmpty()) {
      throw new IllegalArgumentException("Container cannot be null or empty");
    }
    return container;
  }

  public static <T> Collection<T> containsNoNulls(Collection<T> container) {
    container = checkNotNull(container);
    for (T item : container) {
      if (item == null) {
        throw new NullPointerException("Container cannot contain null values");
      }
    }
    return container;
  }
}
