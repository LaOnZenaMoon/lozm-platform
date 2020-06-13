package lozm.object.vo.delivery;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryVo {

    private Long id;
    private String country;
    private String zipCode;
    private String city;
    private String street;
    private String etc;
    private String status;
    private int flag;

}
