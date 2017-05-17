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

import java.util.ArrayList;
import java.util.List;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class ConsistencyUtilsTest {

  @Rule public ExpectedException thrown = ExpectedException.none();

  @Test public void testObjectNullWhenObjectIsNotNullReturnFalse() {
    boolean isNull = ConsistencyUtils.isObjectNull(new Object());
    assertEquals(false, isNull);
  }

  @Test public void testObjectNullWhenObjectIsNullReturnTrue() {
    boolean isNull = ConsistencyUtils.isObjectNull(null);
    assertEquals(true, isNull);
  }

  @Test public void testStringEmptyWhenStringIsEmptyButNotNullReturnTrue() {
    boolean isEmpty = ConsistencyUtils.isStringEmpty("     ");
    assertEquals(true, isEmpty);
  }

  @Test public void testStringEmptyWhenStringIsEmptyReturnTrue() {
    boolean isEmpty = ConsistencyUtils.isStringEmpty("");
    assertEquals(true, isEmpty);
  }

  @Test public void testStringEmptyWhenStringIsNotEmptyReturnFalse() {
    boolean isEmpty = ConsistencyUtils.isStringEmpty("test");
    assertEquals(false, isEmpty);
  }

  @Test public void testStringEmptyWhenStringIsNullReturnFalse() {
    boolean isEmpty = ConsistencyUtils.isStringEmpty(null);
    assertEquals(false, isEmpty);
  }

  @Test public void testInstanceOfWhenIsInstanceReturnTrue() {
    Object object = new Object();
    boolean isInstance = ConsistencyUtils.isInstanceOf(object, new Object().getClass());
    assertEquals(true, isInstance);
  }

  @Test public void testInstanceOfWhenIsNotInstanceReturnFalse() {
    Object object = new Object();
    String otherObject = new String();
    boolean isInstance = ConsistencyUtils.isInstanceOf(object, otherObject.getClass());
    assertEquals(false, isInstance);
  }

  @Test public void testHasCollectionNullItemWhenHasNullReturnTrue() {
    List collection = new ArrayList();
    collection.add(null);
    boolean hasItemNull = ConsistencyUtils.hasCollectionNullItem(collection);
    assertEquals(true, hasItemNull);
  }

  @Test public void testHasCollectionNullItemWhenHasNotNullReturnFalse() {
    List collection = new ArrayList();
    collection.add(new Object());
    boolean hasItemNull = ConsistencyUtils.hasCollectionNullItem(collection);
    assertEquals(false, hasItemNull);
  }

  @Test public void testIsCollectionEmptyWhenCollectionEmptyReturnTrue() {
    List collection = new ArrayList();
    boolean isEmpty = ConsistencyUtils.isCollectionEmpty(collection);
    assertEquals(true, isEmpty);
  }

  @Test public void testIsCollectionEmptyWhenCollectionNotEmptyReturnFalse() {
    List collection = new ArrayList();
    collection.add(new Object());
    boolean isEmpty = ConsistencyUtils.isCollectionEmpty(collection);
    assertEquals(false, isEmpty);
  }

  @Test public void isCollectionEmptyWithNullItemReturnTrue() {
    List collection = new ArrayList();
    collection.add(null);
    collection.add(null);
    boolean isEmpty = ConsistencyUtils.isCollectionEmpty(collection);
    assertEquals(true, isEmpty);
  }
}
