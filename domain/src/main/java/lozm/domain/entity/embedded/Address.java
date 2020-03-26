package lozm.domain.entity.embedded;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {

    private String country;
    private String zipCode;
    private String city;
    private String street;
    private String etc;

}
