package lozm.api.board;

import lombok.RequiredArgsConstructor;
import lozm.global.properties.AdminApiProps;
import lozm.global.service.BaseService;
import lozm.object.dto.ApiResponseDto;
import lozm.object.dto.board.GetBoardDto;
import lozm.object.dto.board.GetCommentDto;
import org.springframework.core.ParameterizedTypeReference;
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


    public GetBoardDto getBoardDetail(Long boardId) {
        ApiResponseDto<GetBoardDto> responseBody = restTemplate.exchange(
                adminApiProps.getBoardUrl() + "/" + boardId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponseDto<GetBoardDto>>() {}
        ).getBody();

        BaseService.checkResponseBody(responseBody, "Failed to interface getBoardDetail API.");
        return responseBody.getData();
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

}