package lozm.object.dto.board;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class DeleteCommentDto {

    private Long id;

    @Getter
    public static class Request {
        private List<DeleteCommentDto> list = new ArrayList<>();
    }

}
