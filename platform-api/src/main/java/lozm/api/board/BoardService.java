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
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;


    public List<GetBoardDto> getBoardList(String boardType) {
        List<Board> boardList = null;
        if(String.valueOf(BoardType.ALL).equals(boardType)) {
            boardList = boardRepository.selectBoardList();
        } else {
            boardList = boardRepository.selectBoardListByBoardType(boardType);
        }

        return boardList.stream()
                .map(board -> GetBoardDto.builder()
                        .id(board.getId())
                        .boardType(board.getBoardType())
                        .contentType(board.getContentType())
                        .title(board.getTitle())
                        .content(board.getContent())
                        .build())
                .collect(Collectors.toList());
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
        return commentRepository.selectCommentListByBoardId(boardId)
                .stream()
                .map(comment -> GetCommentDto.builder()
                        .id(comment.getId())
                        .commentType(comment.getCommentType())
                        .flag(comment.getFlag())
                        .content(comment.getContent())
                        .build())
                .collect(Collectors.toList());
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
        findBoard.orElseThrow(() -> new ServiceException("BOARD_0002", "Board doesn't exist."));
        return findBoard;
    }

    private Optional<Comment> findComment(Long commentId) {
        Optional<Comment> findComment = commentRepository.findById(commentId);
        findComment.orElseThrow(() -> new ServiceException("COMMENT_0002", "Comment doesn't exist."));
        return findComment;
    }

}
