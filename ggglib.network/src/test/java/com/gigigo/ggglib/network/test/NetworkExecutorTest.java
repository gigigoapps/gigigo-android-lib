package com.gigigo.ggglib.network.test;

import com.gigigo.ggglib.context.TestUtils;
import com.gigigo.ggglib.network.context.BaseApiClient;
import com.gigigo.ggglib.network.context.collaborators.DefatultErrorConverterImpl;
import com.gigigo.ggglib.network.context.collaborators.DefaultRetryOnErrorPolicyImpl;
import com.gigigo.ggglib.network.context.responses.MockApiErrorResponse;
import com.gigigo.ggglib.network.context.responses.MockApiResponse;
import com.gigigo.ggglib.network.context.collaborators.NoExceptionRetryOnErrorPolicyImpl;
import com.gigigo.ggglib.network.context.responses.TestMockData;
import com.gigigo.ggglib.network.converters.ErrorConverter;
import com.gigigo.ggglib.network.defaultelements.RetryOnErrorPolicy;
import com.gigigo.ggglib.network.executors.ApiServiceExecutor;
import com.gigigo.ggglib.network.executors.RetrofitApiServiceExcecutor;
import com.gigigo.ggglib.network.responses.ApiGenericExceptionResponse;
import com.gigigo.ggglib.network.responses.ApiGenericResponse;
import com.gigigo.ggglib.network.responses.HttpResponse;
import com.squareup.okhttp.mockwebserver.Dispatcher;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.okhttp.mockwebserver.RecordedRequest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static junit.framework.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class NetworkExecutorTest {

    private final static int ERROR_RESPONSE_CODE = 500;

    private MockWebServer server;
    private Retrofit retrofit;
    private BaseApiClient apiClient;

    @Before
    public void setUp() throws Exception {
        this.server = new MockWebServer();
        server.setDispatcher(getMockwebserverDispatcherInstance());
        apiClient = initializeApiclient();
    }


    @Test(expected = IllegalStateException.class)
    public void serviceExecutorInstanceBuilderTest1() throws Exception {
        new RetrofitApiServiceExcecutor.Builder()
                .errorConverter(null)
                .retryOnErrorPolicy(null).build();
    }

    @Test(expected = IllegalStateException.class)
    public void serviceExecutorInstanceBuilderTest2() throws Exception {

        new RetrofitApiServiceExcecutor.Builder()
                .errorConverter(buildErrorConverterInstance())
                .retryOnErrorPolicy(null).build();
    }
/*
  @Test public void apiServiceOKExecutorTest() throws Exception {

    ApiServiceExecutor apiServiceExecutor = getServiceExecutorInstance();

    ApiGenericResponse apiGenericResponse = apiServiceExecutor.executeNetworkServiceConnection(
        MockApiResponse.class, apiClient.testHttpConnection("ok"));

    TestMockData testResponse = (TestMockData) apiGenericResponse.getResult();

    assertEquals(testResponse.getTest(), "Hello World");
    assertEquals(apiGenericResponse.getHttpResponse().getHttpStatus(),200);

  }



  @Test public void apiServiceErrorExecutorTest() throws Exception {

    ApiServiceExecutor apiServiceExecutor = getServiceExecutorInstance();

    ApiGenericResponse response = apiServiceExecutor.executeNetworkServiceConnection(
        MockApiResponse.class, apiClient.testHttpConnection("error"));

    MockApiErrorResponse testResponse = (MockApiErrorResponse) response.getBusinessError();

    assertEquals(testResponse.getMessage(), "Error.");
    assertEquals(response.getHttpResponse().getHttpStatus(), ERROR_RESPONSE_CODE);

  }
*/

    @Test(expected = Exception.class)
    public void apiServiceBadExecutorExceptionTest() throws Exception {

        ApiServiceExecutor apiServiceExecutor = getServiceExecutorInstance();

        apiServiceExecutor.executeNetworkServiceConnection(MockApiResponse.class,
                apiClient.testHttpConnection("bad"));
    }

    @Test
    public void apiServiceBadExecutorExceptionErrorBusinessTest() throws Exception {

        ApiServiceExecutor apiServiceExecutor = getServiceExecutorExceptionHandlerInstance();

        ApiGenericResponse apiGenericResponse = apiServiceExecutor.executeNetworkServiceConnection(
                MockApiResponse.class, apiClient.testHttpConnection("bad"));

        Exception e = (Exception) apiGenericResponse.getBusinessError();
        HttpResponse httpResponse = apiGenericResponse.getHttpResponse();
        assertNotNull(e);
        assertEquals(httpResponse.getHttpStatus(), ApiGenericExceptionResponse.HTTP_EXCEPTION_CODE);

    }

    @After
    public void tearDown() throws Exception {
        server.shutdown();
    }

    private Dispatcher getMockwebserverDispatcherInstance() {
        return new Dispatcher() {
            @Override
            public MockResponse dispatch(RecordedRequest request)
                    throws InterruptedException {
                try {

                    if (request.getPath().equals("/ok/")) {
                        return new MockResponse().setResponseCode(200).setBody(
                                TestUtils.getContentFromFile("testOkHttp.json", this));
                    } else if (request.getPath().equals("/error/")) {
                        return new MockResponse().setStatus("HTTP/1.1 500 KO")
                                .setBody(TestUtils.getContentFromFile("testErrorHttp.json", this));
                    } else {
                        return new MockResponse().setResponseCode(ERROR_RESPONSE_CODE).setBody(
                                TestUtils.getContentFromFile("testBadHttp.json", this));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return new MockResponse().setResponseCode(ERROR_RESPONSE_CODE).setBody("");
                }
            }
        };
    }

    private ApiServiceExecutor getServiceExecutorExceptionHandlerInstance() {
        return new RetrofitApiServiceExcecutor.Builder()
                .errorConverter(buildErrorConverterInstance())
                .retryOnErrorPolicy(buildErrorPolicyInstanceWithoutThrowException()).build();
    }

    private RetrofitApiServiceExcecutor getServiceExecutorInstance() {
        return new RetrofitApiServiceExcecutor.Builder()
                .errorConverter(buildErrorConverterInstance())
                .retryOnErrorPolicy(buildErrorPolicyInstance()).build();
    }

    private BaseApiClient initializeApiclient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(server.url("/").toString())
                .addConverterFactory(GsonConverterFactory.create()).build();
        // server.start() is auto-magically called after doing this
        this.retrofit = retrofit;
        return retrofit.create(BaseApiClient.class);
    }

    private RetryOnErrorPolicy buildErrorPolicyInstance() {
        return new DefaultRetryOnErrorPolicyImpl();
    }

    private RetryOnErrorPolicy buildErrorPolicyInstanceWithoutThrowException() {
        return new NoExceptionRetryOnErrorPolicyImpl();
    }

    private ErrorConverter buildErrorConverterInstance() {
        return new DefatultErrorConverterImpl(retrofit, MockApiResponse.class);
    }


}