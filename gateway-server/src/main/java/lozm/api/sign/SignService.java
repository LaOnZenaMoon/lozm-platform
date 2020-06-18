package lozm.api.sign;

import lombok.RequiredArgsConstructor;
import lozm.entity.user.User;
import lozm.global.exception.ServiceException;
import lozm.repository.RepositorySupport;
import lozm.object.vo.sign.SignVo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SignService implements UserDetailsService {
    
    private final RepositorySupport repositorySupport;

    public List<SignVo> signIn(SignVo signVo) throws Exception {
        List<User> userList = repositorySupport.selectUserDetail(signVo);
        if(userList.size() < 1) throw new ServiceException("USER_0002", "User doesn't exist.");

        return userList.stream().map(u -> new SignVo(
                u.getName(),
                u.getIdentifier(),
                u.getType()
        )).collect(Collectors.toList());
    }

    public SignVo getUserInfo(SignVo signVo) {
        List<User> users = repositorySupport.selectUserInfoForJwt(signVo);

        return new SignVo(
                users.get(0).getId(),
                users.get(0).getName(),
                users.get(0).getIdentifier(),
                users.get(0).getPassword(),
                users.get(0).getType()
        );
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}

