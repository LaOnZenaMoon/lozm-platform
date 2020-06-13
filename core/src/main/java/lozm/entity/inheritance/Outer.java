package lozm.entity.inheritance;

import lombok.Getter;
import lozm.entity.embedded.Clothing;
import lozm.entity.item.Item;
import lozm.entity.store.Store;
import lozm.object.vo.item.ItemVo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("OUTER")
@Getter
public class Outer extends Item {

    @Embedded
    private Clothing clothing;

    public void insertOuter(ItemVo itemVo, Store store) {
        insertItem(itemVo, store);
        clothing = new Clothing();
        clothing.setClothing(itemVo.getContents(), itemVo.getSize());
    }

    public void updateOuter(ItemVo itemVo) {
        updateItem(itemVo);
        clothing.setClothing(itemVo.getContents(), itemVo.getSize());
    }
}
