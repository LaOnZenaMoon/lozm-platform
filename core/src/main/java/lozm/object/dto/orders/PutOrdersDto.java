package lozm.object.dto.orders;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PutOrdersDto {

    @Getter
    public static class Request {
        @NotNull
        private Long id;

        @NotEmpty
        private String status;

        private int flag;
    }

}
