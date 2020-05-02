package lozm.dto.coupon;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lozm.code.UsersType;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class GetCouponUserDto {

    private Long id;
    private Long quantity;
    private Long userId;
    private String userName;
    private String userIdentifier;
    private UsersType userType;

    public GetCouponUserDto(Long id, Long quantity, Long userId, String userName, String userIdentifier, UsersType userType) {
        this.id = id;
        this.quantity = quantity;
        this.userId = userId;
        this.userName = userName;
        this.userIdentifier = userIdentifier;
        this.userType = userType;
    }

    @Getter
    @Setter
    public static class Response {
        private List<GetCouponUserDto> list = new ArrayList<>();
    }

}
