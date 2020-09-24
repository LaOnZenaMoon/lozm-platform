package lozm.object.dto.board;

import lombok.*;
import lozm.entity.board.Comment;

import java.util.ArrayList;
import java.util.List;

@Getter @Builder
@NoArgsConstructor @AllArgsConstructor
public class GetBoardDto {

    private Long id;
    private String boardType;
    private String contentType;
    private String title;
    private String content;
    private List<Comment> comments;


    @Getter
    @Setter
    public static class Response {
        List<GetBoardDto> list = new ArrayList<>();
    }

}
