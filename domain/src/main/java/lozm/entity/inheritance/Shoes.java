package lozm.entity.inheritance;

import lombok.Getter;
import lozm.vo.item.ItemVo;
import lozm.entity.item.Item;
import lozm.entity.embedded.Clothing;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;

@Entity
@Getter
@DiscriminatorValue("SHOES")
public class Shoes extends Item {

    @Embedded
    private Clothing clothing;

    public void insertShoes(ItemVo itemVo) {
        insertItem(itemVo);
        clothing = new Clothing();
        clothing.setClothing(itemVo.getContents(), itemVo.getSize());
    }

    public void updateShoes(ItemVo itemVo) {
        updateItem(itemVo);
        clothing.setClothing(itemVo.getContents(), itemVo.getSize());
    }
}
