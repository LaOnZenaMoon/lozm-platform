package lozm.api.sign;

import lombok.RequiredArgsConstructor;
import static lozm.object.code.SessionType.*;

import lozm.object.dto.APIResponseDto;
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
    public APIResponseDto signIn(@RequestBody @Valid PostSignDto.Request reqDto) {
        APIResponseDto resDto = new APIResponseDto<>();

        try {
            SignVo signVo = SignVo.builder()
                    .identifier(reqDto.getIdentifier())
                    .password(reqDto.getPassword())
                    .build();
            List<SignVo> result = signService.signIn(signVo);

            resDto.setMessage(setSessionInfo(result.get(0)));
            resDto.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            resDto.setMessage(e.getMessage());
            resDto.setSuccess(false);
        }

        return resDto;
    }

    private String setSessionInfo(SignVo SignVo) {
        httpSession.setAttribute(USER.name(), SignVo);

        String previousPage = httpSession.getAttribute(PREV_PAGE.name()).toString();
        if( StringUtils.isEmpty(previousPage) || "/".equals(previousPage)) {
            return "/home";
        }

        return previousPage;
    }

}
