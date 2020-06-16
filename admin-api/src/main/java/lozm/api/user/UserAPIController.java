package lozm.api.user;

import lombok.RequiredArgsConstructor;
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
    public ApiResponseDto getUser() {
        ApiResponseDto resDto = new ApiResponseDto<>();

        try {
            List<GetUserDto> result = userService.getUserList();

            GetUserDto.Response getUserDto = new GetUserDto.Response();
            getUserDto.setList(result);
            resDto.setData(getUserDto);
            resDto.setSuccess(true);
        } catch (Exception e) {
            resDto.setSuccess(false);
            resDto.setMessage(e.getMessage());
        }

        return resDto;
    }

    @PostMapping
    public ApiResponseDto postUser(@RequestBody @Valid PostUserDto.Request reqDto) {
        ApiResponseDto resDto = new ApiResponseDto<>();

        try {
            UserVo userVo = UserVo.builder()
                    .name(reqDto.getName())
                    .identifier(reqDto.getIdentifier())
                    .password(reqDto.getPassword())
                    .type(reqDto.getType())
                    .build();

            userService.save(userVo);
            resDto.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            resDto.setSuccess(false);
            resDto.setMessage(e.getMessage());
        }

        return resDto;
    }

    @PutMapping
    public ApiResponseDto putUser(@RequestBody @Valid PutUserDto.Request reqDto) {
        ApiResponseDto resDto = new ApiResponseDto<>();

        try {
            UserVo userVo = UserVo.builder()
                    .id(reqDto.getId())
                    .name(reqDto.getName())
                    .identifier(reqDto.getIdentifier())
                    .password(reqDto.getPassword())
                    .type(reqDto.getType())
                    .flag(1)
                    .build();

            userService.update(userVo);
            resDto.setSuccess(true);
        } catch (Exception e) {
            resDto.setSuccess(false);
            resDto.setMessage(e.getMessage());
        }

        return resDto;
    }

    @DeleteMapping
    public ApiResponseDto deleteUser(@RequestBody @Valid DeleteUserDto.Request reqDto) {
        ApiResponseDto resDto = new ApiResponseDto<>();

        try {
            for(DeleteUserDto dto : reqDto.getList()) {
                UserVo userVo = UserVo.builder()
                        .id(dto.getId())
                        .flag(0)
                        .build();

                userService.delete(userVo);
            }

            resDto.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            resDto.setSuccess(false);
            resDto.setMessage(e.getMessage());
        }

        return resDto;
    }

}
