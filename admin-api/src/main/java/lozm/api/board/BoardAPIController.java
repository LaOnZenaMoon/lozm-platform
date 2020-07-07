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


    @GetMapping
    public ApiResponseDto getBoard() throws Exception {
        List<GetBoardDto> result = boardService.getBoardList();

        GetBoardDto.Response resDto = new GetBoardDto.Response();
        resDto.setList(result);

        return ApiResponseDto.createException(ApiResponseCode.OK, resDto);
    }

    @GetMapping("/{couponId}")
    public ApiResponseDto getBoardDetail(@PathVariable(value = "couponId") Long couponId) throws Exception {
        BoardVo couponVo = BoardVo.builder()
                .id(couponId)
                .build();

        GetBoardDto resDto = boardService.getBoardDetail(couponVo);

        return ApiResponseDto.createException(ApiResponseCode.OK, resDto);
    }

    @PostMapping
    public ApiResponseDto postBoard(@RequestBody @Valid PostBoardDto.Request reqDto) throws Exception {
        BoardVo couponVo = BoardVo.builder()
                .name(reqDto.getName())
                .contents(reqDto.getContents())
                .type(reqDto.getType())
                .amount(reqDto.getAmount())
                .quantity(reqDto.getQuantity())
                .startDt(reqDto.getStartDt())
                .endDt(reqDto.getEndDt())
                .flag(1)
                .build();

        boardService.save(couponVo);

        return ApiResponseDto.createException(ApiResponseCode.OK, null);
    }

    @PutMapping
    public ApiResponseDto putBoard(@RequestBody @Valid PutBoardDto.Request reqDto) throws Exception {
        BoardVo couponVo = BoardVo.builder()
                .id(reqDto.getId())
                .name(reqDto.getName())
                .contents(reqDto.getContents())
                .type(reqDto.getType())
                .amount(reqDto.getAmount())
                .quantity(reqDto.getQuantity())
                .startDt(reqDto.getStartDt())
                .endDt(reqDto.getEndDt())
                .flag(1)
                .build();

        boardService.update(couponVo);

        return ApiResponseDto.createException(ApiResponseCode.OK, null);
    }

    @DeleteMapping
    public ApiResponseDto deleteBoard(@RequestBody @Valid DeleteBoardDto.Request reqDto) throws Exception {
        for(DeleteBoardDto dto : reqDto.getList()) {
            BoardVo couponVo = BoardVo.builder()
                    .id(dto.getId())
                    .flag(0)
                    .build();

            boardService.delete(couponVo);
        }

        return ApiResponseDto.createException(ApiResponseCode.OK, null);
    }

    @GetMapping
    public ApiResponseDto getComment() throws Exception {
        List<GetCommentDto> result = boardService.getCommentList();

        GetCommentDto.Response resDto = new GetCommentDto.Response();
        resDto.setList(result);

        return ApiResponseDto.createException(ApiResponseCode.OK, resDto);
    }

    @GetMapping("/{couponId}")
    public ApiResponseDto getCommentDetail(@PathVariable(value = "couponId") Long couponId) throws Exception {
        CommentVo couponVo = CommentVo.builder()
                .id(couponId)
                .build();

        GetCommentDto resDto = boardService.getCommentDetail(couponVo);

        return ApiResponseDto.createException(ApiResponseCode.OK, resDto);
    }

    @PostMapping
    public ApiResponseDto postComment(@RequestBody @Valid PostCommentDto.Request reqDto) throws Exception {
        CommentVo couponVo = CommentVo.builder()
                .name(reqDto.getName())
                .contents(reqDto.getContents())
                .type(reqDto.getType())
                .amount(reqDto.getAmount())
                .quantity(reqDto.getQuantity())
                .startDt(reqDto.getStartDt())
                .endDt(reqDto.getEndDt())
                .flag(1)
                .build();

        boardService.save(couponVo);

        return ApiResponseDto.createException(ApiResponseCode.OK, null);
    }

    @PutMapping
    public ApiResponseDto putComment(@RequestBody @Valid PutCommentDto.Request reqDto) throws Exception {
        CommentVo couponVo = CommentVo.builder()
                .id(reqDto.getId())
                .name(reqDto.getName())
                .contents(reqDto.getContents())
                .type(reqDto.getType())
                .amount(reqDto.getAmount())
                .quantity(reqDto.getQuantity())
                .startDt(reqDto.getStartDt())
                .endDt(reqDto.getEndDt())
                .flag(1)
                .build();

        boardService.update(couponVo);

        return ApiResponseDto.createException(ApiResponseCode.OK, null);
    }

    @DeleteMapping
    public ApiResponseDto deleteComment(@RequestBody @Valid DeleteCommentDto.Request reqDto) throws Exception {
        for(DeleteCommentDto dto : reqDto.getList()) {
            CommentVo couponVo = CommentVo.builder()
                    .id(dto.getId())
                    .flag(0)
                    .build();

            boardService.delete(couponVo);
        }

        return ApiResponseDto.createException(ApiResponseCode.OK, null);
    }

}
