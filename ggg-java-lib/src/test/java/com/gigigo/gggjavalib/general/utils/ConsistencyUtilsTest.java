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

package com.gigigo.gggjavalib.general.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class ConsistencyUtilsTest {

  @Rule public ExpectedException thrown = ExpectedException.none();

  @Test public void utilsNotNullTestNull() {
    thrown.expect(Exception.class);
    thrown.expectMessage("This instance cannot be null");

    ConsistencyUtils.checkNotNull(null, "This instance cannot be null");
  }

  @Test public void utilsNotNullTestOK() {
    String testString =
        ConsistencyUtils.checkNotNull("Hello World", "This instance cannot be null");
    assertEquals(testString, "Hello World");
  }

  @Test public void utilsNotNullNoMessageTestOK() {
    String testString = ConsistencyUtils.checkNotNull("Hello World");
    assertEquals(testString, "Hello World");
  }

  @Test public void utilsEmptyStringTestNull() {
    thrown.expect(Exception.class);
    thrown.expectMessage("This String cannot be null");

    ConsistencyUtils.checkEmptyString(null, "This String cannot be null");
  }

  @Test public void utilsEmptyStringTestEmpty() {
    thrown.expect(Exception.class);
    thrown.expectMessage("This String cannot be empty");

    ConsistencyUtils.checkEmptyString("", "This String cannot be empty");
  }

  @Test public void utilsEmptyStringTestNullNoMessage() {
    thrown.expect(NullPointerException.class);

    ConsistencyUtils.checkEmptyString(null);
  }

  @Test public void utilsEmptyStringTestOK() {
    String testString = ConsistencyUtils.checkEmptyString("Hello World");
    assertEquals(testString, "Hello World");
  }

  @Test public void testCheckInstanceIncorrect() throws Exception {
    assertEquals(new Integer(10), ConsistencyUtils.checkInstance(new Integer(10), Integer.class));
  }

  @Test public void testCheckNullInstance() throws Exception {
    Integer integer = new Integer(10);

    thrown.expect(ClassCastException.class);
    thrown.expectMessage(integer.getClass() + " incompatible type with " + Float.class.getName());

    ConsistencyUtils.checkInstance(integer, Float.class);
  }

  @Test public void shouldReturnSameObjectWhenCollectionHasItems() throws Exception {
    List<String> list = new ArrayList<>();
    list.add("0");

    Collection<String> returnedList = ConsistencyUtils.checkNotEmpty(list);

    assertSame(list, returnedList);
  }

  @Test public void shouldThrowNullPointerExceptionWhenCollectionIsNull() throws Exception {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("The object is null");

    List<String> list = null;

    ConsistencyUtils.checkNotEmpty(list);
  }

  @Test public void shouldThrowIllegalArgumentExceptionWhenCollectionIsEmpty() throws Exception {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("Container cannot be null or empty");

    List<String> list = new ArrayList<>();

    ConsistencyUtils.checkNotEmpty(list);
  }

  @Test public void shouldReturnSameObjectWhenCollectionNotHasNullItems() throws Exception {
    List<String> list = new ArrayList<>();
    list.add("0");

    Collection<String> returnedList = ConsistencyUtils.containsNoNulls(list);

    assertSame(list, returnedList);
  }

  @Test public void shouldThrowNullPointerExceptionWhenListIsNull() throws Exception {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("The object is null");

    List<String> list = null;

    ConsistencyUtils.containsNoNulls(list);
  }

  @Test public void shouldThrowNullPointerExceptionWhenCollectionHasNullItems() throws Exception {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("Container cannot contain null values");

    List<String> list = new ArrayList<>();
    list.add(null);

    ConsistencyUtils.containsNoNulls(list);
  }
}
