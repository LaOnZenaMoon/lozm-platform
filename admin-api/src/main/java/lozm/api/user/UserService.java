package lozm.api.user;

import lombok.RequiredArgsConstructor;
import lozm.entity.auth.Account;
import lozm.global.exception.ServiceException;
import lozm.object.dto.user.GetUserDto;
import lozm.object.vo.user.UserVo;
import lozm.repository.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public List<GetUserDto> getUserList() throws Exception {
        List<Account> accountList = userRepository.selectUserList();
        List<GetUserDto> rtnList = new ArrayList<>();
        for (Account account : accountList) {
            GetUserDto dto = GetUserDto.builder()
                    .id(account.getId())
                    .name(account.getName())
                    .identifier(account.getIdentifier())
                    .password(null)
                    .type(account.getType())
                    .build();

            rtnList.add(dto);
        }

        return rtnList;
    }

    @Transactional
    public void save(UserVo userVo) throws Exception {
        Account account = new Account();
        account.insertUser(userVo);

        //1. check ID duplicated
        List<Account> findUsersIdDuplicated = userRepository.findByIdentifier(account.getIdentifier());
        if(findUsersIdDuplicated.size() > 0) throw new ServiceException("USER_0001", "User Identifier is duplicated.");

        userRepository.save(account);
    }

    @Transactional
    public void update(UserVo userVo) throws Exception {
        Optional<Account> findUser = findUser(userVo.getId());
        findUser.get().updateUser(userVo);
    }

    @Transactional
    public void delete(UserVo userVo) throws Exception {
        Optional<Account> findUser = findUser(userVo.getId());
        findUser.get().deleteUser(userVo);
    }

    private Optional<Account> findUser(Long userId) {
        Optional<Account> findUser = userRepository.findById(userId);
        findUser.orElseThrow(() -> {
            throw new ServiceException("USER_0002", "User doesn't exist.");
        });
        return findUser;
    }

}

