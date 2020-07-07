package lozm.object.dto.sign;

import lombok.*;
import lozm.object.code.UsersType;
import lozm.object.dto.BaseUserDto;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostSignDto {

    private Long id;
    private String name;
    private String identifier;
    private UsersType type;

    @Getter
    public static class Request extends BaseUserDto {
        @NotEmpty
        private String identifier;

        @NotEmpty
        private String password;
    }

    @Getter
    @Setter
    static public class Response implements Serializable {
        private static final long serialVersionUID = -8091879091924046844L;
        private String token;
        private String previousPage;
    }

}
