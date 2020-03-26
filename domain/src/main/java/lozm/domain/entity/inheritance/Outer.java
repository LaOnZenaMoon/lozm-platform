package lozm.domain.entity.inheritance;

import lozm.domain.entity.Item;
import lozm.domain.entity.embedded.Clothing;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("OUTER")
public class Outer extends Item {

    @Embedded
    private Clothing clothing;

}
