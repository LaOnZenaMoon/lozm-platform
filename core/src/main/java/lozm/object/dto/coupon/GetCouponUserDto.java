package lozm.object.dto.coupon;

import lombok.*;
import lozm.object.code.AccountType;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetCouponUserDto {

    private Long id;
    private Long quantity;
    private Long userId;
    private Long couponId;
    private String userName;
    private String userIdentifier;
    private AccountType userType;


    @Getter
    @Setter
    public static class Response {
        private List<GetCouponUserDto> list = new ArrayList<>();
    }

}
