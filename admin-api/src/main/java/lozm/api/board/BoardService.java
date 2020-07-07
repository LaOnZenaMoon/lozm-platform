package lozm.api.board;

import lombok.RequiredArgsConstructor;
import lozm.object.dto.board.GetBoardDto;
import lozm.object.dto.board.GetCommentDto;
import lozm.object.vo.board.BoardVo;
import lozm.object.vo.board.CommentVo;
import lozm.repository.board.BoardRepository;
import lozm.repository.board.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;


    public List<GetBoardDto> getBoardList(String boardType) {

    }

    public GetBoardDto getBoardDetail(BoardVo boardVo) {

    }

    public void save(BoardVo boardVo) {
    }

    public void update(BoardVo boardVo) {
    }

    public void delete(BoardVo boardVo) {
    }

    public List<GetCommentDto> getCommentList(Long boardId) {

    }

    public void save(CommentVo commentVo) {

    }

    public void update(CommentVo commentVo) {
    }

    public void delete(CommentVo commentVo) {

    }

}
