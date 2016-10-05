package com.gigigo.ggglib.network.context;

import com.gigigo.ggglib.network.context.responses.GitHubResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GitHubApiClient {

  @GET("users/defunkt") Call<GitHubResponse> getOneUser();

}
