package lozm.api.user;

import lombok.RequiredArgsConstructor;
import lozm.core.dto.GetUserDto;
import lozm.domain.entity.User;
import lozm.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<GetUserDto.Response> findAllUsers() {
        List<User> userList = userRepository.findAll();

        return userList.stream()
                .map(u -> new GetUserDto.Response())
                .collect(Collectors.toList());
    }

}

