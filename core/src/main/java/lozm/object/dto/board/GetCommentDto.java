package lozm.object.dto.board;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetCommentDto {

    private Long id;
    private String commentType;
    private int flag;
    private String content;

    @Getter
    @Setter
    public static class Response {
        List<GetCommentDto> list = new ArrayList<>();
    }

}
