package lozm.object.dto.delivery;

import lombok.*;
import lozm.object.code.DeliveryStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetDeliveryDto {

    private Long id;
    private DeliveryStatus status;
    private String country;
    private String zipCode;
    private String city;
    private String street;
    private String etc;

    @Getter
    @Setter
    public static class Response {
        List<GetDeliveryDto> list = new ArrayList<>();
    }

}
