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

package com.gigigo.ggglib.network.mappers;

import com.gigigo.ggglib.core.business.model.BusinessContentType;
import com.gigigo.ggglib.core.business.model.BusinessError;
import com.gigigo.ggglib.core.business.model.BusinessObject;
import com.gigigo.ggglib.mappers.ExternalClassToModelMapper;
import com.gigigo.ggglib.network.responses.ApiGenericExceptionResponse;
import com.gigigo.ggglib.network.responses.ApiGenericResponse;


public abstract class ApiGenericResponseMapper<ModelData, ApiResponseData, ApiBusinessError> {

  private ExternalClassToModelMapper<ApiResponseData, ModelData> mapper;

  public ApiGenericResponseMapper(ExternalClassToModelMapper mapper) {
    this.mapper = mapper;
  }

  public <ApiResponse extends ApiGenericResponse> BusinessObject mapApiGenericResponseToBusiness
      (ApiResponse apiResponse){

    if (apiResponse.getResult()!=null && apiResponse.getBusinessError()==null){
      return createSuccessfulResponseBusinessObject(apiResponse);
    }else if(apiResponse.getBusinessError()!=null){
      return createErrorResponseBusinessObject(apiResponse);
    }else{
      return createEmptyOkResponseBusinessObject(apiResponse);
    }

  }

  protected <ApiResponse extends ApiGenericResponse> BusinessObject
  createEmptyOkResponseBusinessObject(ApiResponse apiResponse){
    return new BusinessObject<>(null, createCleanBusinessError());
  }

  private <ApiResponse extends ApiGenericResponse> BusinessObject
  createSuccessfulResponseBusinessObject(ApiResponse apiResponse) {

    ModelData data = mapper.externalClassToModel((ApiResponseData) apiResponse.getResult());
    return new BusinessObject<>(data, createCleanBusinessError());
  }

  private <ApiResponse extends ApiGenericResponse> BusinessObject
  createErrorResponseBusinessObject(ApiResponse apiResponse) {

    if (apiResponse.isException()){
      return createExceptionErrorResponseBusinessObject((ApiGenericExceptionResponse) apiResponse);
    }else{
      return createBusinessErrorResponseBusinessResponse(apiResponse);
    }
  }

  private <ApiResponse extends ApiGenericResponse> BusinessObject
  createBusinessErrorResponseBusinessResponse(ApiResponse apiResponse) {
    BusinessError businessError = createBusinessError(
        (ApiBusinessError) apiResponse.getBusinessError(), (ApiResponseData) apiResponse.getResult());
    businessError.setBusinessContentType(BusinessContentType.BUSINESS_ERROR_CONTENT);
    return new BusinessObject<>(null, businessError);
  }

  private  BusinessObject createExceptionErrorResponseBusinessObject(
      ApiGenericExceptionResponse apiResponse) {

    ApiGenericExceptionResponse exceptionResponse = apiResponse;
    BusinessError businessError = onException(exceptionResponse);
    businessError.setBusinessContentType(BusinessContentType.EXCEPTION_CONTENT);
    return new BusinessObject<>(null, businessError);
  }

  private BusinessError createCleanBusinessError() {

    BusinessError businessError = new BusinessError(
        BusinessError.EXCEPTION_BUSINESS_ERROR_CODE,
        BusinessError.NO_ERROR_BUSINESS_ERROR_MESSAGE,
        BusinessContentType.EXCEPTION_CONTENT);

    businessError.setBusinessContentType(BusinessContentType.NO_ERROR_CONTENT);

    return businessError;
  }

  protected abstract BusinessError onException(ApiGenericExceptionResponse exceptionResponse);

  protected abstract BusinessError createBusinessError(ApiBusinessError businessError, ApiResponseData result);

}
