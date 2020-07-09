package lozm.api.board;

import lombok.RequiredArgsConstructor;
import lozm.entity.board.Board;
import lozm.entity.board.Comment;
import lozm.global.exception.ServiceException;
import lozm.object.code.BoardType;
import lozm.object.dto.board.GetBoardDto;
import lozm.object.dto.board.GetCommentDto;
import lozm.object.vo.board.BoardVo;
import lozm.object.vo.board.CommentVo;
import lozm.repository.board.BoardRepository;
import lozm.repository.board.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;


    public List<GetBoardDto> getBoardList(String boardType) {
        List<Board> boardList = new ArrayList<>();

        if(String.valueOf(BoardType.ALL).equals(boardType)) {
            boardList = boardRepository.selectBoardList();
        } else {
            boardList = boardRepository.selectBoardListByBoardType(boardType);
        }

        List<GetBoardDto> rtnList = new ArrayList<>();
        for (Board board : boardList) {
            GetBoardDto dto = GetBoardDto.builder()
                    .id(board.getId())
                    .boardType(board.getBoardType())
                    .contentType(board.getContentType())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .build();

            rtnList.add(dto);
        }

        return rtnList;
    }

    public GetBoardDto getBoardDetail(BoardVo boardVo) {
        Optional<Board> findBoard = findBoard(boardVo.getId());
        Board board = findBoard.get();
        return GetBoardDto.builder()
                .id(board.getId())
                .boardType(board.getBoardType())
                .contentType(board.getContentType())
                .title(board.getTitle())
                .content(board.getContent())
                .build();
    }

    @Transactional
    public void save(BoardVo boardVo) {
        Board board = new Board();
        board.insertBoard(boardVo);

        boardRepository.save(board);
    }

    @Transactional
    public void update(BoardVo boardVo) {
        Optional<Board> findBoard = findBoard(boardVo.getId());
        findBoard.get().updateBoard(boardVo);
    }

    @Transactional
    public void delete(BoardVo boardVo) {
        Optional<Board> findBoard = findBoard(boardVo.getId());
        findBoard.get().deleteBoard(boardVo);
    }

    public List<GetCommentDto> getCommentList(Long boardId) {
        List<Comment> commentList = commentRepository.selectCommentListByBoardId(boardId);
        List<GetCommentDto> rtnList = new ArrayList<>();
        for (Comment comment : commentList) {
            GetCommentDto dto = GetCommentDto.builder()
                    .id(comment.getId())
                    .commentType(comment.getCommentType())
                    .content(comment.getContent())
                    .build();

            rtnList.add(dto);
        }

        return rtnList;
    }

    @Transactional
    public void save(CommentVo commentVo) {
        Optional<Board> findBoard = findBoard(commentVo.getBoardId());

        Comment comment = new Comment();
        comment.insertComment(commentVo, findBoard.get());

        commentRepository.save(comment);
    }

    @Transactional
    public void update(CommentVo commentVo) {
        Optional<Comment> findComment = findComment(commentVo.getId());
        findComment.get().updateComment(commentVo);
    }

    @Transactional
    public void delete(CommentVo commentVo) {
        Optional<Comment> findComment = findComment(commentVo.getId());
        findComment.get().deleteComment(commentVo);
    }

    private Optional<Board> findBoard(Long boardId) {
        Optional<Board> findBoard = boardRepository.findById(boardId);
        findBoard.orElseThrow(() -> {
            throw new ServiceException("BOARD_0002", "Board doesn't exist.");
        });
        return findBoard;
    }

    private Optional<Comment> findComment(Long commentId) {
        Optional<Comment> findComment = commentRepository.findById(commentId);
        findComment.orElseThrow(() -> {
            throw new ServiceException("COMMENT_0002", "Comment doesn't exist.");
        });
        return findComment;
    }

}
