package lozm.global.advice;

import lombok.extern.slf4j.Slf4j;
import lozm.global.exception.NotFoundException;
import lozm.object.dto.ApiException;
import lozm.object.dto.ApiResponseCode;
import lozm.object.dto.ApiResponseDto;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;
import java.util.Objects;

@Slf4j
@RestControllerAdvice
public class ApiCommonAdvice {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class})
    public ApiResponseDto<String> handleBaseException(Exception e) {
        log.error("[{}] {}", ApiResponseCode.SERVER_ERROR.getId(), e);
        return ApiResponseDto.createException(ApiResponseCode.SERVER_ERROR, e.getMessage());
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler
    public ApiResponseDto<String> handleBaseException(ApiException e) {
        log.error("[{}] {}", ApiResponseCode.SERVER_ERROR.getId(), e);
        return ApiResponseDto.createException(e);
    }


    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({ConstraintViolationException.class})
    public ApiResponseDto<String> handleBaseException(ConstraintViolationException e) {
        return ApiResponseDto.createException(ApiResponseCode.BAD_PARAMETER, "파라미터가 잘못됐습니다.");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({NotFoundException.class})
    public ApiResponseDto<String> handleValidException(NotFoundException e) {
        ApiResponseDto<String> exception = ApiResponseDto.createException(new ApiException(ApiResponseCode.NOT_FOUND, e.getMessage()));
        log.error("[{}] {}", ApiResponseCode.NOT_FOUND.getId(), exception);
        return exception;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ConversionFailedException.class})
    public ApiResponseDto<String> handleValidException(ConversionFailedException e) {
        String errorMessage = String.format(" %s 요청 파라미터를 확인해주세요.", e.getTargetType().getType().getSimpleName());
        ApiResponseDto<String> exception = ApiResponseDto.createException(new ApiException(ApiResponseCode.BAD_PARAMETER, errorMessage));
        log.error("[{}] {}", ApiResponseCode.BAD_PARAMETER.getId(), exception);
        return exception;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({BindException.class})
    public ApiResponseDto<ErrorMessageCollection> handleValidException(BindException e) {

        ApiResponseDto<ErrorMessageCollection> exception = ApiResponseDto.createException(
                ApiResponseCode.BAD_PARAMETER,
                new ErrorMessageCollection(e.getBindingResult().getFieldErrors(), e.getBindingResult().getGlobalErrors()));

        log.error("[{}] {}", ApiResponseCode.BAD_PARAMETER.getId(), exception);
        return exception;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ApiResponseDto<String> handleValidException(MethodArgumentNotValidException e) {

        BindingResult result = e.getBindingResult();

        String errorMessage = String.format(" %s 요청 파라미터를 확인해주세요.", Objects.requireNonNull(result.getFieldError()).getField());
        ApiResponseDto<String> exception = ApiResponseDto.createException(new ApiException(ApiResponseCode.BAD_PARAMETER, errorMessage));
        log.error("[{}] {}", ApiResponseCode.BAD_PARAMETER.getId(), exception);
        return exception;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NoHandlerFoundException.class})
    public ApiResponseDto<String> noHandlerFoundException(NoHandlerFoundException e) {

        return ApiResponseDto.createException(ApiResponseCode.NOT_FOUND, "존재하지 않는 URL 입니다.");
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({NumberFormatException.class})
    public ApiResponseDto<String> handleValidException(NumberFormatException e) {

        return ApiResponseDto.createException(ApiResponseCode.BAD_PARAMETER, "파라미터가 잘못됐습니다.");
    }

}
