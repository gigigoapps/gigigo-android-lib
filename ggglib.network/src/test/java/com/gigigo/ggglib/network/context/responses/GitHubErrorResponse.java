package com.gigigo.ggglib.network.context.responses;

import com.gigigo.ggglib.network.responses.ApiGenericResponse;
import com.gigigo.ggglib.network.responses.HttpResponse;
import com.google.gson.annotations.SerializedName;


public class GitHubErrorResponse implements ApiGenericResponse<GitHubResponse,GitHubErrorResponse> {

  //Error fields
  @SerializedName("message")
  private String message;
  @SerializedName("documentation_url")
  private String documentationUrl;

  private HttpResponse httpResponse;

  @Override public HttpResponse getHttpResponse() {
    return httpResponse;
  }

  @Override public void setHttpResponse(HttpResponse httpResponse) {
    this.httpResponse = httpResponse;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getDocumentationUrl() {
    return documentationUrl;
  }

  public void setDocumentationUrl(String documentationUrl) {
    this.documentationUrl = documentationUrl;
  }

  @Override public GitHubResponse getResult() {
    return null;
  }

  @Override public GitHubErrorResponse getBusinessError() {
    return this;
  }

  @Override public void setResult(GitHubResponse gitHubResponse) {

  }

  @Override public void setBusinessError(GitHubErrorResponse businessError) {
    this.documentationUrl = businessError.documentationUrl;
    this.message = businessError.message;
  }

  @Override public boolean isException() {
    return false;
  }
}
