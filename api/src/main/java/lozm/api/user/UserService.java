package lozm.api.user;

import lombok.RequiredArgsConstructor;
import lozm.core.dto.user.GetUserDto;
import lozm.core.dto.user.PostUserDto;
import lozm.core.dto.user.PutUserDto;
import lozm.core.exception.APIException;
import lozm.domain.entity.user.User;
import lozm.domain.repository.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
                .map(u -> new GetUserDto.Response(u.getId(), u.getName(), u.getIdentifier(), u.getType()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void save(PostUserDto.Request reqDto) throws Exception {
        User user = new User();
        user.insertUser(reqDto);

        //1. check ID duplicated
        List<User> findUsersIdDuplicated = userRepository.findByIdentifier(user.getIdentifier());
        if(findUsersIdDuplicated.size() > 0) throw new APIException("USER_0001", "User Identifier is duplicated.");

        userRepository.save(user);
    }

    @Transactional
    public void update(PutUserDto.Request reqDto) throws Exception {
        Optional<User> findUser = userRepository.findById(reqDto.getId());
        findUser.orElseThrow(() -> new APIException("USER_0002", "User doesn't exist."));
        findUser.get().updateUser(reqDto);
    }

}

