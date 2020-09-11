package lozm.api.store;

import lombok.RequiredArgsConstructor;
import lozm.global.exception.ServiceException;
import lozm.object.dto.store.GetStoreDto;
import lozm.entity.store.Store;
import lozm.repository.store.StoreRepository;
import lozm.object.vo.store.StoreVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;


    public List<GetStoreDto> getStoreList() {
        List<Store> storeList = storeRepository.selectStoreList();
        List<GetStoreDto> rtnList = new ArrayList<>();
        for (Store store : storeList) {
            GetStoreDto dto = GetStoreDto.builder()
                    .id(store.getId())
                    .name(store.getName())
                    .telNumber(store.getTelNumber())
                    .info(store.getInfo())
                    .build();

            rtnList.add(dto);
        }

        return rtnList;
    }

    public Store findById(StoreVo storeVo) {
        return findStore(storeVo.getId()).get();
    }

    @Transactional
    public void save(StoreVo storeVo) throws Exception {
        Store store = new Store();
        store.insertStore(storeVo);

        List<Store> findStoreNameDuplicated = storeRepository.findByName(store.getName());
        if(findStoreNameDuplicated.size() > 0) throw new ServiceException("STORE_0001", "Store name is duplicated.");

        storeRepository.save(store);
    }

    @Transactional
    public void update(StoreVo storeVo) throws Exception {
        Optional<Store> findStore = findStore(storeVo.getId());
        findStore.get().updateStore(storeVo);
    }

    @Transactional
    public void delete(StoreVo storeVo) {
        Optional<Store> findStore = findStore(storeVo.getId());
        findStore.get().deleteStore(storeVo);
    }

    private Optional<Store> findStore(Long storeId) {
        Optional<Store> findStore = storeRepository.findById(storeId);
        findStore.orElseThrow(() -> {
            throw new ServiceException("STORE_0002", "The store doesn't exist.");
        });
        return findStore;
    }

}
