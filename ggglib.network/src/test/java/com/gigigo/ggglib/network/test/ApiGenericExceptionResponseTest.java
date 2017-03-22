package com.gigigo.ggglib.network.test;

import com.gigigo.ggglib.network.responses.ApiGenericExceptionResponse;

import static junit.framework.Assert.*;
import org.junit.Test;


public class ApiGenericExceptionResponseTest {

  @Test public void createExceptionResponseWithStaticMethod() {

    Exception e = new Exception("Hello Exception World");

    ApiGenericExceptionResponse apiGenericExceptionResponse =
        ApiGenericExceptionResponse.getApiGenericExceptionResponseInstace(e);

    assertNotNull(apiGenericExceptionResponse);
    assertNotNull(apiGenericExceptionResponse.getBusinessError());
    assertNotNull(apiGenericExceptionResponse.getHttpResponse());
    assertNull(apiGenericExceptionResponse.getResult());
    assertEquals(apiGenericExceptionResponse.getBusinessError().getMessage(),
        "Hello Exception World");

  }

}
