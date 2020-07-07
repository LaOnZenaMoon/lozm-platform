package lozm.api.board;

import lombok.RequiredArgsConstructor;
import lozm.object.dto.ApiResponseCode;
import lozm.object.dto.ApiResponseDto;

import lozm.object.dto.board.*;
import lozm.object.vo.board.BoardVo;
import lozm.object.vo.board.CommentVo;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(value = "/api/board")
@RestController
@RequiredArgsConstructor
public class BoardAPIController {

    private final BoardService boardService;


    @GetMapping("/{boardType}")
    public ApiResponseDto getBoard(@PathVariable(value = "boardType") String boardType) throws Exception {
        List<GetBoardDto> result = boardService.getBoardList(boardType);

        GetBoardDto.Response resDto = new GetBoardDto.Response();
        resDto.setList(result);

        return ApiResponseDto.createException(ApiResponseCode.OK, resDto);
    }

    @GetMapping("/{boardId}")
    public ApiResponseDto getBoardDetail(@PathVariable(value = "boardId") Long boardId) throws Exception {
        BoardVo boardVo = BoardVo.builder()
                .id(boardId)
                .build();

        GetBoardDto resDto = boardService.getBoardDetail(boardVo);

        return ApiResponseDto.createException(ApiResponseCode.OK, resDto);
    }

    @PostMapping
    public ApiResponseDto postBoard(@RequestBody @Valid PostBoardDto.Request reqDto) throws Exception {
        BoardVo boardVo = BoardVo.builder()
                .boardType(reqDto.getBoardType())
                .contentType(reqDto.getContentType())
                .title(reqDto.getTitle())
                .content(reqDto.getContent())
                .build();

        boardService.save(boardVo);

        return ApiResponseDto.createException(ApiResponseCode.OK, null);
    }

    @PutMapping
    public ApiResponseDto putBoard(@RequestBody @Valid PutBoardDto.Request reqDto) throws Exception {
        BoardVo boardVo = BoardVo.builder()
                .id(reqDto.getId())
                .title(reqDto.getTitle())
                .content(reqDto.getContent())
                .flag(1)
                .build();

        boardService.update(boardVo);

        return ApiResponseDto.createException(ApiResponseCode.OK, null);
    }

    @DeleteMapping
    public ApiResponseDto deleteBoard(@RequestBody @Valid DeleteBoardDto.Request reqDto) throws Exception {
        for(DeleteBoardDto dto : reqDto.getList()) {
            BoardVo boardVo = BoardVo.builder()
                    .id(dto.getId())
                    .flag(0)
                    .build();

            boardService.delete(boardVo);
        }

        return ApiResponseDto.createException(ApiResponseCode.OK, null);
    }

    @GetMapping("/{boardId}")
    public ApiResponseDto getComment(@PathVariable(value = "boardId") Long boardId) throws Exception {
        List<GetCommentDto> result = boardService.getCommentList(boardId);

        GetCommentDto.Response resDto = new GetCommentDto.Response();
        resDto.setList(result);

        return ApiResponseDto.createException(ApiResponseCode.OK, resDto);
    }

    @PostMapping
    public ApiResponseDto postComment(@RequestBody @Valid PostCommentDto.Request reqDto) throws Exception {
        CommentVo commentVo = CommentVo.builder()
                .commentType(reqDto.getCommentType())
                .content(reqDto.getContent())
                .build();

        boardService.save(commentVo);

        return ApiResponseDto.createException(ApiResponseCode.OK, null);
    }

    @PutMapping
    public ApiResponseDto putComment(@RequestBody @Valid PutCommentDto.Request reqDto) throws Exception {
        CommentVo commentVo = CommentVo.builder()
                .id(reqDto.getId())
                .commentType(reqDto.getCommentType())
                .content(reqDto.getContent())
                .build();

        boardService.update(commentVo);

        return ApiResponseDto.createException(ApiResponseCode.OK, null);
    }

    @DeleteMapping
    public ApiResponseDto deleteComment(@RequestBody @Valid DeleteCommentDto.Request reqDto) throws Exception {
        for(DeleteCommentDto dto : reqDto.getList()) {
            CommentVo commentVo = CommentVo.builder()
                    .id(dto.getId())
                    .flag(0)
                    .build();

            boardService.delete(commentVo);
        }

        return ApiResponseDto.createException(ApiResponseCode.OK, null);
    }

}
