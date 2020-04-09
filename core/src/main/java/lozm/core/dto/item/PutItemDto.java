package lozm.core.dto.item;

import lombok.Getter;
import lombok.Setter;
import lozm.core.code.OrderStatus;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PutItemDto {

    @Getter
    public static class Request {
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
