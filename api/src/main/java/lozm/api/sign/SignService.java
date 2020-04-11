package lozm.api.sign;

import lombok.RequiredArgsConstructor;
import lozm.core.vo.sign.SignVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SignService {

    public void signIn(SignVo signVo) {

    }

}

