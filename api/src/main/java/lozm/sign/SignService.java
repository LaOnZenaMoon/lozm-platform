package lozm.sign;

import lombok.RequiredArgsConstructor;
import lozm.repository.user.UserRepositorySupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SignService {
    
    private final UserRepositorySupport userRepositorySupport;

//    public List<SignVo> signIn(SignVo signVo) throws Exception {
//        List<User> userList = userRepositorySupport.selectUserDetail(signVo);
//        if(userList.size() < 1) new APIException("USER_0002", "User doesn't exist.");
//
//        return userList.stream().map(u -> new SignVo(
//                u.getName(),
//                u.getIdentifier(),
//                u.getType()
//        )).collect(Collectors.toList());
//    }

}

