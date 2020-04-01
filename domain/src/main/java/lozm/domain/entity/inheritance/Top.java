package lozm.domain.entity.inheritance;

import lombok.Getter;
import lozm.core.dto.item.PostItemDto;
import lozm.domain.entity.item.Item;
import lozm.domain.entity.embedded.Clothing;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;

@Entity
@Getter
@DiscriminatorValue("TOP")
public class Top extends Item {

    @Embedded
    private Clothing clothing;

    public void insertTop(PostItemDto.Request reqDto) {
        insertItem(reqDto);
        clothing = new Clothing();
        clothing.setClothing(reqDto.getContents(), reqDto.getSize());
    }

}
