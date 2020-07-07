package lozm.api.board;

import lombok.RequiredArgsConstructor;
import lozm.global.jwt.JwtTokenUtils;
import lozm.object.dto.ApiResponseCode;
import lozm.object.dto.ApiResponseDto;
import lozm.object.dto.board.*;
import lozm.object.dto.coupon.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(value = "/coupon")
@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardAPIService boardAPIService;
    private final JwtTokenUtils jwtTokenUtils;


    @GetMapping("/{boardType}")
    public ApiResponseDto getBoard(@PathVariable(value = "boardType") Long boardType) throws Exception {
        return ApiResponseDto.createException(ApiResponseCode.OK, boardAPIService.getBoard(boardType));
    }

    @GetMapping("/{boardId}")
    public ApiResponseDto getBoardDetail(@PathVariable(value = "boardId") Long boardId) throws Exception {
        return ApiResponseDto.createException(ApiResponseCode.OK, boardAPIService.getBoardDetail(boardId));
    }

    @PostMapping
    public ApiResponseDto postBoard(@RequestBody @Valid PostBoardDto.Request reqDto) throws Exception {
        reqDto.setCreatedBy();
        return ApiResponseDto.createException(ApiResponseCode.OK, boardAPIService.postBoard(reqDto));
    }

    @PutMapping
    public ApiResponseDto putBoard(@RequestBody @Valid PutBoardDto.Request reqDto) throws Exception {
        return ApiResponseDto.createException(ApiResponseCode.OK, boardAPIService.putBoard(reqDto));
    }

    @DeleteMapping
    public ApiResponseDto deleteBoard(@RequestBody @Valid DeleteBoardDto.Request reqDto) throws Exception {
        return ApiResponseDto.createException(ApiResponseCode.OK, boardAPIService.deleteBoard(reqDto));
    }

    @GetMapping("/{boardId}")
    public ApiResponseDto getComment(@PathVariable(value = "boardId") Long boardId) throws Exception {
        return ApiResponseDto.createException(ApiResponseCode.OK, boardAPIService.getComment(boardId));
    }

    @PostMapping
    public ApiResponseDto postComment(@RequestBody @Valid PostCommentDto.Request reqDto) throws Exception {
        reqDto.setCreatedBy();
        return ApiResponseDto.createException(ApiResponseCode.OK, boardAPIService.postComment(reqDto));
    }

    @PutMapping
    public ApiResponseDto putComment(@RequestBody @Valid PutCommentDto.Request reqDto) throws Exception {
        return ApiResponseDto.createException(ApiResponseCode.OK, boardAPIService.putComment(reqDto));
    }

    @DeleteMapping
    public ApiResponseDto deleteComment(@RequestBody @Valid DeleteCommentDto.Request reqDto) throws Exception {
        return ApiResponseDto.createException(ApiResponseCode.OK, boardAPIService.deleteComment(reqDto));
    }

}
