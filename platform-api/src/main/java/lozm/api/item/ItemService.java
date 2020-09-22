package lozm.api.item;

import lombok.RequiredArgsConstructor;
import lozm.global.exception.ServiceException;
import lozm.object.code.ItemType;
import lozm.object.dto.item.GetClothingDto;
import lozm.object.dto.item.GetItemDto;
import lozm.entity.inheritance.Bottom;
import lozm.entity.inheritance.Outer;
import lozm.entity.inheritance.Shoes;
import lozm.entity.inheritance.Top;
import lozm.entity.item.Item;
import lozm.entity.store.Store;
import lozm.repository.RepositorySupport;
import lozm.repository.item.ItemRepository;
import lozm.repository.item.inherit.BottomRepository;
import lozm.repository.item.inherit.OuterRepository;
import lozm.repository.item.inherit.ShoesRepository;
import lozm.repository.item.inherit.TopRepository;
import lozm.api.store.StoreService;
import lozm.object.vo.item.ItemVo;
import lozm.object.vo.store.StoreVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private final StoreService storeService;
    private final RepositorySupport repositorySupport;


    public List<GetItemDto> getItemList() {
        List<Item> itemList = repositorySupport.selectItemList();
        return getItemDtoList(itemList);
    }

    public GetItemDto getItemDetail(ItemVo itemVo) throws Exception {
        if(ItemType.OUTER.toString().equals(itemVo.getType())) {
            Optional<Outer> findItem = findOuter(itemVo.getId());
            Outer item = findItem.get();

            return GetItemDto.builder()
                    .id(item.getId())
                    .name(item.getName())
                    .price(item.getPrice())
                    .quantity(item.getQuantity())
                    .type(item.getType())
                    .contents(item.getClothing().getContents())
                    .sizes(item.getClothing().getSizes())
                    .storeId(item.getStore().getId())
                    .storeName(item.getStore().getName())
                    .build();

        } else if(ItemType.TOP.toString().equals(itemVo.getType())) {
            Optional<Top> findItem = findTop(itemVo.getId());
            Top item = findItem.get();

            return GetItemDto.builder()
                    .id(item.getId())
                    .name(item.getName())
                    .price(item.getPrice())
                    .quantity(item.getQuantity())
                    .type(item.getType())
                    .contents(item.getClothing().getContents())
                    .sizes(item.getClothing().getSizes())
                    .storeId(item.getStore().getId())
                    .storeName(item.getStore().getName())
                    .build();

        } else if(ItemType.BOTTOM.toString().equals(itemVo.getType())) {
            Optional<Bottom> findItem = findBottom(itemVo.getId());
            Bottom item = findItem.get();

            return GetItemDto.builder()
                    .id(item.getId())
                    .name(item.getName())
                    .price(item.getPrice())
                    .quantity(item.getQuantity())
                    .type(item.getType())
                    .contents(item.getClothing().getContents())
                    .sizes(item.getClothing().getSizes())
                    .storeId(item.getStore().getId())
                    .storeName(item.getStore().getName())
                    .build();

        } else if(ItemType.SHOES.toString().equals(itemVo.getType())) {
            Optional<Shoes> findItem = findShoes(itemVo.getId());
            Shoes item = findItem.get();

            return GetItemDto.builder()
                    .id(item.getId())
                    .name(item.getName())
                    .price(item.getPrice())
                    .quantity(item.getQuantity())
                    .type(item.getType())
                    .contents(item.getClothing().getContents())
                    .sizes(item.getClothing().getSizes())
                    .storeId(item.getStore().getId())
                    .storeName(item.getStore().getName())
                    .build();

        } else {
            throw new ServiceException("ITEM_SAVE_NO_ITEM_TYPE", "ItemType doesn't exist.");
        }
    }

    public List<GetItemDto> getItemListByStoreId(ItemVo itemVo) {
        List<Item> itemList = itemRepository.selectItemListByStoreId(itemVo.getStoreId());
        return getItemDtoList(itemList);
    }

    public List<GetClothingDto> getClothingList(ItemVo itemVo) throws Exception {
        List<GetClothingDto> rtnList = new ArrayList<>();

        if(ItemType.OUTER.toString().equals(itemVo.getType())) {

            return repositorySupport.selectOuterList(itemVo)
                    .stream()
                    .map(outer -> GetClothingDto.builder()
                            .id(outer.getId())
                            .name(outer.getName())
                            .price(outer.getPrice())
                            .quantity(outer.getQuantity())
                            .contents(outer.getClothing().getContents())
                            .size(outer.getClothing().getSizes())
                            .build())
                    .collect(Collectors.toList());
        } else if(ItemType.TOP.toString().equals(itemVo.getType())) {

            return repositorySupport.selectTopList(itemVo)
                    .stream()
                    .map(top -> GetClothingDto.builder()
                            .id(top.getId())
                            .name(top.getName())
                            .price(top.getPrice())
                            .quantity(top.getQuantity())
                            .contents(top.getClothing().getContents())
                            .size(top.getClothing().getSizes())
                            .build())
                    .collect(Collectors.toList());
        } else if(ItemType.BOTTOM.toString().equals(itemVo.getType())) {

            return repositorySupport.selectBottomList(itemVo).stream()
                    .map(bottom -> GetClothingDto.builder()
                            .id(bottom.getId())
                            .name(bottom.getName())
                            .price(bottom.getPrice())
                            .quantity(bottom.getQuantity())
                            .contents(bottom.getClothing().getContents())
                            .size(bottom.getClothing().getSizes())
                            .build())
                    .collect(Collectors.toList());
        } else if(ItemType.SHOES.toString().equals(itemVo.getType())) {

            return repositorySupport.selectShoesList(itemVo).stream()
                    .map(shoes -> GetClothingDto.builder()
                            .id(shoes.getId())
                            .name(shoes.getName())
                            .price(shoes.getPrice())
                            .quantity(shoes.getQuantity())
                            .contents(shoes.getClothing().getContents())
                            .size(shoes.getClothing().getSizes())
                            .build())
                    .collect(Collectors.toList());
        } else {
            throw new ServiceException("ITEM_SAVE_NO_ITEM_TYPE", "ItemType doesn't exist.");
        }
    }

    @Transactional
    public void save(ItemVo itemVo) throws Exception {
        StoreVo storeVo = StoreVo.builder()
                .id(itemVo.getStoreId())
                .build();

        Store findStore = storeService.findById(storeVo);

        String itemType = itemVo.getType();
        if(ItemType.OUTER.toString().equals(itemType)) {
            Outer outer = new Outer();
            outer.insertOuter(itemVo, findStore);
            outerRepository.save(outer);
        } else if(ItemType.TOP.toString().equals(itemType)) {
            Top top = new Top();
            top.insertTop(itemVo, findStore);
            topRepository.save(top);
        } else if(ItemType.BOTTOM.toString().equals(itemType)) {
            Bottom bottom = new Bottom();
            bottom.insertBottom(itemVo, findStore);
            bottomRepository.save(bottom);
        } else if(ItemType.SHOES.toString().equals(itemType)) {
            Shoes shoes = new Shoes();
            shoes.insertShoes(itemVo, findStore);
            shoesRepository.save(shoes);
        } else {
            throw new ServiceException("ITEM_SAVE_NO_ITEM_TYPE", "ItemType doesn't exist.");
        }
    }

    @Transactional
    public void update(ItemVo itemVo) throws Exception {
        Optional<Item> findItem = findItem(itemVo.getId());
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

    @Transactional
    public void delete(ItemVo itemVo) throws Exception {
        Optional<Item> findItem = findItem(itemVo.getId());
        findItem.get().deleteItem(itemVo);
    }

    private List<GetItemDto> getItemDtoList(List<Item> itemList) {
        return itemList.stream()
                .map(item -> GetItemDto.builder()
                        .id(item.getId())
                        .name(item.getName())
                        .price(item.getPrice())
                        .quantity(item.getQuantity())
                        .type(item.getType())
                        .storeId(item.getStore().getId())
                        .storeName(item.getStore().getName())
                        .build())
                .collect(Collectors.toList());
    }

    private Optional<Item> findItem(Long itemId) {
        Optional<Item> findItem = itemRepository.findById(itemId);
        findItem.orElseThrow(() -> new ServiceException("ITEM_0002", "Item doesn't exist."));
        return findItem;
    }

    private Optional<Shoes> findShoes(Long itemId) {
        Optional<Shoes> findItem = shoesRepository.findById(itemId);
        findItem.orElseThrow(() -> new ServiceException("ITEM_0002", "Item doesn't exist."));
        return findItem;
    }

    private Optional<Bottom> findBottom(Long itemId) {
        Optional<Bottom> findItem = bottomRepository.findById(itemId);
        findItem.orElseThrow(() -> new ServiceException("ITEM_0002", "Item doesn't exist."));
        return findItem;
    }

    private Optional<Top> findTop(Long itemId) {
        Optional<Top> findItem = topRepository.findById(itemId);
        findItem.orElseThrow(() -> new ServiceException("ITEM_0002", "Item doesn't exist."));
        return findItem;
    }

    private Optional<Outer> findOuter(Long itemId) {
        Optional<Outer> findItem = outerRepository.findById(itemId);
        findItem.orElseThrow(() -> new ServiceException("ITEM_0002", "Item doesn't exist."));
        return findItem;
    }

}
