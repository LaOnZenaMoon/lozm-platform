package lozm.object.dto.board;

import lombok.Getter;

import javax.validation.constraints.NotNull;

public class PutCommentDto {

    @Getter
    public static class Request {
        @NotNull
        private Long id;

        private String commentType;

        private String content;
    }

}
