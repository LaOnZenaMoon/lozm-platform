package lozm.core.dto.delivery;

import lombok.Getter;
import lombok.Setter;
import lozm.core.code.DeliveryStatus;


public class GetDeliveryDto {

    @Getter
    public static class Request {

    }

    @Getter
    @Setter
    public static class Response {
        private Long id;
        private DeliveryStatus status;
        private String country;
        private String zipCode;
        private String city;
        private String street;
        private String etc;

        public Response(Long id, DeliveryStatus status, String country, String zipCode, String city, String street, String etc) {
            this.id = id;
            this.status = status;
            this.country = country;
            this.zipCode = zipCode;
            this.city = city;
            this.street = street;
            this.etc = etc;
        }
    }

}
