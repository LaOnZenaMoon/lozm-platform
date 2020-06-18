package lozm.api.delivery;

import lombok.RequiredArgsConstructor;

import lozm.global.exception.ServiceException;
import lozm.object.dto.delivery.GetDeliveryDto;
import lozm.repository.RepositorySupport;
import lozm.object.vo.delivery.DeliveryVo;
import lozm.entity.delivery.Delivery;
import lozm.repository.delivery.DeliveryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final RepositorySupport repositorySupport;


    public List<GetDeliveryDto> getDeliveryList() {
        List<Delivery> deliveryList = repositorySupport.selectDeliveryList();

        return deliveryList.stream().map(d -> new GetDeliveryDto(
                    d.getId(),
                    d.getStatus(),
                    isEmpty(d.getAddress()) ? null : d.getAddress().getCountry(),
                    isEmpty(d.getAddress()) ? null : d.getAddress().getZipCode(),
                    isEmpty(d.getAddress()) ? null : d.getAddress().getCity(),
                    isEmpty(d.getAddress()) ? null : d.getAddress().getStreet(),
                    isEmpty(d.getAddress()) ? null : d.getAddress().getEtc()
        )).collect(toList());
    }

    @Transactional
    public void save(DeliveryVo deliveryVo) throws Exception {
        Delivery delivery = new Delivery();
        delivery.insertDelivery(deliveryVo);

        deliveryRepository.save(delivery);
    }

    @Transactional
    public void update(DeliveryVo deliveryVo) throws Exception {
        Optional<Delivery> findDelivery = deliveryRepository.findById(deliveryVo.getId());
        findDelivery.orElseThrow(() -> {
            throw new ServiceException("DELIVERY_0002", "Delivery doesn't exist.");
        });

        findDelivery.get().updateDelivery(deliveryVo);
    }

    @Transactional
    public void delete(DeliveryVo deliveryVo) {
        Optional<Delivery> findDelivery = deliveryRepository.findById(deliveryVo.getId());
        findDelivery.orElseThrow(() -> {
            throw new ServiceException("DELIVERY_0002", "Delivery doesn't exist.");
        });

        findDelivery.get().deleteDelivery(deliveryVo);
    }
}
