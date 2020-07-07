package lozm.object.dto.board;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class DeleteCommentDto {

    private Long id;
    private String name;
    private String contents;
    private String type;
    private Long amount;
    private Long quantity;
    private LocalDateTime startDt;
    private LocalDateTime endDt;
    private int flag;

    @Getter
    public static class Request {
        private List<DeleteCommentDto> list = new ArrayList<>();
    }

}
