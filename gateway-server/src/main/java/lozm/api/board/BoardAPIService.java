package lozm.api.board;

import lombok.RequiredArgsConstructor;
import lozm.global.properties.AdminApiProps;
import lozm.global.service.BaseService;
import lozm.object.dto.ApiResponseDto;
import lozm.object.dto.board.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardAPIService {

    private final RestTemplate restTemplate;
    private final AdminApiProps adminApiProps;


    public GetBoardDto.Response getBoard(Long boardType) {
        ApiResponseDto<GetBoardDto.Response> responseBody = restTemplate.exchange(
                adminApiProps.getBoardUrl() + "/" + boardType,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponseDto<GetBoardDto.Response>>() {}
        ).getBody();

        BaseService.checkResponseBody(responseBody, "Failed to interface getBoard API.");
        return responseBody.getData();
    }

    public GetBoardDto.Response getBoardDetail(Long boardId) {
        ApiResponseDto<GetBoardDto.Response> responseBody = restTemplate.exchange(
                adminApiProps.getBoardUrl() + "/" + boardId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponseDto<GetBoardDto.Response>>() {}
        ).getBody();

        BaseService.checkResponseBody(responseBody, "Failed to interface getBoardDetail API.");
        return responseBody.getData();
    }

    public ApiResponseDto postBoard(PostBoardDto.Request reqDto) {
        ApiResponseDto responseBody = restTemplate.exchange(
                adminApiProps.getBoardUrl(),
                HttpMethod.POST,
                new HttpEntity<>(reqDto),
                new ParameterizedTypeReference<ApiResponseDto>() {}
        ).getBody();

        BaseService.checkResponseBody(responseBody, "Failed to interface postBoard API.");
        return responseBody;
    }

    public ApiResponseDto putBoard(PutBoardDto.Request reqDto) {
        ApiResponseDto responseBody = restTemplate.exchange(
                adminApiProps.getBoardUrl(),
                HttpMethod.PUT,
                new HttpEntity<>(reqDto),
                new ParameterizedTypeReference<ApiResponseDto>() {}
        ).getBody();

        BaseService.checkResponseBody(responseBody, "Failed to interface putBoard API.");
        return responseBody;
    }

    public ApiResponseDto deleteBoard(DeleteBoardDto.Request reqDto) {
        ApiResponseDto responseBody = restTemplate.exchange(
                adminApiProps.getBoardUrl(),
                HttpMethod.DELETE,
                new HttpEntity<>(reqDto),
                new ParameterizedTypeReference<ApiResponseDto>() {}
        ).getBody();

        BaseService.checkResponseBody(responseBody, "Failed to interface deleteBoard API.");
        return responseBody;
    }

    public GetCommentDto.Response getComment(Long boardId) {
        ApiResponseDto<GetCommentDto.Response> responseBody = restTemplate.exchange(
                adminApiProps.getBoardUrl() + "/" + boardId + "/comment",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponseDto<GetCommentDto.Response>>() {}
        ).getBody();

        BaseService.checkResponseBody(responseBody, "Failed to interface getComment API.");
        return responseBody.getData();
    }

    public ApiResponseDto postComment(PostCommentDto.Request reqDto) {
        ApiResponseDto responseBody = restTemplate.exchange(
                adminApiProps.getBoardUrl() + "/comment",
                HttpMethod.POST,
                new HttpEntity<>(reqDto),
                new ParameterizedTypeReference<ApiResponseDto>() {}
        ).getBody();

        BaseService.checkResponseBody(responseBody, "Failed to interface postComment API.");
        return responseBody;
    }

    public ApiResponseDto putComment(PutCommentDto.Request reqDto) {
        ApiResponseDto responseBody = restTemplate.exchange(
                adminApiProps.getBoardUrl() + "/comment",
                HttpMethod.PUT,
                new HttpEntity<>(reqDto),
                new ParameterizedTypeReference<ApiResponseDto>() {}
        ).getBody();

        BaseService.checkResponseBody(responseBody, "Failed to interface putComment API.");
        return responseBody;
    }

    public ApiResponseDto deleteComment(DeleteCommentDto.Request reqDto) {
        ApiResponseDto responseBody = restTemplate.exchange(
                adminApiProps.getBoardUrl() + "/user",
                HttpMethod.DELETE,
                new HttpEntity<>(reqDto),
                new ParameterizedTypeReference<ApiResponseDto>() {}
        ).getBody();

        BaseService.checkResponseBody(responseBody, "Failed to interface deleteComment API.");
        return responseBody;
    }

}