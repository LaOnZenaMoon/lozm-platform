package lozm.object.dto.orders;

import lombok.*;
import lozm.object.code.OrderStatus;
import lozm.object.code.AccountType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetOrdersDto {

    //Orders
    private Long ordersId;
    private LocalDateTime orderDt;
    private OrderStatus ordersStatus;

    //Delivery
    private Long deliveryId;
    private String deliveryCountry;
    private String deliveryZipCode;
    private String deliveryCity;
    private String deliveryStreet;
    private String deliveryEtc;

    //User
    private Long userId;
    private String userName;
    private String identifier;
    private AccountType userType;


    @Getter
    @Setter
    public static class Response {
        List<GetOrdersDto> list = new ArrayList<>();
    }

}
