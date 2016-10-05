package com.gigigo.ggglib.network.test;

import com.gigigo.ggglib.context.TestUtils;
import com.gigigo.ggglib.network.context.GitHubApiClient;
import com.gigigo.ggglib.network.context.collaborators.DefatultErrorConverterImpl;
import com.gigigo.ggglib.network.context.collaborators.GithubRetryOnErrorPolicyImpl;
import com.gigigo.ggglib.network.context.responses.GitHubErrorResponse;
import com.gigigo.ggglib.network.context.responses.GitHubResponse;
import com.gigigo.ggglib.network.converters.ErrorConverter;
import com.gigigo.ggglib.network.defaultelements.RetryOnErrorPolicy;
import com.gigigo.ggglib.network.executors.ApiServiceExecutor;
import com.gigigo.ggglib.network.executors.RetrofitApiServiceExcecutor;
import com.gigigo.ggglib.network.responses.ApiGenericResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class GihubNetworkExecutorTest {

  private Retrofit retrofit;
  private GitHubApiClient apiClient;

  @Before public void setUp()throws Exception{
    apiClient = initializeApiclient();
  }

  @After public void tearDown()throws Exception {
  }

  /**
   * Measure of time and allocated objects
   * @throws Exception
   */
  @Test public void apiServiceCloneExecutorTest() throws Exception {
    TestUtils.printMemoryInform();

    for(int i=0; i<1; i++){
      Thread t = new Thread(new Runnable() {
      @Override public void run() {

          ApiServiceExecutor apiServiceExecutor = getServiceExecutorInstance();

          long startTime = System.currentTimeMillis();

          ApiGenericResponse apiGenericResponse = apiServiceExecutor.executeNetworkServiceConnection(
              GitHubResponse.class,
              apiClient.getOneUser());

          GitHubResponse gitHubResponse = (GitHubResponse) apiGenericResponse.getResult();

          long stopTime = System.currentTimeMillis();
          long elapsedTime = stopTime - startTime;

          assertNotNull(gitHubResponse);
          assertTrue(elapsedTime > 100);

          System.out.println("Time for request -->" + elapsedTime);
        }
    });
      t.start();
    }

    Thread.sleep(3000);

    TestUtils.printMemoryInform();

  }

  private RetrofitApiServiceExcecutor getServiceExecutorInstance() {
    return new RetrofitApiServiceExcecutor.Builder()
        .errorConverter(buildErrorConverterInstance())
        .retryOnErrorPolicy(buildErrorPolicyInstance()).build();
  }

  private GitHubApiClient initializeApiclient() {

    Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.github.com")
        .addConverterFactory(GsonConverterFactory.create()).build();

    this.retrofit = retrofit;
    return retrofit.create(GitHubApiClient.class);
  }

  private RetryOnErrorPolicy buildErrorPolicyInstance() {
    return new GithubRetryOnErrorPolicyImpl();
  }

  private ErrorConverter buildErrorConverterInstance() {
    return new DefatultErrorConverterImpl(retrofit, GitHubErrorResponse.class);
  }

}