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

package com.gigigo.ggglib.core.business.model;

import com.gigigo.ggglib.core.business.model.BusinessError;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

public class BusinessObjectTest {

  @Test
  public void testOKErrorBusinessInstance() throws Exception {
    BusinessError businessError = BusinessError.createOKInstance();
    assertNotNull(businessError);
    assertEquals(businessError.getCode(), BusinessError.NO_ERROR_BUSINESS_ERROR_CODE);
    assertEquals(businessError.getMessage(), BusinessError.NO_ERROR_BUSINESS_ERROR_MESSAGE);

  }
}
