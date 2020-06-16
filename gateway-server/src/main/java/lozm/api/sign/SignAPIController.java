package lozm.api.sign;

import lombok.RequiredArgsConstructor;
import static lozm.object.code.SessionType.*;
import static org.springframework.util.ObjectUtils.isEmpty;

import lozm.object.dto.ApiResponseCode;
import lozm.object.dto.ApiResponseDto;
import lozm.object.dto.sign.PostSignDto;
import lozm.object.vo.sign.SignVo;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RequestMapping(value = "/api/sign")
@RestController
@RequiredArgsConstructor
public class SignAPIController {

    private final SignService signService;
    private final HttpSession httpSession;


    @PostMapping(value = "/in")
    public ApiResponseDto signIn(@RequestBody @Valid PostSignDto.Request reqDto) throws Exception {
        SignVo signVo = SignVo.builder()
                .identifier(reqDto.getIdentifier())
                .password(reqDto.getPassword())
                .build();

        List<SignVo> result = signService.signIn(signVo);

        return ApiResponseDto.createException(ApiResponseCode.OK, setSessionInfo(result.get(0)));
    }

    private String setSessionInfo(SignVo SignVo) throws Exception {
        httpSession.setAttribute(USER.name(), SignVo);

        Object attribute = httpSession.getAttribute(PREV_PAGE.name());
        String previousPage = isEmpty(attribute) ? "" : attribute.toString();

        if( StringUtils.isEmpty(previousPage) || "/".equals(previousPage)) {
            return "/home";
        }

        return previousPage;
    }

}
