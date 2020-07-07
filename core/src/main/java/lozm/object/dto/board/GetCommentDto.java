package lozm.object.dto.board;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class GetCommentDto {

    private Long id;
    private String name;
    private String contents;
    private String type;
    private Long amount;
    private Long quantity;
    private LocalDateTime startDt;
    private LocalDateTime endDt;

//    public GetCouponDto(Long id, String name, String contents, CouponType type, Long amount) {
//        this.id = id;
//        this.name = name;
//        this.contents = contents;
//        this.type = type;
//        this.amount = amount;
//    }


    public GetCommentDto(Long id, String name, String contents, String type, Long amount, Long quantity, LocalDateTime startDt, LocalDateTime endDt) {
        this.id = id;
        this.name = name;
        this.contents = contents;
        this.type = type;
        this.amount = amount;
        this.quantity = quantity;
        this.startDt = startDt;
        this.endDt = endDt;
    }

    @Getter
    @Setter
    public static class Response {
        List<GetCommentDto> list = new ArrayList<>();
    }

}
