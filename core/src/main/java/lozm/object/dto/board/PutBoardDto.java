package lozm.object.dto.board;

import lombok.Getter;
import lozm.object.dto.BaseUserDto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class PutBoardDto {

    @Getter
    public static class Request extends BaseUserDto {
        @NotNull
        private Long id;

        @NotEmpty
        private String boardType;

        @NotEmpty
        private String contentType;

        private String title;

        private String content;
    }

}
