package lozm.core.dto.delivery;

import lombok.Getter;
import lombok.Setter;
import lozm.core.code.DeliveryStatus;
import lozm.core.dto.item.PostItemDto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PostDeliveryDto {

    @Getter
    public static class Request {
        @NotEmpty
        private String country;

        @NotEmpty
        private String zipCode;

        @NotEmpty
        private String city;

        @NotEmpty
        private String street;

        private String etc;

        private String status;

        public static Request setRequestTestData(String country, String zipCode, String city, String street, String etc, String status) {
            PostDeliveryDto.Request reqDto = new PostDeliveryDto.Request();
            reqDto.country = country;
            reqDto.zipCode = zipCode;
            reqDto.city = city;
            reqDto.street = street;
            reqDto.etc = etc;
            reqDto.status = status;

            return reqDto;
        }

    }

    @Getter
    @Setter
    public static class Response {

    }

}
