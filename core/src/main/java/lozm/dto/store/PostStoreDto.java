package lozm.dto.store;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;

public class PostStoreDto {

    @Getter
    public static class Request {
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
