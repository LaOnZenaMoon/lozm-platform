package lozm.object.dto.store;

import lombok.Getter;
import lozm.object.dto.BaseUserDto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PutStoreDto {

    @Getter
    public static class Request extends BaseUserDto {
        @NotNull
        private Long id;

        @NotEmpty
        private String name;

        @NotEmpty
        private String telNumber;

        @NotEmpty
        private String info;

        private int flag;
    }

}
