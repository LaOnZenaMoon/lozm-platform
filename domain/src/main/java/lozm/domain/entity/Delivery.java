package lozm.domain.entity;

import lombok.Getter;
import lozm.core.code.DeliveryStatus;
import lozm.domain.entity.embedded.Address;

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

}
