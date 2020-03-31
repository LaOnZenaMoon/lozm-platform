package lozm.core.dto.item;

import lombok.Getter;
import lombok.Setter;
import lozm.core.code.ItemType;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PostItemDto {

    @Getter
    public static class Request {
        @NotEmpty
        private String name;

        @NotNull
        private Long price;

        @NotNull
        private Long quantity;

        @NotEmpty
        private String type;

        @NotEmpty
        private String contents;

        @NotEmpty
        private String size;
    }

    @Getter
    @Setter
    public static class Response {

    }

}
