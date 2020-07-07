package lozm.object.dto.orders;

import lombok.Getter;
import lozm.object.dto.BaseUserDto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PutOrdersDto {

    @Getter
    public static class Request extends BaseUserDto {
        @NotNull
        private Long id;

        @NotEmpty
        private String status;

        private int flag;
    }

}
