package lozm.api.user;

import lombok.RequiredArgsConstructor;
import lozm.object.dto.ApiResponseCode;
import lozm.object.dto.ApiResponseDto;
import lozm.object.dto.user.DeleteUserDto;
import lozm.object.dto.user.GetUserDto;
import lozm.object.dto.user.PostUserDto;
import lozm.object.dto.user.PutUserDto;
import lozm.object.vo.user.UserVo;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(value = "/api/user")
@RestController
@RequiredArgsConstructor
public class UserAPIController {

    private final UserService userService;


    @GetMapping()
    public ApiResponseDto getUser() throws Exception {
        List<GetUserDto> result = userService.getUserList();

        GetUserDto.Response resDto = new GetUserDto.Response();
        resDto.setList(result);

        return ApiResponseDto.createException(ApiResponseCode.OK, resDto);
    }

    @PostMapping
    public ApiResponseDto postUser(@RequestBody @Valid PostUserDto.Request reqDto) throws Exception {
        UserVo userVo = UserVo.builder()
                .name(reqDto.getName())
                .identifier(reqDto.getIdentifier())
                .password(reqDto.getPassword())
                .type(reqDto.getType())
                .createdBy(reqDto.getCreatedBy())
                .build();

        userService.save(userVo);

        return ApiResponseDto.createException(ApiResponseCode.OK, null);
    }

    @PutMapping
    public ApiResponseDto putUser(@RequestBody @Valid PutUserDto.Request reqDto) throws Exception {
        UserVo userVo = UserVo.builder()
                .id(reqDto.getId())
                .name(reqDto.getName())
                .identifier(reqDto.getIdentifier())
                .password(reqDto.getPassword())
                .type(reqDto.getType())
                .modifiedBy(reqDto.getModifiedBy())
                .build();

        userService.update(userVo);

        return ApiResponseDto.createException(ApiResponseCode.OK, null);
    }

    @DeleteMapping
    public ApiResponseDto deleteUser(@RequestBody @Valid DeleteUserDto.Request reqDto) throws Exception {
        for(DeleteUserDto dto : reqDto.getList()) {
            UserVo userVo = UserVo.builder()
                    .id(dto.getId())
                    .flag(0)
                    .modifiedBy(reqDto.getModifiedBy())
                    .build();

            userService.delete(userVo);
        }

        return ApiResponseDto.createException(ApiResponseCode.OK, null);
    }

}
