package lozm.entity.inheritance;

import lombok.Getter;
import lozm.entity.embedded.Clothing;
import lozm.entity.item.Item;
import lozm.entity.store.Store;
import lozm.vo.item.ItemVo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("TOP")
@Getter
public class Top extends Item {

    @Embedded
    private Clothing clothing;

    public void insertTop(ItemVo itemVo, Store store) {
        insertItem(itemVo, store);
        clothing = new Clothing();
        clothing.setClothing(itemVo.getContents(), itemVo.getSize());
    }

    public void updateTop(ItemVo itemVo) {
        updateItem(itemVo);
        clothing.setClothing(itemVo.getContents(), itemVo.getSize());
    }
}
