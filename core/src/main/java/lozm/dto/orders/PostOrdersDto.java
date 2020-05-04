package lozm.dto.orders;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

public class PostOrdersDto {

    @Getter
    public static class Request {
        @NotNull
        private Long quantity;

        @NotNull
        private Long userId;

        @NotNull
        private Long itemId;

        private Long couponId;

        public static Request setRequestTestData(Long userId, Long itemId, Long couponId, Long quantity) {
            PostOrdersDto.Request reqDto = new PostOrdersDto.Request();
            reqDto.userId = userId;
            reqDto.itemId = itemId;
            reqDto.couponId = couponId;
            reqDto.quantity = quantity;

            return reqDto;
        }
    }



}
