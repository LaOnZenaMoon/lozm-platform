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
    }

}
