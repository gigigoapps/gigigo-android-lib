package com.gigigo.ggglib.network.context.collaborators;


import com.gigigo.ggglib.network.converters.ErrorConverter;
import com.gigigo.ggglib.network.converters.RetrofitErrorConverter;

import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;

public class DefatultErrorConverterImpl<ErrorResponse> implements ErrorConverter<ErrorResponse, ResponseBody> {

  RetrofitErrorConverter retrofitErrorConverter;

  public DefatultErrorConverterImpl(Retrofit retrofit, Class<ErrorResponse> errorResponse) {
    this.retrofitErrorConverter = new RetrofitErrorConverter( retrofit, errorResponse);
  }

  @Override public ErrorResponse convert(ResponseBody value) throws IOException {
    return (ErrorResponse) retrofitErrorConverter.convert(value);
  }
}
