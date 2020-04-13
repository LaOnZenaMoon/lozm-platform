package lozm.item;

import lombok.RequiredArgsConstructor;
import lozm.code.ItemType;
import lozm.dto.item.GetClothingDto;
import lozm.dto.item.GetItemDto;
import lozm.exception.APIException;
import lozm.vo.item.ItemVo;
import lozm.entity.inheritance.Bottom;
import lozm.entity.inheritance.Outer;
import lozm.entity.inheritance.Shoes;
import lozm.entity.inheritance.Top;
import lozm.entity.item.Item;
import lozm.repository.item.ItemRepository;
import lozm.repository.item.inherit.BottomRepository;
import lozm.repository.item.inherit.OuterRepository;
import lozm.repository.item.inherit.ShoesRepository;
import lozm.repository.item.inherit.TopRepository;
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


    public List<GetItemDto> findAllItems() {
        List<Item> itemList = itemRepository.findAll();

        return itemList.stream().map(o -> new GetItemDto(o.getId(), o.getName(), o.getPrice(), o.getQuantity()))
                .collect(toList());
    }

    @Transactional
    public void save(ItemVo itemVo) throws Exception {
        String itemType = itemVo.getType();
        if(ItemType.OUTER.toString().equals(itemType)) {
            Outer outer = new Outer();
            outer.insertOuter(itemVo);
            outerRepository.save(outer);
        } else if(ItemType.TOP.toString().equals(itemType)) {
            Top top = new Top();
            top.insertTop(itemVo);
            topRepository.save(top);
        } else if(ItemType.BOTTOM.toString().equals(itemType)) {
            Bottom bottom = new Bottom();
            bottom.insertBottom(itemVo);
            bottomRepository.save(bottom);
        } else if(ItemType.SHOES.toString().equals(itemType)) {
            Shoes shoes = new Shoes();
            shoes.insertShoes(itemVo);
            shoesRepository.save(shoes);
        } else {
            throw new APIException("ITEM_SAVE_NO_ITEM_TYPE", "ItemType doesn't exist.");
        }
    }

    @Transactional
    public void update(ItemVo itemVo) throws Exception {
        Optional<Item> findItem = itemRepository.findById(itemVo.getId());
        findItem.orElseThrow(() -> new APIException("ITEM_0002", "Item doesn't exist."));
//        findItem.get().updateItem(itemVo);

        String itemType = findItem.get().getType();
        if(ItemType.OUTER.toString().equals(itemType)) {
            Optional<Outer> findOuter = outerRepository.findById(itemVo.getId());
            findOuter.get().updateOuter(itemVo);
        } else if(ItemType.TOP.toString().equals(itemType)) {
            Optional<Top> findTop = topRepository.findById(itemVo.getId());
            findTop.get().updateTop(itemVo);
        } else if(ItemType.BOTTOM.toString().equals(itemType)) {
            Optional<Bottom> findBottom = bottomRepository.findById(itemVo.getId());
            findBottom.get().updateBottom(itemVo);
        } else if(ItemType.SHOES.toString().equals(itemType)) {
            Optional<Shoes> findShoes = shoesRepository.findById(itemVo.getId());
            findShoes.get().updateShoes(itemVo);
        }
    }

    public List<GetClothingDto> findAllClothing(ItemVo itemVo) throws InterruptedException {
        if(ItemType.OUTER.toString().equals(itemVo.getType())) {
            List<Outer> itemList = outerRepository.findAll();
            return itemList.stream().map(o -> new GetClothingDto(
                    o.getId(),
                    o.getName(),
                    o.getPrice(),
                    o.getQuantity(),
                    o.getClothing().getContents(),
                    o.getClothing().getSizes()
            )).collect(toList());
        } else if(ItemType.TOP.toString().equals(itemVo.getType())) {
            List<Top> itemList = topRepository.findAll();
            return itemList.stream().map(o -> new GetClothingDto(
                    o.getId(),
                    o.getName(),
                    o.getPrice(),
                    o.getQuantity(),
                    o.getClothing().getContents(),
                    o.getClothing().getSizes()
            )).collect(toList());
        } else if(ItemType.BOTTOM.toString().equals(itemVo.getType())) {
            List<Bottom> itemList = bottomRepository.findAll();
            return itemList.stream().map(o -> new GetClothingDto(
                    o.getId(),
                    o.getName(),
                    o.getPrice(),
                    o.getQuantity(),
                    o.getClothing().getContents(),
                    o.getClothing().getSizes()
            )).collect(toList());
        } else if(ItemType.SHOES.toString().equals(itemVo.getType())) {
            List<Shoes> itemList = shoesRepository.findAll();
            return itemList.stream().map(o -> new GetClothingDto(
                    o.getId(),
                    o.getName(),
                    o.getPrice(),
                    o.getQuantity(),
                    o.getClothing().getContents(),
                    o.getClothing().getSizes()
            )).collect(toList());
        } else {
            throw new APIException("ITEM_SAVE_NO_ITEM_TYPE", "ItemType doesn't exist.");
        }
    }
}
