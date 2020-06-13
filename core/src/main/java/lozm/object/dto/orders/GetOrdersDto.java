package lozm.object.dto.orders;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lozm.object.code.OrderStatus;
import lozm.object.code.UsersType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
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
    private UsersType userType;


    public GetOrdersDto(Long ordersId, LocalDateTime orderDt, OrderStatus ordersStatus, Long deliveryId, String deliveryCountry, String deliveryZipCode, String deliveryCity, String deliveryStreet, String deliveryEtc, Long userId, String userName, String identifier, UsersType userType) {
        this.ordersId = ordersId;
        this.orderDt = orderDt;
        this.ordersStatus = ordersStatus;
        this.deliveryId = deliveryId;
        this.deliveryCountry = deliveryCountry;
        this.deliveryZipCode = deliveryZipCode;
        this.deliveryCity = deliveryCity;
        this.deliveryStreet = deliveryStreet;
        this.deliveryEtc = deliveryEtc;
        this.userId = userId;
        this.userName = userName;
        this.identifier = identifier;
        this.userType = userType;
    }


    @Getter
    @Setter
    public static class Response {
        List<GetOrdersDto> list = new ArrayList<>();
    }

}
