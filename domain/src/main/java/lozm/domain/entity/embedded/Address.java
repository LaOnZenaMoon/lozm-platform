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

    public void setAddress(String country, String zipCode, String city, String street, String etc) {
        this.country = country;
        this.zipCode = zipCode;
        this.city = city;
        this.street = street;
        this.etc = etc;
    }

}
