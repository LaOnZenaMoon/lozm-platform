package lozm.api.sign;

import lombok.RequiredArgsConstructor;
import lozm.global.jwt.JwtAuthenticationService;
import lozm.global.jwt.SignInfo;
import lozm.object.dto.ApiResponseCode;
import lozm.object.dto.ApiResponseDto;
import lozm.object.dto.sign.PostSignDto;
import lozm.object.vo.sign.SignVo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

import static lozm.object.code.SessionType.PREV_PAGE;
import static lozm.object.code.SessionType.USER;
import static org.springframework.util.ObjectUtils.isEmpty;

@RequestMapping(value = "/api/sign")
@RestController
@RequiredArgsConstructor
public class SignAPIController {

    private final JwtAuthenticationService jwtAuthenticationService;


    @PostMapping(value = "/in")
    public ApiResponseDto signIn(
            @RequestBody @Valid PostSignDto.Request reqDto,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws Exception {
        SignVo signVo = SignVo.builder()
                .identifier(reqDto.getIdentifier())
                .password(reqDto.getPassword())
                .build();

        SignVo jwt = jwtAuthenticationService.getToken(signVo);
        PostSignDto.Response resDto = new PostSignDto.Response();
        resDto.setToken(jwt.getToken());

//        RequestCache requestCache = new HttpSessionRequestCache();
//        String redirectUrl = requestCache.getRequest(request, response)
//                .getRedirectUrl();
//        redirectUrl = isEmpty(redirectUrl) ? "/home" : redirectUrl;
        String redirectUrl = "/pages/home";
        resDto.setPreviousPage(redirectUrl);

        return ApiResponseDto.createException(ApiResponseCode.OK, resDto);
    }

}
