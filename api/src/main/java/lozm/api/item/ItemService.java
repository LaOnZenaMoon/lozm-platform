package lozm.api.item;

import lombok.RequiredArgsConstructor;
import lozm.core.code.ItemType;
import lozm.core.dto.item.GetItemDto;
import lozm.core.dto.item.PostItemDto;
import lozm.core.dto.item.PutItemDto;
import lozm.core.exception.APIException;
import lozm.domain.entity.item.Item;
import lozm.domain.entity.inheritance.Bottom;
import lozm.domain.entity.inheritance.Outer;
import lozm.domain.entity.inheritance.Shoes;
import lozm.domain.entity.inheritance.Top;
import lozm.domain.repository.item.ItemRepository;
import lozm.domain.repository.item.inherit.BottomRepository;
import lozm.domain.repository.item.inherit.OuterRepository;
import lozm.domain.repository.item.inherit.ShoesRepository;
import lozm.domain.repository.item.inherit.TopRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final OuterRepository outerRepository;
    private final TopRepository topRepository;
    private final BottomRepository bottomRepository;
    private final ShoesRepository shoesRepository;


    public List<GetItemDto.Response> findAllItems() {
        List<Item> itemList = itemRepository.findAll();

        return itemList.stream().map(o -> new GetItemDto.Response(o.getId(), o.getName(), o.getPrice(), o.getQuantity()))
                .collect(toList());
    }

    @Transactional
    public void save(PostItemDto.Request reqDto) throws Exception {
        String itemType = reqDto.getType();
        if(ItemType.OUTER.toString().equals(itemType)) {
            Outer outer = new Outer();
            outer.insertOuter(reqDto);
            outerRepository.save(outer);
        } else if(ItemType.TOP.toString().equals(itemType)) {
            Top top = new Top();
            top.insertTop(reqDto);
            topRepository.save(top);
        } else if(ItemType.BOTTOM.toString().equals(itemType)) {
            Bottom bottom = new Bottom();
            bottom.insertBottom(reqDto);
            bottomRepository.save(bottom);
        } else if(ItemType.SHOES.toString().equals(itemType)) {
            Shoes shoes = new Shoes();
            shoes.insertShoes(reqDto);
            shoesRepository.save(shoes);
        } else {
            throw new APIException("ITEM_SAVE_NO_ITEM_TYPE", "ItemType doesn't exist.");
        }
    }

    @Transactional
    public void update(PutItemDto.Request reqDto) throws Exception {
        Optional<Item> findItem = itemRepository.findById(reqDto.getId());
        findItem.orElseThrow(() -> new APIException("ITEM_0002", "Item doesn't exist."));
        findItem.get().updateItem(reqDto);
    }
    
}
