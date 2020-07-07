package lozm.object.vo.board;

import lombok.Builder;
import lombok.Getter;
import lozm.object.vo.BaseVo;

import java.time.LocalDateTime;

@Getter
public class CommentVo extends BaseVo {

    private Long id;
    private String commentType;
    private String content;

    private Long boardId;


    @Builder
    public CommentVo(LocalDateTime createdDt, LocalDateTime modifiedDt, Long createdBy, Long modifiedBy, int flag, Long id, String commentType, String content, Long boardId) {
        super(createdDt, modifiedDt, createdBy, modifiedBy, flag);
        this.id = id;
        this.commentType = commentType;
        this.content = content;
        this.boardId = boardId;
    }

}
