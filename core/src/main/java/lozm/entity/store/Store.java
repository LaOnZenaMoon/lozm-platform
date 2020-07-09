package lozm.entity.store;

import lombok.Getter;
import lozm.entity.BaseEntity;
import lozm.entity.item.Item;
import lozm.object.vo.store.StoreVo;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(schema = "LOZM", name = "STORE")
@Getter
public class Store extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "STORE_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "TEL_NUMBER")
    private String telNumber;

    @Column(name = "INFO")
    private String info;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Item> itemList;

    public void insertStore(StoreVo storeVo) {
        this.name = storeVo.getName();
        this.telNumber = storeVo.getTelNumber();
        this.info = storeVo.getInfo();
        this.setBaseEntity(storeVo.getCreatedBy(), null, 1);
    }

    public void updateStore(StoreVo storeVo) {
        this.name = storeVo.getName();
        this.telNumber = storeVo.getTelNumber();
        this.info = storeVo.getInfo();
        this.setBaseEntity(null, storeVo.getModifiedBy(), 1);
    }

    public void deleteStore(StoreVo storeVo) {
        this.setBaseEntity(null, storeVo.getModifiedBy(), 0);
    }

}
