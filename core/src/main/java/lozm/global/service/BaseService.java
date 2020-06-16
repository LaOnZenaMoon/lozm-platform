package lozm.global.service;

import lozm.object.dto.ApiException;
import lozm.object.dto.ApiResponseDto;

public class BaseService {

    static public void checkResponseBody(ApiResponseDto dto, String exceptionMessage) throws ApiException {
        if(!dto.isSuccess()) {
            throw new ApiException(null, exceptionMessage);
        }
    }

}
