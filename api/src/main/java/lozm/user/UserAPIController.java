package lozm.user;

import lombok.RequiredArgsConstructor;
import lozm.dto.APIResponseDto;
import lozm.dto.user.GetUserDto;
import lozm.dto.user.PostUserDto;
import lozm.dto.user.PutUserDto;
import lozm.vo.user.UserVo;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(value = "/api/user")
@RestController
@RequiredArgsConstructor
public class UserAPIController {

    private final UserService userService;


    @GetMapping()
    public APIResponseDto getUser() {
        APIResponseDto resDto = new APIResponseDto<>();

        try {
            List<GetUserDto> result = userService.findAllUsers();
            resDto.setSuccess(true);
            resDto.setData(result);
        } catch (Exception e) {
            resDto.setSuccess(false);
            resDto.setMessage(e.getMessage());
        }

        return resDto;
    }

    @PostMapping
    public APIResponseDto postUser(@RequestBody @Valid PostUserDto.Request reqDto) {
        APIResponseDto resDto = new APIResponseDto<>();

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
    public APIResponseDto putUser(@RequestBody @Valid PutUserDto.Request reqDto) {
        APIResponseDto resDto = new APIResponseDto<>();

        try {
            UserVo userVo = UserVo.builder()
                    .id(reqDto.getId())
                    .name(reqDto.getName())
                    .password(reqDto.getPassword())
                    .flag(reqDto.getFlag())
                    .build();

            userService.update(userVo);
            resDto.setSuccess(true);
        } catch (Exception e) {
            resDto.setSuccess(false);
            resDto.setMessage(e.getMessage());
        }

        return resDto;
    }



}
