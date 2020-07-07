package lozm.object.dto.board;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lozm.entity.board.Comment;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class GetBoardDto {

    private Long id;
    private String boardType;
    private String contentType;
    private String title;
    private String content;
    private List<Comment> comments;

    public GetBoardDto(Long id, String boardType, String contentType, String title, String content, List<Comment> comments) {
        this.id = id;
        this.boardType = boardType;
        this.contentType = contentType;
        this.title = title;
        this.content = content;
        this.comments = comments;
    }

    @Getter
    @Setter
    public static class Response {
        List<GetBoardDto> list = new ArrayList<>();
    }

}
