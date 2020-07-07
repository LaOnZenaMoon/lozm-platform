package lozm.entity.board;

import lombok.Getter;
import lozm.entity.BaseEntity;
import lozm.object.vo.board.BoardVo;
import lozm.object.vo.board.CommentVo;

import javax.persistence.*;

@Entity
@Table(schema = "LOZM", name = "COMMENT")
@Getter
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "DELIVERY_ID")
    private Long id;

    @Column(name = "COMMENT_TYPE")
    private String commentType;

    @Column(name = "CONTENT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_ID")
    private Board board;

    public void insertComment(CommentVo commentVo, Board board) {
        this.commentType = commentVo.getCommentType();
        this.content = commentVo.getContent();
        this.board = board;
    }

    public void updateComment(CommentVo commentVo) {
        this.commentType = commentVo.getCommentType();
        this.content = commentVo.getContent();
        this.setBaseEntity("", commentVo.getFlag());
    }

    public void deleteComment(CommentVo commentVo) {
        this.setBaseEntity("", commentVo.getFlag());
    }

}
