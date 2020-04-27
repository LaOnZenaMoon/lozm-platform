package lozm.store;

import lombok.RequiredArgsConstructor;
import lozm.dto.store.GetStoreDto;
import lozm.entity.store.Store;
import lozm.exception.APIException;
import lozm.repository.store.StoreRepository;
import lozm.vo.store.StoreVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;


    public List<GetStoreDto> findAllStores() {
        List<Store> storeList = storeRepository.selectStoreList();

        return storeList.stream().map(s -> new GetStoreDto(s.getId(), s.getName(), s.getTelNumber(), s.getInfo()))
                .collect(toList());
    }

    public Store findById(StoreVo storeVo) {
        Optional<Store> findStore = storeRepository.findById(storeVo.getId());
        findStore.orElseThrow(() -> new APIException("STORE_0002", "The store doesn't exist."));

        return findStore.get();
    }

    @Transactional
    public void save(StoreVo storeVo) throws Exception {
        Store store = new Store();
        store.insertStore(storeVo);

        List<Store> findStoreNameDuplicated = storeRepository.findByName(store.getName());
        if(findStoreNameDuplicated.size() > 0) throw new APIException("STORE_0001", "Store name is duplicated.");

        storeRepository.save(store);
    }

    @Transactional
    public void update(StoreVo storeVo) throws Exception {
        Optional<Store> findStore = storeRepository.findById(storeVo.getId());
        findStore.orElseThrow(() -> new APIException("STORE_0002", "The store doesn't exist."));

        findStore.get().updateStore(storeVo);
    }

    @Transactional
    public void delete(StoreVo storeVo) {
        storeRepository.deleteById(storeVo.getId());
    }
}
