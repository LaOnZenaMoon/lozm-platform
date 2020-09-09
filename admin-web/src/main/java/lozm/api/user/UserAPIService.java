package lozm.api.user;

import lombok.RequiredArgsConstructor;
import lozm.global.properties.AdminApiProps;
import lozm.global.service.BaseService;
import lozm.object.dto.ApiResponseDto;
import lozm.object.dto.user.DeleteUserDto;
import lozm.object.dto.user.GetUserDto;
import lozm.object.dto.user.PostUserDto;
import lozm.object.dto.user.PutUserDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserAPIService {

    private final RestTemplate restTemplate;
    private final AdminApiProps adminApiProps;
    private final PasswordEncoder passwordEncoder;


    public GetUserDto.Response getUser() {
        ApiResponseDto<GetUserDto.Response> responseBody = restTemplate.exchange(
                adminApiProps.getUserUrl(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponseDto<GetUserDto.Response>>() {}
        ).getBody();

        BaseService.checkResponseBody(responseBody, "Failed to interface getUser API.");
        return responseBody.getData();
    }

    public ApiResponseDto postUser(PostUserDto.Request reqDto) {
        reqDto.setPassword(encode(reqDto.getPassword()));

        ApiResponseDto responseBody = restTemplate.exchange(
                adminApiProps.getUserUrl(),
                HttpMethod.POST,
                new HttpEntity<>(reqDto),
                new ParameterizedTypeReference<ApiResponseDto>() {}
        ).getBody();

        BaseService.checkResponseBody(responseBody, "Failed to interface postUser API.");
        return responseBody;
    }

    public ApiResponseDto putUser(PutUserDto.Request reqDto) {
        reqDto.setPassword(encode(reqDto.getPassword()));

        ApiResponseDto responseBody = restTemplate.exchange(
                adminApiProps.getUserUrl(),
                HttpMethod.PUT,
                new HttpEntity<>(reqDto),
                new ParameterizedTypeReference<ApiResponseDto>() {}
        ).getBody();

        BaseService.checkResponseBody(responseBody, "Failed to interface putUser API.");
        return responseBody;
    }

    public ApiResponseDto deleteUser(DeleteUserDto.Request reqDto) {
        ApiResponseDto responseBody = restTemplate.exchange(
                adminApiProps.getUserUrl(),
                HttpMethod.DELETE,
                new HttpEntity<>(reqDto),
                new ParameterizedTypeReference<ApiResponseDto>() {}
        ).getBody();

        BaseService.checkResponseBody(responseBody, "Failed to interface deleteUser API.");
        return responseBody;
    }

    private String encode(String source) {
        return StringUtils.isEmpty(source) ? null : passwordEncoder.encode(source);
    }

}
