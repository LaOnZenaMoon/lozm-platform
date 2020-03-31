package lozm.api.item;

import lombok.RequiredArgsConstructor;
import lozm.core.code.ItemType;
import lozm.core.dto.item.GetItemDto;
import lozm.core.dto.item.PostItemDto;
import lozm.core.dto.item.PutItemDto;
import lozm.core.exception.APIException;
import lozm.domain.entity.Item;
import lozm.domain.repository.ItemRepository;
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


    public List<GetItemDto.Response> findAllItems() {
        List<Item> itemList = itemRepository.findAll();

        return itemList.stream().map(o -> new GetItemDto.Response(o.getId(), o.getName(), o.getPrice(), o.getQuantity()))
                .collect(toList());
    }

    @Transactional
    public void save(PostItemDto.Request reqDto) throws Exception {
        //TODO @Inheritance 분기 처리 추가 필요.

        String itemType = reqDto.getType();
        if(ItemType.OUTER.equals(itemType)) {

        } else if(ItemType.TOP.equals(itemType)) {

        } else if(ItemType.BOTTOM.equals(itemType)) {

        } else if(ItemType.SHOES.equals(itemType)) {

        } else {
            throw new APIException("ITEM_SAVE_NO_ITEM_TYPE", "ItemType doesn't exist.");
        }

        Item item = new Item();
        item.insertItem(reqDto);

        itemRepository.save(item);
    }

    @Transactional
    public void update(PutItemDto.Request reqDto) throws Exception {
        Optional<Item> findItem = itemRepository.findById(reqDto.getId());
        findItem.orElseThrow(() -> new APIException("ITEM_0002", "Item doesn't exist."));
        findItem.get().updateItem(reqDto);
    }
    
}
