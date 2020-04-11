package lozm.domain.entity.delivery;

import lombok.Getter;
import lozm.core.code.DeliveryStatus;
import lozm.core.dto.delivery.PostDeliveryDto;
import lozm.core.dto.delivery.PutDeliveryDto;
import lozm.core.vo.delivery.DeliveryVo;
import lozm.domain.entity.BaseEntity;
import lozm.domain.entity.embedded.Address;
import org.springframework.util.StringUtils;

import javax.persistence.*;

@Entity
@Getter
@Table(schema = "LOZM", name = "DELIVERY")
public class Delivery extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "DELIVERY_ID")
    private Long id;

    @Embedded
    private Address address;

    @Column(name = "STATUS")
    private DeliveryStatus status;

    public void insertDelivery(DeliveryVo deliveryVo) {
        address = new Address();
        address.setAddress(
                deliveryVo.getCountry(),
                deliveryVo.getZipCode(),
                deliveryVo.getCity(),
                deliveryVo.getStreet(),
                deliveryVo.getEtc()
        );

        this.status = StringUtils.isEmpty(deliveryVo.getStatus()) ? DeliveryStatus.PREPARATION : DeliveryStatus.valueOf(deliveryVo.getStatus());
    }

    public void updateDelivery(DeliveryVo deliveryVo) {
        this.address.setAddress(
                deliveryVo.getCountry(),
                deliveryVo.getZipCode(),
                deliveryVo.getCity(),
                deliveryVo.getStreet(),
                deliveryVo.getEtc()
        );
        this.status = DeliveryStatus.valueOf(deliveryVo.getStatus());
        this.setBaseEntity("", deliveryVo.getFlag());
    }
}
