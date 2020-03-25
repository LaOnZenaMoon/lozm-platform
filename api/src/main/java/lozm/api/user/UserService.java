package lozm.api.user;

import lombok.RequiredArgsConstructor;
import lozm.core.dto.GetUserDto;
import lozm.core.dto.PostUserDto;
import lozm.core.dto.PutUserDto;
import lozm.domain.entity.User;
import lozm.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public List<GetUserDto.Response> findAllUsers() throws Exception {
        List<User> userList = userRepository.findAll();

        return userList.stream()
                .map(u -> new GetUserDto.Response())
                .collect(Collectors.toList());
    }

    public void save(PostUserDto.Request reqDto) throws Exception {
        User user = new User();
        user.insertUser(reqDto);

        List<User> findUsers = userRepository.findByIdentifier(user.getIdentifier(), user.getName());
        if(findUsers.isEmpty()) userRepository.save(user);

        throw new Exception();
    }

    public void update(PutUserDto.Request reqDto) throws Exception {
        Optional<User> findUser = userRepository.findById(reqDto.getId());
        findUser.orElseThrow(() -> new NoSuchElementException());
        findUser.get().updateUser(reqDto);
    }

}
