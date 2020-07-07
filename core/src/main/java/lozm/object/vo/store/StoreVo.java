package lozm.object.vo.store;

import lombok.Builder;
import lombok.Getter;
import lozm.object.vo.BaseVo;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class StoreVo extends BaseVo {

    private Long id;
    private String name;
    private String telNumber;
    private String info;
    private int flag;

    private List<StoreVo> storeList;


    @Builder
    public StoreVo(LocalDateTime createdDt, LocalDateTime modifiedDt, Long createdBy, Long modifiedBy, int flag, Long id, String name, String telNumber, String info, int flag1, List<StoreVo> storeList) {
        super(createdDt, modifiedDt, createdBy, modifiedBy, flag);
        this.id = id;
        this.name = name;
        this.telNumber = telNumber;
        this.info = info;
        this.flag = flag1;
        this.storeList = storeList;
    }

}
