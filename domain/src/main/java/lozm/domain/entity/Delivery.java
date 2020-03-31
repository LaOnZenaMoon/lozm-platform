package lozm.domain.entity;

import lombok.Getter;
import lozm.core.code.DeliveryStatus;
import lozm.core.dto.delivery.PostDeliveryDto;
import lozm.core.dto.delivery.PutDeliveryDto;
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

    public void insertDelivery(PostDeliveryDto.Request reqDto) {
        address = new Address();
        address.setAddress(
                reqDto.getCountry(),
                reqDto.getZipCode(),
                reqDto.getCity(),
                reqDto.getStreet(),
                reqDto.getEtc()
        );

        this.status = StringUtils.isEmpty(reqDto.getStatus()) ? DeliveryStatus.PREPARATION : DeliveryStatus.valueOf(reqDto.getStatus());
    }

    public void updateDelivery(PutDeliveryDto.Request reqDto) {
        this.address.setAddress(
                reqDto.getCountry(),
                reqDto.getZipCode(),
                reqDto.getCity(),
                reqDto.getStreet(),
                reqDto.getEtc()
        );
        this.status = reqDto.getStatus();
        this.setBaseEntity("", reqDto.getFlag());
    }
}
