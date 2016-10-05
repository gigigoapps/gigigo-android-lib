package com.gigigo.ggglib.network.test;

import com.gigigo.ggglib.model.TestMock;
import com.gigigo.ggglib.network.context.mappers.BaseTestApiResponseMapper;
import com.gigigo.ggglib.network.context.mappers.TestMapper;
import com.gigigo.ggglib.network.context.responses.MockApiErrorResponse;
import com.gigigo.ggglib.network.context.responses.MockApiResponse;
import com.gigigo.ggglib.network.context.responses.TestMockData;
import com.gigigo.ggglib.network.executors.ApiServiceExecutor;
import com.gigigo.ggglib.network.mappers.ApiGenericResponseMapper;
import com.gigigo.gggjavalib.business.model.BusinessContentType;
import com.gigigo.gggjavalib.business.model.BusinessObject;
import com.gigigo.ggglib.network.responses.ApiGenericExceptionResponse;
import com.gigigo.ggglib.network.responses.ApiGenericResponse;
import com.gigigo.ggglib.network.responses.HttpResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.Assert.assertEquals;

import static org.mockito.Mockito.when;


public class ApiGenericResponseMapperTest {

  ApiServiceExecutor apiServiceExecutor;
  ApiGenericResponseMapper apiGenericResponseMapper;

  @Before public void setUp(){

    apiServiceExecutor = Mockito.mock(ApiServiceExecutor.class);
    apiGenericResponseMapper = new BaseTestApiResponseMapper(new TestMapper());

    when(apiServiceExecutor.executeNetworkServiceConnection(MockApiResponse.class, "ok")).thenReturn(
        mockApiResponseOkClass());

    when(apiServiceExecutor.executeNetworkServiceConnection(MockApiResponse.class, "error"))
        .thenReturn(mockApiResponseBusinessErrorClass());

    when(apiServiceExecutor.executeNetworkServiceConnection(MockApiResponse.class, "bad"))
        .thenReturn(mockApiResponseExceptionClass());

  }

  @Test public void mapperOkResultTest() throws Exception {

    ApiGenericResponse apiGenericResponse = apiServiceExecutor.executeNetworkServiceConnection(
        MockApiResponse.class, "ok");

    BusinessObject<TestMock> bo = apiGenericResponseMapper.mapApiGenericResponseToBusiness(
        apiGenericResponse);

    assertEquals(bo.getBusinessError().getBusinessContentType(), BusinessContentType.NO_ERROR_CONTENT);
    assertEquals(bo.getData().getTest(), "Hello World");

  }

  @Test public void mapperErrorResultTest() throws Exception {

    ApiGenericResponse apiGenericResponse = apiServiceExecutor.executeNetworkServiceConnection(
        MockApiResponse.class, "error");

    BusinessObject<TestMock> bo = apiGenericResponseMapper.mapApiGenericResponseToBusiness(
        apiGenericResponse);

    assertEquals(bo.getBusinessError().getBusinessContentType(), BusinessContentType.BUSINESS_ERROR_CONTENT);
    assertEquals(bo.getBusinessError().getMessage(), "bad User");

  }

  @Test public void mapperbadResultTest() throws Exception {

    ApiGenericResponse apiGenericResponse = apiServiceExecutor.executeNetworkServiceConnection(
        MockApiResponse.class, "bad");

    BusinessObject<TestMock> bo = apiGenericResponseMapper.mapApiGenericResponseToBusiness(
        apiGenericResponse);

    assertEquals(bo.getBusinessError().getBusinessContentType(),
        BusinessContentType.EXCEPTION_CONTENT);
  }

  private ApiGenericResponse mockApiResponseOkClass() {

    TestMockData testMockData = new TestMockData();
    testMockData.setTest("Hello World");

    HttpResponse httpResponse = new HttpResponse(200, "OK");

    MockApiResponse mockApiResponse = new MockApiResponse();
    mockApiResponse.setData(testMockData);
    mockApiResponse.setHttpResponse(httpResponse);

    return mockApiResponse;
  }

  private ApiGenericResponse mockApiResponseBusinessErrorClass() {

    MockApiErrorResponse mockApiErrorResponse = new MockApiErrorResponse(1550, "bad User");
    HttpResponse httpResponse = new HttpResponse(500, "KO");

    MockApiResponse mockApiResponse = new MockApiResponse();
    mockApiResponse.setBusinessError(mockApiErrorResponse);
    mockApiResponse.setHttpResponse(httpResponse);

    return mockApiResponse;
  }

  private ApiGenericResponse mockApiResponseExceptionClass() {

    Exception e = new Exception("This is a mock exception");
    ApiGenericExceptionResponse apiGenericExceptionResponse = new ApiGenericExceptionResponse(e);

    return apiGenericExceptionResponse;
  }

}
