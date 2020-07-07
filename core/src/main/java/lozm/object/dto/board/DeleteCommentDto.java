package lozm.object.dto.board;

import lombok.Getter;
import lozm.object.dto.BaseUserDto;

import java.util.ArrayList;
import java.util.List;

@Getter
public class DeleteCommentDto {

    private Long id;

    @Getter
    public static class Request extends BaseUserDto {
        private List<DeleteCommentDto> list = new ArrayList<>();
    }

}
