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

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

final public class Hashing {

  private Hashing() { }

  public static String generateMd5(String string)
      throws UnsupportedEncodingException, NoSuchAlgorithmException {

    byte[] bytesOfMessage = string.getBytes("UTF-8");
    MessageDigest md = MessageDigest.getInstance("MD5");
    byte[] thedigest = md.digest(bytesOfMessage);

    StringBuffer sb = new StringBuffer();

    for (int i = 0; i < thedigest.length; ++i) {
      sb.append(Integer.toHexString((thedigest[i] & 0xFF) | 0x100).substring(1, 3));
    }

    return sb.toString();
  }
}
