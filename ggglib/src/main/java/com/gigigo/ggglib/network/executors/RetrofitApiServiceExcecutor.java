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

package com.gigigo.ggglib.network.executors;

import com.gigigo.ggglib.network.converters.ErrorConverter;
import com.gigigo.ggglib.network.defaultelements.RetryOnErrorPolicy;
import com.gigigo.ggglib.network.responses.ApiGenericExceptionResponse;
import com.gigigo.ggglib.network.responses.ApiGenericResponse;
import com.gigigo.ggglib.network.responses.HttpResponse;
import com.gigigo.ggglogger.GGGLogImpl;
import com.gigigo.ggglogger.LogLevel;

import java.io.IOException;
import retrofit2.Call;
import retrofit2.Response;

public class RetrofitApiServiceExcecutor implements ApiServiceExecutor<Call<?>> {

  private RetryOnErrorPolicy retryOnErrorPolicy;
  private ErrorConverter errorConverter;

  public RetrofitApiServiceExcecutor(RetryOnErrorPolicy retryOnErrorPolicy, ErrorConverter errorConverter) {
    this.retryOnErrorPolicy = retryOnErrorPolicy;
    this.errorConverter = errorConverter;
  }

  @Override public <ApiResponse extends ApiGenericResponse> ApiGenericResponse
  executeNetworkServiceConnection(Class<ApiResponse> responseType, Call<?> requestType) {

    ApiGenericResponse apiResponse;
    Call<ApiResponse> clonedCall;
    int tries = 0;
    Exception exception = null;
    boolean success = false;

    do {
      Response<ApiResponse> retrofitResponse = null;
      try {
        tries++;
        clonedCall = (Call<ApiResponse>) requestType.clone();
        retrofitResponse = clonedCall.execute();
        success = retrofitResponse.isSuccessful();
        apiResponse = parseRetrofitResponseToApi(retrofitResponse);
      } catch (Exception e) {
        exception = e;
        apiResponse = ApiGenericExceptionResponse.getApiGenericExceptionResponseInstace(e);

        GGGLogImpl.log(e.getMessage(), LogLevel.ERROR);
      }
    }while (shouldRetry(tries, apiResponse, success, exception));

    return apiResponse;
  }

  private <ApiResponse extends ApiGenericResponse> ApiGenericResponse parseRetrofitResponseToApi(
      Response<ApiResponse> retrofitResponse) throws IOException {

    ApiResponse apiResponse;

    if (retrofitResponse.isSuccessful()){
      apiResponse = retrofitResponse.body();
    }else{
      apiResponse = buildApiErrorResponse(retrofitResponse);
    }

    apiResponse.setHttpResponse(new HttpResponse(retrofitResponse.code(),retrofitResponse.message()));
    return apiResponse;

  }

  private <ApiResponse> ApiResponse buildApiErrorResponse(
      Response<ApiResponse> retrofitResponse) throws IOException {
    return (ApiResponse) errorConverter.convert(retrofitResponse.errorBody());
  }

  private boolean shouldRetry(int tries, ApiGenericResponse apiGenericResponse, boolean success,
      Exception e) {

    if (success){
      return false;
    }else{
      return retryPolicyResult(tries, apiGenericResponse, e);
    }
  }

  private boolean retryPolicyResult(int tries, ApiGenericResponse apiResponse, Exception e) {
    if (e!=null){
      return exceptionRetryPolicyResult(tries, e);
    }else{
      return businessErrorRetryPolicyResult(tries, apiResponse);
    }
  }

  private boolean businessErrorRetryPolicyResult(int tries, ApiGenericResponse apiResponse) {
    Object businessResponse = apiResponse.getBusinessError();
    HttpResponse httpResponse = apiResponse.getHttpResponse();
    return retryOnErrorPolicy.shouldRetryWithErrorAndTries(tries, businessResponse, httpResponse);
  }

  private boolean exceptionRetryPolicyResult(int tries, Exception e) {
    return retryOnErrorPolicy.shouldRetryOnException(tries, e);
  }

  public static final class Builder {
    private RetryOnErrorPolicy retryOnErrorPolicy;
    private ErrorConverter errorConverter;

    public Builder() {
    }

    public Builder retryOnErrorPolicy(RetryOnErrorPolicy retryOnErrorPolicy) {
      this.retryOnErrorPolicy = retryOnErrorPolicy;
      return this;
    }

    public Builder errorConverter(ErrorConverter errorConverter) {
      this.errorConverter = errorConverter;
      return this;
    }

    public RetrofitApiServiceExcecutor build() {

      ErrorConverter errorConverter = this.errorConverter;
      if (errorConverter == null) {
        throw new IllegalStateException("ErrorConverter Instance required.");
      }

      RetryOnErrorPolicy retryOnErrorPolicy = this.retryOnErrorPolicy;
      if (retryOnErrorPolicy == null) {
        throw new IllegalStateException("RetryOnErrorPolicy Instance required.");
      }

      return new RetrofitApiServiceExcecutor(retryOnErrorPolicy, errorConverter);
    }
  }
}
