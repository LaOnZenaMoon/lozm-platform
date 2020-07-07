package lozm.object.dto.board;

import lombok.Getter;
import lozm.object.dto.BaseUserDto;

import javax.validation.constraints.NotNull;

public class PutCommentDto {

    @Getter
    public static class Request extends BaseUserDto {
        @NotNull
        private Long id;

        private String commentType;

        private String content;
    }

}
