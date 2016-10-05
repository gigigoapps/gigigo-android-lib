package com.gigigo.ggglib.network.context.mappers;

import com.gigigo.gggjavalib.business.model.BusinessContentType;
import com.gigigo.ggglib.network.context.responses.MockApiErrorResponse;
import com.gigigo.ggglib.network.mappers.ApiGenericResponseMapper;
import com.gigigo.gggjavalib.business.model.BusinessError;
import com.gigigo.ggglib.mappers.ExternalClassToModelMapper;
import com.gigigo.ggglib.network.responses.ApiGenericExceptionResponse;


public class BaseTestApiResponseMapper<Model,Data> extends
    ApiGenericResponseMapper<Model, Data, MockApiErrorResponse> {

  public BaseTestApiResponseMapper(ExternalClassToModelMapper<Model, Data> mapper) {
    super(mapper);
  }

  @Override
  protected BusinessError createBusinessError(MockApiErrorResponse mockApiErrorResponse, Data result) {
    int code = mockApiErrorResponse.getCode();
    String message = mockApiErrorResponse.getMessage();
    BusinessError businessError = new BusinessError(code, message, BusinessContentType.BUSINESS_ERROR_CONTENT);
    return businessError;
  }

  @Override protected BusinessError onException(ApiGenericExceptionResponse exceptionResponse) {
    int code = BusinessError.EXCEPTION_BUSINESS_ERROR_CODE;
    String message = exceptionResponse.getBusinessError().getMessage();
    BusinessError businessError = new BusinessError(code, message, BusinessContentType.EXCEPTION_CONTENT);
    return businessError;
  }
}
