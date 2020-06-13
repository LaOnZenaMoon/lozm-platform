package lozm.object.dto.store;

import lombok.Getter;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
public class DeleteStoreDto {

    private Long id;
    private String name;
    private String telNumber;
    private String info;

    @Getter
    public static class Request {
        @Size(min = 1)
        private List<DeleteStoreDto> list = new ArrayList<>();
    }

}
