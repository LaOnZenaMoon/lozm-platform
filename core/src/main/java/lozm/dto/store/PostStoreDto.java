package lozm.dto.store;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PostStoreDto {

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

        public static Request setRequestTestData(String name, Long price, Long quantity, String type, String contents, String size) {
            PostStoreDto.Request reqDto = new PostStoreDto.Request();
            reqDto.name = name;
            reqDto.price = price;
            reqDto.quantity = quantity;
            reqDto.type = type;
            reqDto.contents = contents;
            reqDto.size = size;

            return reqDto;
        }
    }

}
