package lozm.api.auth;

import lombok.RequiredArgsConstructor;
import lozm.object.dto.ApiResponseCode;
import lozm.object.dto.ApiResponseDto;
import lozm.object.dto.auth.AccountDeleteDto;
import lozm.object.dto.auth.AccountGetDto;
import lozm.object.dto.auth.AccountPostDto;
import lozm.object.dto.auth.AccountPutDto;
import lozm.object.vo.auth.AccountVo;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(value = "/api/auth")
@RestController
@RequiredArgsConstructor
public class AuthAPIController {

    private final AuthService authService;


    @GetMapping("/account")
    public ApiResponseDto getAccount() throws Exception {
        List<AccountGetDto> result = authService.getAccountList();

        AccountGetDto.Response resDto = new AccountGetDto.Response();
        resDto.setList(result);

        return ApiResponseDto.createException(ApiResponseCode.OK, resDto);
    }

    @PostMapping("/account")
    public ApiResponseDto postAccount(@RequestBody @Valid AccountPostDto.Request reqDto) throws Exception {
        AccountVo userVo = AccountVo.builder()
                .name(reqDto.getName())
                .identifier(reqDto.getIdentifier())
                .password(reqDto.getPassword())
                .type(reqDto.getType())
                .createdBy(reqDto.getCreatedBy())
                .build();

        authService.save(userVo);

        return ApiResponseDto.createException(ApiResponseCode.OK, null);
    }

    @PutMapping("/account")
    public ApiResponseDto putAccount(@RequestBody @Valid AccountPutDto.Request reqDto) throws Exception {
        AccountVo userVo = AccountVo.builder()
                .id(reqDto.getId())
                .name(reqDto.getName())
                .identifier(reqDto.getIdentifier())
                .password(reqDto.getPassword())
                .type(reqDto.getType())
                .modifiedBy(reqDto.getModifiedBy())
                .build();

        authService.update(userVo);

        return ApiResponseDto.createException(ApiResponseCode.OK, null);
    }

    @DeleteMapping("/account")
    public ApiResponseDto deleteAccount(@RequestBody @Valid AccountDeleteDto.Request reqDto) throws Exception {
        for(AccountDeleteDto dto : reqDto.getList()) {
            AccountVo userVo = AccountVo.builder()
                    .id(dto.getId())
                    .modifiedBy(reqDto.getModifiedBy())
                    .build();

            authService.delete(userVo);
        }

        return ApiResponseDto.createException(ApiResponseCode.OK, null);
    }

}
