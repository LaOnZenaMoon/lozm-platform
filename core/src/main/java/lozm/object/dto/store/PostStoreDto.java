package lozm.object.dto.store;

import lombok.Getter;
import lozm.object.dto.BaseUserDto;

import javax.validation.constraints.NotEmpty;

public class PostStoreDto {

    @Getter
    public static class Request extends BaseUserDto {
        @NotEmpty
        private String name;

        @NotEmpty
        private String telNumber;

        @NotEmpty
        private String info;

        public static Request setRequestTestData(String name, String telNumber, String info) {
            PostStoreDto.Request reqDto = new PostStoreDto.Request();
            reqDto.name = name;
            reqDto.telNumber = telNumber;
            reqDto.info = info;

            return reqDto;
        }
    }

}
