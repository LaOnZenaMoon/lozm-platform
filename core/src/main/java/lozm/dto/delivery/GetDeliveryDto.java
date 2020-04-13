package lozm.dto.delivery;

import lombok.Getter;
import lombok.Setter;
import lozm.code.DeliveryStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GetDeliveryDto {

    private Long id;
    private DeliveryStatus status;
    private String country;
    private String zipCode;
    private String city;
    private String street;
    private String etc;

    public GetDeliveryDto(Long id, DeliveryStatus status, String country, String zipCode, String city, String street, String etc) {
        this.id = id;
        this.status = status;
        this.country = country;
        this.zipCode = zipCode;
        this.city = city;
        this.street = street;
        this.etc = etc;
    }

    @Getter
    @Setter
    public static class Response {
        List<GetDeliveryDto> list = new ArrayList<>();
    }

}
