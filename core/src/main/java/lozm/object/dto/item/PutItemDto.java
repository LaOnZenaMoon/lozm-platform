package lozm.object.dto.item;

import lombok.Getter;
import lozm.object.dto.BaseUserDto;

import javax.validation.constraints.NotNull;

public class PutItemDto {

    @Getter
    public static class Request extends BaseUserDto {
        @NotNull
        private Long id;

        private String name;

        private Long price;

        private Long quantity;

        private String contents;

        private String size;

        private int flag;
    }

}
