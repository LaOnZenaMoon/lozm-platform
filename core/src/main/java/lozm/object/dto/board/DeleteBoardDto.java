package lozm.object.dto.board;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class DeleteBoardDto {

    private Long id;

    @Getter
    public static class Request {
        private List<DeleteBoardDto> list = new ArrayList<>();
    }

}
