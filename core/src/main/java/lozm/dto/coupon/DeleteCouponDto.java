package lozm.dto.coupon;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class DeleteCouponDto {

    private Long id;
    private String name;
    private String contents;
    private String type;
    private Long amount;
    private Long quantity;
    private LocalDateTime startDt;
    private LocalDateTime endDt;
    private int flag;

    @Getter
    public static class Request {
        private List<DeleteCouponDto> list = new ArrayList<>();
    }

}
