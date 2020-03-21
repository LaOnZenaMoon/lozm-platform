package lozm.api.user;

import lombok.RequiredArgsConstructor;
import lozm.core.dto.APIResponseDto;
import lozm.core.dto.GetUserDto;
import lozm.core.dto.PostUserDto;
import lozm.core.dto.PutUserDto;
import org.springframework.web.bind.annotation.*;

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
            List<GetUserDto.Response> result = userService.findAllUsers();
            resDto.setSuccess(true);
            resDto.setData(result);
        } catch (Exception e) {
            resDto.setSuccess(false);
            resDto.setMessage(e.getMessage());
        }

        return resDto;
    }

    @PostMapping
    public APIResponseDto postUser(@RequestBody PostUserDto.Request reqDto) {
        APIResponseDto resDto = new APIResponseDto<>();

        try {
            userService.save(reqDto);
        } catch (Exception e) {
            resDto.setSuccess(false);
            resDto.setMessage(e.getMessage());
        }

        return resDto;
    }

    @PutMapping
    public APIResponseDto putUser(@RequestBody PutUserDto.Request reqDto) {
        APIResponseDto resDto = new APIResponseDto<>();

        try {
            userService.update(reqDto);
        } catch (Exception e) {
            resDto.setSuccess(false);
            resDto.setMessage(e.getMessage());
        }

        return resDto;
    }

}
