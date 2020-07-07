package lozm.object.vo.board;

import lombok.Builder;
import lombok.Getter;
import lozm.object.vo.BaseVo;

import java.time.LocalDateTime;

@Getter
public class BoardVo extends BaseVo {

    private Long id;
    private String boardType;
    private String contentType;
    private String title;
    private String content;


    @Builder
    public BoardVo(LocalDateTime createdDt, LocalDateTime modifiedDt, Long createdBy, Long modifiedBy, int flag, Long id, String boardType, String contentType, String title, String content) {
        super(createdDt, modifiedDt, createdBy, modifiedBy, flag);
        this.id = id;
        this.boardType = boardType;
        this.contentType = contentType;
        this.title = title;
        this.content = content;
    }

}
