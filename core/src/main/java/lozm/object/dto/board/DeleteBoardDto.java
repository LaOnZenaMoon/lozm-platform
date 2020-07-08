package lozm.object.dto.board;

import lombok.Getter;
import lozm.object.dto.BaseUserDto;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class DeleteBoardDto {
    @NotNull
    private Long id;

    @Getter
    public static class Request extends BaseUserDto {
        private List<DeleteBoardDto> list = new ArrayList<>();
    }

}
