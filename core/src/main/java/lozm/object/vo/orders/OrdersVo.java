package lozm.object.vo.orders;

import lombok.Builder;
import lombok.Getter;
import lozm.object.vo.BaseVo;

import java.time.LocalDateTime;

@Getter
public class OrdersVo extends BaseVo {

    private Long id;
    private Long orderedPrice;
    private Long quantity;
    private Long userId;
    private Long itemId;
    private Long deliveryId;
    private Long couponId;
    private String status;
    private int flag;


    @Builder
    public OrdersVo(LocalDateTime createdDt, LocalDateTime modifiedDt, Long createdBy, Long modifiedBy, int flag, Long id, Long orderedPrice, Long quantity, Long userId, Long itemId, Long deliveryId, Long couponId, String status, int flag1) {
        super(createdDt, modifiedDt, createdBy, modifiedBy, flag);
        this.id = id;
        this.orderedPrice = orderedPrice;
        this.quantity = quantity;
        this.userId = userId;
        this.itemId = itemId;
        this.deliveryId = deliveryId;
        this.couponId = couponId;
        this.status = status;
        this.flag = flag1;
    }

}
