package lozm.object.dto.sign;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class PostSignDto {

    @Getter
    public static class Request {
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
