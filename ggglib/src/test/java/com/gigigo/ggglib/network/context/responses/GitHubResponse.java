package com.gigigo.ggglib.network.context.responses;

import com.gigigo.ggglib.network.responses.ApiGenericResponse;
import com.gigigo.ggglib.network.responses.HttpResponse;
import com.google.gson.annotations.SerializedName;


public class GitHubResponse implements ApiGenericResponse<GitHubResponse,GitHubErrorResponse>{

  @SerializedName("login")
  private String login;

  @SerializedName("id")
  private Integer id;

  @SerializedName("avatar_url")
  private String avatarUrl;

  @SerializedName("name")
  private String name;

  @SerializedName("email")
  private String email;

  @SerializedName("bio")
  private Object bio;

  private HttpResponse httpResponse;

  @Override public HttpResponse getHttpResponse() {
    return httpResponse;
  }

  @Override public void setHttpResponse(HttpResponse httpResponse) {
    this.httpResponse = httpResponse;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getAvatarUrl() {
    return avatarUrl;
  }

  public void setAvatarUrl(String avatarUrl) {
    this.avatarUrl = avatarUrl;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Object getBio() {
    return bio;
  }

  public void setBio(Object bio) {
    this.bio = bio;
  }

  @Override public GitHubResponse getResult() {
    return this;
  }

  @Override public GitHubErrorResponse getBusinessError() {
    return null;
  }

  @Override public void setResult(GitHubResponse gitHubResponse) {
    this.avatarUrl = gitHubResponse.avatarUrl;
    this.bio = gitHubResponse.bio;
    this.login = gitHubResponse.login;
    this.id = gitHubResponse.id;
    this.email = gitHubResponse.email;
    this.name = gitHubResponse.name;

  }

  @Override public void setBusinessError(GitHubErrorResponse businessError) {

  }

  @Override public boolean isException() {
    return false;
  }

  @Override public String toString() {
    return "GitHubResponse{" +
        "login='" + login + '\'' +
        ", id=" + id +
        ", avatarUrl='" + avatarUrl + '\'' +
        ", name='" + name + '\'' +
        ", email='" + email + '\'' +
        ", bio=" + bio +
        ", httpResponse=" + httpResponse +
        '}';
  }
}
