package lozm.dto.item;

import lombok.Getter;

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

        @NotNull
        private Long storeId;

        public static Request setRequestTestData(String name, Long price, Long quantity, String type, String contents, String size, Long storeId) {
            PostItemDto.Request reqDto = new PostItemDto.Request();
            reqDto.name = name;
            reqDto.price = price;
            reqDto.quantity = quantity;
            reqDto.type = type;
            reqDto.contents = contents;
            reqDto.size = size;
            reqDto.storeId = storeId;

            return reqDto;
        }
    }

}
