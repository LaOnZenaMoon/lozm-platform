package lozm.shop.api;

import lombok.RequiredArgsConstructor;
import lozm.core.dto.GetUserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserAPIController {

    private final UserService userService;

    @GetMapping
    public List<GetUserDto.Response> getUser() {
        List<GetUserDto.Response> result = userService.findAllUsers();
        return result;
    }

    @PostMapping
    public void postUser() {

    }

}
