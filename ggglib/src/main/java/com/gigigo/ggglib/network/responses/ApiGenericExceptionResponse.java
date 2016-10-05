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

package com.gigigo.ggglib.network.responses;


public class ApiGenericExceptionResponse implements ApiGenericResponse<Object, Exception> {

  public static final int HTTP_EXCEPTION_CODE = -222;

  private Exception exception;
  private HttpResponse httpResponse;

  public ApiGenericExceptionResponse(Exception exception) {
    this.exception = exception;
    httpResponse = HttpResponse.getHttpResponseExceptionInstance(HTTP_EXCEPTION_CODE, exception);
  }

  @Override public Object getResult() {
    return null;
  }

  @Override public Exception getBusinessError() {
    return exception;
  }

  @Override public HttpResponse getHttpResponse() {
    return httpResponse;
  }

  @Override public void setResult(Object e) {}

  @Override public void setBusinessError(Exception businessError) {
    this.exception = businessError;
  }

  @Override public void setHttpResponse(HttpResponse httpResponse) {
    this.httpResponse = httpResponse;
  }

  @Override public boolean isException() {
    return true;
  }

  public static ApiGenericExceptionResponse getApiGenericExceptionResponseInstace(Exception e){
    return new ApiGenericExceptionResponse(e);
  }
}
