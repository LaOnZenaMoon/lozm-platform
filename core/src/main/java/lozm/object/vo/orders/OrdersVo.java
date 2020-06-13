package lozm.object.vo.orders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdersVo {

    private Long id;
    private Long orderedPrice;
    private Long quantity;
    private Long userId;
    private Long itemId;
    private Long deliveryId;
    private Long couponId;
    private String status;
    private int flag;

}
