package lozm.global.jwt;

import lombok.RequiredArgsConstructor;
import lozm.object.dto.sign.PostSignDto;
import lozm.object.vo.sign.SignVo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class JwtAuthenticationController {

    private final JwtAuthenticationService jwtAuthenticationService;


    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody PostSignDto.Request reqDto) {
        PostSignDto.Response resDto = new PostSignDto.Response();

        try {
            SignVo loginReqVo = SignVo.builder()
                    .identifier(reqDto.getIdentifier())
                    .password(reqDto.getPassword())
                    .build();

            SignVo jwt = jwtAuthenticationService.getToken(loginReqVo);
            resDto.setToken(jwt.getToken());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok(resDto);
    }

}
