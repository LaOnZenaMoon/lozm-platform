package lozm.domain.entity.inheritance;

import lombok.Getter;
import lozm.core.dto.item.PostItemDto;
import lozm.core.vo.item.ItemVo;
import lozm.domain.entity.item.Item;
import lozm.domain.entity.embedded.Clothing;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;

@Entity
@Getter
@DiscriminatorValue("BOTTOM")
public class Bottom extends Item {

    @Embedded
    private Clothing clothing;

    public void insertBottom(ItemVo itemVo) {
        insertItem(itemVo);
        clothing = new Clothing();
        clothing.setClothing(itemVo.getContents(), itemVo.getSize());
    }

}
