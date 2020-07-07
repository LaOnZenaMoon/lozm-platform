package lozm.object.vo.delivery;

import lombok.Builder;
import lombok.Getter;
import lozm.object.vo.BaseVo;

import java.time.LocalDateTime;

@Getter
public class DeliveryVo extends BaseVo {

    private Long id;
    private String country;
    private String zipCode;
    private String city;
    private String street;
    private String etc;
    private String status;
    private int flag;


    @Builder
    public DeliveryVo(LocalDateTime createdDt, LocalDateTime modifiedDt, Long createdBy, Long modifiedBy, int flag, Long id, String country, String zipCode, String city, String street, String etc, String status, int flag1) {
        super(createdDt, modifiedDt, createdBy, modifiedBy, flag);
        this.id = id;
        this.country = country;
        this.zipCode = zipCode;
        this.city = city;
        this.street = street;
        this.etc = etc;
        this.status = status;
        this.flag = flag1;
    }

}
