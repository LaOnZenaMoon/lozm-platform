package lozm.api.sign;

import lombok.RequiredArgsConstructor;
import lozm.global.jwt.JwtAuthenticationService;
import lozm.object.dto.ApiResponseCode;
import lozm.object.dto.ApiResponseDto;
import lozm.object.dto.sign.PostSignDto;
import lozm.object.vo.sign.SignVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController @RequestMapping(value = "/api/sign")
@RequiredArgsConstructor
public class SignAPIController {

    private final JwtAuthenticationService jwtAuthenticationService;


    @PostMapping(value = "/in")
    public ApiResponseDto signIn(@RequestBody @Valid PostSignDto.Request reqDto, HttpServletRequest request, HttpServletResponse response) throws Exception {
        SignVo signVo = SignVo.builder()
                .identifier(reqDto.getIdentifier())
                .password(reqDto.getPassword())
                .build();

        SignVo jwt = jwtAuthenticationService.getToken(signVo);
        PostSignDto.Response resDto = new PostSignDto.Response();
        resDto.setToken(jwt.getToken());
        resDto.setPreviousPage("/pages/home");

        return ApiResponseDto.createException(ApiResponseCode.OK, resDto);
    }

}