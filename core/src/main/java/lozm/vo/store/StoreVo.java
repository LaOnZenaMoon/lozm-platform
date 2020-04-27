package lozm.vo.store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreVo {

    private Long id;
    private String name;
    private String telNumber;
    private String info;
    private int flag;

    private List<StoreVo> storeList;

}
