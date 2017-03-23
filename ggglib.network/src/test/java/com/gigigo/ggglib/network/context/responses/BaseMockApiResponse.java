package com.gigigo.ggglib.network.context.responses;

import com.gigigo.ggglib.network.responses.ApiGenericResponse;
import com.gigigo.ggglib.network.responses.HttpResponse;
import com.google.gson.annotations.SerializedName;


public class BaseMockApiResponse<JSONData> implements
    ApiGenericResponse<JSONData, MockApiErrorResponse> {

  @SerializedName("status")
  private String status;
  @SerializedName("data")
  private JSONData data;
  @SerializedName("error")
  private MockApiErrorResponse error;

  private HttpResponse httpResponse;

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @Override public HttpResponse getHttpResponse() {
    return httpResponse;
  }

  @Override public void setHttpResponse(HttpResponse httpResponse) {
    this.httpResponse = httpResponse;
  }

  @Override public boolean isException() {
    return false;
  }

  public JSONData getData() {
    return data;
  }

  public void setData(JSONData data) {
    this.data = data;
  }

  @Override public JSONData getResult() {
    return data;
  }

  @Override public void setResult(JSONData data) {
    this.data = data;
  }

  @Override public void setBusinessError(MockApiErrorResponse error) {
    this.error = error;
  }

  @Override public MockApiErrorResponse getBusinessError() {
    return error;
  }

  public static BaseMockApiResponse newExceptionInstance(){
    return new BaseMockApiResponse();
  }

}
