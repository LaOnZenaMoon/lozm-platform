package lozm.object.dto.board;

import lombok.Getter;
import lozm.object.dto.BaseUserDto;

import javax.validation.constraints.NotNull;

public class PostBoardDto {

    @Getter
    public static class Request extends BaseUserDto {
        @NotNull
        private String boardType;

        @NotNull
        private String contentType;

        @NotNull
        private String title;

        @NotNull
        private String content;

        public static PostBoardDto.Request setRequestTestData(String boardType, String contentType, String title, String content, Long userId) {
            PostBoardDto.Request reqDto = new PostBoardDto.Request();
            reqDto.boardType = boardType;
            reqDto.contentType = contentType;
            reqDto.title = title;
            reqDto.content = content;
            reqDto.setCreatedBy(userId);

            return reqDto;
        }
    }

}
