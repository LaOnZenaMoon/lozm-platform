package lozm.object.dto.delivery;

import lombok.Getter;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
public class DeleteDeliveryDto {

    private Long id;
    private String country;
    private String zipCode;
    private String city;
    private String street;
    private String etc;
    private String status;
    private int flag;

    @Getter
    public static class Request {
        @Size(min = 1)
        private List<DeleteDeliveryDto> list = new ArrayList<>();
    }

}
