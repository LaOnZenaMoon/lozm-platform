package lozm.object.dto.board;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class PutBoardDto {

    @Getter
    public static class Request {
        @NotNull
        private Long id;

        private String title;

        private String content;
    }

}
