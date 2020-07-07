package lozm.object.dto.coupon;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetCouponDto {

    private Long id;
    private String name;
    private String contents;
    private String type;
    private Long amount;
    private Long quantity;
    private LocalDateTime startDt;
    private LocalDateTime endDt;

    @Getter
    @Setter
    public static class Response {
        List<GetCouponDto> list = new ArrayList<>();
    }

}
