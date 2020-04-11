package lozm.api.sign;

import lombok.RequiredArgsConstructor;
import lozm.core.dto.APIResponseDto;
import lozm.core.dto.sign.PostSignDto;
import lozm.core.dto.user.PostUserDto;
import lozm.core.vo.sign.SignVo;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(value = "/api/sign")
@RestController
@RequiredArgsConstructor
public class SignAPIController {

    private final SignService signService;


    @PostMapping(value = "/in")
    public APIResponseDto signIn(@RequestBody @Valid PostSignDto.Request reqDto) {
        APIResponseDto resDto = new APIResponseDto<>();

        try {
            SignVo signVo = SignVo.builder()
                    .identifier(reqDto.getIdentifier())
                    .password(reqDto.getPassword())
                    .build();
            signService.signIn(signVo);

            resDto.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            resDto.setSuccess(false);
            resDto.setMessage(e.getMessage());
        }

        return resDto;
    }

    @PostMapping(value = "/out")
    public APIResponseDto postUser(@RequestBody @Valid PostSignDto.Request reqDto) {
        APIResponseDto resDto = new APIResponseDto<>();

        try {

            resDto.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            resDto.setSuccess(false);
            resDto.setMessage(e.getMessage());
        }

        return resDto;
    }

}
