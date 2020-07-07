package lozm.object.dto.board;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;

public class PostCommentDto {

    @Getter
    public static class Request {
        @NotEmpty
        private Long boardId;

        @NotEmpty
        private String commentType;

        @NotEmpty
        private String content;
    }

}
