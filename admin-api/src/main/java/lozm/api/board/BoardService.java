package lozm.api.board;

import lombok.RequiredArgsConstructor;
import lozm.repository.board.BoardRepository;
import lozm.repository.board.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;



}
