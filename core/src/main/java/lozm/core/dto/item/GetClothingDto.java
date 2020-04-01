package lozm.core.dto.item;

import lombok.Getter;
import lombok.Setter;


public class GetClothingDto {

    @Getter
    public static class Request {

    }

    @Getter
    @Setter
    public static class Response {
        private Long id;
        private String name;
        private Long price;
        private Long quantity;
        private String contents;
        private String sizes;

        public Response(Long id, String name, Long price, Long quantity, String contents, String sizes) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.quantity = quantity;
            this.contents = contents;
            this.sizes = sizes;
        }
    }

}
