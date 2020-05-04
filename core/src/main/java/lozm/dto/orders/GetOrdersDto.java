package lozm.dto.orders;

import lombok.Getter;
import lombok.Setter;
import lozm.code.OrderStatus;
import org.hibernate.usertype.UserType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class GetOrdersDto {

    //Orders
    private Long id;
    private LocalDateTime orderDt;
    private OrderStatus status;

    //OrdersItem
    private Long ordersItemId;
    private Long orderedPrice;
    private Long quantity;

    //Item
    private Long itemId;
    private String itemName;
    private Long itemPrice;
    private String type;

    //Store
    private Long storeId;
    private String storeName;
    private String telNumber;

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
    private UserType userType;


    public GetOrdersDto(Long id, LocalDateTime orderDt, OrderStatus status) {
        this.id = id;
        this.orderDt = orderDt;
        this.status = status;
    }

    @Getter
    @Setter
    public static class Response {
        List<GetOrdersDto> list = new ArrayList<>();
    }

}
