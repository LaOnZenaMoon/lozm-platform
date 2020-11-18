package lozm.global.jwt;

import lombok.RequiredArgsConstructor;
import lozm.api.sign.SignService;
import lozm.object.vo.sign.SignVo;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class JwtAuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtils jwtTokenUtils;
    private final SignService signService;


    public SignVo getToken(SignVo signVo) {
        authenticate(signVo.getIdentifier(), signVo.getPassword());
        SignVo userInfo = signService.getUserInfo(signVo);
        final String token = jwtTokenUtils.generateToken(userInfo);
        userInfo.setToken(token);

        return userInfo;
    }

    private void authenticate(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new RuntimeException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new RuntimeException("INVALID_CREDENTIALS", e);
        }
    }

}
