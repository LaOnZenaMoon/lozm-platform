package lozm.object.dto.delivery;

import lombok.Getter;
import lombok.Setter;
import lozm.object.dto.BaseUserDto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PutDeliveryDto {

    @Getter
    public static class Request extends BaseUserDto {
        @NotNull
        private Long id;

        @NotEmpty
        private String country;

        @NotEmpty
        private String zipCode;

        @NotEmpty
        private String city;

        @NotEmpty
        private String street;

        private String etc;

        private String status;

        private int flag;
    }

}
