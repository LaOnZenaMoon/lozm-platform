package lozm.sign;

import lombok.RequiredArgsConstructor;
import lozm.entity.user.User;
import lozm.exception.APIException;
import lozm.repository.RepositorySupport;
import lozm.vo.sign.SignVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SignService {
    
    private final RepositorySupport repositorySupport;

    public List<SignVo> signIn(SignVo signVo) throws Exception {
        List<User> userList = repositorySupport.selectUserDetail(signVo);
        if(userList.size() < 1) throw new APIException("USER_0002", "User doesn't exist.");

        return userList.stream().map(u -> new SignVo(
                u.getName(),
                u.getIdentifier(),
                u.getType()
        )).collect(Collectors.toList());
    }

}

