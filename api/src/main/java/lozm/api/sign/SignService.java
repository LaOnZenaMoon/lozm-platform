package lozm.api.sign;

import lombok.RequiredArgsConstructor;
import lozm.core.exception.APIException;
import lozm.core.vo.sign.SignVo;
import lozm.domain.entity.user.User;
import lozm.domain.repository.user.UserRepositorySupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SignService {
    
    private final UserRepositorySupport userRepositorySupport;

    public void signIn(SignVo signVo) throws Exception {
        List<User> userList = userRepositorySupport.selectUserDetail(signVo);
        if(userList.size() < 1) new APIException("USER_0002", "User doesn't exist.");
    }

}

