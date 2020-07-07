package lozm.entity.board;

import lombok.Getter;
import lozm.entity.BaseEntity;
import lozm.object.vo.board.BoardVo;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(schema = "LOZM", name = "BOARD")
@Getter
public class Board extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "BOARD_ID")
    private Long id;

    @Column(name = "BOARD_TYPE")
    private String boardType;

    @Column(name = "CONTENT_TYPE")
    private String contentType;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT")
    private String content;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Comment> comments;


    public void insertBoard(BoardVo boardVo) {
        this.boardType = boardVo.getBoardType();
        this.contentType = boardVo.getContentType();
        this.title = boardVo.getTitle();
        this.content = boardVo.getContent();
    }

    public void updateBoard(BoardVo boardVo) {
        this.boardType = boardVo.getBoardType();
        this.contentType = boardVo.getContentType();
        this.title = boardVo.getTitle();
        this.content = boardVo.getContent();
        this.setBaseEntity("", boardVo.getFlag());
    }

    public void deleteBoard(BoardVo boardVo) {
        this.setBaseEntity("", boardVo.getFlag());
    }

}
