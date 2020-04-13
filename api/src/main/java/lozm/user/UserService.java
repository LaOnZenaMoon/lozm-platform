package lozm.user;

import lombok.RequiredArgsConstructor;
import lozm.dto.user.GetUserDto;
import lozm.exception.APIException;
import lozm.vo.user.UserVo;
import lozm.entity.user.User;
import lozm.repository.user.UserRepository;
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


    public List<GetUserDto> findAllUsers() throws Exception {
        List<User> userList = userRepository.findAll();

        return userList.stream()
                .map(u -> new GetUserDto(u.getId(), u.getName(), u.getIdentifier(), u.getType()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void save(UserVo userVo) throws Exception {
        User user = new User();
        user.insertUser(userVo);

        //1. check ID duplicated
        List<User> findUsersIdDuplicated = userRepository.findByIdentifier(user.getIdentifier());
        if(findUsersIdDuplicated.size() > 0) throw new APIException("USER_0001", "User Identifier is duplicated.");

        userRepository.save(user);
    }

    @Transactional
    public void update(UserVo userVo) throws Exception {
        Optional<User> findUser = userRepository.findById(userVo.getId());
        findUser.orElseThrow(() -> new APIException("USER_0002", "User doesn't exist."));
        findUser.get().updateUser(userVo);
    }

}

