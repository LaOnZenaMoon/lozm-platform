package lozm.entity.board;

import lombok.Getter;
import lozm.entity.BaseEntity;

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

}
