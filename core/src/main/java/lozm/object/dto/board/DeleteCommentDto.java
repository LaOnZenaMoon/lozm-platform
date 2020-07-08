package lozm.object.dto.board;

import lombok.Getter;
import lozm.object.dto.BaseUserDto;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
public class DeleteCommentDto {
    @NotNull
    private Long id;

    @Getter
    public static class Request extends BaseUserDto {
        private List<DeleteCommentDto> list = new ArrayList<>();
    }

}
