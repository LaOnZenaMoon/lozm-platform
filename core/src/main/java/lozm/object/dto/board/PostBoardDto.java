package lozm.object.dto.board;

import lombok.Getter;

import javax.validation.constraints.NotNull;

public class PostBoardDto {

    @Getter
    public static class Request {
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
