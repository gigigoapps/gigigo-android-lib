package com.gigigo.ggglib.network.context;

import com.gigigo.ggglib.network.context.responses.MockApiResponse;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BaseApiClient {

  @POST("{type}/") Call<MockApiResponse> testHttpConnection(@Path("type") String callType);

}
