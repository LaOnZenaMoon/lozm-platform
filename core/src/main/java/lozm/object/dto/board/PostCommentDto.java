package lozm.object.dto.board;

import lombok.Getter;
import lozm.object.dto.BaseUserDto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PostCommentDto {

    @Getter
    public static class Request extends BaseUserDto {
        @NotNull
        private Long boardId;

        @NotEmpty
        private String commentType;

        @NotEmpty
        private String content;

        public static PostCommentDto.Request setRequestTestData(Long boardId, String commentType, String content, Long userId) {
            PostCommentDto.Request reqDto = new PostCommentDto.Request();
            reqDto.boardId = boardId;
            reqDto.commentType = commentType;
            reqDto.content = content;
            reqDto.setCreatedBy(userId);

            return reqDto;
        }
    }

}
