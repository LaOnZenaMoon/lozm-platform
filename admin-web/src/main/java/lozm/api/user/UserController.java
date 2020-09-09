package lozm.api.user;

import lombok.RequiredArgsConstructor;
import lozm.global.jwt.SignInfo;
import lozm.object.dto.ApiResponseCode;
import lozm.object.dto.ApiResponseDto;
import lozm.object.dto.user.DeleteUserDto;
import lozm.object.dto.user.PostUserDto;
import lozm.object.dto.user.PutUserDto;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(value = "/user")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserAPIService userAPIService;
    private final SignInfo signInfo;


    @GetMapping()
    public ApiResponseDto getUser() throws Exception {
        return ApiResponseDto.createException(ApiResponseCode.OK, userAPIService.getUser());
    }

    @PostMapping
    public ApiResponseDto postUser(@RequestBody @Valid PostUserDto.Request reqDto) throws Exception {
        reqDto.setCreatedBy(signInfo.getId());
        return ApiResponseDto.createException(ApiResponseCode.OK, userAPIService.postUser(reqDto));
    }

    @PutMapping
    public ApiResponseDto putUser(@RequestBody @Valid PutUserDto.Request reqDto) throws Exception {
        reqDto.setModifiedBy(signInfo.getId());
        return ApiResponseDto.createException(ApiResponseCode.OK, userAPIService.putUser(reqDto));
    }

    @DeleteMapping
    public ApiResponseDto deleteUser(@RequestBody @Valid DeleteUserDto.Request reqDto) throws Exception {
        reqDto.setModifiedBy(signInfo.getId());
        return ApiResponseDto.createException(ApiResponseCode.OK, userAPIService.deleteUser(reqDto));
    }

}
